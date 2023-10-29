package src.main.Tests;

import org.junit.Before;
import org.junit.Test;
import src.main.Habilidad.HabilidadAtaque;

import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HabilidadAtaqueTest {

    @Test
    public void testMismoTipo() {
        habilidadAtaque = new HabilidadAtaque("HabilidadAtaqueTest", "Fire", 5, 50, true);
        assertEquals(habilidadAtaque.MismoTipo(), 1.5);
        habilidadAtaque = new HabilidadAtaque("HabilidadAtaqueTest", "Water", 5, 50, false);
        assertEquals(habilidadAtaque.MismoTipo(), 1);
    }

    @Test
    public void testAtacar() {
        int poder = 50;
        int ataque = 100;
        int nivel = 10;
        int defensa = 50;
        double efectividad = 1.5;
        float numeroRandom = 0.9F;
        int critico = 2;
        int mismoTipoMultiplier = (int) 1.5;

        double danio = 2 * nivel * critico * poder;
        danio = danio * ataque/defensa;
        danio = ((danio/5) +2)/50;

        float resultado = 32.496F;
        float margenError = 0.0001F;

        assertEquals(danio * (mismoTipoMultiplier * efectividad * numeroRandom), resultado, margenError);
    }

    public void generarNumeroRandomRangoTest(){
        float nRandom = habilidadAtaque.generarNumeroRandom();
        assertTrue(nRandom >= 217/255 && nRandom <= 1.0);
    }
}
