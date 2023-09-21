package src.main;




public class ModificacionEstado extends Habilidad{

    

    public ModificacionEstado(String nombre, int usos){
        super(nombre,usos);
    }

    public void envenenar(Pokemon pokemon){
        pokemon.envenenar();

    }

    public void paralizar(Pokemon pokemon){
        pokemon.paralizar();

    }

    public void Dormir(Pokemon pokemon){
        pokemon.dormir();


    }




    
}