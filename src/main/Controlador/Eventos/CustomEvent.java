package src.main.Controlador.Eventos;

import javafx.event.Event;
import javafx.event.EventType;

public class CustomEvent extends Event {

    public static final EventType<Event> ANY;
    public CustomEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }

    static {
        ANY = EventType.ROOT;
    }
}
