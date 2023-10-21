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
        String nombre2 = pedirNombreEntrenador(nombre1);

        this.juego.asignarEntrenadores(new Entrenador(nombre1), new Entrenador(nombre2));
        this.juego.crearPokemones();
        this.juego.crearItems();
        this.seleccionarPrimerPokemon(this.juego.obtenerPrimerEntrenador());
        this.seleccionarPrimerPokemon(this.juego.obtenerSegundoEntrenador());
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
        this.juego.actualizarEstado();
        this.juego.cambiarTurno();
    }

    private void seleccionarPrimerPokemon(Entrenador entrenador){
        int opcion = NOT_INT;
        boolean opcionValida = false;
        VistaPokemon.mostrarPokemones(entrenador, true);
        String nombrePokemon;

        while (!opcionValida) {
            opcion = leerInt();
            opcionValida = !(opcion > entrenador.obtenerPokemones().size() || opcion < 1);
            
            if(!opcionValida) { VistaJuego.imprimir("Seleccione una opción correcta!"); }
            
        }
        entrenador.cambiarPokemon(opcion - 1);
        nombrePokemon = entrenador.obtenerPokemonActual().obtenerNombre();
        VistaJuego.imprimir(entrenador.obtenerNombre() + " ha elegido a " + nombrePokemon + " como primer Pokemon");
    }

    private boolean seleccionarPokemon(Entrenador entrenador, boolean seleccionObligatoria) {
        boolean seleccionValida = false;
        Pokemon pokemonSeleccionado;
        String nombrePokemonActual = entrenador.obtenerPokemonActual().obtenerNombre();
        int indicePokemon = Constant.NULA;
        
        while (!seleccionValida) {
            indicePokemon = consultarPokemon(entrenador, seleccionObligatoria);

            if (indicePokemon == NOT_INT && !seleccionObligatoria) { return false; }

            pokemonSeleccionado = entrenador.obtenerPokemones().get(indicePokemon);
            if (pokemonSeleccionado.estaMuerto()) {
                VistaJuego.imprimir("Ese Pokemon esta muerto!");
            } else if (nombrePokemonActual.equals(pokemonSeleccionado.obtenerNombre())) {
                VistaJuego.imprimir("Se debe elegir un Pokemon distinto al actual mientras él siga con vida!");
            } else {
                seleccionValida = true;
            }
        }
        entrenador.cambiarPokemon(indicePokemon);
        return true;
    }

    private int consultarPokemon(Entrenador entrenador, boolean seleccionObligatoria){
        int opcion = Constant.SALIR;
        VistaPokemon.mostrarPokemones(entrenador, seleccionObligatoria);
        boolean opcionValida, seleccionValida = false;

        while (!seleccionValida) {
            opcion = leerInt();
            if (!seleccionObligatoria && opcion == Constant.SALIR) { return NOT_INT; }
            opcionValida = opcion < this.juego.obtenerEntrenadorActual().obtenerItems().size() && opcion > Constant.SALIR;
            
            if (!opcionValida) {
                VistaJuego.imprimir("Seleccione una opcion correcta porfavor");
                continue;
            }
            seleccionValida = true;
        }
        return opcion - 1;
    }

    private int consultarHabilidad() {
        Pokemon pokemonActual = this.juego.obtenerEntrenadorActual()
                                    .obtenerPokemonActual();
        Habilidad habilidadSeleccionada;
        boolean esHabilidadDeAtaque, opcionValida, habilidadValida = false;
        int opcion = Constant.SALIR;
        
        VistaHabilidad.mostrarHabilidades(pokemonActual);
        while (!habilidadValida) {
            opcion = leerInt();
            opcionValida = opcion <= 4 && opcion >= 0;

            if (!opcionValida) {
                VistaJuego.imprimir("Seleccione una opción correcta!");
                continue;
            }
            else if (opcion == Constant.SALIR) { return opcion; }
            else { esHabilidadDeAtaque = opcion == 1 || opcion == 2; }
            habilidadSeleccionada = pokemonActual.obtenerHabilidades().get(opcion - 1);

            if (!habilidadSeleccionada.quedanUsosDisponibles()) {
                VistaJuego.imprimir("Esta habilidad no tiene más usos");
            } else if (esHabilidadDeAtaque && !pokemonActual.puedeAtacar()) {
                VistaJuego.imprimir("El Pokemon esta dormido, no puede atacar");
            } else { habilidadValida = true; }
        }
        return opcion;
    }

    public boolean seleccionarHabilidad(){

        //TODO: Esto va en juego, y la comparacion va en pokemon
        if(this.juego.obtenerEntrenadorActual().obtenerPokemonActual().estado == Estados.PARALIZADO){
            Boolean probabilidad = calcularProbabilidad();
            if(probabilidad == false){
                VistaJuego.imprimir("El pokemon esta paralizado!");
                return false;
            }
        }
        int opcion = consultarHabilidad();
        int habilidadSeleccionada = opcion - 1;

        switch (opcion) {
            case Constant.SALIR: return false;
            case 1:
                VistaJuego.mostrarEfectividad(this.juego.atacar(habilidadSeleccionada)); break;
            case 2:
                VistaJuego.mostrarEfectividad(this.juego.atacar(habilidadSeleccionada)); break;
            case 3:
                this.juego.usarHabilidad(habilidadSeleccionada); break;
            case 4:
                this.juego.usarHabilidad(habilidadSeleccionada); break;
        }
        return true;
    } 
    


    public Boolean calcularProbabilidad(){
        Random rand = new Random();
        int probabilidad = rand.nextInt(2);
        
        if(probabilidad == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    private boolean seleccionarItem() {
        int opcion, numeroItem = NOT_INT;
        boolean itemSeleccionadoValido = false, itemEsAplicable = false;
        boolean opcionInvalida, itemDisponible;
        Entrenador entrenadorActual = this.juego.obtenerEntrenadorActual();

        int pokemonSeleccionado = consultarPokemon(entrenadorActual, false);
        if (pokemonSeleccionado == NOT_INT) { return false; }

        while (!itemSeleccionadoValido && !itemEsAplicable) {

            VistaItem.mostrarItems(entrenadorActual);
            opcion = leerInt();

            if (opcion == Constant.SALIR) { return false; }

            opcionInvalida = opcion > this.juego.obtenerEntrenadorActual().obtenerItems().size() || opcion <= Constant.SALIR;
            if(opcionInvalida) {
                VistaJuego.imprimir("Seleccione una opción correcta!");
                continue;
            }

            numeroItem = opcion - 1;
            itemDisponible = entrenadorActual.obtenerItems().get(opcion - 1).obtenerCantidad() > 0;
            if (!itemDisponible) {
                VistaJuego.imprimir("Este item no tiene más usos");
                continue;
            } else { 
                itemSeleccionadoValido = true;
                itemEsAplicable = entrenadorActual.puedeAplicarItem(pokemonSeleccionado, numeroItem);
            }
            if (itemSeleccionadoValido && !itemEsAplicable) {
                VistaJuego.imprimir("No es posible aplicar el item debido al estado del Pokemon seleccionado");
                itemSeleccionadoValido = itemEsAplicable;
            } 
        }
        this.juego.usarItem(numeroItem, pokemonSeleccionado);
        VistaItem.notificarUsoDeItem(entrenadorActual, numeroItem);
        return true;
    }

    public void terminar() {
        VistaJuego.imprimir("Presione cualquier tecla para terminar");
        this.scanner.close();
    }

    public void declararGanador() {
        VistaJuego.imprimir("El ganador es: " + juego.obtenerGanador().obtenerNombre());
    }

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