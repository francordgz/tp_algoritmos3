package src.test.HabilidadTest;

import src.main.Clima.ClimaLluvia;

import org.junit.jupiter.api.*;
import src.main.Habilidad.HabilidadClima;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HabilidadClimaTest {


    @Test
    public void HabilidadClimaFuncionaBien(){

        HabilidadClima Hc = new HabilidadClima("Lluvia",4, 0, new ClimaLluvia());

        assertEquals((new ClimaLluvia()).getClass(),(Hc.modificarClima()).getClass());


    }

    //
}
