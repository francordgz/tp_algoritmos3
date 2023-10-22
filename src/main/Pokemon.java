package src.main;
import src.main.Enums.Estados;
import src.main.Enums.Tipo;

import java.util.ArrayList;
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
    public List<Estados> estados;
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
        this.turnosDormido = 0;
        this.habilidades = habilidades;

        this.estados = new ArrayList<Estados>();
        estados.add(Estados.NORMAL);
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

    public int obtenerVidaMaxima() {
        return this.vidaMaxima;
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

    public List<Estados> obtenerEstados() {
        return this.estados;
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

    public void agregarEstado(Estados estado) {
        if (estado == Estados.MUERTO || 
            estado == Estados.NORMAL ||
            tieneEstado(Estados.NORMAL)) {
            
            this.estados.clear();
        }
        this.estados.add(estado);
    }

    public void recibirDanio(double danio){
        this.vidaActual -= danio;

        if (this.vidaActual < 0) {
            this.vidaActual = 0;
        }
        if (this.vidaActual == 0){
            this.estados.clear();
            this.estados.add(Estados.MUERTO);
        }
    }

    public double atacar(int habilidad, Pokemon rival, double[][] efectividades){
        double efectividad = calcularEfectividad(rival,efectividades);
        return this.habilidades.get(habilidad).atacar(ataque, nivel, rival.defensa, efectividad);
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
        if (tieneEstado(Estados.DORMIDO)) {
                this.actualizarEstadoDormido();
                if (turnosDormido >= 4) {
                    agregarEstado(Estados.NORMAL);
                }
        } else if (tieneEstado(Estados.ENVENENADO)) {
            this.recibirDanio(this.vidaMaxima * 0.5);
        }
    }

    private void actualizarEstadoDormido() {
        Boolean probabilidad = calcularProbabilidadDespertarse();

        if (probabilidad) {
            agregarEstado(Estados.NORMAL);
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
        this.estados.clear();
        this.estados.add(Estados.NORMAL);
        this.vidaActual = this.vidaMaxima;
    }

    public boolean estaMuerto() {
        return tieneEstado(Estados.MUERTO);
    }

    public Habilidad habilidades(int habilidad) {
        return habilidades.get(habilidad);
    }

    public boolean puedeAtacar() {
        return !tieneEstado(Estados.DORMIDO);
    }

    public boolean necesitaCurarse() {
        return !tieneEstado(Estados.NORMAL) && !tieneEstado(Estados.MUERTO);
    }

    public boolean tieneEstado(Estados estadoBuscado) {
        for (int i = 0; i < estados.size(); i++) {
            if (this.estados.get(i) == estadoBuscado) {
                return true;
            }
        }
        return false;
    }
}
