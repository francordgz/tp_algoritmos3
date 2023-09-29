package src.main;

import src.main.Enums.Atributos;

public class HabilidadEstadistica extends Habilidad{

    int poder;
    Atributos atributo;
    Boolean AfectarRival;

    public HabilidadEstadistica(String nombre, Atributos atributo, int usos, int poder, Boolean AfectarRival) {
        super(nombre,usos);
        this.poder = poder;
        this.atributo = atributo;
        this.AfectarRival = AfectarRival;
    }


    @Override
    public void ModificarEstado(Pokemon pokemon){

        if(atributo == Atributos.VIDA){
            pokemon.curar(poder);
        }else if(atributo == Atributos.ATAQUE){
            pokemon.modificarAtaque(poder);
        } else if(atributo == Atributos.VELOCIDAD){
            pokemon.modificarVelocidad(poder);
        }else if(atributo == Atributos.DEFENSA)
            pokemon.modificarDefensa(poder);

        this.usos -=1;


    }

    @Override
    public Boolean AfectarRival(){
        return AfectarRival;
    }




}
