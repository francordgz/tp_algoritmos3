package src.main;

import java.util.Scanner;


public class Controller{

    Juego juego;
    Scanner scanner = new Scanner(System.in);


    public Controller(Juego juego){
        this.juego = juego;
        String nombre1 = pedirNombreEntrenador("", 1);
        String nombre2 = pedirNombreEntrenador(nombre1, 2);
        this.juego.inicializarEntrenadores(nombre1, nombre2);
        this.SeleccionarPokemon(this.juego.entrenador1);
        this.SeleccionarPokemon(this.juego.entrenador2);
        this.juego.asignarPrimerTurno();
    }

     private String pedirNombreEntrenador(String nombreOponente, int numeroEntrenador) {
        String ingreso;
        Boolean longitudValida, nombreRepetido, nombreValido;

        do {
            System.out.println("Ingrese un nombre para el entrenador " + Integer.toString(numeroEntrenador) + ": ");
            ingreso = scanner.nextLine();
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


        System.out.println("Selecciona una opcion");
        System.out.println("1 : Ver Campo" );
        System.out.println("2 : Usar Habilidad");
        System.out.println("3 : Usar Item");
        System.out.println("4 : Cambiar Pokemon");
        System.out.println("5 : Rendirse");

        opcion = scanner.nextInt();

        switch(opcion){

            case 1: this.mostrarJuego();

            case 2: this.Habilidad();

            case 3: this.Item();

            case 4: this.CambioPokemon();

            case 5: juego.rendirse();


        }

    }

    public void SeleccionarPokemon(Entrenador jugador){

        System.out.println("Selecciona un pokemon");
        for (int i=0; i<jugador.pokemones.size(); i++){
            System.out.println(jugador.pokemones.get(i).obtenerNombre());
            System.out.println(jugador.pokemones.get(i).obtenerVelocidad());
            System.out.println(jugador.pokemones.get(i).obtenerAtaque());
            System.out.println(jugador.pokemones.get(i).obtenerDefensa());
            System.out.println(jugador.pokemones.get(i).obtenerVidaActual());
            System.out.println(("--------------------------"));
        }

        int opcion = 0;
        opcion = scanner.nextInt();
        jugador.cambiarPokemon(opcion-1);
    }


    public void Habilidad(){

        int opcion = 0;
        System.out.println("Selecciona una habilidad");
        juego.mostrarHabilidades();
        opcion = scanner.nextInt();

        if(opcion == 1){
            juego.atacar(0);
        }else if(opcion == 2){
            juego.atacar(1);
        }else if(opcion == 3){
            juego.UsarHabilidad(2);
        }else if(opcion == 4){
            juego.UsarHabilidad(3);


        }
        
        

    }

    public void Item(){

        System.out.println("Selecciona que item queres usar - > ");
        juego.mostrarItems();
        int opcion = scanner.nextInt();
        juego.usarItem(opcion-1);



    }

    public void CambioPokemon(){


    }




}