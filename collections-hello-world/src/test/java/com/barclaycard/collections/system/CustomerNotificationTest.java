package com.barclaycard.collections.system;

import com.barclaycard.collections.model.CustomerProfile;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class CustomerNotificationTest {
    @Test
    public void getNotification_shouldReturnLetterWhenCustomerHasNietherEmailNorMobile() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_contact", "risk_rating", null, null, null);
        CustomerNotification noContacts = new CustomerNotification(noContactsCustomer);

        assertThat(noContacts.getNotification(), IsEqual.equalTo(Notification.Letter));
    }

    @Test
    public void getNotification_shouldReturnSMSWhenCustomerHasOnlyMobile() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_contact", "risk_rating", null, "07712123123", null);
        CustomerNotification noContacts = new CustomerNotification(noContactsCustomer);

        assertThat(noContacts.getNotification(), IsEqual.equalTo(Notification.SMS));
    }

    @Test
    public void getNotification_shouldReturnEmailWhenCustomerHasOnlyEmail() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_contact", "risk_rating", "test_contact@test.com", null, null);
        CustomerNotification noContacts = new CustomerNotification(noContactsCustomer);

        assertThat(noContacts.getNotification(), IsEqual.equalTo(Notification.Email));
    }

    @Test
    public void getNotification_shouldReturnEmailWhenCustomerHasEmailAndMobile() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_contact", "risk_rating", "test_contact@test.com", "07123123123", null);
        CustomerNotification noContacts = new CustomerNotification(noContactsCustomer);

        assertThat(noContacts.getNotification(), IsEqual.equalTo(Notification.Email));
    }

    @Test
    public void getNotification_shouldReturnEmailWhenCustomerHasEmailAndMobileWithEmailPreferred() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_contact", "risk_rating", "test_contact@test.com", "07123123123", "email");
        CustomerNotification noContacts = new CustomerNotification(noContactsCustomer);

        assertThat(noContacts.getNotification(), IsEqual.equalTo(Notification.Email));
    }

    @Test
    public void getNotification_shouldReturnSMSWhenCustomerHasEmailAndMobileWithMobilePreferred() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_contact", "risk_rating", "test_contact@test.com", "07123123123", "mobile");
        CustomerNotification noContacts = new CustomerNotification(noContactsCustomer);

        assertThat(noContacts.getNotification(), IsEqual.equalTo(Notification.SMS));
    }

    @Test
    public void getNotification_shouldReturnSMSWhenCustomerHasOnlyMobileWithEmailPreferred() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_contact", "risk_rating", null, "07123123123", "email");
        CustomerNotification noContacts = new CustomerNotification(noContactsCustomer);

        assertThat(noContacts.getNotification(), IsEqual.equalTo(Notification.SMS));
    }

    @Test
    public void getNotification_shouldReturnEmailWhenCustomerHasOnlyEmailWithMobilePreferred() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_contact", "risk_rating", "test_contact@test.com", null, "email");
        CustomerNotification noContacts = new CustomerNotification(noContactsCustomer);

        assertThat(noContacts.getNotification(), IsEqual.equalTo(Notification.Email));
    }

    @Test
    public void getNotification_shouldReturnLetterWhenCustomerHasNeitherEmailNorMobileAndSomePreference() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_contact", "risk_rating", null, null, "email");
        CustomerNotification noContacts = new CustomerNotification(noContactsCustomer);

        assertThat(noContacts.getNotification(), IsEqual.equalTo(Notification.Letter));
    }
}
