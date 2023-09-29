package src.main;

import src.main.Enums.Estados;

public class HabilidadEstado extends Habilidad{

    Estados tipo;

    public HabilidadEstado(String nombre, int usos, Estados tipo){
        super(nombre,usos);
        this.tipo = tipo;
    }


    @Override
    public void ModificarEstado(Pokemon pokemon){

        if(tipo == Estados.ENVENENADO){
            pokemon.modificarEstado(Estados.ENVENENADO);
        }else if(tipo == Estados.DORMIDO){
            pokemon.modificarEstado(Estados.DORMIDO);
        } else if(tipo == Estados.PARALIZADO){
            pokemon.modificarEstado(Estados.PARALIZADO);
        }

        this.usos -= 1;

    }

}




