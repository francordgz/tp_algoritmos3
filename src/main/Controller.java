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
        this.seleccionarPokemon(this.juego.entrenador1);
        this.seleccionarPokemon(this.juego.entrenador2);
        this.juego.asignarPrimerTurno();
    }

     private String pedirNombreEntrenador(String nombreOponente, int numeroEntrenador) {
        String ingreso;
        boolean longitudValida, nombreRepetido, nombreValido;

        do {
            System.out.println("Ingrese un nombre para el entrenador " + numeroEntrenador + ": ");
            ingreso = scanner.nextLine();
            longitudValida = ingreso.length() > 0 && ingreso.length() < 50;
            nombreRepetido = (ingreso == nombreOponente);
            nombreValido = longitudValida && !nombreRepetido;

            if (!longitudValida) {
                VistaJuego.mensaje("Error! El nombre debe contener al menos 1 caracter y menos de 50.");
            } else if (nombreRepetido) {
                VistaJuego.mensaje("Error! El nombre no puede coincidir con el del entrenador rival.");
            }
        } while (!nombreValido);
        return ingreso;
    } 

    public void mostrarJuego() {

    }


    public void Menu(){
        VistaJuego.mostrarMenu();
        boolean opcionCorrecta;
        int opcion = scanner.nextInt();
        do {
            opcionCorrecta = true;
            switch (opcion) {
                case 1:
                    this.mostrarJuego();
                    break;
                case 2:
                    this.Habilidad();
                    break;
                case 3:
                    this.Item();
                    break;
                case 4:
                    this.CambioPokemon();
                    break;
                case 5:
                    juego.rendirse();
                    break;
                default:
                    VistaJuego.mensaje("Seleccione una opción correcta!");
                    opcionCorrecta = false;
            }
        } while (!opcionCorrecta);
    }

    public void seleccionarPokemon(Entrenador jugador){

        int cantPokemones = VistaPokemon.mostrarPokemones(jugador);

        int opcion = 0;
        opcion = scanner.nextInt();
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