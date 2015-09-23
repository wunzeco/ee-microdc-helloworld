import com.mixpanel.mixpanelapi.ClientDelivery;
import com.mixpanel.mixpanelapi.MessageBuilder;
import com.mixpanel.mixpanelapi.MixpanelAPI;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final String PROJECT_TOKEN = "efd30f07dad9b82392950d66c08e318b";

    public static void main(String[] args) throws JSONException, IOException {
// Create an event
        String segment = "low";
        String cycle = "0";
        List<Journey> journeys = new ArrayList<>();

        journeys.add(sloppyPayerJourney("bob", segment, cycle, LocalDateTime.now().minus(3, ChronoUnit.DAYS)));
        journeys.add(difficultPayerJourney("hank", segment, cycle, LocalDateTime.now().minus(3, ChronoUnit.DAYS)));
        journeys.add(nonPayingJourney("barbara", segment, cycle, LocalDateTime.now().minus(3, ChronoUnit.DAYS)));

        journeys.add(sloppyPayerJourney("bab", segment, cycle, LocalDateTime.now().minus(2, ChronoUnit.DAYS)));
        journeys.add(difficultPayerJourney("honk", segment, cycle, LocalDateTime.now().minus(2, ChronoUnit.DAYS)));
        journeys.add(nonPayingJourney("barbs", segment, cycle, LocalDateTime.now().minus(2, ChronoUnit.DAYS)));


        journeys.parallelStream().forEach(Main::sendEvents);
    }

    private static void sendEvents(Journey journey) {
        MessageBuilder messageBuilder =
                new MessageBuilder(PROJECT_TOKEN);
        final MixpanelAPI mixpanel = new MixpanelAPI();

        journey.journeyEvents.stream()
                .forEach((e) -> {
                    JSONObject event = messageBuilder.event(journey.userId, e.name, e.toJson());
                    try {
                        final ClientDelivery delivery = new ClientDelivery();
//                        Thread.currentThread().sleep(3000L);
                        delivery.addMessage(event);
                        mixpanel.deliver(delivery);
                    } catch (IOException exc) {
                        exc.printStackTrace();
                    }
                });

    }

    private static Journey sloppyPayerJourney(String userId, String segment, String cycle, LocalDateTime startTime) {
        return new Journey(Arrays.asList(new Event[]{
                new Events.EnterDelinquency(userId, segment, startTime),
                new Events.CommunicationToCustomer("email", userId, segment, cycle, startTime.plus(1, ChronoUnit.HOURS)),
                new Events.CustomerDidLogin("web", userId, segment, cycle, startTime.plus(2, ChronoUnit.HOURS)),
                new Events.CustomerDidMakeBalancePayment("voice", userId, segment, cycle, startTime.plus(3, ChronoUnit.HOURS)),
                new Events.CustomerDidLogout("web", userId, segment, cycle, startTime.plus(4, ChronoUnit.HOURS)),
                new Events.LeaveDelinquency(userId, segment, cycle, startTime.plus(5, ChronoUnit.HOURS))
        }), userId);
    }

    private static Journey nonPayingJourney(String userId, String segment, String cycle, LocalDateTime startTime) {
        return new Journey(Arrays.asList(new Event[]{
                new Events.EnterDelinquency(userId, segment, startTime),
                new Events.CommunicationToCustomer("email", userId, segment, cycle, startTime.plus(1, ChronoUnit.HOURS)),
                new Events.CustomerCollectionCycleChanged("web", userId, segment, "1", startTime.plus(30, ChronoUnit.HOURS))
        }), userId);
    }

    private static Journey difficultPayerJourney(String userId, String segment, String cycle, LocalDateTime startTime) {
        return new Journey(Arrays.asList(new Event[]{
                new Events.EnterDelinquency(userId, segment, startTime),
                new Events.CommunicationToCustomer("sms", userId, segment, cycle, startTime.plus(1, ChronoUnit.HOURS)),
                new Events.CustomerDidLogin("web", userId, segment, cycle, startTime.plus(8, ChronoUnit.HOURS)),
                new Events.CustomerDidLogout("web", userId, segment, cycle, startTime.plus(9, ChronoUnit.HOURS)),
                new Events.CommunicationToCustomer("voice", userId, segment, cycle, startTime.plus(24, ChronoUnit.HOURS)),
                new Events.CustomerDidMakeMinimumPayment("voice", userId, segment, cycle, startTime.plus(25, ChronoUnit.HOURS)),
                new Events.LeaveDelinquency(userId, segment, cycle, startTime.plus(26, ChronoUnit.HOURS))
        }), userId);
    }

    private static JSONObject buildEventJSON(Event theEvent) throws JSONException {
        JSONObject eventJson = theEvent.toJson();
        // May be need to add other properties here

        return eventJson;
    }
}
