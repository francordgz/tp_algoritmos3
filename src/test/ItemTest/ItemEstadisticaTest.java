package src.test.ItemTest;

import org.junit.jupiter.api.BeforeAll;
import src.main.Enums.Tipo;
import src.main.Enums.TipoModificacion;
import src.main.Item.Item;
import src.main.Item.ItemEstadistica;
import src.main.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemEstadisticaTest {
    private List<Item> items;
    private Pokemon pokemon;
    @BeforeAll
    public void setUp() {
        this.items = new ArrayList<Item>();
        items.add(new ItemEstadistica("Ataque", TipoModificacion.ATAQUE, 2));
        items.add(new ItemEstadistica("Defensa", TipoModificacion.DEFENSA, 1));

        this.pokemon = new Pokemon("Bulbasur", Tipo.PLANTA, 120, 10, 10, 10,
                "Bulbasur lleva una planta en su espalda, que crece a medida que evoluciona.", Arrays.asList(), 5);
    }
}
