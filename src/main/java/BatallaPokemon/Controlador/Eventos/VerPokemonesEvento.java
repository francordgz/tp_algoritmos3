package BatallaPokemon.Controlador.Eventos;
import javafx.event.Event;
import javafx.event.EventType;

public class VerPokemonesEvento extends Event {
    public static final EventType<VerPokemonesEvento> VER_POKEMONES_EVENTO = new EventType<>("Ver Pokemones");

    public VerPokemonesEvento() {
        super(VER_POKEMONES_EVENTO);
    }
}
