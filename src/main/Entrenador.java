package src.main;
import java.util.*;

public class Entrenador {
    String nombre;
    List<Item> items;
    List<Pokemon> pokemones;

    // Constructor
    public Entrenador(String nombre, List<Item> items, List<Pokemon> pokemones) {
        this.nombre = nombre;
        this.items = items;
        this.pokemones = pokemones;
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

    public void addPokemon(List<Pokemon> pokemones) {
        this.pokemones = pokemones;
    }

}