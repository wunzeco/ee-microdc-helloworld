package com.barclaycard.collections.system;

import com.barclaycard.collections.model.CustomerProfile;
import com.barclaycard.collections.model.Notification;
import com.barclaycard.collections.model.NotificationDetails;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.*;

public class NotificationServiceTest {

    @Test
    public void send_shouldNotSendNotificationIfSuppressFlagIsTrue() throws Exception {
        CustomerProfile testCustomerProfile = new CustomerProfile("test_customer", "C", "1", "test@test.com", null, null);
        NotificationDetails testNotificationDetails = new NotificationDetails(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "some notification text");
        Notification notification = new Notification(testCustomerProfile, testNotificationDetails);
        NotificationService sender = new NotificationService(notification, NotificationType.Email, true);

        final NotificationService spyNotificationService = spy(sender);

        spyNotificationService.send();

        verify(spyNotificationService, never()).doSend();
    }

    @Test
    public void send_shouldSendNotificationIfSuppressFlagIsFalse() throws Exception {
        CustomerProfile testCustomerProfile = new CustomerProfile("test_customer", "C", "1", "test@test.com", null, null);
        NotificationDetails testNotificationDetails = new NotificationDetails(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "some notification text");
        Notification notification = new Notification(testCustomerProfile, testNotificationDetails);
        NotificationService sender = new NotificationService(notification, NotificationType.Email, false);

        final NotificationService spyNotificationService = spy(sender);

        spyNotificationService.send();

        verify(spyNotificationService, times(1)).doSend();
    }
}
