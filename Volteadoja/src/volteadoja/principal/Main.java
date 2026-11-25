
package volteadoja.principal;

import javafx.application.Application;
import volteadoja.vista.Ventana;

/**
 * Clase principal del juego VolteaRoja.
 *
 * Representa el punto de entrada de la aplicación
 * y se encarga de iniciar la interfaz gráfica
 * del videojuego mediante JavaFX.
 *
 * Delega la creación y administración de la
 * interfaz gráfica a la clase Ventana.
 *
 * @see Ventana
 * @see Application
 */
public class Main {

    /**
     * Constructor de la clase Main.
     */
    public Main() {
    }

    /**
     * Método principal encargado de iniciar
     * la ejecución del videojuego.
     *
     * Utiliza el método launch de JavaFX para
     * iniciar la aplicación gráfica mediante
     * la clase Ventana.
     *
     * @param args argumentos recibidos desde
     *             la línea de comandos.
     */
    public static void main(String[] args) {

        Application.launch(Ventana.class, args);

    }
}