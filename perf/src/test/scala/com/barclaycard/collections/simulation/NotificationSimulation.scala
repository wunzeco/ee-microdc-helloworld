package com.barclaycard.collections.simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

object NotificationSimulation extends Simulation {
  private val sendNotification = exec(http("send_notifications")
    .post("/sendNotifications")
    .body(StringBody("""{
            |    "customerId": "Bobby Smith",
            |    "riskRating": "High",
            |    "email" :"bs@gmail.com",
            |    "contactPreference": "email"
            |}""".stripMargin)).asJSON
    .check(status.is(204)))

  val sendNotificationsScenario = scenario("send_notifications").during(sys.env("durationInMinutes").toInt minutes) {
      group("send_notifications") { exec(sendNotification)}
  }

}
