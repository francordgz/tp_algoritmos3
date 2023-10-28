package src.main.Habilidad;

import src.main.Enums.Atributos;
import src.main.Pokemon;

public class HabilidadEstadistica extends Habilidad{
    private final int poder;
    private final Atributos atributo;

    public HabilidadEstadistica(String nombre, Atributos atributo, int usos, int poder) {
        super(nombre,usos);
        this.poder = poder;
        this.atributo = atributo;
    }

    @Override
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
}
