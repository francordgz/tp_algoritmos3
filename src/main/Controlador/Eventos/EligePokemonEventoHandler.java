package src.main.Controlador.Eventos;

import javafx.event.EventHandler;

public interface EligePokemonEventoHandler extends EventHandler<EligePokemonEvento> {
    @Override
    void handle(EligePokemonEvento event);

}