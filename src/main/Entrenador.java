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

    public String obtenerNombre() {
        return nombre;
    }

    public List<Item> obtenerItems() {
        return items;
    }

    public List<Pokemon> obtenerPokemones() {
        return pokemones;
    }

    public Pokemon obtenerPokemonActual(){
        return pokemonActual;
    }

    public void agregarPokemon(Pokemon pokemon) {
        this.pokemones.add(pokemon);
    }

    public void agregarItem(List<Item> items){
        this.items = items;
    }

    public void cambiarPokemon(int posicion){
        this.pokemonActual = this.pokemones.get(posicion);
    }

    public void usarItem(int item){
        this.items.get(item).usarItem(pokemonActual);
    }

}
