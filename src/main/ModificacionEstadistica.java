package src.main;

enum Atributos {
    VIDA, ATAQUE, DEFENSA, VELOCIDAD
}


public class ModificacionEstadistica extends Habilidad{

    int poder;
    Atributos atributo;





    public ModificacionEstadistica(String nombre, int usos, int poder, Atributos atributo){
        super(nombre,usos);
        this.poder= poder;
        this.atributo = atributo;
    }




    public void ModificacarEstadistica(){



        
    }



    
}