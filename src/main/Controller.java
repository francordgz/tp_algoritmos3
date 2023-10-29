package src.main;

import src.main.Enums.Estados;
import src.main.Vista.*;

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
        this.juego.inicializarClima();
    }

    private String pedirNombreEntrenador(String nombreOponente) {
        String ingreso;
        boolean longitudValida;
        boolean nombreRepetido;

        do {
            VistaJuego.imprimirMismaLinea("Ingrese un nombre para el entrenador: ");
            ingreso = leerString();
            longitudValida = !ingreso.isEmpty() && ingreso.length() < Constant.MAX_NOMBRE;
            nombreRepetido = ingreso.equals(nombreOponente);

            if (!longitudValida)
                VistaJuego.imprimir("Error! El nombre debe contener al menos 1 caracter y menos de 50.");
            else if (nombreRepetido)
                VistaJuego.imprimir("Error! El nombre no puede coincidir con el del entrenador rival.");

        } while (!longitudValida || nombreRepetido);

        return ingreso;
    }

    public void menuPrincipal() {
        int opcion;
        boolean turnoTerminado = false;
        boolean mostrar = true;

        this.juego.efectoClimatico();
        Entrenador entrenadorActual = this.juego.obtenerEntrenadorActual();
        Pokemon pokemonActual = entrenadorActual.obtenerPokemonActual();

        //TODO: Que pasa si el clima mata al pokemon rival pero no al actual??
        //TODO: No esta claro en el PDF
        if (this.juego.pokemonActualTieneEstado(Estados.MUERTO)) {
            VistaJuego.imprimirMismaLinea(pokemonActual.obtenerNombre() + " ha muerto!");
            this.seleccionarPokemon(entrenadorActual, true);
            turnoTerminado = true;
        }

        this.juego.actualizarEstado();

        while (!turnoTerminado) {
            if (mostrar)
                VistaJuego.mostrarMenu(entrenadorActual.obtenerNombre());
            else
                mostrar = true;

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

        this.juego.cambiarTurno();
        this.juego.actualizarClima();
    }

    private void seleccionarPrimerPokemon(Entrenador entrenador){
        int opcion = NOT_INT;
        boolean opcionValida = false;
        VistaPokemon.mostrarPokemones(entrenador, true);
        String nombrePokemon;

        while (!opcionValida) {
            opcion = leerInt();
            opcionValida = !(opcion > entrenador.obtenerPokemones().size() || opcion < 1);
            
            if(!opcionValida)
                VistaJuego.imprimir("Seleccione una opción correcta!");
        }
        //TODO: Controller necesita conocer el entrenado
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
            if (pokemonSeleccionado.estaMuerto())
                VistaJuego.imprimir("Ese Pokemon esta muerto!");
            else if (nombrePokemonActual.equals(pokemonSeleccionado.obtenerNombre()))
                VistaJuego.imprimir("Se debe elegir un Pokemon distinto al actual mientras él siga con vida!");
            else
                seleccionValida = true;
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
            if (!seleccionObligatoria && opcion == Constant.SALIR)
                return NOT_INT;

            opcionValida = opcion < this.juego.obtenerEntrenadorActual().obtenerItems().size() && opcion > Constant.SALIR;
            if (!opcionValida) {
                VistaJuego.imprimir("Seleccione una opcion correcta porfavor");
                continue;
            }
            seleccionValida = true;
        }
        return opcion - 1;
    }

    private int pedirHabilidad() {
        int opcion = Constant.SALIR;
        boolean habilidadValida = false;

        VistaHabilidad.mostrarHabilidades(this.juego.obtenerEntrenadorActual().obtenerPokemonActual());

        while (!habilidadValida) {
            opcion = leerInt();

            if (opcion < 0 || opcion > 5)
                VistaJuego.imprimir("Seleccione una opción correcta!");
            else
                habilidadValida = true;
        }

        if (opcion == Constant.SALIR)
            return opcion;

        if (!this.juego.validarHabilidad(opcion - 1))
            VistaJuego.imprimir("Esta habilidad no tiene más usos");

        return opcion;
    }

    public Boolean seleccionarHabilidad(){
        double ataque;
        Boolean ataqueEfectivo = true;
        int opcion = pedirHabilidad();
        int habilidadSeleccionada = opcion - 1;

        if (this.juego.pokemonActualTieneEstado(Estados.DORMIDO)) {
            VistaJuego.imprimir("El Pokemon esta dormido, no puede atacar");
            return true;
        }

        switch (opcion) {
            case Constant.SALIR:
                return false;
            case 1:
                ataque = this.juego.atacar(habilidadSeleccionada);

                if (ataque == 0 && this.juego.pokemonActualTieneEstado(Estados.PARALIZADO)) {
                    ataqueEfectivo = false;
                    break;
                }
                VistaJuego.mostrarEfectividad(ataque);
                break;
            case 2:
                ataque = this.juego.atacar(habilidadSeleccionada);

                if (ataque == 0 && this.juego.pokemonActualTieneEstado(Estados.PARALIZADO)) {
                    ataqueEfectivo = false;
                    break;
                }
                VistaJuego.mostrarEfectividad(this.juego.atacar(habilidadSeleccionada));
                break;
            case 3:
                ataqueEfectivo = this.juego.usarHabilidad(habilidadSeleccionada);
                break;
            case 4:
                ataqueEfectivo = this.juego.usarHabilidad(habilidadSeleccionada);
                break;
            case 5:
                ataqueEfectivo = this.juego.usarHabilidad(habilidadSeleccionada);
                break;
        }
        if (!ataqueEfectivo)
            VistaJuego.imprimir("El pokemon esta paralizado!");

        return true;
    }


    
    private boolean seleccionarItem() {
        int opcion, numeroItem = NOT_INT;
        boolean itemSeleccionadoValido = false, itemEsAplicable = false;
        boolean opcionInvalida, itemDisponible;
        Entrenador entrenadorActual = this.juego.obtenerEntrenadorActual();

        int pokemonSeleccionado = consultarPokemon(entrenadorActual, false);
        if (pokemonSeleccionado == NOT_INT)
            return false;

        while (!itemSeleccionadoValido) {
            VistaItem.mostrarItems(entrenadorActual);
            opcion = leerInt();

            if (opcion == Constant.SALIR)
                return false;

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
            if (!itemEsAplicable) {
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