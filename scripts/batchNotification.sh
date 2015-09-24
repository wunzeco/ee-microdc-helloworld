#!/bin/sh

hour=$(date +%H)
day=$(date +%d)
endpoint="http://localhost:8888/sendNotifications"

hour=$(($hour-1))
curl --request POST -H "Content-Type: application/json"  $endpoint --data '
{
    "customerId": "John Smith",
    "accountStatus": "C",
    "cycle": "1",
    "email": "john.smith@thesmiths.com",
    "contactPreference": "mobile"
,     "time": "2015-09-'$day' '$(printf %02d $hour)':00:00" }
'

hour=$(($hour-1))
curl --request POST -H "Content-Type: application/json"  $endpoint --data '
{
    "customerId": "Jon Doe",
    "accountStatus": "C",
    "cycle": "1",
    "email": "jon@jondoe.com",
    "contactPreference": "email"
,     "time": "2015-09-'$day' '$(printf %02d $hour)':00:00" }
'

hour=$(($hour-1))
curl --request POST -H "Content-Type: application/json"  $endpoint --data '
{
    "customerId": "Jane Doe",
    "accountStatus": "C",
    "cycle": "1",
    "email": "jane@janey.com",
    "mobile": "07123456789",
    "contactPreference": "mobile"
,     "time": "2015-09-'$day' '$(printf %02d $hour)':00:00" }
'

hour=$(($hour-1))
curl --request POST -H "Content-Type: application/json"  $endpoint --data '
{
    "customerId": "Bubba Hanks",
    "accountStatus": "C",
    "cycle": "1",
    "email": "bhanks@apd.com",
    "mobile": "07123456789"
,     "time": "2015-09-'$day' '$(printf %02d $hour)':00:00" }
'

hour=$(($hour-1))
curl --request POST -H "Content-Type: application/json"  $endpoint --data '
{
    "customerId": "Santa Claus",
    "accountStatus": "C",
    "cycle": "1"
,     "time": "2015-09-'$day' '$(printf %02d $hour)':00:00" }
'

hour=$(($hour-1))
curl --request POST -H "Content-Type: application/json"  $endpoint --data '
{
    "customerId": "Sam Green",
    "accountStatus": "C",
    "cycle": "1",
    "email": "sgreen@yahoo.com",
    "mobile": "071231231234",
    "contactPreference": "mobile"
,     "time": "2015-09-'$day' '$(printf %02d $hour)':00:00" }
'

hour=$(($hour-1))
curl --request POST -H "Content-Type: application/json"  $endpoint --data '
{
    "customerId": "Pritpal Singh",
    "accountStatus": "C",
    "cycle": "1",
    "email": "pritpal_singh@gmail.com",
    "contactPreference": "mobile"
,     "time": "2015-09-'$day' '$(printf %02d $hour)':00:00" }
'

hour=$(($hour-1))
curl --request POST -H "Content-Type: application/json"  $endpoint --data '
{
    "customerId": "Chandler Bing",
    "accountStatus": "C",
    "cycle": "1"
,     "time": "2015-09-'$day' '$(printf %02d $hour)':00:00" }
'

hour=$(($hour-1))
curl --request POST -H "Content-Type: application/json"  $endpoint --data '
{
    "customerId": "Edmund Blackadder",
    "accountStatus": "C",
    "cycle": "1",
    "email": "edmund@blackadder.tv"
,     "time": "2015-09-'$day' '$(printf %02d $hour)':00:00" }
'

hour=$(($hour-1))
curl --request POST -H "Content-Type: application/json"  $endpoint --data '
{
    "customerId": "Baldrick",
    "accountStatus": "C",
    "cycle": "1",
    "email": "baldrick@drains.com",
    "contactPreference": "email"
,     "time": "2015-09-'$day' '$(printf %02d $hour)':00:00" }
'

hour=$(($hour-1))
curl --request POST -H "Content-Type: application/json"  $endpoint --data '
{
    "customerId": "Lord Melchitt",
    "accountStatus": "C",
    "cycle": "1"
,     "time": "2015-09-'$day' '$(printf %02d $hour)':00:00" }
'

hour=$(($hour-1))
curl --request POST -H "Content-Type: application/json"  $endpoint --data '
{
    "customerId": "Walter White",
    "accountStatus": "C",
    "cycle": "1",
    "email": "walter@thelabs.com",
    "mobile": "07911911911",
    "contactPreference": "mobile"
,     "time": "2015-09-'$day' '$(printf %02d $hour)':00:00" }
'

hour=$(($hour-1))
curl --request POST -H "Content-Type: application/json"  $endpoint --data '
{
    "customerId": "Hank Schrader ",
    "accountStatus": "C",
    "cycle": "1",
    "email": "hank@apd.com",
    "mobile": "07911911999",
    "contactPreference": "email"
,     "time": "2015-09-'$day' '$(printf %02d $hour)':00:00" }
'
