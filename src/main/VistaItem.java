package src.main;

public class VistaItem extends VistaJuego{

    // Devuelve la cantidad de items con al menos un uso restante
    public int mostrarItems(Entrenador entrenador) {
        System.out.println("Selecciona una opcion");
        int i = 0;
        for (Item item : entrenador.getItems()) {
            if (item.obtenerCantidad() > 0) {
                i++;
                System.out.println(i + ": " + item.obtenerNombre() + " (Cantidad: " + item.obtenerCantidad() + ")");
            }
        }

        System.out.println((i + 1)+ ": Volver atrás");

        return i;
    }
}
