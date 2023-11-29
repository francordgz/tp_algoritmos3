package BatallaPokemon.Modelo.Habilidad;

import BatallaPokemon.Modelo.Clima.Clima;

public class HabilidadClima extends Habilidad{
    private final Clima clima;
    public HabilidadClima(String nombre, int usos, int id, Clima clima) {
        super(nombre, usos, id);
        this.clima = clima;
    }

    public Clima modificarClima() {
        this.usos -= 1;
        return this.clima;
    }

    @Override
    public String getTipoHabilidad() {
        return "clima";
    }
}
