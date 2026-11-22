package volteadoja.logica;

import volteadoja.modelo.Carta;
import volteadoja.modelo.Mazo;
import volteadoja.modelo.Heroe;
import volteadoja.modelo.Enemigo;

public class Juego {
	
	 private Tablero tablero;
	 private Combate combate;
	 private int turnos;
	 private Carta primera;   // Primera carta del turno (null significa que no hay)
	 private Carta segunda;   // segunda carta si no hicieron pareja(null significa que no hay)
	 
	 // Constructor el bob o3o
	    public Juego() {

	        Mazo mazo = new Mazo();

	        tablero = new Tablero(mazo.prepararTablero());
	        
	        Heroe arthur = new Heroe(
	                "Arthur", "Guerrero", 100, 20, 8
	            );

	        Enemigo goblin = new Enemigo(
	                "Goblin", "Monstruo", 60, 15, 3
	            );

	        combate = new Combate(arthur, goblin);
	        
	        turnos = 0;
	    }

	    // Interface a tablero
	    public Carta getCarta(int fila, int columna) {
	        return tablero.getCarta(fila, columna);
	    }
	    
	    //Selecciona carta solo si no esta en espera.
	    public Resultado seleccionar(int fila, int columna) {

	    	//Primer chequeo para evitar clicks extra.
	        if (segunda != null) {
	            return Resultado.ESPERANDO;
	        }
	        
	        //Segundo, para evitar selecciones invalidas y voltear correctamente
	        Carta carta = tablero.getCarta(fila, columna);

	        if (carta == null || carta.isEliminada() || carta.isDescubierta()) {
	            return Resultado.INVALIDA;
	        }

	        carta.voltear();
	        
	        // Tercero, manejo de la primera carta
	        if (primera == null) {
	            primera = carta;
	            return Resultado.PRIMERA;
	        }
	        
	        // Resolucion de turno, solo si vamos por la segunda carta
	        turnos++;

	        if (primera.esPareja(carta)) {
	            combate.aplicarEfecto(primera);
	            primera.eliminar();
	            carta.eliminar();
	            primera = null;
	            return Resultado.PAREJA;
	        }
	        // Guardamos la segunda para establecer el estado.
	        segunda = carta;

	        //Revisamos si hay Joker
	        if (primera.getId() == 0 || carta.getId() == 0) {
	            return Resultado.JOKER;
	        }

	        combate.turnoEnemigo();
	        return Resultado.NO_PAREJA;
	    }
	    
	    // Oculta las dos cartas falladas y deja listo el siguiente turno
	    public void ocultarFallidas() {
	        if (segunda != null) {
	            primera.ocultar();
	            segunda.ocultar();
	            primera = null;
	            segunda = null;
	        }
	    }
	}


