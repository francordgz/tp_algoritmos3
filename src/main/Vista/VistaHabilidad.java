package src.main.Vista;

import src.main.Habilidad;
import src.main.Pokemon;
public class VistaHabilidad extends VistaJuego {
    static public void mostrarHabilidades(Pokemon pokemon) {
        int i = 1;
        imprimir("\nSelecciona una habilidad:");

        for (Habilidad habilidad : pokemon.obtenerHabilidades()) {
                imprimir(i + " : " + habilidad.obtenerNombre() +
                        " (Usos Restantes: " + habilidad.getUsos() + ")");
                i++;
        }

        imprimir("0: Volver atrás\n");
    }
}