package src.main.Tests;

import org.junit.jupiter.api.*;
import src.main.Enums.Atributos;
import src.main.Enums.Estados;
import src.main.Enums.Tipo;
import src.main.Habilidad.HabilidadAtaque;
import src.main.Habilidad.HabilidadEstadistica;
import src.main.Habilidad.HabilidadEstado;
import src.main.Pokemon;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokemonTests {


    @Test

    public void test01Pokemon() {


        Pokemon p = new Pokemon("Picachu",Tipo.RAYO, 100, 5, 25, 15,
                "Este Pokémon es conocido por su cola en forma de rayo y su capacidad para generar electricidad.", Arrays.asList(
                new HabilidadAtaque("Golpe", "Normal", 3, 100, false),
                new HabilidadAtaque("Impactrueno", "Rayo", 1, 120, true),
                new HabilidadEstadistica("Aumentar ataque", Atributos.ATAQUE, 2, 10,true),
                new HabilidadEstado("Paralizar", 2, Estados.PARALIZADO),
                new HabilidadEstado("Confundir", 1, Estados.CONFUSO)
        ),5);

        assertEquals(p, p);


    }



}
