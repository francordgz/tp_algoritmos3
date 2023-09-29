package src.main.Vista;

public class VistaJuego {
    // Nueva línea
    static public void imprimir(String mensaje) {
        System.out.println(mensaje);
    }

    // Misma línea, para el input
    static public void imprimirMismaLinea(String mensaje) {
        System.out.print(mensaje);
    }

    static public void mostrarMenu(String nombreActual) {
        imprimir("\n" + nombreActual + ", seleccione una opcion:");
        imprimir("1 : Ver Campo" );
        imprimir("2 : Usar Habilidad");
        imprimir("3 : Usar Item");
        imprimir("4 : Cambiar Pokemon");
        imprimir("5 : Rendirse\n");
    }
}
