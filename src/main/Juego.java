package src.main;

import java.util.ArrayList;
import java.util.List;

public class Juego {



    Entrenador entrenador1;
    Entrenador entrenador2;
    Entrenador entrenadorActual = null;
    administradorTurno administrador = new administradorTurno();
    Boolean terminado;

    public Juego(){

        this.crearPokemons();
        this.crearHabilidades();
        this.crearItems();
        this.terminado = false;
    }


    public void crearPokemons(){

    List<Pokemon> pokemones1 = new ArrayList<Pokemon>();
    List<Pokemon> pokemones2 = new ArrayList<Pokemon>();

    pokemones1.add(new Pokemon("Pikachu","Rayo",100,30,25,15));
    pokemones1.add(new Pokemon("Bulbasur","Planta",120,40,10,10));
    pokemones1.add(new Pokemon("Venusar","Veneno",70,100,20,12));
    pokemones1.add(new Pokemon("Charmander","Fuego",100,50,35,25));
    pokemones1.add(new Pokemon("Charizard","Dragon",120,25,15,50));
    pokemones1.add(new Pokemon("Squirtle","Agua",150,40,18,17));

    pokemones2.add(new Pokemon("Magikarp","Agua",10,10,10,15));
    pokemones2.add(new Pokemon("Raichu","Rayo",150,30,25,15));
    pokemones2.add(new Pokemon("Kadabra","Psiquico",140,35,30,20));
    pokemones2.add(new Pokemon("Clefable","Fantasma",30,30,20,15));
    pokemones2.add(new Pokemon("Ekans","Veneno",120,35,30,20));
    pokemones2.add(new Pokemon("Rattata","Bicho",100,40,35,45));

    entrenador1.addPokemon(pokemones1);
    entrenador2.addPokemon(pokemones2);



    }

    public void crearHabilidades(){

    }

    public void crearItems(){

        List<Item> items = new ArrayList<Item>();

        items.add(new ItemCuracion(20, "Pocion", 3));
        items.add(new ItemCuracion(50, "MegaPocion", 2));
        items.add(new ItemCuracion(100, "Hiperpocion", 1));
        items.add(new ItemEstadistica("Ataque",tipoModificacion.ATAQUE , 2));
        items.add(new ItemEstadistica("Defensa", tipoModificacion.DEFENSA, 1));
        items.add(new ItemEstado("Estado",3));
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




    
}
