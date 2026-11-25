package volteadoja.logica;

import java.util.ArrayList;
import volteadoja.modelo.Carta;

/**
 * Representa el tablero de cartas del juego VolteaRoja.
 *
 * Esta clase administra una matriz bidimensional de
 * 5 filas y 5 columnas, con capacidad para 25 cartas.
 *
 * Permite distribuir las cartas, consultar posiciones,
 * mostrar el estado del tablero y comprobar si todas
 * las cartas normales han sido eliminadas.
 *
 * @see Carta
 * @see Juego
 */
public class Tablero {

    /**
     * Matriz bidimensional que almacena las cartas
     * distribuidas dentro del tablero.
     */
    private Carta[][] cartas;

    /**
     * Constante que establece la cantidad de filas
     * y columnas del tablero.
     */
    private static final int TAMANO = 5;

    /**
     * Constructor de la clase Tablero.
     *
     * Recibe una lista de 25 cartas y distribuye
     * cada una de ellas dentro de una matriz de 5 x 5.
     *
     * Antes de realizar la distribución, comprueba
     * que la cantidad de cartas recibidas sea correcta.
     *
     * @param cartasSeleccionadas lista de cartas
     *                            destinadas al tablero.
     * @throws IllegalArgumentException si la cantidad
     *         de cartas recibidas es diferente de 25.
     */
    public Tablero(ArrayList<Carta> cartasSeleccionadas) {

        /*
         * Comprueba que la cantidad de cartas
         * coincida con la capacidad del tablero.
         */
        if (cartasSeleccionadas.size() != 25) {

            throw new IllegalArgumentException(
                "El tablero necesita 25 cartas"
            );
        }

        cartas = new Carta[TAMANO][TAMANO];

        int indice = 0;

        /*
         * Recorre las filas y columnas de la matriz
         * para distribuir las cartas seleccionadas.
         */
        for (int fila = 0; fila < TAMANO; fila++) {
            for (int columna = 0; columna < TAMANO; columna++) {

                cartas[fila][columna] =
                    cartasSeleccionadas.get(indice);

                indice++;
            }
        }
    }

    /**
     * Muestra el estado actual del tablero en la consola.
     *
     * Recorre todas las posiciones de la matriz y
     * representa cada carta según su estado.
     *
     * Las cartas eliminadas se representan mediante
     * espacios vacíos, las descubiertas mediante
     * su identificador y las ocultas mediante
     * un signo de interrogación.
     */
    public void mostrarTablero() {

        System.out.println("\nTABLERO VOLTEADOJA\n");

        for (int fila = 0; fila < TAMANO; fila++) {

            for (int columna = 0; columna < TAMANO; columna++) {

                Carta carta = cartas[fila][columna];

                /*
                 * Determina la representación de la
                 * carta según su estado actual.
                 */
                if (carta.isEliminada()) {

                    System.out.print("[   ] ");

                } else if (carta.isDescubierta()) {

                    System.out.printf("[%3d] ", carta.getId());

                } else {

                    System.out.print("[ ? ] ");
                }
            }

            System.out.println();
        }
    }

    /**
     * Obtiene una carta ubicada en una posición
     * específica del tablero.
     *
     * Valida que las coordenadas recibidas se
     * encuentren dentro de los límites de la matriz.
     *
     * Las posiciones válidas están comprendidas
     * entre 0 y 4, tanto para filas como columnas.
     *
     * @param fila índice de la fila solicitada.
     * @param columna índice de la columna solicitada.
     * @return carta ubicada en la posición indicada
     *         o null si las coordenadas son inválidas.
     */
    public Carta getCarta(int fila, int columna) {

        /*
         * Valida los límites de la matriz para
         * evitar accesos a posiciones inexistentes.
         */
        if (fila < 0 || fila >= TAMANO
                || columna < 0 || columna >= TAMANO) {

            return null;
        }

        return cartas[fila][columna];
    }

    /**
     * Comprueba si todas las cartas normales
     * del tablero han sido eliminadas.
     *
     * Recorre las filas y columnas de la matriz,
     * contando las cartas que todavía permanecen
     * disponibles.
     *
     * El comodín, identificado mediante el valor
     * cero, no se incluye dentro del conteo.
     *
     * @return true si no quedan cartas normales
     *         sin eliminar; false en caso contrario.
     */
    public boolean todasLasParejasEncontradas() {

        int cartasRestantes = 0;

        for (int fila = 0; fila < TAMANO; fila++) {
            for (int columna = 0; columna < TAMANO; columna++) {

                Carta carta = cartas[fila][columna];

                /*
                 * Cuenta las cartas que no han sido
                 * eliminadas, excluyendo el comodín.
                 */
                if (!carta.isEliminada() && carta.getId() != 0) {

                    cartasRestantes++;
                }
            }
        }

        return cartasRestantes == 0;
    }
}

