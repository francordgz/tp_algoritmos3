package src.main;

public class VistaItem extends VistaJuego{

    // Devuelve la cantidad de items con usos restantes
    public int mostrarItems(Entrenador entrenador) {
        System.out.println("Selecciona una opcion");
        int i = 0;
        for (Item item : entrenador.getItems()) {
            if (item.obtenerCantidad() > 0) {
                i++;
                System.out.println(i + " : " + item.obtenerNombre() + " (Cantidad: " + item.obtenerCantidad() + ")");
            }
        }

        return i;
    }
}
