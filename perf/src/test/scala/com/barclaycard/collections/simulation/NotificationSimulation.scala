package com.barclaycard.collections.simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

object NotificationSimulation extends Simulation {
  private val sendNotification = exec(http("send_notifications")
    .post("/sendNotifications")
    .body(StringBody("""
    {
       "profile": {
           "customerId": "John Smith",
           "accountStatus": "C",
           "cycle": "1",
           "email": "john.smith@thesmiths.com",
           "mobile": "07123123123",
           "contactPreference": "sms"
       },
       "details": {
           "method": "sms",
           "from": "barclaycard",
           "time": "2015-09-12 13:27:45",
           "text": "We noticed that you missed a payment recently. Is there anything we can help with?"
       }
    }
    """.stripMargin)).asJSON
    .check(status.is(204)))

  val sendNotificationsScenario = scenario("send_notifications").during(sys.env("durationInMinutes").toInt minutes) {
      group("send_notifications") { exec(sendNotification)}
  }

}
