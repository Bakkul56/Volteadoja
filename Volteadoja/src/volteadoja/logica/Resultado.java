package volteadoja.logica;

/**
 * Representa los posibles resultados de la selección
 * de cartas dentro del juego VolteaRoja.
 *
 * Esta enumeración permite identificar el resultado
 * de cada acción realizada por el jugador sobre
 * las cartas del tablero.
 *
 * Facilita la comunicación entre la lógica del juego
 * y la interfaz gráfica, permitiendo determinar
 * las acciones correspondientes a cada resultado.
 *
 * @see Juego
 */
public enum Resultado {

    /**
     * Indica que la selección de la carta no es válida.
     *
     * Ocurre cuando la carta no existe, ha sido
     * eliminada o ya se encuentra descubierta.
     */
    INVALIDA,

    /**
     * Indica que existe un turno pendiente de resolución.
     *
     * Impide seleccionar cartas adicionales mientras
     * las dos cartas del turno permanecen descubiertas.
     */
    ESPERANDO,

    /**
     * Indica que se ha seleccionado y descubierto
     * la primera carta del turno.
     *
     * El juego queda a la espera de una segunda
     * selección por parte del jugador.
     */
    PRIMERA,

    /**
     * Indica que las dos cartas seleccionadas
     * forman una pareja válida.
     *
     * Permite aplicar el efecto correspondiente
     * a la carta durante el combate.
     */
    PAREJA,

    /**
     * Indica que las cartas seleccionadas
     * no forman una pareja ni incluyen un comodín.
     *
     * Como consecuencia, se ejecuta el turno
     * del enemigo y posteriormente se ocultan
     * las cartas seleccionadas.
     */
    NO_PAREJA,

    /**
     * Indica que una de las cartas seleccionadas
     * corresponde al comodín Joker.
     *
     * Permite activar el efecto de la otra carta
     * sin necesidad de encontrar su pareja original.
     */
    JOKER
}