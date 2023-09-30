package src.main;
import src.main.Enums.Estados;
import src.main.Enums.Tipo;

import java.util.List;

public class Pokemon {

    String nombre;
    String historia;
    double vidaActual;
    int vidaMaxima;
    int velocidad;
    int defensa;
    Tipo tipo;
    int ataque;
    Estados estado;
    int nivel;
    List<Habilidad> habilidades;

    int turnosDormido;

    public Pokemon(String nombre,Tipo tipo,int vidaMaxima,int defensa,int velocidad,int danio, String historia, List<Habilidad> habilidades,int nivel){
        this.nombre = nombre;
        this.tipo = tipo;
        this.vidaMaxima = vidaMaxima;
        this.defensa = defensa;
        this.ataque = danio;
        this.velocidad = velocidad;
        this.vidaActual = vidaMaxima;
        this.estado = Estados.NORMAL;
        this.historia = historia;
        this.habilidades = habilidades;
        this.nivel = nivel;
        this.turnosDormido = 0;
    }


    public void recibirDanio(double danio){
        this.vidaActual -= danio;

        if (this.vidaActual < 0) {
            this.vidaActual = 0;
        }

        if (this.vidaActual == 0){
            this.estado = Estados.MUERTO;
        }
    }

    // Devuelve la efectividad para mostrar si es efectivo o no
    public double atacar(int habilidad, Pokemon rival, double[][] efectividades){

        double efectividad = calcularEfectividad(rival,efectividades);
        
        habilidades.get(habilidad).atacar(ataque, nivel, rival, efectividad);

        return efectividad;
    }

    public double calcularEfectividad(Pokemon rival,double [][] efectividades){

        int posicionAtacante = this.tipo.ordinal();
        int PosicionRival = rival.obtenerTipo().ordinal();

        return efectividades[posicionAtacante][PosicionRival];
    }


    public void UsarHabilidad(int Numerohabilidad,Pokemon rival){

        habilidades.get(Numerohabilidad).modificarEstado(rival);

    }

    /* TODO:
        El Pokemon puede despertarse en cada turno con probabilidad 0.25 + N * 0.25
        siendo N la cantidad de turnos que ya perdio estando dormido. Al
        cuarto se despertaria seguro.*/
    public void actualizarEstado() {
        if (this.estado == Estados.DORMIDO) {
                if (turnosDormido >= 4) {
                    this.estado = Estados.NORMAL;
                }
        } else if (this.estado == Estados.ENVENENADO) {
            this.recibirDanio(this.vidaMaxima * 0.5);
        }
    }

    public String obtenerNombre(){
        return nombre;
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

    public int obtenerVelocidad(){
        return velocidad;
    }

    public Tipo obtenerTipo(){
        return this.tipo;
    }

    public Estados obtenerEstado() {
        return estado;
    }

    public void modificarAtaque(int poder){
        this.ataque += poder;
    }

    public void curar(int poder){
        this.vidaActual += poder;
    }

    public void revivir() {
        this.estado = Estados.NORMAL;
        this.vidaActual = this.vidaMaxima;
    }

    public int obtenerVidaActual() {
        return (int)vidaActual;
    }

    public void modificarDefensa(int poder){
        this.defensa += poder;
    }

    public void modificarVelocidad(int poder){
        this.velocidad += poder;
    }

    public void modificarEstado(Estados estado) {
        this.estado = estado;
    }

    public boolean estaMuerto() {
        return this.estado == Estados.MUERTO;
    }

    public Habilidad habilidades(int habilidad){

        return habilidades.get(habilidad);

    }

    public List<Habilidad> obtenerHabilidades() {
        return habilidades;
    }


}
