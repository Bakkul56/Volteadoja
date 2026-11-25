package volteadoja.modelo;

/**
 * Representa a un enemigo dentro del juego VolteaRoja.
 *
 * Esta clase hereda los atributos y métodos de la clase
 * Personaje, incorporando un atributo adicional que
 * permite identificar el tipo de enemigo.
 *
 * Su propósito es gestionar la información específica
 * del enemigo y proporcionar las acciones necesarias
 * para su participación en el combate.
 *
 * @see Personaje
 */
public class Enemigo extends Personaje {

    /**
     * Almacena el tipo o categoría a la que pertenece
     * el enemigo dentro del juego.
     */
    private String tipo;

    /**
     * Constructor de la clase Enemigo.
     *
     * Inicializa los atributos heredados de la clase
     * Personaje mediante el constructor de la clase padre.
     * Adicionalmente, establece el tipo del enemigo.
     *
     * @param nombre nombre del enemigo.
     * @param tipo tipo o categoría del enemigo.
     * @param vida cantidad inicial de puntos de vida.
     * @param ataque valor inicial de ataque.
     * @param defensa valor inicial de defensa.
     */
    public Enemigo(String nombre, String tipo,
                   int vida, int ataque, int defensa) {

        super(nombre, vida, ataque, defensa);

        this.tipo = tipo;
    }

    /**
     * Obtiene el tipo o categoría del enemigo.
     *
     * @return cadena de texto que representa el tipo
     *         del enemigo.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Muestra la información del enemigo en la consola.
     *
     * Presenta el tipo de enemigo e invoca el método
     * mostrarEstado(), heredado de la clase Personaje,
     * para mostrar su estado actual.
     */
    public void mostrarEnemigo() {

        System.out.println("Tipo: " + tipo);

        mostrarEstado();
    }

    /**
     * Ejecuta la acción de ataque del enemigo.
     *
     * Muestra un mensaje en la consola indicando que
     * el enemigo realiza un ataque y devuelve su valor
     * de ataque actual.
     *
     * Este método no aplica directamente el daño sobre
     * otro personaje, sino que proporciona el valor
     * utilizado posteriormente por la lógica del combate.
     *
     * @return valor actual de ataque del enemigo.
     */
    public int atacar() {

        System.out.println(getNombre() + " ataca!");

        return getAtaque();
    }
}