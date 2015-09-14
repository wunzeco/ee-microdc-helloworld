package com.barclaycard.collections.simulation

import com.barclaycard.collections.simulation.GreetingSimulation._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import scala.concurrent.duration._

class AllSimulation extends Simulation{

  val httpConf = http
    .baseURL(sys.env("appUrl")).disableFollowRedirect

  setUp(
    loginAndGreetScenario.inject(rampUsers(sys.env("noOfUsers").toInt) over (sys.env("rampUpInMinutes").toInt minutes))
  ).protocols(httpConf).assertions(
      global.responseTime.percentile3.lessThan(50),
      global.successfulRequests.percent.greaterThan(95)
    )

}
