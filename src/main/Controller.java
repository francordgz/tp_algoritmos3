package src.main;
import java.util.Scanner;

import static src.main.Constant.NOT_INT;

public class Controller{

    Juego juego;
    Scanner scanner = new Scanner(System.in);


    public Controller(Juego juego){
        this.juego = juego;
        String nombre1 = pedirNombreEntrenador("");
        this.juego.entrenador1 = new Entrenador(nombre1);

        String nombre2 = pedirNombreEntrenador(nombre1);
        this.juego.entrenador2 = new Entrenador(nombre2);

        this.juego.crearPokemones();
        this.juego.crearItems();

        this.seleccionarPokemon(this.juego.obtenerPrimerEntrenador());
        this.seleccionarPokemon(this.juego.obtenerSegundoEntrenador());

        this.juego.inicializarTurnos();
    }

     private String pedirNombreEntrenador(String nombreOponente) {
        String ingreso;
        boolean longitudValida, nombreRepetido, nombreValido;

        do {
            VistaJuego.mensaje("Ingrese un nombre para el entrenador");
            ingreso = leerString();
            longitudValida = ingreso.length() > 0 && ingreso.length() < Constant.MAX_NOMBRE;
            nombreRepetido = ingreso.equals(nombreOponente);
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
        VistaPokemon.mostrarTodosLosPokemones(entrenador);

        int opcion = leerInt();

        do {
            opcionCorrecta = true;
            if(opcion > entrenador.obtenerPokemones().size() || opcion == NOT_INT){
                VistaJuego.mensaje("Seleccione una opción correcta!");
                opcionCorrecta = false;
                opcion = leerInt();
            }else if(opcion == 0){
                return;
            }
        } while (!opcionCorrecta);

        entrenador.cambiarPokemon(opcion-1);
    }

    public void menu(){

        boolean opcionCorrecta;
        VistaJuego.mostrarMenu();
        int opcion = leerInt();

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
                    opcion = leerInt();
            }
        } while (!opcionCorrecta);
    }

    public void habilidad(){
        VistaHabilidad.mostrarHabilidades(this.juego.obtenerEntrenadorActual().obtenerPokemonActual());
        int opcion = leerInt();
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
        VistaItem.mostrarItems(this.juego.obtenerEntrenadorActual());
        int opcion = leerInt();
        do {
            opcionCorrecta = true;
            if(opcion > this.juego.obtenerEntrenadorActual().obtenerItems().size()){
                VistaJuego.mensaje("Seleccione una opción correcta!");
                opcionCorrecta = false;
                opcion = leerInt();
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

    //Auxiliares:

    private String leerString() {
        return this.scanner.nextLine();
    }

    private int leerInt() {
        if (this.scanner.hasNextInt()) {
            return this.scanner.nextInt();
        } else {
            this.scanner.nextLine(); // "Consume" el input invalido"
            return NOT_INT;
        }
    }
}