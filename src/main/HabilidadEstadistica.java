package src.main;

enum atributos {
    VIDA, ATAQUE, VELOCIDAD, DEFENSA
}
public class HabilidadEstadistica extends Habilidad{

    int poder;
    atributos atributo;
    Boolean AfectarRival;

    public HabilidadEstadistica(String nombre, atributos atributo, int usos, int poder,Boolean AfectarRival) {
        super(nombre,usos);
        this.poder = poder;
        this.atributo = atributo;
        this.AfectarRival = AfectarRival;
    }


    @Override
    public void ModificarEstado(Pokemon pokemon){

        if(atributo == atributos.VIDA){
            pokemon.modificarVida(poder);
        }else if(atributo == atributos.ATAQUE){
            pokemon.modificarAtaque(poder);
        } else if(atributo == atributos.VELOCIDAD){
            pokemon.modificarVelocidad(poder);
        }else if(atributo == atributos.DEFENSA)
            pokemon.modificarDefensa(poder);

        this.usos -=1;


    }

    @Override
    public Boolean AfectarRival(){
        return AfectarRival;
    }




}
