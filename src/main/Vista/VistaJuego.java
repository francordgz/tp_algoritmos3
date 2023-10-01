package src.main.Vista;

import src.main.Constant;

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

    static public void mostrarEfectividad(double efectividad) {
        if (efectividad == Constant.NULA) {
            VistaJuego.imprimir("El ataque es cero efectivo!\n");
        } else if (efectividad == Constant.MEDIA) {
            VistaJuego.imprimir("El ataque es poco efectivo.\n");
        } else if (efectividad == Constant.SIMPLE) {
            VistaJuego.imprimir("El ataque es normal\n");
        } else if (efectividad == Constant.DOBLE) {
            VistaJuego.imprimir("El ataque es muy efectivo!\n");
        }
    }
}