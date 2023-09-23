package src.main;
import java.util.*;

public class Entrenador {
    String nombre;
    List<Item> items;
    List<Pokemon> pokemones;
    Pokemon pokemonActual;

    // Constructor
    public Entrenador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Item> getItems() {
        return items;
    }

    public void agregarItem(Item item) {
        this.items.add(item);
    }

    ///public Item usarItem(Item item) {
        ///this.items.remover(item);
    ////}

    public List<Pokemon> obtenerPokemones() {
        return pokemones;
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemones.add(pokemon);
    }

    public void addItem(List<Item> items){
        this.items = items;
    }

    public void usarItem() {



    }


    //ToDo: Esto tiene que ir en vistas;

    public void mostrarHabilidades(){

        System.out.println("1 - > " + pokemonActual.habilidades.get(0).nombre());
        System.out.println("2 - > " + pokemonActual.habilidades.get(1).nombre());
        System.out.println("3 - > " + pokemonActual.habilidades.get(2).nombre());
        System.out.println("4 - > " + pokemonActual.habilidades.get(3).nombre());


    }


    public Pokemon obtenerPokemonActual(){

        return pokemonActual;

    }

    public void usarItem(int item){

        this.items.get(item).usarItem(pokemonActual);

    }

    public void mostrarItems(){

        for(int i = 0;i<items.size();i++){
            
            System.out.println(i + items.get(i).obtenerNombre());
        

        }


    }

    public void cambiarPokemon(int posicion){
        this.pokemonActual = this.pokemones.get(posicion);
    }


}
