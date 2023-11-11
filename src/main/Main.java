package src.main;


import src.main.Controlador.Controller;
import src.main.Modelo.Juego;

public class Main  {

    public static void main(String[] args){

        Juego juego = new Juego();
        Controller controlador = new Controller(juego);

        while(!juego.terminado()) {
           controlador.menuPrincipal();
        }

        controlador.declararGanador();
        controlador.terminar();
    }
}