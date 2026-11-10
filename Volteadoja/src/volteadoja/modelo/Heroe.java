package volteadoja.modelo;

public class Heroe extends Personaje {

    private String clase;

    // Bob el constructor de personajes atacando otra véz
    public Heroe(String nombre, String clase,
                 int vida, int ataque, int defensa) {

        super(nombre, vida, ataque, defensa);

        this.clase = clase;
    }

    public String getClase() {
        return clase;
    }

    // Mostrar información del héroe pos las caracteristicas
    public void mostrarHeroe() {

        System.out.println("Clase: " + clase);
        mostrarEstado();
    }
}