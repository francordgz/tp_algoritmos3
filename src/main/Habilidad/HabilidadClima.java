package src.main.Habilidad;

import src.main.Clima.Clima;

public class HabilidadClima extends Habilidad{
    private Clima clima;
    public HabilidadClima(String nombre, int usos, Clima clima) {
        super(nombre, usos);
        this.clima = clima;
    }

    @Override
    public Clima modificarClima() {
        this.usos -= 1;
        return this.clima;
    }
}
