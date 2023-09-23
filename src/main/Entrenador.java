package src.main;
import java.util.*;

public class Entrenador {
    String nombre;
    List<Item> items;
    List<Pokemon> pokemones;

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

    public List<Pokemon> getPokemones() {
        return pokemones;
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemones.add(pokemon);
    }

    public void addItem(List<Item> items){
        this.items = items;
    }

    public void usarItem(){



    }

}