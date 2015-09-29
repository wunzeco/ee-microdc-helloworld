#!/usr/bin/env python
import requests
import random
import datetime
import itertools

from loremipsum import get_paragraph

def send_notification_request(customer_profile):
    host   = 'localhost:8888'
    url = "http://{0}/sendNotifications".format(host)
    r = requests.post(url, json=customer_profile)
    r.raise_for_status()

email_domains = ["gmail.com", "yahoo.com", "hotmail.com"]
first_names = ["John", "Jane", "Bob", "Liz", "Mike", "Ellie", "Oliver", "Jessica"]
last_names = ["Smith", "Jones", "Hanks", "Osbourne", "Taylor", "Grant", "Miller"]
preferences = ["email", "mobile", "letter"]
froms = ["customer","barclaycard"]

def create_random_profile(time):
    first_name = random.choice(first_names)
    last_name = random.choice(last_names)
    customerId = first_name + " " + last_name
    email = customerId.lower().replace(" ", ".") + "@" + random.choice(email_domains)
    profile = {
        "customerId": customerId,
        "accountStatus": "C",
        "cycle": "1",
        "email": email,
        "mobile": ''.join("%0.11d" % random.randint(0,999999999999)),
        "contactPreference": random.choice(preferences)
    }
    details = {
        "from": random.choice(froms),
        "time": time.strftime("%Y-%m-%d %H:%M:%S"),
        "text": get_paragraph()
    }
    payload = {
        "profile": profile,
        "details": details
    }
    print payload
    return payload

date_generator = (datetime.datetime.today() - datetime.timedelta(minutes=i) for i in itertools.count())

for t in itertools.islice(date_generator, 10):
    send_notification_request(create_random_profile(t))
