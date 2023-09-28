package src.main;

public class VistaHabilidad extends VistaJuego{

    // Devuelve la cantidad de habilidades con usos restantes
    static public int mostrarHabilidades(Pokemon pokemon) {

        int i = 1;
        System.out.println("Selecciona una opcion: ");

        for (Habilidad habilidad : pokemon.obtenerHabilidades()) {
            if (habilidad.getUsos() > 0) {
                System.out.println(i + " : " + habilidad.obtenerNombre() +
                        " (Usos Restantes: " + habilidad.getUsos() + ")");
                i++;
            }
        }

        System.out.println(0 + ": Volver atrás");

        return i;
    }
}
