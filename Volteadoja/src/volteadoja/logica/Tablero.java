package volteadoja.logica;

import java.util.ArrayList;
import volteadoja.modelo.Carta;

public class Tablero {
	
	 private Carta[][] cartas;
	    private static final int TAMANO = 5;

	    // Bob el constructor
	    public Tablero(ArrayList<Carta> cartasSeleccionadas) {

	        if (cartasSeleccionadas.size() != 25) {
	            throw new IllegalArgumentException(
	                "El tablero necesita 25 cartas"
	            );
	        }

	        cartas = new Carta[TAMANO][TAMANO];

	        int indice = 0;

	        for (int fila = 0; fila < TAMANO; fila++) {
	            for (int columna = 0; columna < TAMANO; columna++) {

	                cartas[fila][columna] =
	                    cartasSeleccionadas.get(indice);

	                indice++;
	            }
	        }
	    }

	    // generar el tablero y mostrarlo
	    public void mostrarTablero() {

	        System.out.println("\nTABLERO VOLTEADOJA\n");

	        for (int fila = 0; fila < TAMANO; fila++) {

	            for (int columna = 0; columna < TAMANO; columna++) {

	                Carta carta = cartas[fila][columna];

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

	    // hacer posiciones 
	    public Carta getCarta(int fila, int columna) {

	        if (fila < 0 || fila >= TAMANO
	                || columna < 0 || columna >= TAMANO) {
	            return null;
	        }

	        return cartas[fila][columna];
	    }
	    
	 // Comprobar si quedan parejas en el tablero
	    public boolean todasLasParejasEncontradas() {

	        int cartasRestantes = 0;

	        for (int fila = 0; fila < TAMANO; fila++) {
	            for (int columna = 0; columna < TAMANO; columna++) {

	                Carta carta = cartas[fila][columna];

	                if (!carta.isEliminada() && carta.getId() != 0) {
	                    cartasRestantes++;
	                }
	            }
	        }

	        return cartasRestantes == 0;
	    }
	}



