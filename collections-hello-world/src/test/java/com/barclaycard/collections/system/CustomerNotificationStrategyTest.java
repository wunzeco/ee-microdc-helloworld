package com.barclaycard.collections.system;

import com.barclaycard.collections.model.CustomerProfile;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class CustomerNotificationStrategyTest {
    @Test
    public void getNotification_shouldReturnLetterWhenCustomerHasNietherEmailNorMobile() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_contact", "C", "1", null, null, null);
        CustomerNotificationStrategy noContacts = new CustomerNotificationStrategy(noContactsCustomer);

        assertThat(noContacts.getNotificationType(), IsEqual.equalTo(NotificationType.Letter));
    }

    @Test
    public void getNotification_shouldReturnSMSWhenCustomerHasOnlyMobile() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_contact", "C", "1", null, "07712123123", null);
        CustomerNotificationStrategy noContacts = new CustomerNotificationStrategy(noContactsCustomer);

        assertThat(noContacts.getNotificationType(), IsEqual.equalTo(NotificationType.SMS));
    }

    @Test
    public void getNotification_shouldReturnEmailWhenCustomerHasOnlyEmail() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_contact", "C", "1", "test_contact@test.com", null, null);
        CustomerNotificationStrategy noContacts = new CustomerNotificationStrategy(noContactsCustomer);

        assertThat(noContacts.getNotificationType(), IsEqual.equalTo(NotificationType.Email));
    }

    @Test
    public void getNotification_shouldReturnEmailWhenCustomerHasEmailAndMobile() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_contact", "C", "1", "test_contact@test.com", "07123123123", null);
        CustomerNotificationStrategy noContacts = new CustomerNotificationStrategy(noContactsCustomer);

        assertThat(noContacts.getNotificationType(), IsEqual.equalTo(NotificationType.Email));
    }

    @Test
    public void getNotification_shouldReturnEmailWhenCustomerHasEmailAndMobileWithEmailPreferred() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_contact", "C", "1", "test_contact@test.com", "07123123123", "email");
        CustomerNotificationStrategy noContacts = new CustomerNotificationStrategy(noContactsCustomer);

        assertThat(noContacts.getNotificationType(), IsEqual.equalTo(NotificationType.Email));
    }

    @Test
    public void getNotification_shouldReturnSMSWhenCustomerHasEmailAndMobileWithMobilePreferred() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_contact", "C", "1", "test_contact@test.com", "07123123123", "mobile");
        CustomerNotificationStrategy noContacts = new CustomerNotificationStrategy(noContactsCustomer);

        assertThat(noContacts.getNotificationType(), IsEqual.equalTo(NotificationType.SMS));
    }

    @Test
    public void getNotification_shouldReturnSMSWhenCustomerHasOnlyMobileWithEmailPreferred() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_contact", "C", "1", null, "07123123123", "email");
        CustomerNotificationStrategy noContacts = new CustomerNotificationStrategy(noContactsCustomer);

        assertThat(noContacts.getNotificationType(), IsEqual.equalTo(NotificationType.SMS));
    }

    @Test
    public void getNotification_shouldReturnEmailWhenCustomerHasOnlyEmailWithMobilePreferred() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_contact", "C", "1", "test_contact@test.com", null, "email");
        CustomerNotificationStrategy noContacts = new CustomerNotificationStrategy(noContactsCustomer);

        assertThat(noContacts.getNotificationType(), IsEqual.equalTo(NotificationType.Email));
    }

    @Test
    public void getNotification_shouldReturnLetterWhenCustomerHasNeitherEmailNorMobileAndSomePreference() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_contact", "C", "1", null, null, "email");
        CustomerNotificationStrategy noContacts = new CustomerNotificationStrategy(noContactsCustomer);

        assertThat(noContacts.getNotificationType(), IsEqual.equalTo(NotificationType.Letter));
    }
}
