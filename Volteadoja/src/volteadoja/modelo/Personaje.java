package volteadoja.modelo;

/**
 * Representa la estructura general de los personajes
 * del juego VolteaRoja.
 *
 * Esta clase contiene los atributos y comportamientos
 * comunes del héroe y del enemigo, incluyendo sus
 * puntos de vida, ataque y defensa.
 *
 * Permite gestionar el daño recibido, la recuperación
 * de vida, la modificación de estadísticas y la
 * comprobación del estado vital del personaje.
 *
 * Sirve como clase padre para Heroe y Enemigo.
 *
 * @see Heroe
 * @see Enemigo
 */
public class Personaje {

    /**
     * Nombre que identifica al personaje.
     */
    private String nombre;

    /**
     * Cantidad máxima de puntos de vida
     * que puede tener el personaje.
     */
    private int vidaMaxima;

    /**
     * Cantidad actual de puntos de vida
     * del personaje durante el combate.
     */
    private int vidaActual;

    /**
     * Valor de ataque actual del personaje.
     */
    private int ataque;

    /**
     * Valor de defensa utilizado para reducir
     * el daño recibido durante el combate.
     */
    private int defensa;

    /**
     * Constructor de la clase Personaje.
     *
     * Inicializa las características principales
     * del personaje y establece su vida actual
     * con el mismo valor de su vida máxima.
     *
     * @param nombre nombre del personaje.
     * @param vidaMaxima cantidad máxima de puntos de vida.
     * @param ataque valor inicial de ataque.
     * @param defensa valor inicial de defensa.
     */
    public Personaje(String nombre, int vidaMaxima,
                     int ataque, int defensa) {

        this.nombre = nombre;
        this.vidaMaxima = vidaMaxima;
        this.vidaActual = vidaMaxima;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    /**
     * Obtiene el nombre del personaje.
     *
     * @return nombre del personaje.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la cantidad actual de puntos de vida.
     *
     * @return puntos de vida actuales.
     */
    public int getVidaActual() {
        return vidaActual;
    }

    /**
     * Obtiene la cantidad máxima de puntos de vida.
     *
     * @return puntos de vida máximos.
     */
    public int getVidaMaxima() {
        return vidaMaxima;
    }

    /**
     * Obtiene el valor actual de ataque.
     *
     * @return valor de ataque del personaje.
     */
    public int getAtaque() {
        return ataque;
    }

    /**
     * Obtiene el valor actual de defensa.
     *
     * @return valor de defensa del personaje.
     */
    public int getDefensa() {
        return defensa;
    }

    /**
     * Comprueba si el personaje continúa con vida.
     *
     * El personaje se considera vivo cuando
     * sus puntos de vida actuales son mayores que cero.
     *
     * @return true si el personaje está vivo;
     *         false en caso contrario.
     */
    public boolean estaVivo() {

        return vidaActual > 0;
    }

    /**
     * Aplica una cantidad de daño al personaje.
     *
     * El daño recibido se reduce mediante el valor
     * de defensa del personaje, evitando que el
     * resultado del daño sea negativo.
     *
     * Posteriormente, se descuentan los puntos de
     * vida correspondientes, impidiendo que la vida
     * actual quede por debajo de cero.
     *
     * Finalmente, se muestra en la consola la
     * cantidad de daño recibido.
     *
     * @param cantidad cantidad de daño antes de
     *                 aplicar la defensa.
     */
    public void recibirDanio(int cantidad) {

        /*
         * Calcula el daño efectivo teniendo en
         * cuenta la defensa del personaje.
         */
        int danioReal = Math.max(0, cantidad - defensa);

        vidaActual -= danioReal;

        /*
         * Evita que los puntos de vida
         * tengan un valor negativo.
         */
        if (vidaActual < 0) {

            vidaActual = 0;
        }

        System.out.println(nombre + " recibe "
                + danioReal + " puntos de daño.");
    }

    /**
     * Recupera los puntos de vida del personaje.
     *
     * La recuperación únicamente se realiza si
     * el personaje continúa con vida.
     *
     * No se permite que una cantidad negativa
     * de curación reduzca los puntos de vida.
     *
     * La vida recuperada no puede superar el
     * valor máximo establecido para el personaje.
     *
     * @param cantidad cantidad de puntos de vida
     *                 que se desea recuperar.
     */
    public void curar(int cantidad) {

        /*
         * Impide la recuperación de vida
         * cuando el personaje ha sido derrotado.
         */
        if (!estaVivo()) {

            return;
        }

        vidaActual += Math.max(0, cantidad);

        /*
         * Limita la recuperación de vida
         * al máximo permitido.
         */
        if (vidaActual > vidaMaxima) {

            vidaActual = vidaMaxima;
        }

        System.out.println(nombre + " tiene "
                + vidaActual + "/" + vidaMaxima
                + " puntos de vida.");
    }

    /**
     * Muestra las estadísticas actuales del personaje
     * mediante un mensaje en la consola.
     *
     * La información presentada incluye el nombre,
     * los puntos de vida actuales y máximos,
     * el valor de ataque y el valor de defensa.
     */
    public void mostrarEstado() {

        System.out.println(

            nombre + " | HP: "
            + vidaActual + "/" + vidaMaxima
            + " | ATQ: " + ataque
            + " | DEF: " + defensa

        );
    }

    /**
     * Incrementa el valor de defensa del personaje.
     *
     * Este método permite aplicar el efecto ESCUDO
     * durante el combate.
     *
     * Utiliza Math.max() para evitar que una cantidad
     * negativa reduzca la defensa existente.
     *
     * @param cantidad cantidad de defensa que
     *                 se desea incrementar.
     */
    public void aumentarDefensa(int cantidad) {

        defensa += Math.max(0, cantidad);

        System.out.println(nombre + " ahora tiene "
                + defensa + " de defensa.");
    }

    /**
     * Reduce el valor de ataque del personaje.
     *
     * Este método permite aplicar el efecto DEBILIDAD
     * durante el combate.
     *
     * Evita que una cantidad negativa incremente
     * el ataque y garantiza que el valor resultante
     * no sea inferior a cero.
     *
     * @param cantidad cantidad de ataque que
     *                 se desea reducir.
     */
    public void reducirAtaque(int cantidad) {

        ataque = Math.max(0, ataque - Math.max(0, cantidad));

        System.out.println(nombre + " ahora tiene "
                + ataque + " de ataque.");
    }
}