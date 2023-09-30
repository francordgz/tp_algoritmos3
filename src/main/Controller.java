package src.main;
import src.main.Vista.*;

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

        // TODO: Esto donde va??
        if (this.juego.obtenerEntrenadorActual().obtenerPokemonActual().estaMuerto()) {
            this.seleccionarPokemon(this.juego.obtenerEntrenadorActual(), true);
        }

        while (!turnoTerminado) {
            if (mostrar) VistaJuego.mostrarMenu(this.juego.obtenerEntrenadorActual().obtenerNombre());
            else mostrar = true;

            opcion = leerInt();
            switch (opcion) {
                case 1:
                    VistaPokemon.mostrarCampo(this.juego.obtenerEntrenadorActual(), this.juego.obtenerEntrenadorRival());
                    break;
                case 2:
                    turnoTerminado = this.seleccionarHabilidad();
                    break;
                case 3:
                    turnoTerminado = this.seleccionarItem();
                    break;
                case 4:
                    turnoTerminado = this.seleccionarPokemon(this.juego.obtenerEntrenadorActual(), false);
                    break;
                case 5:
                    this.juego.rendirse();
                    return;
                default:
                    VistaJuego.imprimir("Seleccione una opción correcta!");
                    mostrar = false;
            }
        }

        juego.usarTurno();
    }

    public boolean seleccionarPokemon(Entrenador entrenador, boolean seleccionObligatoria){
        int opcion;
        VistaPokemon.mostrarPokemones(entrenador, seleccionObligatoria);

        while (true) {
            opcion = leerInt();
            if (!seleccionObligatoria && opcion == 0) return false;
            if(opcion == NOT_INT || opcion > entrenador.obtenerPokemones().size() || opcion == 0) {
                VistaJuego.imprimir("Seleccione una opción correcta!");
                continue;
            }
            Pokemon pokemonSeleccionado = entrenador.obtenerPokemones().get(opcion-1); // TODO: Esto esta medio mal
            if (pokemonSeleccionado.estaMuerto()) VistaJuego.imprimir("Ese Pokemon esta muerto!");
            else break; // Opcion correcta seleccionada
        }

        entrenador.cambiarPokemon(opcion-1);
        return true;
    }

    public boolean seleccionarHabilidad(){
        Pokemon pokemonActual = this.juego.obtenerEntrenadorActual().obtenerPokemonActual();
        VistaHabilidad.mostrarHabilidades(pokemonActual);
        int opcion;
        while (true) {
            opcion = leerInt();
            if (opcion > 0 && pokemonActual.obtenerHabilidades().get(opcion - 1).getUsos() <= 0) {
                VistaJuego.imprimir("Esta habilidad no tiene más usos");
                continue;
            }
            switch (opcion) {
                case 0: return false;
                case 1:
                    this.juego.atacar(0);
                    return true;
                case 2:
                    this.juego.atacar(1);
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

    public boolean seleccionarItem() {
        int opcion;
        Entrenador entrenadorActual = this.juego.obtenerEntrenadorActual();
        VistaItem.mostrarItems(entrenadorActual);

        while (true) {
            opcion = leerInt();
            if (opcion > 0 && entrenadorActual.obtenerItems().get(opcion - 1).obtenerCantidad() <= 0) {
                VistaJuego.imprimir("Esta habilidad no tiene más usos");
                continue;
            }
            if (opcion == 0) return false;
            if(opcion == NOT_INT || opcion > this.juego.obtenerEntrenadorActual().obtenerItems().size()) {
                VistaJuego.imprimir("Seleccione una opción correcta!");
            } else break; // Opcion correcta seleccionada
        }

        this.juego.usarItem(opcion - 1);
        return true;
    }

    public void cerrarScanner() { this.scanner.close(); }

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