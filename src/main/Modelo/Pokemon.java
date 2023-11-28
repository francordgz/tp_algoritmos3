package src.main.Modelo;
import src.main.Modelo.Enums.Estados;
import src.main.Modelo.Enums.Tipo;
import src.main.Modelo.Habilidad.*;

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

    public Pokemon(int id, String nombre, Tipo tipo,
                   int vidaMaxima, int defensa, int velocidad, int danio,
                   String historia, int nivel, List<Habilidad> habilidades){
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
    public int obtenerId() {
        return this.id;
    }

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
    public Habilidad habilidades(int habilidad) {
        return habilidades.get(habilidad);
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

    public double atacar(int habilidad, Pokemon rival, double efectividad){
        HabilidadAtaque habilidadAtaque = (HabilidadAtaque) this.habilidades.get(habilidad);
        return habilidadAtaque.atacar(ataque, nivel, rival.defensa, efectividad);
    }

    public void usarHabilidadEstado(int habilidad, Pokemon rival) {
        HabilidadEstado habilidadEstado = (HabilidadEstado) this.habilidades.get(habilidad);
        habilidadEstado.modificarEstado(rival);
    }

    public boolean usarHabilidadEstadistica(int habilidad, Pokemon rival) {
        HabilidadEstadistica habilidadEstado = (HabilidadEstadistica) this.habilidades.get(habilidad);
        boolean afectaRival = habilidadEstado.afectaRival();
        habilidadEstado.modificarEstado(afectaRival ? rival : this);
        return afectaRival;
    }

    public void recibirAtaque(double danio){
        this.vidaActual -= danio;

        if (this.vidaActual <= 0) {
            this.vidaActual = 0;
            this.estados.clear();
            this.estados.add(Estados.MUERTO);
        }
    }

    public void actualizarEstado() {
        if (tieneEstado(Estados.ENVENENADO))
            this.recibirAtaque(this.vidaMaxima * 0.05);

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

    public Boolean actualizarEstadoConfuso() {
        Random rand = new Random();
        int probabilidad = rand.nextInt(100);
        boolean pierdeVida = probabilidad <= Constant.TERCIO;

        if (pierdeVida)
            recibirAtaque(this.vidaMaxima * 0.15);

        this.turnosConfundido += 1;
        if (this.turnosConfundido == 3) {
            removerEstado(Estados.CONFUSO);
            this.turnosConfundido = 0;
        }

        return !pierdeVida;
    }

    public Boolean tieneEstado(Estados estadoBuscado) {
        for (Estados estado : estados) {
            if (estado == estadoBuscado)
                return true;
        }
        return false;
    }

    public Boolean estaMuerto() {
        return tieneEstado(Estados.MUERTO);
    }

    public Boolean puedeAtacar() {
        return !tieneEstado(Estados.DORMIDO);
    }

    public boolean estaCurado() {
        return this.vidaActual == this.vidaMaxima;
    }

    public Boolean necesitaCurarse() {
        return !tieneEstado(Estados.NORMAL) && !tieneEstado(Estados.MUERTO);
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
}
