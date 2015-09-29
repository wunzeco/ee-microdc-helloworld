#!/bin/sh

hour=$(date +%H)
day=$(date +%d)
endpoint="http://localhost:8888/sendNotifications"

hour=$(($hour-1))
curl --request POST -H "Content-Type: application/json"  $endpoint --data '
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
        "time": "2015-09-'$day' '$(printf %02d $hour)':00:00",
        "text": "We noticed that you missed a payment recently. Is there anything we can help with?"
    }
}
'

hour=$(($hour-1))
curl --request POST -H "Content-Type: application/json"  $endpoint --data '
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
        "method": "voice",
        "from": "customer",
        "time": "2015-09-'$day' '$(printf %02d $hour)':00:00",
        "text": "Customer called and explained that the payment was missed as was on holiday. Will when back in about 1 week."
    }
}
'

hour=$(($hour-1))
curl --request POST -H "Content-Type: application/json"  $endpoint --data '
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
        "method": "voice",
        "from": "customer",
        "time": "2015-09-'$day' '$(printf %02d $hour)':00:00",
        "text": "Customer called and payed missed payment amount."
    }
}
'
