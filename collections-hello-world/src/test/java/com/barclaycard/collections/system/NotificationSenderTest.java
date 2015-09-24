package com.barclaycard.collections.system;

import com.barclaycard.collections.model.CustomerProfile;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class NotificationSenderTest {

    @Test
    public void send_shouldNotSendNotificationIfSuppressFlagIsTrue() throws Exception {
        CustomerProfile testCustomerProfile = new CustomerProfile("test_customer", "High", "test@test.com", null, null);
        NotificationSender sender = new NotificationSender(testCustomerProfile, NotificationType.Email, true);

        final NotificationSender spyNotificationSender = spy(sender);

        spyNotificationSender.send();

        verify(spyNotificationSender, never()).doSend();
    }

    @Test
    public void send_shouldSendNotificationIfSuppressFlagIsFalse() throws Exception {
        CustomerProfile testCustomerProfile = new CustomerProfile("test_customer", "High", "test@test.com", null, null);
        NotificationSender sender = new NotificationSender(testCustomerProfile, NotificationType.Email, false);

        final NotificationSender spyNotificationSender = spy(sender);

        spyNotificationSender.send();

        verify(spyNotificationSender, times(1)).doSend();
    }
}
