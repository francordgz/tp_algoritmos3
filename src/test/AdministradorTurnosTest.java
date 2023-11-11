package src.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import src.main.Modelo.AdministradorDeTurnos;
import src.main.Modelo.Entrenador;
import src.main.Modelo.Pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



public class AdministradorTurnosTest {
    private AdministradorDeTurnos admin;
    private Entrenador entrenador1;
    private Entrenador entrenador2;

    @BeforeEach
    public void nuevoEntrenador() {
        admin = new AdministradorDeTurnos();

        Pokemon pokemonRapido = Mockito.mock(Pokemon.class);
        when(pokemonRapido.obtenerVelocidad()).thenReturn(20);
        Pokemon pokemonLento = Mockito.mock(Pokemon.class);
        when(pokemonLento.obtenerVelocidad()).thenReturn(10);

        entrenador1 = mock(Entrenador.class);
        when(entrenador1.obtenerPokemonActual()).thenReturn(pokemonRapido);
        entrenador2 = mock(Entrenador.class);
        when(entrenador2.obtenerPokemonActual()).thenReturn(pokemonLento);
    }


   @Test
   public void  administradorAsignaPrimerTurnoBien(){
        admin.asignarPrimerTurno(entrenador1,entrenador2);
        assertEquals(entrenador1,admin.obtenerEntrenadorActual());
        assertEquals(entrenador2,admin.obtenerEntrenadorRivalActual());
   }

   @Test
   public void administradorDeTurnosCambiaBienPokemon(){
        admin.asignarPrimerTurno(entrenador1,entrenador2);
        admin.cambiarTurno();
        assertEquals(entrenador2,admin.obtenerEntrenadorActual());
        assertEquals(entrenador1,admin.obtenerEntrenadorRivalActual());
   }
}
