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

        entrenadorActual.pokemonActual().atacar(habilidad,entrenadorRival.pokemonActual());

    }

    public  void crearEfectividades(){

        for(int i = 0;i<15;i++){
            for(int j = 0;j <15;j++){
                this.efectividades[i][j] = 1;

            }
        }




    }



}


