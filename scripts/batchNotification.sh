#!/bin/sh

curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "John Smith",
    "email": "john.smith@thesmiths.com",
    "contactPreference": "mobile"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Jon Doe",
    "email": "jon@jondoe.com",
    "contactPreference": "email"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Jane Doe",
    "email": "jane@janey.com",
    "mobile": "07123456789",
    "contactPreference": "mobile"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Bubba Hanks",
    "email": "bhanks@apd.com",
    "mobile": "07123456789"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Santa Claus"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Sam Green",
    "email": "sgreen@yahoo.com",
    "mobile": "071231231234",
    "contactPreference": "mobile"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Pritpal Singh",
    "email": "pritpal_singh@gmail.com",
    "contactPreference": "mobile"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Chandler Bing"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Edmund Blackadder",
    "email": "edmund@blackadder.tv"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Baldrick",
    "email": "baldrick@drains.com",
    "contactPreference": "email"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Lord Melchitt"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Walter White",
    "email": "walter@thelabs.com",
    "mobile": "07911911911",
    "contactPreference": "mobile"
}
'
sleep 1800
curl --request POST -H "Content-Type: application/json"  http://localhost:8888/sendNotifications --data '
{
    "customerId": "Hank Schrader ",
    "email": "hank@apd.com",
    "mobile": "07911911999",
    "contactPreference": "email"
}
'
