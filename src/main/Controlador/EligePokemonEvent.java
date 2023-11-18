package src.main.Controlador;

import javafx.event.Event;
import javafx.event.EventType;

public class EligePokemonEvent extends Event {
    public static EventType<EligePokemonEvent> ELIGE_POKEMON_EVENT = new EventType<>("Elige Pokemon");
    int opcion;
    public EligePokemonEvent(int opcion) {
        super(ELIGE_POKEMON_EVENT);
        System.out.println(opcion);
        this.opcion = opcion;
    }

    public int getOpcion() {
        return opcion;
    }
}
