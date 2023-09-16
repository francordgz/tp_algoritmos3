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

        int critico = generarProba();

        int efectividad = buscarEfectividad(atacante,rival);
        
        int numeroRandom = generarNumeroRandom();


        int danio = (((((2*atacante.obtenerNivel()*critico*this.poder*atacante.obtenerAtaque())/rival.obtenerDefensa()*5)+2)/50)*MismoTipo*efectividad*numeroRandom);




    }



    public int generarNumeroRandom(){

        Random rand = new Random();

        int numeroAleatorio = (rand.nextInt(38) + 217) / 255;      

        return numeroAleatorio;



    }


    public int generarProba(){

        
        int probabilidad = 1;

        Random rand = new Random();

        int numeroAleatorio = rand.nextInt(100);



        if(numeroAleatorio <= 90){

            probabilidad = 2;


        };


        return probabilidad;


    }





    
}

