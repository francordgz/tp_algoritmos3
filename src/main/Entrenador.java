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

    public void usarItem(int item) {

        Scanner scan = new Scanner(System.in);

        for(int i = 0;i<this.pokemones.size();i++){
            System.out.println((i+1) + ": " + this.pokemones.get(i).nombre);
        }
        System.out.println("Seleccione en que Pokemon quiere aplicar el item: ");
        int posicion = scan.nextInt();
        this.items.get(item).usarItem(this.pokemones.get(posicion-1));
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
}
