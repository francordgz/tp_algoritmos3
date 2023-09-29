package src.main;


public class Main  {

    public static void main(String[] args){

        Juego juego = new Juego();
        Controller controlador = new Controller(juego);

        while(!juego.terminado()) {
           controlador.menuPrincipal();
        }

        controlador.cerrarScanner();
        controlador.declararGanador();
    }
}