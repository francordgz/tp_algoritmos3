import java.util;


public class Pokemon {

    String Nombre;
    String historia;
    int vida = 8;
    int vidaMaxima;
    int velocidad;
    int defensa;
    String tipo;
    int danio;
    Boolean vivo;
    String estado;
    int nivel;


    public Pokemon(String unNombre,String unTipo,int vidaMaxima,int defensa,int velocidad,int danio){
        this.Nombre = unNombre;
        this.tipo = unTipo;
        this.vivo = true;
        this.vidaMaxima = vidaMaxima;
        this.defensa = defensa;
        this.danio = danio;
        this.velocidad = velocidad;

    }

    public int Vida(){
        return this.vida;
    }


    public recibirDanio(int danio){

        ///this.vida -= danio;

        //estado()


    }

    public estado(){

        if(this.vida <= 0){
            this.vivo = false;
        }
    }

    public int atacar(int nivelEnemigo,String tipoEnemigo){

        //Aca se tiene que hacer la formula de ataque para calcular el danio.
        return this.danio;
    }

    public subirNivel(){

        this.nivel += 1;
    }

   






    
}
