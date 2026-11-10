package volteadoja.modelo;

public class Carta {

	 // Aquí se dan los atributos a las cartas
    private int id;
    private String nombre;
    private String efecto;
    private int poder;
    private boolean descubierta;
    private boolean eliminada;

    // La composiçión de las cartas
    public Carta(int id, String nombre,
                 String efecto, int poder) {

        this.id = id;
        this.nombre = nombre;
        this.efecto = efecto;
        this.poder = poder;
        this.descubierta = false;
        this.eliminada = false;
    }

    // Arrastrar la información de las cartas
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEfecto() {
        return efecto;
    }

    public int getPoder() {
        return poder;
    }

    public boolean isDescubierta() {
        return descubierta;
    }

    public boolean isEliminada() {
        return eliminada;
    }

    // El efecto flip 7u7 donde hace lo delicioso 
    public void voltear() {
        if (!eliminada) {
            descubierta = true;
        }
    }

    // donde deja de ser sabrozo del volteo unu
    public void ocultar() {
        if (!eliminada) {
            descubierta = false;
        }
    }

    // Retirar una carta del tablero
    public void eliminar() {
        eliminada = true;
        descubierta = false;
    }

    // se comprueba de si son iguales o parejas
    public boolean esPareja(Carta otra) {
        return otra != null
                && this.id == otra.getId()
                && !this.eliminada
                && !otra.isEliminada();
    }
}
