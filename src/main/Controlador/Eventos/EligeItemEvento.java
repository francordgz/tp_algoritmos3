package src.main.Controlador.Eventos;
import javafx.event.Event;
import javafx.event.EventType;

public class EligeItemEvento extends Event {
    public static final EventType<VerPokemonesEvento.EligeItemEvento> ELIGE_ITEM_EVENT = new EventType<>("Elige Item");
    int opcion;
    public EligeItemEvento(int opcion) {
        super(ELIGE_ITEM_EVENT);
        this.opcion = opcion;
    }

    public int getOpcion() {
        return opcion;
    }
}
