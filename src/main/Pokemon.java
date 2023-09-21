package src.main;

import java.util.*;

enum estados {
        NORMAL, Envenenado, Dormido, Paralizado, Muerto
}


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


    public void recibirDanio(double danio){

        this.vidaActual -= danio;

        estado();


    }

    public void estado(){

        if (vidaActual <= 0){
            estado = estados.Muerto;
        }

    }


    public void UsarHabilidad(int Numerohabilidad){



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

    public void modificarAtaque(int poder){
        this.ataque += poder;

    }

    public void modificarVida(int poder){
        this.vidaActual += poder;
    }

    public void modificarDefensa(int poder){
        this.defensa += poder;
    }

    public void modificarVelocidad(int poder){
        this.velocidad += poder;
    }


    public int obtenerVidaMaxima() {
        return vidaMaxima;
    }

    public void modificarEstado(estados estado) {
        this.estado = estado;
    }
}
