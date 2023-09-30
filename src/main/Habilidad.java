package src.main;

public class Habilidad {

    String nombre;
    int usos;

    public Habilidad(String nombre,int usos){
        this.nombre = nombre;
        this.usos = usos;
    }

    public String nombre(){

        return nombre;
    }

    public void atacar(int ataque,int nivel,Pokemon rival,double efectividad){


    }

    public void modificarEstado(Pokemon pokemon){
    }

    public Boolean AfectarRival(){
        
        return true;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public int getUsos() {
        return usos;
    }
}
