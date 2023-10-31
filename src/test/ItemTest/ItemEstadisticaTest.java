package src.test.ItemTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.Enums.Tipo;
import src.main.Enums.TipoModificacion;
import src.main.Item.Item;
import src.main.Item.ItemEstadistica;
import src.main.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemEstadisticaTest {
    private List<Item> items;
    private Pokemon pokemon;
    @BeforeEach
    public void setUp() {
        this.items = new ArrayList<Item>();
        items.add(new ItemEstadistica("Ataque", TipoModificacion.ATAQUE, 0, 2));
        items.add(new ItemEstadistica("Defensa", TipoModificacion.DEFENSA, 0, 1));

        this.pokemon = new Pokemon(0, "Bulbasur", Tipo.PLANTA, 120, 10, 10, 10,
                "Bulbasur lleva una planta en su espalda, que crece a medida que evoluciona.", 5, Arrays.asList());
    }

    @Test
    public void usarItemTest() {
        int ataqueModificadoEsperado = pokemon.obtenerAtaque();
        ataqueModificadoEsperado += (int) (ataqueModificadoEsperado * 0.1);

        int defensaModificadaEsperada = pokemon.obtenerDefensa();
        defensaModificadaEsperada += (int) (defensaModificadaEsperada * 0.1);

        items.get(0).usarItem(pokemon);
        items.get(1).usarItem(pokemon);
        assertEquals(ataqueModificadoEsperado, pokemon.obtenerAtaque());
        assertEquals(defensaModificadaEsperada, pokemon.obtenerDefensa());
    }
}