package src.main;

public class AdministradorDeTurnos {
    private Entrenador entrenadorActual;
    private Entrenador entrenadorRival;

    public AdministradorDeTurnos() {
    }

    public Entrenador obtenerEntrenadorActual() { return this.entrenadorActual; }

    public Entrenador obtenerEntrenadorRivalActual() {
        return this.entrenadorRival;
    }

    public void cambiarTurno() {
        Entrenador aux = this.entrenadorActual;
        this.entrenadorActual = this.entrenadorRival;
        this.entrenadorRival = aux;
    }

    public void asignarPrimerTurno(Entrenador entrenador1, Entrenador entrenador2) {
        if (entrenador1.pokemonActual.obtenerVelocidad() >= entrenador2.pokemonActual.obtenerVelocidad()) {
            this.entrenadorActual = entrenador1;
            this.entrenadorRival = entrenador2;
        } else {
            this.entrenadorActual = entrenador2;
            this.entrenadorRival = entrenador1;
        }
    }
    
}
