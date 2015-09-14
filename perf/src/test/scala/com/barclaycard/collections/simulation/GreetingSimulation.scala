package com.barclaycard.collections.simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

object GreetingSimulation extends Simulation {

  private val greet = exec(http("greeting_request").get("/sayHello").check(status.is(200)))

  val loginAndGreetScenario = scenario("GreetingSimulation").during(sys.env("durationInMinutes").toInt minutes) {
      group("say hello") { exec(greet)}
  }

}