package com.barclaycard.collections.system;

import com.barclaycard.collections.model.CustomerProfile;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class NotificationServiceTest {

    @Test
    public void send_shouldNotSendNotificationIfSuppressFlagIsTrue() throws Exception {
        CustomerProfile testCustomerProfile = new CustomerProfile("test_customer", "C", "1", "test@test.com", null, null);
        NotificationService sender = new NotificationService(testCustomerProfile, NotificationType.Email, true);

        final NotificationService spyNotificationService = spy(sender);

        spyNotificationService.send();

        verify(spyNotificationService, never()).doSend();
    }

    @Test
    public void send_shouldSendNotificationIfSuppressFlagIsFalse() throws Exception {
        CustomerProfile testCustomerProfile = new CustomerProfile("test_customer", "C", "1", "test@test.com", null, null);
        NotificationService sender = new NotificationService(testCustomerProfile, NotificationType.Email, false);

        final NotificationService spyNotificationService = spy(sender);

        spyNotificationService.send();

        verify(spyNotificationService, times(1)).doSend();
    }
}
