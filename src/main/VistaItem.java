package src.main;

public class VistaItem extends VistaJuego{
    static public void mostrarItems(Entrenador entrenador) {
        imprimir("\nSelecciona un item:");
        int i = 1;

        for (Item item : entrenador.obtenerItems()) {
            if (item.obtenerCantidad() > 0) {
                imprimir(i + ": " + item.obtenerNombre() + " (Cantidad: " + item.obtenerCantidad() + ")");
                i++;
            }
        }

        imprimir("0: Volver atrás\n");
    }
}
