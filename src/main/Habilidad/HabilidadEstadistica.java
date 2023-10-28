package src.main.Habilidad;

import src.main.Enums.Atributos;
import src.main.Pokemon;

public class HabilidadEstadistica extends Habilidad{
    private final int poder;
    private final Atributos atributo;
    private final Boolean afectarRival;

    public HabilidadEstadistica(String nombre, Atributos atributo, int usos, int poder, Boolean afectarRival) {
        super(nombre,usos);
        this.poder = poder;
        this.atributo = atributo;
        this.afectarRival = afectarRival;
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

    @Override
    public Boolean AfectarRival() {
        return afectarRival;
    }
}
