package src.main;

import java.util.Scanner;




public class Controller{

    Juego juego;


    public Controller(Juego juego){

        this.juego = juego;
    }


    public void mostrarJuego(){



    }


    public void Menu(){

        int opcion = 0;


        System.out.println("Que queres hacer?");
        System.out.println("1 : Ver Campo" );
        System.out.println("2 : Usar Habilidad");
        System.out.println("3 : Usar Item");
        System.out.println("4 : Cambiar Pokemon");
        System.out.println("5 : Rendirse");


        switch(opcion){

            case 1: this.mostrarJuego();

            case 2: this.Habilidad();

            case 3: this.Item();

            case 4: this.CambioPokemon();

            case 5: juego.rendirse();


        }
    }


    public void Habilidad(){

        int opcion = 0;
        System.out.println("Que habilidad queres usar");
        

    }

    public void Item(){



    }

    public void CambioPokemon(){


    }


}