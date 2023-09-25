package src.main;

import java.util.ArrayList;
import java.util.List;

public class Juego {

    Entrenador entrenador1;
    Entrenador entrenador2;
    Entrenador entrenadorActual;
    Entrenador entrenadorRival;
    administradorTurno administrador = new administradorTurno();
    Boolean terminado;
    double [][]efectividades = new double[15][15];

    public Juego(){

        this.crearEfectividades();
        this.crearPokemons();
        this.crearItems();
        this.terminado = false;
    }

    public void inicializarEntrenadores(String nombre1, String nombre2) {
        this.entrenador1 = new Entrenador(nombre1);
        this.entrenador2 = new Entrenador(nombre2);
        crearItems();
        crearPokemons();
    }

    public void crearItems(){

        List<Item> items = new ArrayList<Item>();

        items.add(new ItemCuracion(20, "Pocion", 3));
        items.add(new ItemCuracion(50, "MegaPocion", 2));
        items.add(new ItemCuracion(100, "Hiperpocion", 1));
        items.add(new ItemEstadistica("Ataque",tipoModificacion.ATAQUE , 2));
        items.add(new ItemEstadistica("Defensa", tipoModificacion.DEFENSA, 1));
        items.add(new ItemEstado("CuraTodo",3));
        items.add(new ItemRevivir("Revivir",1));

        entrenador1.addItem(items);
        entrenador2.addItem(items);
    }

    public void rendirse(){

        this.terminado = true;
    }


    public void UsarHabilidad(int habilidad){
        
        if(!entrenadorActual.obtenerPokemonActual().habilidades(habilidad).AfectarRival()){
           entrenadorActual.obtenerPokemonActual().UsarHabilidad(habilidad,entrenadorActual.obtenerPokemonActual());
        }else{

            entrenadorActual.obtenerPokemonActual().UsarHabilidad(habilidad,entrenadorRival.obtenerPokemonActual());

        }

    }


    public boolean terminado(){


        return this.terminado;

    }

    public void crearPokemons() {

        Pokedex pokedex = new Pokedex();

        entrenador1.addPokemon(pokedex.crearPokemon("Pikachu"));
        entrenador1.addPokemon(pokedex.crearPokemon("Bulbasur"));
        entrenador1.addPokemon(pokedex.crearPokemon("Venusar"));
        entrenador1.addPokemon(pokedex.crearPokemon("Charmander"));
        entrenador1.addPokemon(pokedex.crearPokemon("Charizard"));
        entrenador1.addPokemon(pokedex.crearPokemon("Squirtle"));

        entrenador2.addPokemon(pokedex.crearPokemon("Magikarp"));
        entrenador2.addPokemon(pokedex.crearPokemon("Raichu"));
        entrenador2.addPokemon(pokedex.crearPokemon("Kadabra"));
        entrenador2.addPokemon(pokedex.crearPokemon("Clefable"));
        entrenador2.addPokemon(pokedex.crearPokemon("Ekans"));
        entrenador2.addPokemon(pokedex.crearPokemon("Rattata"));
    }

    public void asignarPrimerTurno(){
        int velocidad1 = this.entrenador1.obtenerPokemonActual().obtenerVelocidad();
        int velocidad2 = this.entrenador2.obtenerPokemonActual().obtenerVelocidad();
        if (velocidad1 > velocidad2){
            //Todo: Trabajar en el administrador de turnos
            this.entrenadorActual = entrenador1;
            return;
        }
        this.entrenadorActual = entrenador2;
    }



    public void mostrarHabilidades(){

        entrenadorActual.mostrarHabilidades();


    }

    public void atacar(int habilidad){

        entrenadorActual.obtenerPokemonActual().atacar(habilidad,entrenadorRival.obtenerPokemonActual(),efectividades);

    }

    public  void crearEfectividades(){

        for(int i = 0;i<15;i++){
            for(int j = 0;j <15;j++){
                this.efectividades[i][j] = Constant.SIMPLE;

            }
        }

        //0s
        efectividades[3][12] = Constant.NULA;
        efectividades[4][8] = Constant.NULA;
        efectividades[4][10] = Constant.NULA;
        efectividades[7][4] = Constant.NULA;
        efectividades[8][4] = Constant.NULA;
        efectividades[12][14] = Constant.NULA;

        //1/2
        efectividades[0][0] = Constant.MEDIA;
        efectividades[0][2] = Constant.MEDIA;
        efectividades[0][9] = Constant.MEDIA;
        efectividades[1][4] = Constant.MEDIA;
        efectividades[1][5] = Constant.MEDIA;
        efectividades[1][7] = Constant.MEDIA;
        efectividades[1][14] = Constant.MEDIA;
        efectividades[3][2] = Constant.MEDIA;
        efectividades[3][3] = Constant.MEDIA;
        efectividades[3][9] = Constant.MEDIA;
        efectividades[5][0] = Constant.MEDIA;
        efectividades[5][2] = Constant.MEDIA;
        efectividades[5][5] = Constant.MEDIA;
        efectividades[5][11] = Constant.MEDIA;
        efectividades[6][0] = Constant.MEDIA;
        efectividades[6][6] = Constant.MEDIA;
        efectividades[7][1] = Constant.MEDIA;
        efectividades[7][10] = Constant.MEDIA;
        efectividades[7][13] = Constant.MEDIA;
        efectividades[7][14] = Constant.MEDIA;
        efectividades[8][11] = Constant.MEDIA;
        efectividades[9][1] = Constant.MEDIA;
        efectividades[9][2] = Constant.MEDIA;
        efectividades[9][5] = Constant.MEDIA;
        efectividades[9][9] = Constant.MEDIA;
        efectividades[9][13] = Constant.MEDIA;
        efectividades[9][14] = Constant.MEDIA;
        efectividades[10][10] = Constant.MEDIA;
        efectividades[11][7] = Constant.MEDIA;
        efectividades[11][12] = Constant.MEDIA;
        efectividades[12][1] = Constant.MEDIA;
        efectividades[12][9] = Constant.MEDIA;
        efectividades[13][3] = Constant.MEDIA;
        efectividades[13][11] = Constant.MEDIA;
        efectividades[13][12] = Constant.MEDIA;
        efectividades[13][13] = Constant.MEDIA;
        efectividades[14][3] = Constant.MEDIA;
        efectividades[14][11] = Constant.MEDIA;


        // x2

        for(int i = 0;i<15;i++){
            for(int j = 0;j<15;j++){
                if(efectividades[i][j] != Constant.NULA && efectividades[i][j] != 1 && efectividades[i][j] != Constant.MEDIA){
                    efectividades[i][j] = Constant.DOBLE;
                }

            }
        }


    }

    public void usarItem(int item){


        entrenadorActual.usarItem(item);

    }

    public void mostrarItems(){

        entrenadorActual.mostrarItems();



    }

    public Entrenador obtenerEntrenadorActual() {
        return entrenadorActual;
    }

    public Entrenador obtenerEntrenadorRival() {
        return entrenadorRival;
    }
}


