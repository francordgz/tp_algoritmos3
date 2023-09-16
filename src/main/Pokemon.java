package src.main;

import java.util.*;

enum estados {
        NORMAL, Envenenado, Dormido, Paralizado, Muerto
    };


public class Pokemon {

    String Nombre;
    String historia;
    int vidaActual;
    int vidaMaxima;
    int velocidad;
    int defensa;
    String tipo;
    int ataque;
    estados estado;
    int nivel;
    List<Habilidad> habilidades = new ArrayList<Habilidad>();




    public Pokemon(String unNombre,String unTipo,int vidaMaxima,int defensa,int velocidad,int danio){
        this.Nombre = unNombre;
        this.tipo = unTipo;
        this.vidaMaxima = vidaMaxima;
        this.defensa = defensa;
        this.ataque = danio;
        this.velocidad = velocidad;
        this.vidaActual = vidaMaxima;
        this.estado = estados.NORMAL;

    }

    public int Vida(){
        return this.vidaActual;
    }


    public void recibirDanio(int danio){

        ///this.vida -= danio;

        //estado()


    }

    public int atacar(int nivelEnemigo,String tipoEnemigo){

        //Aca se tiene que hacer la formula de ataque para calcular el danio.
        return this.ataque;

    }


    public void UsarHabilidad(){



    }

    public void subirNivel(){

        this.nivel += 1;
    }

    public int obtenerNivel(){
        return nivel;
    }

    public int obtenerDefensa(){
        return defensa;
    }

    public int obtenerAtaque(){
        return ataque;
    }

   






    
}
