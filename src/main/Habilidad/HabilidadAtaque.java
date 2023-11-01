package src.main.Habilidad;
import java.util.Random;

public class HabilidadAtaque extends Habilidad{
    private final int poder;
    private final Boolean mismoTipo;

    public HabilidadAtaque(String nombre, int usos, int id, int poder, boolean mismoTipo) {
        super(nombre,usos, id);
        this.poder = poder;
        this.mismoTipo = mismoTipo;
    }

    public double mismoTipo() {
        if (mismoTipo) return 1.5;
        return 1;
    }

    @Override
    public double atacar(int ataque, int nivel, int defensa, double efectividad){
        float critico = generarProba();
        float numeroRandom = generarNumeroRandom();
        this.usos -= 1;

        double danio = 2 * nivel * critico * this.poder;
        danio = danio * ataque/defensa;
        danio = ((danio/5) +2)/50;
        return danio * (mismoTipo() * efectividad * numeroRandom);
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
