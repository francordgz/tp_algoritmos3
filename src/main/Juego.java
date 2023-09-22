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

        List<Habilidad> habilidades1 = new ArrayList<Habilidad>();
        habilidades1.add(new HabilidadAtaque("Golpe", "Normal", 3, 10, false));
        habilidades1.add(new HabilidadAtaque("Impactrueno", "Rayo", 1, 120, true));
        habilidades1.add(new HabilidadEstadistica("Aumentar ataque", atributos.ATAQUE, 2, 10));
        habilidades1.add(new HabilidadEstado("Paralizar",2,estado.PARALIZAR));

        List<Habilidad> habilidades2 = new ArrayList<Habilidad>();
        habilidades2.add(new HabilidadAtaque("Golpe", "Normal", 3, 10, false));
        habilidades2.add(new HabilidadAtaque("Latigo cepa", "Planta", 2, 90, true));
        habilidades2.add(new HabilidadEstadistica("Aumentar ataque", atributos.ATAQUE, 2, 10));
        habilidades2.add(new HabilidadEstado("Envenenar", 2, estado.ENVENENAR));

        List<Habilidad> habilidades3 = new ArrayList<Habilidad>();
        habilidades3.add(new HabilidadAtaque("Golpe", "Normal", 5, 25, false));
        habilidades3.add(new HabilidadAtaque("Latigo cepa", "Planta", 3, 150, true));
        habilidades3.add(new HabilidadEstadistica("Aumentar ataque", atributos.ATAQUE, 5, 15));
        habilidades3.add(new HabilidadEstado("Envenenar", 5, estado.ENVENENAR));

        List<Habilidad> habilidades4 = new ArrayList<Habilidad>();
        habilidades4.add(new HabilidadAtaque("Golpe", "Normal", 3, 10, false));
        habilidades4.add(new HabilidadAtaque("Lanzallamas", "Fuego", 2, 100, true));
        habilidades4.add(new HabilidadEstadistica("Aumentar ataque", atributos.ATAQUE, 2, 10));
        habilidades4.add(new HabilidadEstado("Paralizar", 2, estado.PARALIZAR));

        List<Habilidad> habilidades5 = new ArrayList<Habilidad>();
        habilidades5.add(new HabilidadAtaque("Golpe", "Normal", 3, 10, false));
        habilidades5.add(new HabilidadAtaque("Lanzallamas", "Fuego", 4, 125, true));
        habilidades5.add(new HabilidadEstadistica("Aumentar ataque", atributos.ATAQUE, 2, 10));
        habilidades5.add(new HabilidadEstado("Paralizar", 4, estado.PARALIZAR));

        List<Habilidad> habilidades6 = new ArrayList<Habilidad>();
        habilidades6.add(new HabilidadAtaque("Golpe", "Normal", 3, 10, false));
        habilidades6.add(new HabilidadAtaque("Giro rapido", "Lucha", 1, 100, false));
        habilidades6.add(new HabilidadEstadistica("Aumentar ataque",atributos.ATAQUE,2,10));
        habilidades6.add(new HabilidadEstado("Dormir", 1, estado.DORMIR));

        pokemones1.get(0).cargarHabilidades(habilidades1);
        pokemones1.get(1).cargarHabilidades(habilidades2);
        pokemones1.get(2).cargarHabilidades(habilidades3);
        pokemones1.get(3).cargarHabilidades(habilidades4);
        pokemones1.get(4).cargarHabilidades(habilidades5);
        pokemones1.get(5).cargarHabilidades(habilidades6);

        List<Habilidad> habilidades7 = new ArrayList<Habilidad>();
        habilidades7.add(new HabilidadAtaque("Golpe", "Normal", 3, 10, false));
        habilidades7.add(new HabilidadAtaque("Impactrueno", "Rayo", 1, 120, true));
        habilidades7.add(new HabilidadEstadistica("Aumentar ataque", atributos.ATAQUE, 2, 10));
        habilidades7.add(new HabilidadEstado("Paralizar",2,estado.PARALIZAR));

        List<Habilidad> habilidades8 = new ArrayList<Habilidad>();
        habilidades8.add(new HabilidadAtaque("Golpe", "Normal", 3, 10, false));
        habilidades8.add(new HabilidadAtaque("Latigo cepa", "Planta", 2, 90, true));
        habilidades8.add(new HabilidadEstadistica("Aumentar ataque", atributos.ATAQUE, 2, 10));
        habilidades8.add(new HabilidadEstado("Envenenar", 2, estado.ENVENENAR));

        List<Habilidad> habilidades9 = new ArrayList<Habilidad>();
        habilidades9.add(new HabilidadAtaque("Golpe", "Normal", 5, 25, false));
        habilidades9.add(new HabilidadAtaque("Latigo cepa", "Planta", 3, 150, true));
        habilidades9.add(new HabilidadEstadistica("Aumentar ataque", atributos.ATAQUE, 5, 15));
        habilidades9.add(new HabilidadEstado("Envenenar", 5, estado.ENVENENAR));

        List<Habilidad> habilidades10 = new ArrayList<Habilidad>();
        habilidades10.add(new HabilidadAtaque("Golpe", "Normal", 3, 10, false));
        habilidades10.add(new HabilidadAtaque("Lanzallamas", "Fuego", 2, 100, true));
        habilidades10.add(new HabilidadEstadistica("Aumentar ataque", atributos.ATAQUE, 2, 10));
        habilidades10.add(new HabilidadEstado("Paralizar", 2, estado.PARALIZAR));

        List<Habilidad> habilidades11 = new ArrayList<Habilidad>();
        habilidades11.add(new HabilidadAtaque("Golpe", "Normal", 3, 10, false));
        habilidades11.add(new HabilidadAtaque("Lanzallamas", "Fuego", 4, 125, true));
        habilidades11.add(new HabilidadEstadistica("Aumentar ataque", atributos.ATAQUE, 2, 10));
        habilidades11.add(new HabilidadEstado("Paralizar", 4, estado.PARALIZAR));

        List<Habilidad> habilidades12 = new ArrayList<Habilidad>();
        habilidades12.add(new HabilidadAtaque("Golpe", "Normal", 3, 10, false));
        habilidades12.add(new HabilidadAtaque("Giro rapido", "Lucha", 1, 100, false));
        habilidades12.add(new HabilidadEstadistica("Aumentar ataque",atributos.ATAQUE,2,10));
        habilidades12.add(new HabilidadEstado("Dormir", 1, estado.DORMIR));


        pokemones2.get(0).cargarHabilidades(habilidades7);
        pokemones2.get(1).cargarHabilidades(habilidades8);
        pokemones2.get(2).cargarHabilidades(habilidades9);
        pokemones2.get(3).cargarHabilidades(habilidades10);
        pokemones2.get(4).cargarHabilidades(habilidades11);
        pokemones2.get(5).cargarHabilidades(habilidades12);

        entrenador1.addPokemon(pokemones1);
        entrenador2.addPokemon(pokemones2);

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




    
}
