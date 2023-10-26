package src.main;
import src.main.Item.Item;

import java.util.*;

public class Entrenador {
    private final String nombre;
    private final ArrayList<Pokemon> pokemones;
    private Pokemon pokemonActual;
    private final ArrayList<Item> items;

    public Entrenador(String nombre) {
        this.nombre = nombre;
        this.pokemones = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    public String obtenerNombre() {
        return nombre;
    }

    public List<Pokemon> obtenerPokemones() {
        return pokemones;
    }

    public Pokemon obtenerPokemonActual(){
        return pokemonActual;
    }

    public List<Item> obtenerItems() {
        return items;
    }

    public void agregarPokemon(Pokemon pokemon) {
        this.pokemones.add(pokemon);
    }

    public void agregarItem(Item items){
        this.items.add(items);
    }

    public void cambiarPokemon(int posicion){
        this.pokemonActual = this.pokemones.get(posicion);
    }

    public void usarItem(int item, int indicePokemon) {
        this.items.get(item).usarItem(pokemones.get(indicePokemon));
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
