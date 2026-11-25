package volteadoja.modelo;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Representa el mazo de cartas del juego VolteaRoja.
 *
 * Esta clase se encarga de crear y almacenar las cartas
 * disponibles, organizarlas en parejas y seleccionar
 * aleatoriamente las que formarán parte del tablero.
 *
 * El mazo contiene 24 tipos de cartas, con dos cartas
 * por cada tipo, y un comodín, para un total de 49 cartas.
 *
 * Cada tablero se prepara con 12 parejas seleccionadas
 * aleatoriamente y un comodín, para un total de 25 cartas.
 *
 * @see Carta
 */
public class Mazo {

    /**
     * Lista que almacena todas las cartas disponibles
     * dentro del mazo.
     */
    private ArrayList<Carta> cartas;

    /**
     * Cantidad total de tipos de cartas con pareja
     * disponibles dentro del mazo.
     */
    private static final int PAREJAS = 24;

    /**
     * Cantidad de parejas seleccionadas para
     * preparar cada tablero de juego.
     */
    public static final int PAREJAS_EN_TABLERO = 12;

    /**
     * Constructor de la clase Mazo.
     *
     * Inicializa la lista de cartas y ejecuta el
     * método encargado de crear el mazo completo.
     */
    public Mazo() {

        cartas = new ArrayList<>();

        crearMazo();
    }

    /**
     * Crea el conjunto completo de cartas del mazo.
     *
     * Define los nombres, efectos y poderes mediante
     * tres arreglos relacionados por su posición.
     *
     * Genera dos cartas por cada tipo, asignándoles
     * el mismo identificador para que puedan formar
     * una pareja durante la partida.
     *
     * Finalmente, incorpora un comodín con identificador
     * cero y mezcla aleatoriamente todas las cartas.
     */
    private void crearMazo() {

        /*
         * Nombres correspondientes a los 24 tipos
         * de cartas disponibles.
         */
        String[] nombres = {
            "Cura", "Fuego", "Escudo",
            "Veneno", "Hielo", "Rayo",
            "Espada", "Pocion", "Defensa",
            "Oscuridad", "Luz", "Viento",
            "Gran Cura", "Meteoro", "Barrera",
            "Toxina", "Terremoto", "Elixir",
            "Maldicion", "Lanza", "Muralla",
            "Tornado", "Regenera", "Plaga"
        };

        /*
         * Efectos asociados a cada tipo de carta,
         * utilizados durante el sistema de combate.
         */
        String[] efectos = {
            "CURACION", "DANO", "ESCUDO",
            "VENENO", "DANO", "DANO",
            "DANO", "CURACION", "ESCUDO",
            "DEBILIDAD", "CURACION", "DANO",
            "CURACION", "DANO", "ESCUDO",
            "VENENO", "DANO", "CURACION",
            "DEBILIDAD", "DANO", "ESCUDO",
            "DANO", "CURACION", "VENENO"
        };

        /*
         * Valores de poder correspondientes a cada
         * uno de los tipos de cartas.
         */
        int[] poderes = {
            20, 15, 15,
            5, 15, 20,
            20, 25, 10,
            10, 30, 15,
            40, 30, 20,
            8, 25, 50,
            15, 18, 25,
            20, 15, 10
        };

        int id = 1;

        /*
         * Genera una pareja de cada tipo de carta.
         *
         * Ambas cartas comparten el mismo identificador,
         * nombre, efecto y poder.
         */
        for (int i = 0; i < PAREJAS; i++) {

            int tipo = i;

            cartas.add(new Carta(
                id,
                nombres[tipo],
                efectos[tipo],
                poderes[tipo]
            ));

            cartas.add(new Carta(
                id,
                nombres[tipo],
                efectos[tipo],
                poderes[tipo]
            ));

            id++;
        }

        /*
         * Incorpora un comodín con identificador cero,
         * diferenciado de las parejas convencionales.
         */
        cartas.add(new Carta(0, "Joker", "COMODIN", 0));

        /*
         * Mezcla aleatoriamente las cartas del mazo.
         */
        Collections.shuffle(cartas);
    }

    /**
     * Prepara las cartas necesarias para un tablero.
     *
     * Selecciona aleatoriamente 12 tipos de cartas
     * entre los 24 disponibles, incorporando las dos
     * cartas correspondientes a cada identificador.
     *
     * Posteriormente, agrega el comodín y mezcla
     * las cartas seleccionadas para distribuirlas
     * aleatoriamente dentro del tablero.
     *
     * @return lista de 25 cartas formada por
     *         12 parejas y un comodín.
     */
    public ArrayList<Carta> prepararTablero() {

        ArrayList<Carta> seleccionadas = new ArrayList<>();

        /*
         * Genera una lista con los identificadores
         * de todos los tipos de cartas disponibles.
         */
        ArrayList<Integer> ids = new ArrayList<>();

        for (int i = 1; i <= PAREJAS; i++) {
            ids.add(i);
        }

        /*
         * Mezcla los identificadores para realizar
         * una selección aleatoria de los tipos.
         */
        Collections.shuffle(ids);

        /*
         * Selecciona 12 identificadores diferentes
         * e incorpora las dos cartas correspondientes
         * a cada uno de ellos.
         */
        for (int i = 0; i < PAREJAS_EN_TABLERO; i++) {

            int idSeleccionado = ids.get(i);

            for (Carta carta : cartas) {

                if (carta.getId() == idSeleccionado) {
                    seleccionadas.add(carta);
                }
            }
        }

        /*
         * Busca e incorpora el comodín mediante
         * su identificador especial.
         */
        for (Carta carta : cartas) {

            if (carta.getId() == 0) {

                seleccionadas.add(carta);
                break;
            }
        }

        /*
         * Mezcla las 25 cartas seleccionadas para
         * evitar una distribución predecible.
         */
        Collections.shuffle(seleccionadas);

        return seleccionadas;
    }

    /**
     * Obtiene la cantidad total de cartas
     * almacenadas dentro del mazo.
     *
     * Incluye las cartas que forman parejas
     * y el comodín.
     *
     * @return cantidad total de cartas del mazo.
     */
    public int getCantidadCartas() {

        return cartas.size();
    }
}