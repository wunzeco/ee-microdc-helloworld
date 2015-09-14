#!/bin/bash
#
#    .   ____          _            __ _ _
#   /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
#  ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
#   \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
#    '  |____| .__|_| |_|_| |_\__, | / / / /
#   =========|_|==============|___/=/_/_/_/
#   :: Spring Boot Startup Script ::
#

### BEGIN INIT INFO
# Provides:          spring-boot-application
# Required-Start:    $remote_fs $syslog $network
# Required-Stop:     $remote_fs $syslog $network
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# Short-Description: Spring Boot Application
# Description:       Spring Boot Application
# chkconfig:         2345 99 01
### END INIT INFO

###
#
# Modified for Ops-UI build because a Jenkins started service could not be killed with 'kill -HUP'
#
###

[[ -n "$DEBUG" ]] && set -x

# Initialize variables that cannot be provided by a .conf file

source /etc/default/ops-ui
WORKING_DIR=$OPS_UI_WORKING_DIR
run_user=$OPS_UI_USER

jarfile="$WORKING_DIR/ops-ui.jar"
jarfolder="$WORKING_DIR"
MODE="service"
identity="ops-ui"

cd "$WORKING_DIR"

# Source any config file
configfile=$(basename "${jarfile%.*}.conf")
[[ -r ${jarfolder}/${configfile} ]] && source ${jarfolder}/${configfile}

# Initialize PID/LOG locations if they weren't provided by the config file
[[ -z "$PID_FOLDER" ]] && PID_FOLDER="/var/run"
[[ -z "$LOG_FOLDER" ]] && LOG_FOLDER="/var/log"
! [[ -x "$PID_FOLDER" ]] && PID_FOLDER="/tmp"
! [[ -x "$LOG_FOLDER" ]] && LOG_FOLDER="/tmp"

# Setup defaults
[[ -z "$MODE" ]] && MODE="auto" # modes are "auto", "service" or "run"

# Create an identity for log/pid files
if [[ -z "$identity" ]]; then
if [[ -n "$init_script" ]]; then
  identity="${init_script}"
else
  identity=$(basename "${jarfile%.*}")_${jar_folder//\//}
fi
fi


# ANSI Colors
echoRed() { echo $'\e[0;31m'$1$'\e[0m'; }
echoGreen() { echo $'\e[0;32m'$1$'\e[0m'; }
echoYellow() { echo $'\e[0;33m'$1$'\e[0m'; }

# Utility functions
checkPermissions() {
touch "$pid_file" &> /dev/null || { echoRed "Operation not permitted (cannot access pid file)"; exit 1; }
touch "$log_file" &> /dev/null || { echoRed "Operation not permitted (cannot access log file)"; exit 1; }
}

isRunning() {
ps -p $1 &> /dev/null
}

# Determine the script mode
action="run"
if [[ "$MODE" == "auto" && -n "$init_script" ]] || [[ "$MODE" == "service" ]]; then
action="$1"
shift
fi

# Build the pid and log filenames
if [[ "$identity" == "$init_script" ]] || [[ "$identity" == "$APP_NAME" ]]; then
PID_FOLDER="$PID_FOLDER/${identity}"
fi
pid_file="$PID_FOLDER/${identity}.pid"
log_file="$LOG_FOLDER/${identity}.log"


# Find Java
if [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]]; then
  javaexe="$JAVA_HOME/bin/java"
elif type -p java 2>&1> /dev/null; then
  javaexe=java
elif [[ -x "/usr/bin/java" ]];  then
  javaexe="/usr/bin/java"
else
  echo "Unable to find Java"
  exit 1
fi

# Build actual command to execute
command="$javaexe -jar -Dsun.misc.URLClassPath.disableJarChecking=true $JAVA_OPTS $jarfile $RUN_ARGS $@"

# Action functions
start() {
if [[ -f "$pid_file" ]]; then
  pid=$(cat "$pid_file")
  isRunning $pid && { echoYellow "Already running [$pid]"; exit 0; }
fi
pushd $(dirname "$jarfile") > /dev/null
if [[ -n "$run_user" ]]; then
  mkdir "$PID_FOLDER" &> /dev/null
  checkPermissions
  chown "$run_user" "$PID_FOLDER"
  chown "$run_user" "$pid_file"
  chown "$run_user" "$log_file"
  su $run_user -c "$command &> \"$log_file\" & echo \$!"  > "$pid_file"
  pid=$(cat "$pid_file")
else
  checkPermissions
  $command &> "$log_file" &
  pid=$!
  disown $pid
  echo "$pid" > "$pid_file"
fi
[[ -z $pid ]] && { echoRed "Failed to start"; exit 1; }
echoGreen "Started [$pid]"
}

stop() {
  [[ -f $pid_file ]] || { echoRed "Not running (pidfile not found)"; exit 1; }
  pid=$(cat "$pid_file")
  rm -f "$pid_file"
  isRunning $pid || { echoRed "Not running (process ${pid} not found)"; exit 1; }
  kill -SIGTERM $pid &> /dev/null || { echoRed "Unable to kill process ${pid}"; exit 1; }
  for i in {1..20}; do
    isRunning ${pid} || { echoGreen "Stopped [$pid]"; rm -f $pid_file; exit 0; }
    sleep 1
  done
  echoRed "Unable to kill process ${pid}";
  exit 3;
}

restart() {
  stop
  start
}

status() {
  [[ -f $pid_file ]] || { echoRed "Not running"; exit 1; }
  pid=$(cat "$pid_file")
  isRunning $pid || { echoRed "Not running (process ${pid} not found)"; exit 1; }
  echoGreen "Running [$pid]"
  exit 0
}

run() {
  pushd $(dirname "$jarfile") > /dev/null
  exec $command
  popd
}

# Call the appropriate action function
case "$action" in
start)
  start "$@";;
stop)
  stop "$@";;
restart)
  restart "$@";;
  status)
    status "$@";;
  run)
    run "$@";;
  *)
  echo "Usage: $0 {start|stop|restart|status|run}"; exit 1;
esac

exit 0;
