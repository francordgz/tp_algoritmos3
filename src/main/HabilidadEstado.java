package src.main;

public class HabilidadEstado extends Habilidad{

    estados tipo;

    public HabilidadEstado(String nombre, int usos, estados tipo){
        super(nombre,usos);
        this.tipo = tipo;
    }


    @Override
    public void ModificarEstado(Pokemon pokemon){

        if(tipo == estados.ENVENENADO){
            pokemon.modificarEstado(estados.ENVENENADO);
        }else if(tipo == estados.DORMIDO){
            pokemon.modificarEstado(estados.DORMIDO);
        } else if(tipo == estados.PARALIZADO){
            pokemon.modificarEstado(estados.PARALIZADO);
        }

        this.usos -= 1;

    }

}




