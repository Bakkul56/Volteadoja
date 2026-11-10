package volteadoja.modelo;

public class Personaje {

    private String nombre;
    private int vidaMaxima;
    private int vidaActual;
    private int ataque;
    private int defensa;

    // Volvemos a bob el constructor de personajes
    public Personaje(String nombre, int vidaMaxima,
                     int ataque, int defensa) {

        this.nombre = nombre;
        this.vidaMaxima = vidaMaxima;
        this.vidaActual = vidaMaxima;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    // Entiendo que no quisieras mucho temas rpg pero aqui tamos
    public String getNombre() {
        return nombre;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    // pos si se murio comprobamos eso con esto
    public boolean estaVivo() {
        return vidaActual > 0;
    }

    // Recibir daño me dueleeeeee
    public void recibirDanio(int cantidad) {

        int danioReal = Math.max(0, cantidad - defensa);

        vidaActual -= danioReal;

        if (vidaActual < 0) {
            vidaActual = 0;
        }

        System.out.println(nombre + " recibe "
                + danioReal + " puntos de daño.");
    }

    // Recuperacion de vida casi muerooooo
    public void curar(int cantidad) {

        if (!estaVivo()) {
            return;
        }

        vidaActual += Math.max(0, cantidad);

        if (vidaActual > vidaMaxima) {
            vidaActual = vidaMaxima;
        }

        System.out.println(nombre + " tiene "
                + vidaActual + "/" + vidaMaxima
                + " puntos de vida.");
    }

    // Mostrar las estadisticas 
    public void mostrarEstado() {

        System.out.println(
            nombre + " | HP: "
            + vidaActual + "/" + vidaMaxima
            + " | ATQ: " + ataque
            + " | DEF: " + defensa
        );
    }
}