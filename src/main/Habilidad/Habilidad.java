package src.main.Habilidad;

import src.main.Clima.Clima;
import src.main.Pokemon;

public abstract class Habilidad {
    protected String nombre;
    protected int usos;
    protected int id;

    public Habilidad(String nombre, int usos, int id) {
        this.nombre = nombre;
        this.usos = usos;
        this.id = id;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public int obtenerUsos() {
        return usos;
    }

    public boolean quedanUsosDisponibles() {
        return this.usos > 0;
    }

    public double atacar(int ataque, int nivel, int defensa, double efectividad) {
        return 0;
    }

    public Clima modificarClima() {
        return null;
    }

    public void modificarEstado(Pokemon pokemon) {
    }

    public Boolean AfectarRival() {
        return true;
    }
}
