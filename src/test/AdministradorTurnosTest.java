package src.test;

import org.mockito.Mockito;
import org.junit.jupiter.api.*;
import src.main.Clima.ClimaHuracan;
import src.main.Enums.Tipo;
import src.main.Pokemon;
import src.main.Entrenador;
import src.main.AdministradorDeTurnos;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



public class AdministradorTurnosTest {

    private Entrenador entrenador1;
    private Entrenador entrenador2;

    private AdministradorDeTurnos admin;


    @BeforeEach
    public void nuevoEntrenador() {
        admin = new AdministradorDeTurnos();
        entrenador1 = mock(Entrenador.class);
        entrenador2 = mock(Entrenador.class);
    }


   @Test
   public void  administradorAsignaPrimerTurnoBien(){

       when(entrenador1.obtenerPokemonActual()).thenReturn(new Pokemon("Picachu", Tipo.RAYO, 20, 5,
               25, 15, "", Arrays.asList(), 5));

       when(entrenador2.obtenerPokemonActual()).thenReturn(new Pokemon("Picachu", Tipo.RAYO, 20, 5,
               20, 15, "", Arrays.asList(), 5));

        admin.asignarPrimerTurno(entrenador1,entrenador2);

        assertEquals(entrenador1,admin.obtenerEntrenadorActual());

   }

   @Test
   public void administradorDeTurnosCambiaBienPokemon(){

        when(entrenador1.obtenerPokemonActual()).thenReturn(new Pokemon("Picachu", Tipo.RAYO, 20, 5,
               25, 15, "", Arrays.asList(), 5));

        when(entrenador2.obtenerPokemonActual()).thenReturn(new Pokemon("Picachu", Tipo.RAYO, 20, 5,
               20, 15, "", Arrays.asList(), 5));

        admin.asignarPrimerTurno(entrenador1,entrenador2);

        admin.cambiarTurno();

        assertEquals(entrenador2,admin.obtenerEntrenadorActual());



   }




}
