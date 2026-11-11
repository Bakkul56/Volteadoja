package volteadoja.modelo;

public class Enemigo extends Personaje {

    private String tipo;

    // Constructor
    public Enemigo(String nombre, String tipo,
                   int vida, int ataque, int defensa) {

        super(nombre, vida, ataque, defensa);

        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    // Mostrar información del enemigo
    @Override
  
    public void mostrarEstado() {

        System.out.println("Tipo: " + tipo);
        super.mostrarEstado();
    }

    // Calcular el daño de un ataque
    public int atacar() {

        System.out.println(getNombre() + " ataca!");

        return getAtaque();
    }
}