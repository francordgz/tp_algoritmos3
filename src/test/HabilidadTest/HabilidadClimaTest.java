package src.test.HabilidadTest;

import src.main.Clima.ClimaLluvia;
import src.main.Habilidad.HabilidadAtaque;

import org.junit.jupiter.api.*;
import src.main.Habilidad.HabilidadClima;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HabilidadClimaTest {


    @Test
    public void HabilidadClimaFuncionaBien(){

        HabilidadClima Hc = new HabilidadClima("Lluvia",4, new ClimaLluvia());

        assertEquals((new ClimaLluvia()).getClass(),(Hc.modificarClima()).getClass());


    }
}
