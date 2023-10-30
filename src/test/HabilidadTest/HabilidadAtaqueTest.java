package src.test.HabilidadTest;

import src.main.Habilidad.HabilidadAtaque;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HabilidadAtaqueTest {
    @Test
    public void testMismoTipo() {
        HabilidadAtaque habilidad = new HabilidadAtaque("HabilidadAtaqueTest", "Fire", 5, 50, true);
        assertEquals(habilidad.MismoTipo(), 1.5);
        habilidad = new HabilidadAtaque("HabilidadAtaqueTest", "Water", 5, 50, false);
        assertEquals(habilidad.MismoTipo(), 1);
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
        danio = danio * ataque / defensa;
        danio = ((danio / 5) + 2) / 50;

        int resultado = 21;

        assertEquals((int) (danio * (mismoTipoMultiplier * efectividad * numeroRandom)), resultado);
    }

    @Test
    public void atacarAtacaEnRango(){

        HabilidadAtaque habilidad = new HabilidadAtaque("HabilidadAtaqueTest", "Fire", 5, 50, true);



        assertTrue(habilidad.atacar(10,10,10,1) < 25);


    }




    @Test
    public void generarNumeroRandomRangoTest(){
        HabilidadAtaque habilidad = new HabilidadAtaque("HabilidadAtaqueTest", "Fire", 5, 50, true);
        float nRandom = (habilidad.generarNumeroRandom());
        assertTrue(nRandom >= (float)217/255 && nRandom <= 1.0);
    }






}


