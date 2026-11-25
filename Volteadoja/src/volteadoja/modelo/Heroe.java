package volteadoja.modelo;

/**
 * Representa al héroe controlado por el jugador
 * dentro del juego VolteaRoja.
 *
 * Esta clase hereda los atributos y métodos de la clase
 * Personaje e incorpora un atributo adicional que
 * identifica la clase o categoría del héroe.
 *
 * Permite consultar su categoría y mostrar la información
 * correspondiente a su estado actual.
 *
 * @see Personaje
 */
public class Heroe extends Personaje {

    /**
     * Almacena la clase o categoría a la que
     * pertenece el héroe dentro del juego.
     */
    private String clase;

    /**
     * Constructor de la clase Heroe.
     *
     * Inicializa los atributos heredados de Personaje
     * mediante el constructor de la clase padre y
     * establece la categoría específica del héroe.
     *
     * @param nombre nombre del héroe.
     * @param clase clase o categoría del héroe.
     * @param vida cantidad inicial de puntos de vida.
     * @param ataque valor inicial de ataque.
     * @param defensa valor inicial de defensa.
     */
    public Heroe(String nombre, String clase,
                 int vida, int ataque, int defensa) {

        super(nombre, vida, ataque, defensa);

        this.clase = clase;
    }

    /**
     * Obtiene la clase o categoría del héroe.
     *
     * @return cadena de texto que representa
     *         la categoría del héroe.
     */
    public String getClase() {
        return clase;
    }

    /**
     * Muestra la información del héroe en la consola.
     *
     * Presenta la categoría del héroe e invoca el
     * método mostrarEstado(), heredado de la clase
     * Personaje, para mostrar sus características
     * y su estado actual.
     */
    public void mostrarHeroe() {

        System.out.println("Clase: " + clase);

        mostrarEstado();
    }
}