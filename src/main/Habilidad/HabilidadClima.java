package src.main.Habilidad;

import src.main.Clima.Clima;

public class HabilidadClima extends Habilidad{
    private final Clima clima;
    public HabilidadClima(String nombre, int usos, int id, Clima clima) {
        super(nombre, usos, id);
        this.clima = clima;
    }

    @Override
    public Clima modificarClima() {
        this.usos -= 1;
        return this.clima;
    }
}
