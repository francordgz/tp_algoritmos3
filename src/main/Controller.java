package src.main;

import java.util.Scanner;




public class Controller{

    Juego juego;


    public Controller(Juego juego){
        String nombre1 = pedirNombreEntrenador("", 1);
        String nombre2 = pedirNombreEntrenador(nombre1, 2);
        juego.inicializarEntrenadores(nombre1, nombre2);
        this.juego = juego;
    }

    private String pedirNombreEntrenador(String nombreOponente, int numeroEntrenador) {
        Scanner scanner = new Scanner();
        String ingreso;
        Boolean longitudValida, nombreRepetido, nombreValido;

        do {
            System.out.println("Ingrese un nombre para el entrenador " + Integer.toString(numeroEntrenador) + ": ");
            ingreso = scanner.nextline();
            longitudValida = ingreso.length() > 0 && ingreso.length() < 50;
            nombreRepetido = ingreso == nombreOponente;
            nombreValido = longitudValida && !nombreRepetido;

            if (!longitudValida) {
                System.out.println("Error! El nombre debe contener al menos 1 caracter y menos de 50.");
            } else if (nombreRepetido) {
                System.out.println("Error! El nombre no puede coincidir con el del entrenador rival.");
            }
        } while (!nombreValido);
        return ingreso;
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