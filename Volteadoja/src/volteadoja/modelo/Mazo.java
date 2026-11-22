package volteadoja.modelo;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
	 
	private ArrayList<Carta> cartas;
	private static final int PAREJAS = 24;             // tipos de carta en el mazo
	public static final int PAREJAS_EN_TABLERO = 12;  // parejas que entran al tablero

	    // Creamos el deck
	    public Mazo() {
	        cartas = new ArrayList<>();
	        crearMazo();
	    }

	    // Crea los 24 tipos de carta (una pareja de cada uno)
	    private void crearMazo() {

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

	        // Crear una pareja de cada tipo
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

	        // Agregar UN jokers supongo que al guason no es trampra :v
	        cartas.add(new Carta(0, "Joker", "COMODIN", 0));


	        Collections.shuffle(cartas);
	    }

	    // Preparar las 25 cartas del tablero 
	    public ArrayList<Carta> prepararTablero() {

	        ArrayList<Carta> seleccionadas = new ArrayList<>();

	        // Elegir 12 tipos al azar; los otros 12 quedan fuera esta partida
	        ArrayList<Integer> ids = new ArrayList<>();

	        for (int i = 1; i <= PAREJAS; i++) {
	            ids.add(i);
	        }

	        Collections.shuffle(ids);

	        for (int i = 0; i < PAREJAS_EN_TABLERO; i++) {

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
