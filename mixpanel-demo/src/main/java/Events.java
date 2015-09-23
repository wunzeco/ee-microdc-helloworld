import java.time.LocalDateTime;

public class Events {
    public static class EnterDelinquency extends Event {
        public EnterDelinquency(String userId, String segment, LocalDateTime time) {
            super("EnterDelinquency", "system", userId, segment, "0", time);
        }
    }

    public static class LeaveDelinquency extends Event {

        public LeaveDelinquency(String userId, String segment, String cycle, LocalDateTime time) {
            super("LeaveDelinquency", "system", userId, segment, cycle, time);
        }
    }

    public static class CommunicationToCustomer extends Event {

        public CommunicationToCustomer(String channel, String userId, String segment, String cycle, LocalDateTime time) {
            super("CommunicationToCustomer", channel, userId, segment, cycle, time);
        }
    }

    public static class CommunicationFromCustomer extends Event {

        public CommunicationFromCustomer(String channel, String userId, String segment, String cycle, LocalDateTime time) {
            super("CommunicationFromCustomer", channel, userId, segment, cycle, time);
        }
    }

    public static class CustomerDidLogin extends Event {

        public CustomerDidLogin(String channel, String userId, String segment, String cycle, LocalDateTime time) {
            super("CustomerLogin", channel, userId, segment, cycle, time);
        }
    }

    public static class CustomerDidLogout extends Event {

        public CustomerDidLogout(String channel, String userId, String segment, String cycle, LocalDateTime time) {
            super("CustomerDidLogout", channel, userId, segment, cycle, time);
        }
    }

    public static class CustomerDidMakeMinimumPayment extends Event {

        public CustomerDidMakeMinimumPayment(String channel, String userId, String segment, String cycle, LocalDateTime time) {
            super("CustomerDidMakeMinimumPayment", channel, userId, segment, cycle, time);
        }
    }

    public static class CustomerDidMakeBalancePayment extends Event {

        public CustomerDidMakeBalancePayment(String channel, String userId, String segment, String cycle, LocalDateTime time) {
            super("CustomerDidMakeBalancePayment", channel, userId, segment, cycle, time);
        }
    }

    public static class CustomerDidMakeBreathingSpaceRequest extends Event {

        public CustomerDidMakeBreathingSpaceRequest(String channel, String userId, String segment, String cycle, LocalDateTime time) {
            super("CustomerDidMakeBreathingSpaceRequest", channel, userId, segment, cycle, time);
        }
    }

    public static class CustomerBreathingSpaceRequestApproved extends Event {

        public CustomerBreathingSpaceRequestApproved(String channel, String userId, String segment, String cycle, LocalDateTime time) {
            super("CustomerBreathingSpaceRequestApproved", channel, userId, segment, cycle, time);
        }
    }

    public static class CustomerDidRequestReAge extends Event {

        public CustomerDidRequestReAge(String channel, String userId, String segment, String cycle, LocalDateTime time) {
            super("CustomerDidRequestReAge", channel, userId, segment, cycle, time);
        }
    }

    public static class CustomerReAgeRequestApproved extends Event {

        public CustomerReAgeRequestApproved(String channel, String userId, String segment, String cycle, LocalDateTime time) {
            super("CustomerReAgeRequestApproved", channel, userId, segment, cycle, time);
        }
    }

    public static class CustomerCollectionCycleChanged extends Event {

        public CustomerCollectionCycleChanged(String channel, String userId, String segment, String cycle, LocalDateTime time) {
            super("CustomerCollectionCycleChanged", channel, userId, segment, cycle, time);
        }
    }
}
