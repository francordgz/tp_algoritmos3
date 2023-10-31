package src.test.ItemTest;

import org.junit.jupiter.api.*;
import src.main.Enums.Tipo;
import src.main.Item.Item;
import src.main.Item.ItemCuracion;
import src.main.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemCuracionTest {
    private List<Item> items;
    private Pokemon pokemon;

    @BeforeEach
    public void setUp() {
        this.items = new ArrayList<Item>();
        items.add(new ItemCuracion(20, "Pocion", 0, 3));
        items.add(new ItemCuracion(50, "MegaPocion", 0, 2));
        items.add(new ItemCuracion(100, "HiperPocion", 0, 3));

        this.pokemon = new Pokemon( 0,"Bulbasur", Tipo.PLANTA, 120, 10, 10, 10,
                "Bulbasur lleva una planta en su espalda, que crece a medida que evoluciona.", 5, Arrays.asList());
    }

    @Test
    public void obtenerNombreTest() {
        assertEquals("Pocion",items.get(0).obtenerNombre());
        assertEquals("MegaPocion",items.get(1).obtenerNombre());
        assertEquals("HiperPocion",items.get(2).obtenerNombre());
    }

    @Test
    public void obtenerCantidadTest() {

        assertEquals(3,items.get(0).obtenerCantidad());
        assertEquals(2,items.get(1).obtenerCantidad());

        ///El otro se borra porque eso se modela desde entrenador
    }

    @Test
    public void decrementarCantidadTest() {
        items.get(0).decrementarCantidad();
        items.get(1).decrementarCantidad();
        items.get(2).decrementarCantidad();

        assertEquals(2, items.get(0).obtenerCantidad());
        assertEquals(1, items.get(1).obtenerCantidad());
        assertEquals(0, items.get(2).obtenerCantidad());
        // Este último es una HiperPocion y por lo tanto solo se puede usar una vez
    }

    @Test
    public void usarItemTest() {
        this.pokemon.recibirDanio(30);
        int vidaActual = this.pokemon.obtenerVidaActual();
        items.get(0).usarItem(this.pokemon);
        assertEquals(vidaActual + 20, pokemon.obtenerVidaActual());
    }

}
