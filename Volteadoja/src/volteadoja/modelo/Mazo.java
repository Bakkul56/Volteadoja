package volteadoja.modelo;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
	 
	private ArrayList<Carta> cartas;

	    // Creamos el deck
	    public Mazo() {
	        cartas = new ArrayList<>();
	        crearMazo();
	    }

	    // Creando las 50 iniciales esto suena como los 50 principales
	    private void crearMazo() {

	        String[] nombres = {
	            "Cura", "Fuego", "Escudo",
	            "Veneno", "Hielo", "Rayo",
	            "Espada", "Pocion", "Defensa",
	            "Oscuridad", "Luz", "Viento"
	        };

	        String[] efectos = {
	            "CURACION", "DANO", "ESCUDO",
	            "VENENO", "DANO", "DANO",
	            "DANO", "CURACION", "ESCUDO",
	            "DEBILIDAD", "CURACION", "DANO"
	        };

	        int[] poderes = {
	            20, 15, 15,
	            5, 15, 20,
	            20, 25, 10,
	            10, 30, 15
	        };

	        int id = 1;

	        // Crear 24 parejas somos mejor que tinder eh
	        for (int i = 0; i < 24; i++) {

	            int tipo = i % nombres.length;

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

	        // Agregar dos jokers supongo que al guason no es trampra :v
	        cartas.add(new Carta(0, "Joker", "COMODIN", 0));
	        cartas.add(new Carta(0, "Joker", "COMODIN", 0));

	        Collections.shuffle(cartas);
	    }

	    // Preparar las 25 cartas del tablero 
	    public ArrayList<Carta> prepararTablero() {

	        ArrayList<Carta> seleccionadas = new ArrayList<>();

	        // Elegir 12 parejas diferentes
	        ArrayList<Integer> ids = new ArrayList<>();

	        for (int i = 1; i <= 24; i++) {
	            ids.add(i);
	        }

	        Collections.shuffle(ids);

	        for (int i = 0; i < 12; i++) {

	            int idSeleccionado = ids.get(i);

	            for (Carta carta : cartas) {
	                if (carta.getId() == idSeleccionado) {
	                    seleccionadas.add(carta);
	                }
	            }
	        }

	        // Agregar un comodin
	        for (Carta carta : cartas) {
	            if (carta.getId() == 0) {
	                seleccionadas.add(carta);
	                break;
	            }
	        }

	        // Mezclar las 25 cartas Shuffle time
	        Collections.shuffle(seleccionadas);

	        return seleccionadas;
	    }

	    public int getCantidadCartas() {
	        return cartas.size();
	    }
}
