package src.main.Controlador.Eventos;

import javafx.event.Event;
import javafx.event.EventType;

public class VolverEvento extends Event {
    public static EventType<VolverEvento> VOLVER_EVENT = new EventType<>("Volver");
    public VolverEvento() {
        super(VOLVER_EVENT);
    }
}