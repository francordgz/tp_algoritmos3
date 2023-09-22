package src.main;
import java.util.Random;
public class HabilidadAtaque extends Habilidad{

    String tipo;
    int poder;
    boolean mismoTipo;

    public HabilidadAtaque(String nombre, String tipo, int usos, int poder, boolean mismoTipo) {
        super(nombre,usos);
        this.tipo = tipo;
        this.poder = poder;
        this.mismoTipo = mismoTipo;
    }
}


    public double MismoTipo(Pokemon pokemon1,Pokemon pokemon2){

        if (pokemon1.tipo == pokemon2.tipo){
            return 1.5;
        }

        return 1;

    }



    /// Que calcule el pokenon el ataque ///

    public void Atacar(Pokemon atacante,Pokemon rival,int efectividad){

        int critico = generarProba();

        int numeroRandom = generarNumeroRandom();

        double danio = (((((2*atacante.obtenerNivel()*critico*this.poder*atacante.obtenerAtaque())/rival.obtenerDefensa()*5)+2)/50)*(MismoTipo(atacante,rival))*efectividad*numeroRandom);

        rival.recibirDanio(danio);

        this.usos -= 1;


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