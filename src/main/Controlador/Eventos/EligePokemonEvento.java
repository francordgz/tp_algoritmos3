package src.main.Controlador.Eventos;
import javafx.event.Event;
import javafx.event.EventType;

public class EligePokemonEvento extends Event {
    public static final EventType<EligePokemonEvento> ELIGE_POKEMON_EVENT = new EventType<>("Elige Pokemon");
    int opcion;
    public EligePokemonEvento(int opcion) {
        super(ELIGE_POKEMON_EVENT);
        this.opcion = opcion;
    }

    public int getOpcion() {
        return opcion;
    }
}
