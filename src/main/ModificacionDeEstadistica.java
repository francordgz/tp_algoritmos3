package src.main;

public class ModificacionDeEstadistica extends Habilidad{

    int poder;
    estados estado;

    
    
    public ModificacionDeEstadistica(String nombre, int usos, int poder,estados estado){
        super(nombre,usos);
        this.poder = poder;
        this.estado = estado;


    }

    public void modificarEstado(Pokemon pokemon){

        if(estado == estados.VIDA){
            pokemon.modificarVida(poder);
        }else if(estado == estados.ATAQUE){
            pokemon.modificarAtaque(poder);
        } else if(estado == estados.VELOCIDAD){
            pokemon.modificarVelocidad(poder);
        }else if(estado == estados.DEFENSA)
            pokemon.modificarDefensa(poder);


    } 



    

    
}
