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
        
        if(!entrenadorActual.pokemonActual().habilidades(habilidad).AfectarRival()){
           entrenadorActual.pokemonActual().UsarHabilidad(habilidad,entrenadorActual.pokemonActual()); 
        }else{

            entrenadorActual.pokemonActual().UsarHabilidad(habilidad,entrenadorRival.pokemonActual());

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



    public void mostrarHabilidades(){

        entrenadorActual.mostrarHabilidades();


    }

    public void atacar(int habilidad){

        entrenadorActual.pokemonActual().atacar(habilidad,entrenadorRival.pokemonActual(),efectividades);

    }

    public  void crearEfectividades(){

        for(int i = 0;i<15;i++){
            for(int j = 0;j <15;j++){
                this.efectividades[i][j] = 1;

            }
        }

        //0s
        efectividades[3][12] = 0;
        efectividades[4][8] = 0;
        efectividades[4][10] = 0;
        efectividades[7][4] = 0;
        efectividades[8][4] = 0;
        efectividades[12][14] = 0;

        //1/2
        efectividades[0][0] = 1/2;
        efectividades[0][2] = 1/2;
        efectividades[0][9] = 1/2;
        efectividades[1][4] = 1/2;
        efectividades[1][5] = 1/2;
        efectividades[1][7] = 1/2;
        efectividades[1][14] = 1/2;
        efectividades[3][2] = 1/2;
        efectividades[3][3] = 1/2;
        efectividades[3][9] = 1/2;
        efectividades[5][0] = 1/2;
        efectividades[5][2] = 1/2;
        efectividades[5][5] = 1/2;
        efectividades[5][11] = 1/2;
        efectividades[6][0] = 1/2;
        efectividades[6][6] = 1/2;
        efectividades[7][1] = 1/2;
        efectividades[7][10] = 1/2;
        efectividades[7][13] = 1/2;
        efectividades[7][14] = 1/2;
        efectividades[8][11] = 1/2;
        efectividades[9][1] = 1/2;
        efectividades[9][2] = 1/2;
        efectividades[9][5] = 1/2;
        efectividades[9][9] = 1/2;
        efectividades[9][13] = 1/2;
        efectividades[9][14] = 1/2;
        efectividades[10][10] = 1/2;
        efectividades[11][7] = 1/2;
        efectividades[11][12] = 1/2;
        efectividades[12][1] = 1/2;
        efectividades[12][9] = 1/2;
        efectividades[13][3] = 1/2;
        efectividades[13][11] = 1/2;
        efectividades[13][12] = 1/2;
        efectividades[13][13] = 1/2;
        efectividades[14][3] = 1/2;
        efectividades[14][11] = 1/2;


        // x2

        for(int i = 0;i<15;i++){
            for(int j = 0;j<15;j++){
                if(efectividades[i][j] != 0 && efectividades[i][j] != 1 && efectividades[i][j] != 1/2){
                    efectividades[i][j] = 2;
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



}


