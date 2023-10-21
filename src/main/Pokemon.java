package src.main;
import src.main.Enums.Estados;
import src.main.Enums.Tipo;

import java.util.List;
import java.util.Random;

public class Pokemon {

    private String nombre;
    private String historia;
    private Tipo tipo;
    private int nivel;
    private double vidaActual;
    private int vidaMaxima;
    private int ataque;
    private int defensa;
    private int velocidad;
    //TODO: Estado tiene que ser private
    public Estados estado;
    private int turnosDormido;
    private List<Habilidad> habilidades;

    public Pokemon(String nombre,Tipo tipo,int vidaMaxima,int defensa,int velocidad,int danio, String historia, List<Habilidad> habilidades,int nivel){
        this.nombre = nombre;
        this.historia = historia;
        this.tipo = tipo;
        this.nivel = nivel;
        this.vidaActual = vidaMaxima;
        this.vidaMaxima = vidaMaxima;
        this.ataque = danio;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.estado = Estados.NORMAL;
        this.turnosDormido = 0;
        this.habilidades = habilidades;
    }

    //GETTERS
    public String obtenerNombre(){
        return nombre;
    }

    public Tipo obtenerTipo(){
        return this.tipo;
    }

    public int obtenerNivel(){
        return nivel;
    }

    public int obtenerVidaActual() {
        return (int)vidaActual;
    }

    public int obtenerAtaque(){
        return ataque;
    }

    public int obtenerDefensa(){
        return defensa;
    }

    public int obtenerVelocidad(){
        return velocidad;
    }

    public Estados obtenerEstado() {
        return estado;
    }

    public List<Habilidad> obtenerHabilidades() {
        return habilidades;
    }

    //SETTERS
    public void modificarAtaque(int poder){
        this.ataque += poder;
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

    public void recibirDanio(double danio){
        this.vidaActual -= danio;

        if (this.vidaActual < 0) {
            this.vidaActual = 0;
        }
        if (this.vidaActual == 0){
            this.estado = Estados.MUERTO;
        }
    }

    public double atacar(int habilidad, Pokemon rival, double[][] efectividades){
        double efectividad = calcularEfectividad(rival,efectividades);
        this.habilidades.get(habilidad).atacar(ataque, nivel, rival, efectividad);
        return efectividad;
    }

    private double calcularEfectividad(Pokemon rival,double [][] efectividades){
        int posicionAtacante = this.tipo.ordinal();
        int PosicionRival = rival.obtenerTipo().ordinal();

        return efectividades[posicionAtacante][PosicionRival];
    }

    public void UsarHabilidad(int Numerohabilidad, Pokemon rival){
        habilidades.get(Numerohabilidad).modificarEstado(rival);
    }

    public void actualizarEstado() {
        if (this.estado == Estados.DORMIDO) {
                this.actualizarEstadoDormido();
                if (turnosDormido >= 4) {
                    this.estado = Estados.NORMAL;
                }
        } else if (this.estado == Estados.ENVENENADO) {
            this.recibirDanio(this.vidaMaxima * 0.5);
        }
    }

    private void actualizarEstadoDormido() {
        Boolean probabilidad = calcularProbabilidadDespertarse();

        if (probabilidad) {
            this.estado = Estados.NORMAL;
            this.turnosDormido = 0;
            return;
        }
        this.turnosDormido += 1;
    }

    private Boolean calcularProbabilidadDespertarse(){
        int rand = new Random().nextInt(100);
        int probabilidad = 25 + (25*this.turnosDormido) - 1;

        if(probabilidad > rand) { return true; }
        return false;
    }

    public void curar(int poder){
        this.vidaActual += poder;
        if (vidaActual > vidaMaxima)
            this.vidaActual = this.vidaMaxima;
    }

    public void revivir() {
        this.estado = Estados.NORMAL;
        this.vidaActual = this.vidaMaxima;
    }

    public boolean estaMuerto() {
        return this.estado == Estados.MUERTO;
    }

    public Habilidad habilidades(int habilidad) {
        return habilidades.get(habilidad);
    }

    public boolean puedeAtacar() {
        return this.estado != Estados.DORMIDO;
    }

    public boolean necesitaCurarse() {
        return this.estado != Estados.NORMAL && this.estado != Estados.MUERTO;
    }
}
