import com.mixpanel.mixpanelapi.ClientDelivery;
import com.mixpanel.mixpanelapi.MessageBuilder;
import com.mixpanel.mixpanelapi.MixpanelAPI;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

public class Generator {

    private static final String PROJECT_TOKEN = "efd30f07dad9b82392950d66c08e318b";
    private static MessageBuilder messageBuilder;
    private static ClientDelivery delivery;

    public static void main(String[] args) throws JSONException, IOException {

        messageBuilder = new MessageBuilder(PROJECT_TOKEN);

        delivery = new ClientDelivery();

        List<String[]> journeys = new ArrayList<>();
        journeys.add(new String[]{"Delinquent_Enter", "ReceiveSMS", "Login_AS2", "Payment_AS2", "Delinquent_Exit"});
        journeys.add(new String[]{"Delinquent_Enter", "ReceiveEmail", "Login_AS2", "Payment_AS2", "Delinquent_Exit"});
        journeys.add(new String[]{"Delinquent_Enter", "ReceiveSMS", "Call_Agent", "Payment_Agent", "Delinquent_Exit"});
        journeys.add(new String[]{"Delinquent_Enter", "ReceiveCall", "Login_CRC", "Payment_CRC", "Delinquent_Exit"});
        journeys.add(new String[]{"Delinquent_Enter", "ReceiveCall", "Payment_Agent", "Delinquent_Exit"});
        journeys.add(new String[]{"Delinquent_Enter", "ReceiveEmail", "Login_AS2"});
        journeys.add(new String[]{"Delinquent_Enter", "ReceiveEmail", "Login_AS2", "Payment_AS2", "Delinquent_Exit"});

        MixpanelAPI mixpanel = new MixpanelAPI();
        final Random random = new Random();
        IntStream.range(1,1000).forEach(i -> {
            try {
                addUserJourney(random.nextInt(1000), journeys.get(random.nextInt(7)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        mixpanel.deliver(delivery);
    }

    private static String[] names = {
            "Tim", "Tom", "Fred", "Harry", "Gubbins", "Geoff", "Saqib", "Ogonna", "Dave", "Baber",
            "Lucy", "Gill", "Nancy", "Gemma", "Veronica", "Julie", "Gary", "Debs", "Chaucer", "Prasun"};

    private static void addUserJourney(int secondsAgo, String... events) throws JSONException {
        String distinctId = UUID.randomUUID().toString();
        final long startTime = System.currentTimeMillis() / 1000;
        JSONObject props = new JSONObject();
        int count = 100;
        String name = names[new Random().nextInt(names.length)];

        for(String event : events) {
            props.put("time", startTime - (secondsAgo * --count));
            props.put("channel", count % 3);
            delivery.addMessage(messageBuilder.event(distinctId, event, props));

            props = new JSONObject();
            props.put("name", name);
            JSONObject update = messageBuilder.set(distinctId, props);
            delivery.addMessage(update);
        }
    }

}
