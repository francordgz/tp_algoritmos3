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



    public double MismoTipo(){

        if (mismoTipo == true){
            return 1.5;
        }

        return 1;

    }



    /// Que calcule el pokenon el ataque ///

    @Override
    public void atacar(int ataque,int nivel,Pokemon rival,int efectividad){

        int critico = generarProba();

        int numeroRandom = generarNumeroRandom();

        double danio = (((((2*nivel*critico*this.poder*ataque)/rival.obtenerDefensa()*5)+2)/50)*(MismoTipo()*efectividad*numeroRandom));

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
