package src.main;

public abstract class Habilidad {

    protected String nombre;
    protected int usos;

    public Habilidad(String nombre, int usos) {
        this.nombre = nombre;
        this.usos = usos;
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

    public void atacar(int ataque, int nivel, Pokemon rival, double efectividad) {}

    public void modificarEstado(Pokemon pokemon) {}

    public Boolean AfectarRival() {
        return true;
    }
}
