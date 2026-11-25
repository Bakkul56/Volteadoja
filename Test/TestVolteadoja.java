package volteadoja.logica;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import volteadoja.modelo.*;

class TestVolteadoja {

    private Heroe heroe;
    private Enemigo enemigo;
    private Combate combate;

    @BeforeEach
    void setUp() {
        heroe = new Heroe("Arthur", "G", 100, 15, 5);
        enemigo = new Enemigo("Goblin", "G", 50, 7, 3);
        combate = new Combate(heroe, enemigo);
    }

    @Test
    void testInicializacionCombate() {
        assertNotNull(combate.getHeroe(), "El héroe no debe ser nulo");
        assertNotNull(combate.getEnemigo(), "El enemigo no debe ser nulo");
        assertFalse(combate.victoria(), "El combate no debe iniciar ganado");
        assertFalse(combate.derrota(), "El combate no debe iniciar perdido");
    }

    @Test
    void testTurnoEnemigoReduceVidaHeroe() {
        int vidaInicial = heroe.getVidaActual();
        combate.turnoEnemigo();
        assertTrue(heroe.getVidaActual() < vidaInicial, "El ataque del enemigo debe reducir la vida del héroe");
    }

    @Test
    void testVictoriaCuandoEnemigoMuere() {
        enemigo.recibirDanio(enemigo.getVidaActual() + enemigo.getDefensa());
        assertTrue(combate.victoria(), "Debe ser victoria si el enemigo llega a 0 de vida");
    }

    @Test
    void testDerrotaCuandoHeroeMuere() {
        heroe.recibirDanio(heroe.getVidaActual() + heroe.getDefensa());
        assertTrue(combate.derrota(), "Debe ser derrota si el héroe llega a 0 de vida");
    }
}