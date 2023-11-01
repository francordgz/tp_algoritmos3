package src.test.HabilidadTest;

import src.main.Constant;
import src.main.Enums.Tipo;
import src.main.Habilidad.HabilidadAtaque;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HabilidadAtaqueTest {
    @Test
    public void testMismoTipo() {
        HabilidadAtaque habilidad = new HabilidadAtaque("HabilidadAtaqueTest", 5, 0, 50, true);
        assertEquals(habilidad.mismoTipo(), 1.5);
        habilidad = new HabilidadAtaque("HabilidadAtaqueTest", 5, 0, 50, false);
        assertEquals(habilidad.mismoTipo(), 1);
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
    public void atacarAtacaEnRangoTest() {
        HabilidadAtaque habilidad = new HabilidadAtaque("HabilidadAtaqueTest", 5, 0, 50, true);
        double efectividad = Constant.calcularEfectividad(Tipo.RAYO, Tipo.FUEGO);
        assertTrue(habilidad.atacar(10,10,10, efectividad) < 25);
    }

    @Test
    public void generarNumeroRandomRangoTest() {
        HabilidadAtaque habilidad = new HabilidadAtaque("HabilidadAtaqueTest", 5, 0, 50, true);
        float nRandom = (habilidad.generarNumeroRandom());
        assertTrue(nRandom >= (float) 217 / 255 && nRandom <= 1.0);
    }
}
