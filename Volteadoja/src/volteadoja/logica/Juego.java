package volteadoja.logica;

import volteadoja.modelo.Carta;
import volteadoja.modelo.Mazo;
import volteadoja.modelo.Heroe;
import volteadoja.modelo.Enemigo;

/**
 * Gestiona la lógica principal del juego VolteaRoja.
 *
 * Esta clase coordina el funcionamiento del tablero,
 * el sistema de combate, la selección de cartas,
 * el conteo de turnos y las parejas encontradas.
 *
 * También administra la renovación del tablero y
 * determina el estado general de la partida.
 *
 * @see Tablero
 * @see Combate
 * @see EstadoPartida
 * @see Resultado
 */
public class Juego {

    /**
     * Tablero que contiene las cartas de la partida.
     */
    private Tablero tablero;

    /**
     * Objeto encargado de gestionar el combate
     * entre el héroe y el enemigo.
     */
    private Combate combate;

    /**
     * Contador de los turnos realizados durante
     * la partida.
     */
    private int turnos;

    /**
     * Contador de las parejas encontradas
     * en el tablero actual.
     */
    private int parejasEncontradas;

    /**
     * Primera carta seleccionada durante el turno.
     * Su valor es null cuando no existe una selección.
     */
    private Carta primera;

    /**
     * Segunda carta seleccionada durante el turno.
     * Su valor es null cuando todavía no se ha
     * realizado una segunda selección.
     */
    private Carta segunda;

    /**
     * Indica si el turno ha finalizado con una
     * pareja válida o mediante el uso del comodín.
     */
    private boolean turnoAcertado;

    /**
     * Constructor de la clase Juego.
     *
     * Prepara el tablero inicial, crea los personajes
     * que participarán en el combate e inicializa
     * el contador de turnos.
     *
     * El héroe inicial es Arthur, de clase Guerrero,
     * y el enemigo inicial es Goblin, de tipo Monstruo.
     */
    public Juego() {

        repartir();

        Heroe arthur = new Heroe(
                "Arthur", "Guerrero", 100, 20, 8
            );

        Enemigo goblin = new Enemigo(
                "Goblin", "Monstruo", 60, 15, 3
            );

        combate = new Combate(arthur, goblin);

        turnos = 0;
    }

    /**
     * Genera un nuevo mazo y prepara un tablero.
     *
     * Utiliza la clase Mazo para obtener las cartas
     * que serán distribuidas dentro del tablero.
     */
    private void repartir() {

        Mazo mazo = new Mazo();

        tablero = new Tablero(mazo.prepararTablero());
    }

    /**
     * Incrementa el contador de parejas encontradas.
     *
     * Cuando se alcanza la cantidad de parejas
     * establecida en Mazo.PAREJAS_EN_TABLERO y
     * todavía no se ha conseguido la victoria,
     * se genera un nuevo tablero.
     *
     * Después de repartir las nuevas cartas,
     * el contador de parejas vuelve a cero.
     */
    private void contarPareja() {

        parejasEncontradas++;

        if (parejasEncontradas == Mazo.PAREJAS_EN_TABLERO
                && !combate.victoria()) {

            repartir();
            parejasEncontradas = 0;
        }
    }

    /**
     * Obtiene una carta ubicada en una posición
     * específica del tablero.
     *
     * Permite consultar las cartas desde otras
     * clases, incluyendo la interfaz gráfica.
     *
     * @param fila posición de la fila.
     * @param columna posición de la columna.
     * @return carta ubicada en la posición indicada,
     *         según el resultado devuelto por el tablero.
     */
    public Carta getCarta(int fila, int columna) {

        return tablero.getCarta(fila, columna);
    }

