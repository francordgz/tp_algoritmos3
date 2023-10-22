package src.main.Vista;

import src.main.Habilidad.Habilidad;
import src.main.Pokemon;
public class VistaHabilidad extends Vista {
    static public void mostrarHabilidades(Pokemon pokemon) {
        int i = 1;
        imprimir("\nSelecciona una habilidad:");

        for (Habilidad habilidad : pokemon.obtenerHabilidades()) {
                imprimir(i + " : " + habilidad.obtenerNombre() +
                        " (Usos Restantes: " + habilidad.obtenerUsos() + ")");
                i++;
        }

        imprimir("0: Volver atrás\n");
    }
}
