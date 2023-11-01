package src.main;
import src.main.Enums.Estados;
import src.main.Enums.Tipo;
import src.main.Habilidad.Habilidad;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pokemon {
    private final int id;
    private final String nombre;
    private final String historia;
    private final Tipo tipo;
    private final int nivel;
    private double vidaActual;
    private final int vidaMaxima;
    private int ataque;
    private int defensa;
    private int velocidad;
    public List<Estados> estados;
    private final List<Habilidad> habilidades;
    private int turnosDormido;
    private int turnosConfundido;

    public Pokemon(int id, String nombre,Tipo tipo,int vidaMaxima,int defensa,int velocidad,int danio, String historia, int nivel, List<Habilidad> habilidades){
        this.id = id;
        this.nombre = nombre;
        this.historia = historia;
        this.tipo = tipo;
        this.nivel = nivel;
        this.vidaActual = vidaMaxima;
        this.vidaMaxima = vidaMaxima;
        this.ataque = danio;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.estados = new ArrayList<>();
        estados.add(Estados.NORMAL);
        this.habilidades = habilidades;
        this.turnosDormido = 0;
        this.turnosConfundido = 0;
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

        if (this.vidaActual <= 0) {
            this.vidaActual = 0;
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

    public void UsarHabilidad(int Numerohabilidad, Pokemon rival) {
        this.habilidades.get(Numerohabilidad).modificarEstado(rival);
    }

    public void actualizarEstado() {
        if (tieneEstado(Estados.ENVENENADO))
            this.recibirDanio(this.vidaMaxima * 0.05);

        if (tieneEstado(Estados.DORMIDO))
            this.actualizarEstadoDormido();
    }

    public void actualizarEstadoDormido() {
        Boolean probabilidad = calcularProbabilidadDespertarse();

        if (probabilidad) {
            removerEstado(Estados.DORMIDO);
            this.turnosDormido = 0;
            return;
        }
        this.turnosDormido += 1;
    }

    private Boolean calcularProbabilidadDespertarse(){
        int rand = new Random().nextInt(100);
        int probabilidad = 25 + (25*this.turnosDormido) - 1;

        return probabilidad > rand;
    }

    public void actualizarEstadoConfuso() {
        Random rand = new Random();
        int probabilidad = rand.nextInt(100);
        boolean pierdeVida = probabilidad <= Constant.TERCIO;

        if (pierdeVida)
            recibirDanio(this.vidaMaxima * 0.15);

        this.turnosConfundido += 1;
        if (this.turnosConfundido == 3) {
            removerEstado(Estados.CONFUSO);
            this.turnosConfundido = 0;
        }
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
        for (Estados estado : estados) {
            if (estado == estadoBuscado)
                return true;
        }
        return false;
    }

    public void removerEstado(Estados eliminado) {
        if (this.estados.size() == 1) {
            this.estados.clear();
            this.estados.add(Estados.NORMAL);
        } else {
            List<Estados> estadosActualizados = new ArrayList<>();

            for (Estados estado : estados) {
                if (estado != eliminado)
                    estadosActualizados.add(estado);
            }
            this.estados = estadosActualizados;
        }
    }

    public Boolean validarHabilidad(int opcion) {
        Habilidad habilidad = this.habilidades.get(opcion);
        return habilidad.quedanUsosDisponibles();
    }

    public int obtenerId() {
        return this.id;
    }
}