    /**
     * Gestiona la selección de una carta en el tablero.
     *
     * Comprueba que la selección sea válida y que
     * no exista un turno pendiente de resolución.
     *
     * Si se trata de la primera carta, almacena
     * la selección y espera una segunda carta.
     *
     * Al seleccionar la segunda carta, incrementa
     * el contador de turnos y comprueba si ambas
     * forman una pareja o si alguna es un comodín.
     *
     * Los posibles resultados son:
     *
     * <ul>
     *     <li>ESPERANDO: existe un turno pendiente.</li>
     *     <li>INVALIDA: la selección no es válida.</li>
     *     <li>PRIMERA: se seleccionó la primera carta.</li>
     *     <li>PAREJA: las cartas forman una pareja.</li>
     *     <li>JOKER: se utilizó una carta comodín.</li>
     *     <li>NO_PAREJA: las cartas no coinciden.</li>
     * </ul>
     *
     * Si se encuentra una pareja o se utiliza
     * un comodín, se aplica la habilidad correspondiente.
     * En caso contrario, se ejecuta el turno del enemigo.
     *
     * @param fila fila de la carta seleccionada.
     * @param columna columna de la carta seleccionada.
     * @return resultado obtenido durante la selección.
     * @see Resultado
     */
    public Resultado seleccionar(int fila, int columna) {

        /*
         * Comprueba si existe un turno pendiente
         * para evitar selecciones adicionales.
         */
        if (segunda != null) {
            return Resultado.ESPERANDO;
        }

        /*
         * Comprueba que la carta exista y que
         * no esté eliminada ni descubierta.
         */
        Carta carta = tablero.getCarta(fila, columna);

        if (carta == null || carta.isEliminada()
                || carta.isDescubierta()) {

            return Resultado.INVALIDA;
        }

        carta.voltear();

        /*
         * Almacena la primera carta seleccionada
         * y espera la selección de una segunda.
         */
        if (primera == null) {

            primera = carta;
            return Resultado.PRIMERA;
        }

        /*
         * Incrementa el contador de turnos después
         * de seleccionar una segunda carta válida.
         */
        turnos++;

        /*
         * Comprueba si ambas cartas forman una pareja
         * y aplica el efecto correspondiente.
         */
        if (primera.esPareja(carta)) {

            combate.aplicarEfecto(primera);
            segunda = carta;
            turnoAcertado = true;

            return Resultado.PAREJA;
        }

        /*
         * Comprueba si alguna de las cartas es
         * un comodín mediante el identificador 0.
         *
         * El comodín permite activar el efecto
         * de la otra carta seleccionada.
         */
        if (primera.getId() == 0 || carta.getId() == 0) {

            Carta normal = (primera.getId() == 0)
                    ? carta : primera;

            combate.aplicarEfecto(normal);
            segunda = carta;
            turnoAcertado = true;

            return Resultado.JOKER;
        }

        /*
         * Si las cartas no forman una pareja,
         * se almacena la segunda selección y
         * se ejecuta el ataque del enemigo.
         */
        segunda = carta;
        turnoAcertado = false;

        combate.turnoEnemigo();

        return Resultado.NO_PAREJA;
    }

    /**
     * Finaliza y resuelve el turno actual.
     *
     * Si no existe una segunda carta seleccionada,
     * el método termina sin realizar modificaciones.
     *
     * Cuando el turno es acertado, ambas cartas
     * son eliminadas del tablero. En caso contrario,
     * las cartas vuelven a quedar ocultas.
     *
     * Finalmente, se reinician las referencias de
     * las cartas seleccionadas y se actualiza el
     * contador de parejas cuando corresponde.
     */
    public void resolverTurno() {

        if (segunda == null) {
            return;
        }

        if (turnoAcertado) {

            primera.eliminar();
            segunda.eliminar();

        } else {

            primera.ocultar();
            segunda.ocultar();
        }

        primera = null;
        segunda = null;

        /*
         * Registra la pareja encontrada y comprueba
         * si es necesario generar un nuevo tablero.
         */
        if (turnoAcertado) {
            contarPareja();
        }
    }

    /**
     * Determina el estado general de la partida.
     *
     * Comprueba primero si el héroe ha sido derrotado
     * y posteriormente si el enemigo ha sido vencido.
     *
     * Si ninguna de estas condiciones se cumple,
     * la partida continúa en desarrollo.
     *
     * @return estado actual de la partida:
     *         DERROTA, VICTORIA o EN_CURSO.
     * @see EstadoPartida
     */
    public EstadoPartida getEstado() {

        if (combate.derrota()) {
            return EstadoPartida.DERROTA;
        }

        if (combate.victoria()) {
            return EstadoPartida.VICTORIA;
        }

        return EstadoPartida.EN_CURSO;
    }

    /**
     * Obtiene el héroe que participa en el combate.
     *
     * Permite acceder a sus características y
     * mostrar su información en la interfaz gráfica.
     *
     * @return objeto que representa al héroe.
     */
    public Heroe getHeroe() {

        return combate.getHeroe();
    }

    /**
     * Obtiene el enemigo que participa en el combate.
     *
     * Permite acceder a sus características y
     * mostrar su información en la interfaz gráfica.
     *
     * @return objeto que representa al enemigo.
     */
    public Enemigo getEnemigo() {

        return combate.getEnemigo();
    }
}