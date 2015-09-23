import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Event {
    public final String name;
    public final String channel;
    public final String userId;
    public final String segment;
    public final String cycle;
    public final LocalDateTime time;

    public Event(String name, String channel, String userId, String segment /*riskLevel*/, String cycle, LocalDateTime time) {
        this.name = name;
        this.channel = channel;
        this.userId = userId;
        this.segment = segment;
        this.cycle = cycle;
        this.time = time;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        try {
            json.put("channel", channel);
            json.put("userId", userId);
            json.put("segment", segment);
            json.put("cycle", cycle);
            json.put("time", time.toEpochSecond(ZoneOffset.UTC));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }
}
