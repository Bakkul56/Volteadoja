package volteadoja.logica;

/**
 * Representa los posibles estados de una partida
 * dentro del juego VolteaRoja.
 *
 * Esta enumeración permite identificar si el combate
 * continúa activo, si el jugador ha conseguido la
 * victoria o si ha sido derrotado.
 *
 * Su finalidad es facilitar el control del estado
 * de la partida mediante valores predefinidos.
 */
public enum EstadoPartida {

    /**
     * Indica que la partida continúa en desarrollo
     * y que el combate todavía no ha finalizado.
     */
    EN_CURSO,

    /**
     * Indica que el enemigo ha sido derrotado
     * y que el jugador ha conseguido la victoria.
     */
    VICTORIA,

    /**
     * Indica que el héroe ha sido derrotado
     * y que la partida ha finalizado con una derrota.
     */
    DERROTA
}