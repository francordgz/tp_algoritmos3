package src.main.Controlador.Eventos;
import javafx.event.Event;
import javafx.event.EventType;

public class VerMochilaEvento extends Event {
    public static final EventType<VerMochilaEvento> VER_MOCHILA_EVENTO = new EventType<>("Ver Mochila");

    public VerMochilaEvento() {
        super(VER_MOCHILA_EVENTO);
    }
}
