package volteadoja.logica;

import volteadoja.modelo.Carta;
import volteadoja.modelo.Heroe;
import volteadoja.modelo.Enemigo;

public class Combate {

    private Heroe heroe;
    private Enemigo enemigo;

    // Constructor 7u7 ya sabes 
    public Combate(Heroe heroe, Enemigo enemigo) {
        this.heroe = heroe;
        this.enemigo = enemigo;
    }

    // Aplicar habilidad de una carta aunque esto estoy pensando en cambiar las habilidades y la tematica pero queda a tu disposición
    public void aplicarEfecto(Carta carta) {

        System.out.println("\nHabilidad activada: "
                + carta.getNombre());

        switch (carta.getEfecto()) {

            case "DANO":

                System.out.println("La habilidad causa daño.");

                enemigo.recibirDanio(carta.getPoder());

                break;

            case "CURACION":

                System.out.println("El heroe recupera vida.");

                heroe.curar(carta.getPoder());

                break;

            default:

                System.out.println(
                    "Esta habilidad aun no esta implementada."
                );

                break;
        }
    }

    // Timeeeeeeeeee toooooooo duelllllllllll enemy
    public void turnoEnemigo() {

        if (enemigo.estaVivo() && heroe.estaVivo()) {

            System.out.println("\nTurno del enemigo:");

            int danio = enemigo.atacar();

            heroe.recibirDanio(danio);
        }
    }

    // Winnnn si es claro
    public boolean victoria() {
        return !enemigo.estaVivo();
    }

    // si no es victoria sino valentina digo derrota
    public boolean derrota() {
        return !heroe.estaVivo();
    }

    // Mostrar estado del combate no tengo nada que decir pero dejare ;3 
    public void mostrarEstado() {

        System.out.println("\n=== ESTADO DEL COMBATE ===");

        heroe.mostrarEstado();
        enemigo.mostrarEstado();
    }
}