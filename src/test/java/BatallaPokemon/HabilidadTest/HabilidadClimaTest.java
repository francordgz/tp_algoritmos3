package BatallaPokemon.HabilidadTest;

import org.junit.jupiter.api.Test;
import BatallaPokemon.Modelo.Clima.ClimaLluvia;
import BatallaPokemon.Modelo.Habilidad.HabilidadClima;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HabilidadClimaTest {
    @Test
    public void habilidadClimaFuncionaBienTest(){
        HabilidadClima Hc = new HabilidadClima("Lluvia",4, 0, new ClimaLluvia());
        assertEquals(ClimaLluvia.class,(Hc.modificarClima()).getClass());
    }
}
