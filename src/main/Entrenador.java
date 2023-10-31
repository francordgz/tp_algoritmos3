package src.main;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import src.main.Enums.Estados;
import src.main.Item.Item;

import java.util.*;

public class Entrenador {
    private final String nombre;
    private final ArrayList<Pokemon> pokemones;
    private Pokemon pokemonActual;
    private final ArrayList<Item> items;

    boolean ganador;

    @JsonCreator
    public Entrenador(@JsonProperty("nombre") String nombre) {
        this.nombre = nombre;
        this.pokemones = new ArrayList<>();
        this.items = new ArrayList<>();
        this.ganador = false;
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

    public String cambiarPokemon(int posicion){
        this.pokemonActual = this.pokemones.get(posicion);
        return this.pokemonActual.obtenerNombre();
    }

    public Boolean pokemonActualTieneEstado(Estados estado) {
        return this.pokemonActual.tieneEstado(estado);
    }

    public boolean tienePokemonesConVida() {
        for (Pokemon pokemon: this.pokemones) {
            if (!pokemon.estaMuerto())
                return true;
        }
        return false;
    }

    public Boolean validarHabilidad(int opcion) {
        return this.pokemonActual.validarHabilidad(opcion);
    }

    public void usarItem(int item, int indicePokemon) {
        this.items.get(item).usarItem(pokemones.get(indicePokemon));
    }

    public Integer obtenerCantidadDeItems() {
        return this.obtenerItems().size();
    }

    public Boolean validarItem(Integer opcion) {
        Item item = this.items.get(opcion);
        return item.obtenerCantidad() > 0;
    }

    public Boolean puedeAplicarItem(int opcion, Integer indicePokemon) {
        Item item = this.items.get(opcion);
        Pokemon pokemon = this.pokemones.get(indicePokemon);
        return item.esAplicable(pokemon);
    }

    public void marcarComoGanador() {
        this.ganador = true;
    }

    public boolean esGanador() {
        return this.ganador;

    public Integer obtenerCantidadDePokemones() {
        return this.pokemones.size();
    }

    public Boolean pokemonEstaMuerto(Integer opcion) {
        Pokemon pokemon = this.pokemones.get(opcion);
        return pokemon.tieneEstado(Estados.MUERTO);
    }

    public Boolean validarPokemon(Integer opcion, Pokemon pokemonActual) {
        Pokemon pokemon = this.pokemones.get(opcion);
        String nombrePokemonActual = pokemonActual.obtenerNombre();
        return nombrePokemonActual.equals(pokemon.obtenerNombre());
    }
}
