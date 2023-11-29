package BatallaPokemon.Modelo.Habilidad;

import BatallaPokemon.Modelo.Enums.Atributos;
import BatallaPokemon.Modelo.Pokemon;

public class HabilidadEstadistica extends Habilidad{
    private final int poder;
    private final Atributos atributo;
    private final Boolean afectarRival;

    public HabilidadEstadistica(String nombre, Atributos atributo, int usos, int id, int poder, Boolean afectarRival) {
        super(nombre,usos, id);
        this.poder = poder;
        this.atributo = atributo;
        this.afectarRival = afectarRival;
    }

    public void modificarEstado(Pokemon pokemon) {
        if(atributo == Atributos.VIDA) {
            pokemon.curar(poder);
        } else if(atributo == Atributos.ATAQUE) {
            pokemon.modificarAtaque(poder);
        } else if(atributo == Atributos.VELOCIDAD) {
            pokemon.modificarVelocidad(poder);
        } else if(atributo == Atributos.DEFENSA) {
            pokemon.modificarDefensa(poder);
        }
        this.usos -=1;
    }

    public Boolean afectaRival() {
        return afectarRival;
    }

    @Override
    public String getTipoHabilidad() {
        return "estadistica";
    }
}
