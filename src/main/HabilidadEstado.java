package src.main;

enum estado {
    ENVENENAR, PARALIZAR, DORMIR
}
public class HabilidadEstado extends Habilidad{

    estado tipo;

    public HabilidadEstado(String nombre, int usos, estado tipo){
        super(nombre,usos);
        this.tipo = tipo;
    }


    @Override
    public void ModificarEstado(Pokemon pokemon){

        if(tipo == estado.ENVENENAR){
            pokemon.modificarEstado(estados.Envenenado);
        }else if(tipo == estado.DORMIR){
            pokemon.modificarEstado(estados.Dormido);
        } else if(tipo == estado.PARALIZAR){
            pokemon.modificarEstado(estados.Paralizado);
        }

        this.usos -= 1;

    }

}




