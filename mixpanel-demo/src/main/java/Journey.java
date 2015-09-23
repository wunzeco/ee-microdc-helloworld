import java.util.List;

public class Journey {
    public final List<Event> journeyEvents;
    public final String userId;

    public Journey(List<Event> journeyEvents, String userId) {
        this.journeyEvents = journeyEvents;
        this.userId = userId;
    }
}
