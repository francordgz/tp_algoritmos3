package BatallaPokemon.Modelo.Habilidad;

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

    public abstract String getTipoHabilidad();
}
