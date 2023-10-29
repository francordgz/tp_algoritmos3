package src.main.Tests.ItemTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.Constant;
import src.main.Enums.Tipo;
import src.main.Item.ItemCurarPorcentaje;
import src.main.Pokemon;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemCurarPorcentajeTest {
    private ItemCurarPorcentaje item;
    private Pokemon pokemon;

    @BeforeEach
    public void setUp() {
        int porcentaje = Constant.TERCIO;
        this.item = new ItemCurarPorcentaje("Pocion molesta alumnos", 2, porcentaje);
        this.pokemon = new Pokemon("Bulbasur", Tipo.PLANTA, 120, 10, 10, 10,
                "Bulbasur lleva una planta en su espalda, que crece a medida que evoluciona.", Arrays.asList(), 5);
    }

    @Test
    public void usarItemTest() {
        pokemon.recibirDanio(60);
        int vidaActual = pokemon.obtenerVidaActual();
        int vidaRecuperada = pokemon.obtenerVidaMaxima() * Constant.TERCIO;
        vidaRecuperada = vidaRecuperada / 100;
        item.usarItem(pokemon);

        assertEquals(vidaActual + vidaRecuperada, pokemon.obtenerVidaActual());
    }
}
