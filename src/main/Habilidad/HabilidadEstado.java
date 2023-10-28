package src.main.Habilidad;

import src.main.Enums.Estados;
import src.main.Pokemon;

public class HabilidadEstado extends Habilidad{
    private final Estados tipo;

    public HabilidadEstado(String nombre, int usos, Estados tipo) {
        super(nombre,usos);
        this.tipo = tipo;
    }

    @Override
    public void modificarEstado(Pokemon pokemon) {
        if(tipo == Estados.ENVENENADO) {
            pokemon.agregarEstado(Estados.ENVENENADO);
        } else if(tipo == Estados.DORMIDO) {
            pokemon.agregarEstado(Estados.DORMIDO);
        } else if(tipo == Estados.PARALIZADO) {
            pokemon.agregarEstado(Estados.PARALIZADO);
        } else if (tipo == Estados.CONFUSO) {
            pokemon.agregarEstado(Estados.CONFUSO);
        }
        this.usos -= 1;
    }

}




