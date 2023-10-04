package src.main;
import src.main.Item.Item;

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

    public void usarItem(int item, int indicePokemon) {
        this.items.get(item).usarItem(pokemones.get(indicePokemon));
    }

    public void cambiarPokemon(int posicion){
        this.pokemonActual = this.pokemones.get(posicion);
    }

    public boolean tienePokemonesConVida() {
        for (Pokemon pokemon: this.pokemones) {
            if (!pokemon.estaMuerto())
                return true;
        }
        return false;
    }

    public boolean puedeAplicarItem(int indecePokemon, int item) {
        Item itemSeleccionado = items.get(item);
        return itemSeleccionado.esAplicable(pokemones.get(indecePokemon));
    }
}
