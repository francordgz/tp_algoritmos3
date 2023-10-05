package src.main.Vista;

public abstract class Vista {
    // Nueva línea
    static public void imprimir(String mensaje) {
        System.out.println(mensaje);
    }

    // Misma línea, para el input
    static public void imprimirMismaLinea(String mensaje) {
        System.out.print(mensaje);
    }
}
