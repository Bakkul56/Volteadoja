package volteadoja.logica;

import volteadoja.modelo.Carta;
import volteadoja.modelo.Heroe;
import volteadoja.modelo.Enemigo;

/**
 * Gestiona la lógica del combate dentro del juego VolteaRoja.
 *
 * Esta clase establece la interacción entre el héroe y el
 * enemigo, permitiendo aplicar los efectos de las cartas,
 * ejecutar los turnos del enemigo y determinar las
 * condiciones de victoria o derrota.
 *
 * También permite mostrar el estado actual de los personajes
 * que participan en el combate.
 */
public class Combate {

    /**
     * Héroe controlado por el jugador durante el combate.
     */
    private Heroe heroe;

    /**
     * Enemigo al que se enfrenta el héroe durante el combate.
     */
    private Enemigo enemigo;

    /**
     * Constructor de la clase Combate.
     *
     * Inicializa los personajes que participarán
     * en el enfrentamiento.
     *
     * @param heroe personaje controlado por el jugador.
     * @param enemigo personaje adversario del héroe.
     */
    public Combate(Heroe heroe, Enemigo enemigo) {
        this.heroe = heroe;
        this.enemigo = enemigo;
    }

    /**
     * Obtiene el héroe que participa en el combate.
     *
     * @return objeto que representa al héroe.
     */
    public Heroe getHeroe() {
        return heroe;
    }

    /**
     * Obtiene el enemigo que participa en el combate.
     *
     * @return objeto que representa al enemigo.
     */
    public Enemigo getEnemigo() {
        return enemigo;
    }

    /**
     * Aplica el efecto de una carta durante el combate.
     *
     * El efecto se determina mediante el tipo de habilidad
     * asociado a la carta y su respectivo valor de poder.
     *
     * Los efectos disponibles son:
     *
     * <ul>
     *     <li>DANO: causa daño al enemigo.</li>
     *     <li>VENENO: causa daño directo al enemigo.</li>
     *     <li>CURACION: recupera la vida del héroe.</li>
     *     <li>ESCUDO: aumenta la defensa del héroe.</li>
     *     <li>DEBILIDAD: reduce el ataque del enemigo.</li>
     * </ul>
     *
     * Los efectos de escudo y debilidad utilizan la quinta
     * parte del poder de la carta mediante división entera.
     *
     * Si la habilidad no está implementada, se muestra
     * un mensaje informativo en la consola.
     *
     * @param carta carta cuyo efecto será aplicado.
     */
    public void aplicarEfecto(Carta carta) {

        System.out.println("\nHabilidad activada: "
                + carta.getNombre());

        switch (carta.getEfecto()) {

            case "DANO":
            case "VENENO":

                System.out.println("La habilidad causa daño.");

                enemigo.recibirDanio(carta.getPoder());

                break;

            case "CURACION":

                System.out.println("El heroe recupera vida.");

                heroe.curar(carta.getPoder());

                break;

            case "ESCUDO":

                System.out.println("El heroe se protege.");

                heroe.aumentarDefensa(carta.getPoder() / 5);

                break;

            case "DEBILIDAD":

                System.out.println("El enemigo se debilita.");

                enemigo.reducirAtaque(carta.getPoder() / 5);

                break;

            default:

                System.out.println(
                    "Esta habilidad aun no esta implementada."
                );

                break;
        }
    }

    /**
     * Ejecuta el turno correspondiente al enemigo.
     *
     * Antes de realizar el ataque, comprueba que tanto
     * el héroe como el enemigo continúen con vida.
     *
     * Si ambos personajes están vivos, obtiene el daño
     * producido por el ataque del enemigo y lo aplica
     * sobre el héroe.
     */
    public void turnoEnemigo() {

        if (enemigo.estaVivo() && heroe.estaVivo()) {

            System.out.println("\nTurno del enemigo:");

            int danio = enemigo.atacar();

            heroe.recibirDanio(danio);
        }
    }

    /**
     * Comprueba si se ha alcanzado la condición de victoria.
     *
     * La victoria se determina cuando el enemigo
     * deja de estar vivo.
     *
     * @return true si el enemigo ha sido derrotado;
     *         false si continúa con vida.
     */
    public boolean victoria() {
        return !enemigo.estaVivo();
    }

    /**
     * Comprueba si se ha alcanzado la condición de derrota.
     *
     * La derrota se determina cuando el héroe
     * deja de estar vivo.
     *
     * @return true si el héroe ha sido derrotado;
     *         false si continúa con vida.
     */
    public boolean derrota() {
        return !heroe.estaVivo();
    }

    /**
     * Muestra el estado actual del combate en la consola.
     *
     * Invoca los métodos correspondientes del héroe
     * y del enemigo para presentar la información
     * actualizada de ambos personajes.
     */
    public void mostrarEstado() {

        System.out.println("\n=== ESTADO DEL COMBATE ===");

        heroe.mostrarEstado();
        enemigo.mostrarEstado();
    }
}