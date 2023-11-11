package src.test.HabilidadTest;

import org.junit.jupiter.api.Test;
import src.main.Modelo.Clima.ClimaLluvia;
import src.main.Modelo.Habilidad.HabilidadClima;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HabilidadClimaTest {
    @Test
    public void habilidadClimaFuncionaBienTest(){
        HabilidadClima Hc = new HabilidadClima("Lluvia",4, 0, new ClimaLluvia());
        assertEquals(ClimaLluvia.class,(Hc.modificarClima()).getClass());
    }
}
