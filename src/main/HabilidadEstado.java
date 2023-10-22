package src.main;

import src.main.Enums.Estados;

public class HabilidadEstado extends Habilidad{

    private Estados tipo;

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
        }
        this.usos -= 1;
    }

}




