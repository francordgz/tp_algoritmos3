package src.main;

public class VistaJuego {
    static public void mostrarMenu() {
        System.out.println("Selecciona una opcion");
        System.out.println("1 : Ver Campo" );
        System.out.println("2 : Usar Habilidad");
        System.out.println("3 : Usar Item");
        System.out.println("4 : Cambiar Pokemon");
        System.out.println("5 : Rendirse");
    }

    static public void mensaje(String mensaje) {
        System.out.println(mensaje);
    }

    static public void mostrarJuego(Juego juego){
        VistaPokemon.mostrarPokemon(juego.obtenerEntrenadorActual());
        VistaPokemon.mostrarPokemon(juego.obtenerEntrenadorRival());
    }
}
