package src.main;

public class VistaHabilidad extends VistaJuego{
    static public void mostrarHabilidades(Pokemon pokemon) {
        int i = 1;
        imprimir("\nSelecciona una habilidad:");

        for (Habilidad habilidad : pokemon.obtenerHabilidades()) {
            if (habilidad.getUsos() > 0) {
                imprimir(i + " : " + habilidad.obtenerNombre() +
                        " (Usos Restantes: " + habilidad.getUsos() + ")");
                i++;
            }
        }

        imprimir("0: Volver atrás\n");
    }
}
