package src.main;

import java.util.Random;


public class Ataque extends Habilidad {

    int poder;
    double MismoTipo;
    





    public Ataque(String nombre, int usos, int poder,Boolean Mismotipo){
        super(nombre,usos);
        this.poder = poder;

        if(Mismotipo == true){
            this.MismoTipo = 1.5;
        }else{
            this.MismoTipo = 1;
        
        }

    }



    public void atacar(Pokemon atacante,Pokemon rival){


        int critico = 




        int danio = 2*atacante.obtenerNivel()*




    }





    
}

