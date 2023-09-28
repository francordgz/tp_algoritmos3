package src.main;

public class VistaItem extends VistaJuego{

    // Devuelve la cantidad de items con al menos un uso restante
    static public int mostrarItems(Entrenador entrenador) {
        System.out.println("Selecciona una opcion");
        int i = 1;

        for (Item item : entrenador.obtenerItems()) {
            if (item.obtenerCantidad() > 0) {
                System.out.println(i + ": " + item.obtenerNombre() + " (Cantidad: " + item.obtenerCantidad() + ")");
                i++;
            }
        }

        System.out.println(0 + ": Volver atrás");

        return i;
    }
}
