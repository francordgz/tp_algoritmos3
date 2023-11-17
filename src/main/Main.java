package src.main;


import src.main.Controlador.MainController;
import src.main.Modelo.Juego;

public class Main  {

    public static void main(String[] args){

        Juego juego = new Juego();
        MainController controlador = new MainController(juego);

        while(!juego.terminado()) {
           controlador.menuPrincipal();
        }

        controlador.declararGanador();
        controlador.terminar();
    }
}