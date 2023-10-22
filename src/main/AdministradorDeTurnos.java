package src.main;

public class AdministradorDeTurnos {
    private Entrenador entrenadorActual;
    private Entrenador entrenadorRival;
    private Integer diasDelClimaActual;

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
        Pokemon primero = entrenador1.obtenerPokemonActual();
        Pokemon segundo = entrenador2.obtenerPokemonActual();
        if (primero.obtenerVelocidad() >= segundo.obtenerVelocidad()) {
            this.entrenadorActual = entrenador1;
            this.entrenadorRival = entrenador2;
        } else {
            this.entrenadorActual = entrenador2;
            this.entrenadorRival = entrenador1;
        }
    }

    public void modificarDiasDelClimaActual(Integer dias) {
        this.diasDelClimaActual = dias;
    }

    public Integer obtenerDiasDelClimaActual() {
        return this.diasDelClimaActual;
    }
}
