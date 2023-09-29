package src.main;
import java.util.*;

public class Entrenador {
    String nombre;
    ArrayList<Item> items;
    ArrayList<Pokemon> pokemones;
    Pokemon pokemonActual;

    public Entrenador(String nombre) {
        this.nombre = nombre;
        this.pokemones = new ArrayList<Pokemon>();
        this.items = new ArrayList<Item>();
    }

    public String obtenerNombre() {
        return nombre;
    }

    public List<Item> obtenerItems() {
        return items;
    }

    public List<Pokemon> obtenerPokemones() {
        return pokemones;
    }

    public void agregarPokemon(Pokemon pokemon) {
        this.pokemones.add(pokemon);
    }

    public void agregarItem(Item items){
        this.items.add(items);
    }

    public Pokemon obtenerPokemonActual(){
        return pokemonActual;
    }

    public void usarItem(int item){
        this.items.get(item).usarItem(pokemonActual);
    }

    public void cambiarPokemon(int posicion){
        this.pokemonActual = this.pokemones.get(posicion);
    }

}
