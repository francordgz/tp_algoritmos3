package src.main.Controlador.Eventos;

import javafx.event.EventHandler;

public interface RendriseEventoHandler extends EventHandler<RendirseEvento> {

    @Override
    void handle(RendirseEvento event);

}