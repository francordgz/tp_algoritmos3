package src.main;
import src.main.Enums.Estados;
import src.main.Vista.*;

import java.util.Random;
import java.util.Scanner;
import static src.main.Constant.NOT_INT;

public class Controller {

    Juego juego;
    Scanner scanner = new Scanner(System.in);


    public Controller(Juego juego) {
        this.juego = juego;
        String nombre1 = pedirNombreEntrenador("");
        this.juego.entrenador1 = new Entrenador(nombre1);

        String nombre2 = pedirNombreEntrenador(nombre1);
        this.juego.entrenador2 = new Entrenador(nombre2);

        // TODO: Esto esta acá para que funcione pero no debería.
        this.juego.crearPokemones();
        this.juego.crearItems();

        this.seleccionarPokemon(this.juego.obtenerPrimerEntrenador(), true);
        this.seleccionarPokemon(this.juego.obtenerSegundoEntrenador(), true);

        this.juego.inicializarTurnos();
    }

    private String pedirNombreEntrenador(String nombreOponente) {
        String ingreso;
        boolean longitudValida;
        boolean nombreRepetido;

        do {
            VistaJuego.imprimirMismaLinea("Ingrese un nombre para el entrenador: ");
            ingreso = leerString();
            longitudValida = ingreso.length() > 0 && ingreso.length() < Constant.MAX_NOMBRE;
            nombreRepetido = ingreso.equals(nombreOponente);

            if (!longitudValida) {
                VistaJuego.imprimir("Error! El nombre debe contener al menos 1 caracter y menos de 50.");
            } else if (nombreRepetido) {
                VistaJuego.imprimir("Error! El nombre no puede coincidir con el del entrenador rival.");
            }
        } while (!longitudValida || nombreRepetido);

        return ingreso;
    }


    public void menuPrincipal() {
        int opcion;
        boolean turnoTerminado = false;
        boolean mostrar = true;
        Entrenador entrenadorActual = this.juego.obtenerEntrenadorActual();
        Pokemon pokemonActual = entrenadorActual.obtenerPokemonActual();

        // TODO: Esto donde va??
        if (pokemonActual.estaMuerto()) {
            VistaJuego.imprimirMismaLinea(pokemonActual.obtenerNombre() + " ha muerto!");
            this.seleccionarPokemon(this.juego.obtenerEntrenadorActual(), true);
            turnoTerminado = true;
        }

        while (!turnoTerminado) {
            if (mostrar) VistaJuego.mostrarMenu(entrenadorActual.obtenerNombre());
            else mostrar = true;

            opcion = leerInt();
            switch (opcion) {
                case 1:
                    VistaPokemon.mostrarCampo(entrenadorActual, this.juego.obtenerEntrenadorRival());
                    break;
                case 2:
                    turnoTerminado = this.seleccionarHabilidad();
                    break;
                case 3:
                    turnoTerminado = this.seleccionarItem();
                    break;
                case 4:
                    turnoTerminado = this.seleccionarPokemon(entrenadorActual, false);
                    break;
                case 5:
                    this.juego.rendirse();
                    return;
                default:
                    VistaJuego.imprimir("Seleccione una opción correcta!");
                    mostrar = false;
            }
        }

        juego.actualizarEstado();
        juego.usarTurno();
    }

    private int consultarPokemon(Entrenador entrenador, boolean seleccionObligatoria) {
        boolean seleccionValida = false;
        boolean seleccionDeSalidaValida = false;
        int opcion = 0;
        VistaPokemon.mostrarPokemones(entrenador, seleccionObligatoria);

        while (!seleccionValida && !seleccionDeSalidaValida) {
            opcion = leerInt();
            seleccionValida = !(opcion == NOT_INT || opcion > entrenador.obtenerPokemones().size() || opcion == Constant.SALIR);
            seleccionDeSalidaValida = !seleccionObligatoria && opcion == Constant.SALIR;

            if(!seleccionValida && !seleccionDeSalidaValida) {
                VistaJuego.imprimir("Seleccione una opción correcta!");
            }
        }
        return opcion;
    }

    // private Pokemon pedirPokemonParaItem() {
    //     boolean itemEsAplicable = false;
    //     int opcion = NOT_INT;
    //     Pokemon seleccionado = null;
    //     VistaJuego.imprimir("Selección de Pokemon que utilizara un item \n");

    //     while(opcion != Constant.SALIR) {
    //         opcion = consultarPokemon(this.juego.obtenerEntrenadorActual(), false);
    //     }
    //     return null;
    // }

    private boolean seleccionarPokemon(Entrenador entrenador, boolean seleccionObligatoria){
        int opcion;
        VistaPokemon.mostrarPokemones(entrenador, seleccionObligatoria);
        String nombrePokemonActual = "";
        Pokemon pokemonSeleccionado;
        Pokemon pokemonActual = entrenador.obtenerPokemonActual();

        while (true) {
            opcion = consultarPokemon(entrenador, seleccionObligatoria);
            if (!seleccionObligatoria && opcion == Constant.SALIR) { return false; }
            //if(opcion == NOT_INT || opcion > entrenador.obtenerPokemones().size() || opcion == Constant.SALIR) {
            //    VistaJuego.imprimir("Seleccione una opción correcta!");
            //    continue;
            //}
            if (pokemonActual != null) { nombrePokemonActual = entrenador.obtenerPokemonActual().obtenerNombre(); }

            pokemonSeleccionado = entrenador.obtenerPokemones().get(opcion-1); // TODO: Esto esta medio mal

            if (pokemonSeleccionado.estaMuerto()) {
                VistaJuego.imprimir("Ese Pokemon esta muerto!");
            } else if (nombrePokemonActual.equals(pokemonSeleccionado.obtenerNombre())) {
                VistaJuego.imprimir("Se debe elegir un Pokemon distinto al actual mientras él siga con vida!");
            } else break; // Opcion correcta seleccionada
        }

        entrenador.cambiarPokemon(opcion-1);
        VistaJuego.imprimir(entrenador.obtenerNombre() + "ha cambiado su Pokemon a " + pokemonSeleccionado);
        return true;
    }

