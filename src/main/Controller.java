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
        this.seleccionarPokemon(this.juego.obtenerPrimerEntrenador());
        this.seleccionarPokemon(this.juego.obtenerSegundoEntrenador());
        this.juego.inicializarTurnos();
    }

     private String pedirNombreEntrenador(String nombreOponente, int numeroEntrenador) {
        String ingreso;
        boolean longitudValida, nombreRepetido, nombreValido;

        do {
            VistaJuego.mensaje("Ingrese un nombre para el entrenador");
            ingreso = scanner.nextLine();
            longitudValida = ingreso.length() > 0 && ingreso.length() < Constant.MAX_NOMBRE;
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

    public void seleccionarPokemon(Entrenador entrenador){

        boolean opcionCorrecta;
        int opcion = scanner.nextInt();
        VistaPokemon.mostrarTodosLosPokemones(entrenador);

        do {
            opcionCorrecta = true;
            if(opcion > entrenador.obtenerPokemonActual().obtenerHabilidades().size()){
                VistaJuego.mensaje("Seleccione una opción correcta!");
                opcionCorrecta = false;
                opcion = scanner.nextInt();
            }else if(opcion == 0){
                return;
            }
        } while (!opcionCorrecta);

        entrenador.cambiarPokemon(opcion-1);
    }

    public void menu(){

        boolean opcionCorrecta;
        int opcion = scanner.nextInt();
        VistaJuego.mostrarMenu();

        do {
            opcionCorrecta = true;
            switch (opcion) {
                case 1:
                    VistaJuego.mostrarJuego(this.juego);
                    break;
                case 2:
                    this.habilidad();
                    break;
                case 3:
                    this.item();
                    break;
                case 4:
                    this.cambioPokemon();
                    break;
                case 5:
                    this.juego.rendirse();
                    break;
                default:
                    VistaJuego.mensaje("Seleccione una opción correcta!");
                    opcionCorrecta = false;
                    opcion = scanner.nextInt();
            }
        } while (!opcionCorrecta);
    }

    public void habilidad(){

        int opcion = scanner.nextInt();
        VistaHabilidad.mostrarHabilidades(this.juego.obtenerEntrenadorActual().obtenerPokemonActual());
        //Optimizable
        if(opcion == 1){
            this.juego.atacar(0);
        }else if(opcion == 2){
            this.juego.atacar(1);
        }else if(opcion == 3){
            this.juego.usarHabilidad(2);
        }else if(opcion == 4){
            this.juego.usarHabilidad(3);
        }
        this.juego.usarTurno();
    }

    public void item(){

        boolean opcionCorrecta;
        int opcion = scanner.nextInt();
        VistaItem.mostrarItems(this.juego.obtenerEntrenadorActual());

        do {
            opcionCorrecta = true;
            if(opcion > this.juego.obtenerEntrenadorActual().obtenerItems().size()){
                VistaJuego.mensaje("Seleccione una opción correcta!");
                opcionCorrecta = false;
                opcion = scanner.nextInt();
            }else if(opcion == 0){
                return;
            }
        } while (!opcionCorrecta);

        this.juego.usarItem(opcion-1);
        this.juego.usarTurno();
    }

    public void cambioPokemon(){
        seleccionarPokemon(this.juego.obtenerEntrenadorActual());
        this.juego.usarTurno();
    }
}