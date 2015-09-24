#!/bin/sh

curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "John Smith",
    "accountStatus": "C",
    "cycle": "1",
    "email": "john.smith@thesmiths.com",
    "contactPreference": "mobile"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Jon Doe",
    "accountStatus": "C",
    "cycle": "1",
    "email": "jon@jondoe.com",
    "contactPreference": "email"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Jane Doe",
    "accountStatus": "C",
    "cycle": "1",
    "email": "jane@janey.com",
    "mobile": "07123456789",
    "contactPreference": "mobile"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Bubba Hanks",
    "accountStatus": "C",
    "cycle": "1",
    "email": "bhanks@apd.com",
    "mobile": "07123456789"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Santa Claus",
    "accountStatus": "C",
    "cycle": "1"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Sam Green",
    "accountStatus": "C",
    "cycle": "1",
    "email": "sgreen@yahoo.com",
    "mobile": "071231231234",
    "contactPreference": "mobile"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Pritpal Singh",
    "accountStatus": "C",
    "cycle": "1",
    "email": "pritpal_singh@gmail.com",
    "contactPreference": "mobile"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Chandler Bing",
    "accountStatus": "C",
    "cycle": "1"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Edmund Blackadder",
    "accountStatus": "C",
    "cycle": "1",
    "email": "edmund@blackadder.tv"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Baldrick",
    "accountStatus": "C",
    "cycle": "1",
    "email": "baldrick@drains.com",
    "contactPreference": "email"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Lord Melchitt",
    "accountStatus": "C",
    "cycle": "1"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Walter White",
    "accountStatus": "C",
    "cycle": "1",
    "email": "walter@thelabs.com",
    "mobile": "07911911911",
    "contactPreference": "mobile"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Hank Schrader ",
    "accountStatus": "C",
    "cycle": "1",
    "email": "hank@apd.com",
    "mobile": "07911911999",
    "contactPreference": "email"
}
'
