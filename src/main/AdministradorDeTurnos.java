package src.main;
import java.util.ArrayList;
import java.util.List;
public class AdministradorDeTurnos {
    private final List<Entrenador> entrenadores;
    private Entrenador entrenadorActual;
    private int turnosJugados;

    public AdministradorDeTurnos() {
        this.turnosJugados = 0;
        this.entrenadores = new ArrayList<Entrenador>();
    }

    public Entrenador obtenerEntrenadorActual() { return this.entrenadorActual; }

    public void cambiarTurno() {
        this.turnosJugados += 1;
        int indice = turnosJugados % 2;
        this.entrenadorActual = entrenadores.get(indice);
    }

    public void asignarPrimerTurno(List<Entrenador> entrenadores) {
        Entrenador primerEntrenador = entrenadores.get(0);
        Entrenador entrenadorComparado = entrenadores.get(1);
        int velocidad = primerEntrenador.obtenerPokemonActual().obtenerVelocidad();
        int velocidadComparada = entrenadorComparado.obtenerPokemonActual().obtenerVelocidad();

        if (velocidad > velocidadComparada){
            this.entrenadorActual = primerEntrenador;
            this.entrenadores.set(1, entrenadorComparado);
        } else {
            this.entrenadorActual = entrenadorComparado;
            this.entrenadores.set(1, primerEntrenador);
        }
        this.entrenadores.set(0, this.entrenadorActual);
    }
    
}
