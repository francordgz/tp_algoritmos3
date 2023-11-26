package src.main.Controlador.Eventos;
import javafx.event.Event;
import javafx.event.EventType;

public class VerPokemonesEvento extends Event {
    public static final EventType<VerPokemonesEvento> VER_POKEMONES_EVENTO = new EventType<>("Ver Pokemones");

    public VerPokemonesEvento() {
        super(VER_POKEMONES_EVENTO);
    }

    public static class EligeItemEvento extends Event {
        public static final EventType<src.main.Controlador.Eventos.EligeItemEvento> ELIGE_ITEM_EVENT = new EventType<>("Elige Item");
        int opcion;
        public EligeItemEvento(int opcion) {
            super(ELIGE_ITEM_EVENT);
            this.opcion = opcion;
        }

        public int getOpcion() {
            return opcion;
        }
    }
}
