package volteadoja.modelo;

/**
 * Representa una carta dentro del juego VolteaRoja.
 *
 * Cada carta posee un identificador, un nombre, un efecto
 * y un valor de poder. También almacena su estado dentro
 * del tablero, indicando si está descubierta o eliminada.
 *
 * Esta clase permite controlar las acciones individuales
 * de las cartas y comprobar si dos cartas forman una pareja.
 */
public class Carta {

    /**
     * Identificador de la carta utilizado para comprobar
     * si dos cartas forman una pareja.
     */
    private int id;

    /**
     * Nombre que identifica visualmente a la carta.
     */
    private String nombre;

    /**
     * Descripción del efecto asociado a la carta.
     */
    private String efecto;

    /**
     * Valor numérico que representa el poder de la carta.
     */
    private int poder;

    /**
     * Indica si la carta se encuentra descubierta
     * dentro del tablero.
     */
    private boolean descubierta;

    /**
     * Indica si la carta ha sido eliminada del tablero.
     */
    private boolean eliminada;

    /**
     * Constructor de la clase Carta.
     *
     * Inicializa los atributos de una nueva carta.
     * Por defecto, la carta comienza oculta y sin eliminar.
     *
     * @param id identificador de la carta.
     * @param nombre nombre de la carta.
     * @param efecto efecto asociado a la carta.
     * @param poder valor de poder de la carta.
     */
    public Carta(int id, String nombre,
                 String efecto, int poder) {

        this.id = id;
        this.nombre = nombre;
        this.efecto = efecto;
        this.poder = poder;
        this.descubierta = false;
        this.eliminada = false;
    }

    /**
     * Obtiene el identificador de la carta.
     *
     * @return identificador de la carta.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre de la carta.
     *
     * @return nombre de la carta.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el efecto asociado a la carta.
     *
     * @return descripción del efecto de la carta.
     */
    public String getEfecto() {
        return efecto;
    }

    /**
     * Obtiene el valor de poder de la carta.
     *
     * @return valor numérico del poder.
     */
    public int getPoder() {
        return poder;
    }

    /**
     * Comprueba si la carta se encuentra descubierta.
     *
     * @return true si está descubierta;
     *         false si está oculta.
     */
    public boolean isDescubierta() {
        return descubierta;
    }

    /**
     * Comprueba si la carta ha sido eliminada.
     *
     * @return true si está eliminada;
     *         false si no ha sido eliminada.
     */
    public boolean isEliminada() {
        return eliminada;
    }

    /**
     * Descubre la carta dentro del tablero.
     *
     * Esta acción únicamente se realiza si la carta
     * no ha sido eliminada previamente.
     */
    public void voltear() {

        if (!eliminada) {
            descubierta = true;
        }
    }

    /**
     * Oculta nuevamente la carta dentro del tablero.
     *
     * Esta acción únicamente se realiza si la carta
     * no ha sido eliminada previamente.
     */
    public void ocultar() {

        if (!eliminada) {
            descubierta = false;
        }
    }

    /**
     * Marca la carta como eliminada del tablero.
     *
     * Actualiza su estado de eliminación y establece
     * que la carta ya no se encuentra descubierta.
     */
    public void eliminar() {

        eliminada = true;
        descubierta = false;
    }

    /**
     * Comprueba si la carta actual y otra carta
     * forman una pareja.
     *
     * Para que la comprobación sea verdadera, la otra
     * carta debe existir, ambas deben tener el mismo
     * identificador y ninguna puede estar eliminada.
     *
     * @param otra carta con la que se realiza la comparación.
     * @return true si ambas cumplen las condiciones;
     *         false en caso contrario.
     */
    public boolean esPareja(Carta otra) {

        return otra != null
                && this.id == otra.getId()
                && !this.eliminada
                && !otra.isEliminada();
    }
}