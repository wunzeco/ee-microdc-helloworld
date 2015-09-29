package com.barclaycard.collections.system;

import com.barclaycard.collections.model.CustomerProfile;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class CustomerNotificationStrategyTest {
    @Test
    public void getNotification_shouldReturnLetterWhenCustomerHasNietherEmailNorMobile() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_01", "test_contact", "C", "1", null, null, null);
        CustomerNotificationStrategy noContacts = new CustomerNotificationStrategy(noContactsCustomer);

        assertThat(noContacts.getNotificationMethod(), IsEqual.equalTo(NotificationMethod.letter));
    }

    @Test
    public void getNotification_shouldReturnSMSWhenCustomerHasOnlyMobile() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_02", "test_contact", "C", "1", null, "07712123123", null);
        CustomerNotificationStrategy noContacts = new CustomerNotificationStrategy(noContactsCustomer);

        assertThat(noContacts.getNotificationMethod(), IsEqual.equalTo(NotificationMethod.sms));
    }

    @Test
    public void getNotification_shouldReturnEmailWhenCustomerHasOnlyEmail() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_03", "test_contact", "C", "1", "test_contact@test.com", null, null);
        CustomerNotificationStrategy noContacts = new CustomerNotificationStrategy(noContactsCustomer);

        assertThat(noContacts.getNotificationMethod(), IsEqual.equalTo(NotificationMethod.email));
    }

    @Test
    public void getNotification_shouldReturnEmailWhenCustomerHasEmailAndMobile() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_04", "test_contact", "C", "1", "test_contact@test.com", "07123123123", null);
        CustomerNotificationStrategy noContacts = new CustomerNotificationStrategy(noContactsCustomer);

        assertThat(noContacts.getNotificationMethod(), IsEqual.equalTo(NotificationMethod.email));
    }

    @Test
    public void getNotification_shouldReturnEmailWhenCustomerHasEmailAndMobileWithEmailPreferred() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_05", "test_contact", "C", "1", "test_contact@test.com", "07123123123", "email");
        CustomerNotificationStrategy noContacts = new CustomerNotificationStrategy(noContactsCustomer);

        assertThat(noContacts.getNotificationMethod(), IsEqual.equalTo(NotificationMethod.email));
    }

    @Test
    public void getNotification_shouldReturnSMSWhenCustomerHasEmailAndMobileWithMobilePreferred() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_06", "test_contact", "C", "1", "test_contact@test.com", "07123123123", "mobile");
        CustomerNotificationStrategy noContacts = new CustomerNotificationStrategy(noContactsCustomer);

        assertThat(noContacts.getNotificationMethod(), IsEqual.equalTo(NotificationMethod.email));
    }

    @Test
    public void getNotification_shouldReturnSMSWhenCustomerHasOnlyMobileWithEmailPreferred() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_07", "test_contact", "C", "1", null, "07123123123", "email");
        CustomerNotificationStrategy noContacts = new CustomerNotificationStrategy(noContactsCustomer);

        assertThat(noContacts.getNotificationMethod(), IsEqual.equalTo(NotificationMethod.sms));
    }

    @Test
    public void getNotification_shouldReturnEmailWhenCustomerHasOnlyEmailWithMobilePreferred() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_08", "test_contact", "C", "1", "test_contact@test.com", null, "email");
        CustomerNotificationStrategy noContacts = new CustomerNotificationStrategy(noContactsCustomer);

        assertThat(noContacts.getNotificationMethod(), IsEqual.equalTo(NotificationMethod.email));
    }

    @Test
    public void getNotification_shouldReturnLetterWhenCustomerHasNeitherEmailNorMobileAndSomePreference() throws Exception {
        final CustomerProfile noContactsCustomer = new CustomerProfile("test_09", "test_contact", "C", "1", null, null, "email");
        CustomerNotificationStrategy noContacts = new CustomerNotificationStrategy(noContactsCustomer);

        assertThat(noContacts.getNotificationMethod(), IsEqual.equalTo(NotificationMethod.letter));
    }
}
