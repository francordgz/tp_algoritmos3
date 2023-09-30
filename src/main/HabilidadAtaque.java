package src.main;
import java.util.Random;

public class HabilidadAtaque extends Habilidad{
    String tipo;
    int poder;
    boolean mismoTipo;

    public HabilidadAtaque(String nombre, String tipo, int usos, int poder, boolean mismoTipo) {
        super(nombre,usos);
        this.tipo = tipo;
        this.poder = poder;
        this.mismoTipo = mismoTipo;
    }

    public double MismoTipo() {
        if (mismoTipo) return 1.5;
        return 1;
    }

    @Override
    public void atacar(int ataque, int nivel, Pokemon rival, double efectividad){
        float critico = generarProba();
        float numeroRandom = generarNumeroRandom();

        double danio = 2 * nivel * critico * this.poder;
        
        danio = danio * ataque/rival.obtenerDefensa();
        
        danio = ((danio/5) +2)/50;
        
        danio = danio * (MismoTipo() * efectividad * numeroRandom);

        rival.recibirDanio(danio);
        this.usos -= 1;
    }

    public float generarNumeroRandom(){
        Random rand = new Random();
        return  ((float)rand.nextInt(39) + 217) / 255;
    }

    public float generarProba() {
        Random rand = new Random();
        float numeroAleatorio = rand.nextInt(100);
        if(numeroAleatorio <= 90) return  2;
        return 1;
    }
}
