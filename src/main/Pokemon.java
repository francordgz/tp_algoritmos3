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
    Tipo tipo;
    int ataque;
    estados estado;
    int nivel;
    List<Habilidad> habilidades = new ArrayList<>();




    public Pokemon(String unNombre,Tipo unTipo,int vidaMaxima,int defensa,int velocidad,int danio, String historia, List<Habilidad> habilidades){
        this.Nombre = unNombre;
        this.tipo = unTipo;
        this.vidaMaxima = vidaMaxima;
        this.defensa = defensa;
        this.ataque = danio;
        this.velocidad = velocidad;
        this.vidaActual = vidaMaxima;
        this.estado = estados.NORMAL;
        this.historia = historia;
        this.habilidades = habilidades;
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

    public void atacar(int habilidad,Pokemon rival,double[][] efectividades){

        double efectividad = calcularEfectividad(rival,efectividades);
        
        habilidades.get(habilidad).atacar(ataque, nivel, rival, efectividad);

    }

    public Tipo tipo(){
        return this.tipo;
    }

    public double calcularEfectividad(Pokemon rival,double [][] efectividades){

        int posicionAtacante = this.tipo.ordinal();
        int PosicionRival = rival.tipo().ordinal();

        double efectividad = efectividades[posicionAtacante][PosicionRival];

        return efectividad;

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