    public boolean seleccionarHabilidad(){
        Pokemon pokemonActual = this.juego.obtenerEntrenadorActual().obtenerPokemonActual();

        // Creo que esto se va, despues vemos
        // if(pokemonActual.estado == Estados.PARALIZADO){
        //     Boolean probabilidad = calcularProbabilidad();
        //     if(probabilidad == false){
        //         VistaJuego.imprimir("El pokemon esta paralizado!");
        //         return false;
        //     }
        // }
            
        VistaHabilidad.mostrarHabilidades(pokemonActual);
        int opcion;
        while (true) {
            opcion = leerInt();
            if (opcion > 0 && pokemonActual.obtenerHabilidades().get(opcion - 1).getUsos() <= 0) { // TODO: Horripilante
                VistaJuego.imprimir("Esta habilidad no tiene más usos");
                continue;
            }
            if (!pokemonActual.puedeAtacar() && (opcion == 1 || opcion == 2)) {
                VistaJuego.imprimir("El Pokemon esta dormido, no puede atacar");
                continue;
            }
            // TODO: Paralizado: El Pokemon no realizara la habilidad seleccionada con probabilidad 0.5.
            /* TODO: Una vez seleccionada, se debe mostrar un mensaje
                mostrando la accion realizada y el resultado. El resultado depende de
                la accion, por ejemplo si cambio la vida, mostrar la vida restante, si
                el estado cambio mostrar un mensaje acorde.*/
            switch (opcion) {
                case Constant.SALIR: return false;
                case 1:
                    VistaJuego.mostrarEfectividad(this.juego.atacar(0));
                return true;
                case 2:
                    VistaJuego.mostrarEfectividad(this.juego.atacar(1));
                    return true;
                case 3:
                    this.juego.usarHabilidad(2);
                    return true;
                case 4:
                    this.juego.usarHabilidad(3);
                    return true;
                default:
                    VistaJuego.imprimir("Seleccione una opción correcta!");
                }
            }
        }
    


    public Boolean calcularProbabilidad(){
        Random rand = new Random();
        int probabilidad = rand.nextInt(2);
        
        if(probabilidad == 1){
            return true;
        }else{
            return false;
        }


    }
    
    private boolean seleccionarItem() {
        int opcion, numeroItem;
        boolean pokemonSeleccionadoValido, itemSeleccionadoValido = false, itemEsAplicable = false;
        Pokemon seleccionado;
        Entrenador entrenadorActual = this.juego.obtenerEntrenadorActual();
        VistaItem.mostrarItems(entrenadorActual);

        while (!itemSeleccionadoValido && !itemEsAplicable) {
            opcion = leerInt();
            seleccionado = pedirPokemonParaItem();
            if (opcion > 0 && entrenadorActual.obtenerItems().get(opcion - 1).obtenerCantidad() <= 0) { // TODO: Horripilante
                VistaJuego.imprimir("Esta habilidad no tiene más usos");
                continue;
            }
            if (opcion == Constant.SALIR || seleccionado == null) {
                VistaJuego.imprimir("Se ha decidido no utilizar un item.");
                return false;
            }
            if(opcion == NOT_INT || opcion > this.juego.obtenerEntrenadorActual().obtenerItems().size()) {
                VistaJuego.imprimir("Seleccione una opción correcta!");
            } else { 
                numeroItem = opcion - 1;
                itemSeleccionadoValido = true;
                itemEsAplicable = entrenadorActual.puedeAplicarItem(seleccionado, numeroItem);
            } // Opcion correcta seleccionada
            if (itemSeleccionadoValido && !itemEsAplicable) {
                VistaJuego.imprimir("No es posible aplicar el item debido al estado del Pokemon seleccionado");
                itemSeleccionadoValido = itemEsAplicable;
            } 
        }
        // TODO: Luego de aplicar el item, se debe mostrar un mensaje que muestre la accion realizada para el otro jugador
        this.juego.usarItem(numeroItem);
        return true;
    }

    public void terminar() {
        VistaJuego.imprimir("Presione cualquier tecla para terminar");
        // TODO: Como es que presione cualquier tecla?
        this.scanner.close();
    }

    public void declararGanador() {
        VistaJuego.imprimir("El ganador es: " + juego.ganador.obtenerNombre());
    }

    //Auxiliares:

    private String leerString() {
        return this.scanner.nextLine();
    }

    private int leerInt() {
        VistaJuego.imprimirMismaLinea("\nIngrese un numero: ");
        if (this.scanner.hasNextInt()) {
            int entero = this.scanner.nextInt();
            scanner.nextLine(); // Consume el /n
            return entero;
        } else {
            scanner.nextLine(); // Consume la entrada inválida
            return NOT_INT;
        }
    }
}