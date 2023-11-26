package src.main.Controlador.Eventos;
import javafx.event.Event;
import javafx.event.EventType;

public class EligeHabilidadEvento extends Event {
    public static final EventType<EligeHabilidadEvento> ELIGE_HABILIDAD_EVENT = new EventType<>("Elige Habilidad");
    int opcion;
    public EligeHabilidadEvento(int opcion) {
        super(ELIGE_HABILIDAD_EVENT);
        this.opcion = opcion;
    }

    public int getOpcion() {
        return opcion;
    }
}

