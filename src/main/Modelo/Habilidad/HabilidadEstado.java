package src.main.Modelo.Habilidad;

import src.main.Modelo.Enums.Estados;
import src.main.Modelo.Pokemon;

public class HabilidadEstado extends Habilidad{
    private final Estados tipo;

    public HabilidadEstado(String nombre, int usos, int id, Estados tipo) {
        super(nombre,usos, id);
        this.tipo = tipo;
    }

    public void modificarEstado(Pokemon pokemon) {
        if(tipo == Estados.ENVENENADO) {
            pokemon.agregarEstado(Estados.ENVENENADO);
        } else if(tipo == Estados.DORMIDO) {
            pokemon.agregarEstado(Estados.DORMIDO);
        } else if(tipo == Estados.PARALIZADO) {
            pokemon.agregarEstado(Estados.PARALIZADO);
        } else if (tipo == Estados.CONFUSO) {
            pokemon.agregarEstado(Estados.CONFUSO);
        }
        this.usos -= 1;
    }

    @Override
    public String getTipoHabilidad() {
        return "estado";
    }
}




