package src.main.Controlador.Eventos;

import javafx.event.Event;
import javafx.event.EventType;

public class RendirseEvento extends Event {
    public static EventType<RendirseEvento> RENDIRSE_EVENT = new EventType<>("Rendirse");
    public RendirseEvento() {
        super(RENDIRSE_EVENT);
    }

}