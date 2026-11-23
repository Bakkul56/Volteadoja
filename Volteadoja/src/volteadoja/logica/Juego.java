package volteadoja.logica;

import volteadoja.modelo.Carta;
import volteadoja.modelo.Mazo;
import volteadoja.modelo.Heroe;
import volteadoja.modelo.Enemigo;

public class Juego {
	
	 private Tablero tablero;
	 private Combate combate;
	 private int turnos;
	 private int parejasEncontradas;
	 private Carta primera;   // Primera carta del turno (null significa que no hay)
	 private Carta segunda;   // segunda carta si no hicieron pareja(null significa que no hay)
	 private boolean turnoAcertado;
	 
	 // Constructor el bob o3o
	    public Juego() {

	    	repartir();
	        
	        Heroe arthur = new Heroe(
	                "Arthur", "Guerrero", 100, 20, 8
	            );

	        Enemigo goblin = new Enemigo(
	                "Goblin", "Monstruo", 60, 15, 3
	            );

	        combate = new Combate(arthur, goblin);
	        
	        turnos = 0;
	    }
	    
	    // Crea un mazo nuevo y arregla parejas otra vez.
	    private void repartir() {
	        Mazo mazo = new Mazo();
	        tablero = new Tablero(mazo.prepararTablero());
	    }
	    
	    // Suma una pareja al llegar a 12 se reparte un tablero nuevo
	    private void contarPareja() {
	        parejasEncontradas++;
	        if (parejasEncontradas == Mazo.PAREJAS_EN_TABLERO && !combate.victoria()) {
	            repartir();
	            parejasEncontradas = 0;
	        }
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
	            segunda = carta;
	            turnoAcertado = true;
	            return Resultado.PAREJA;
	        }
	        
	        // Joker: vale por cualquier carta
	        if (primera.getId() == 0 || carta.getId() == 0) {
	            Carta normal = (primera.getId() == 0) ? carta : primera;
	            combate.aplicarEfecto(normal);
	            segunda = carta;
	            turnoAcertado = true;
	            return Resultado.JOKER;
	        }

	        // No hay pareja, guardamos la segunda para mostrarla un momento
	        segunda = carta;
	        turnoAcertado = false;

	        combate.turnoEnemigo();
	        return Resultado.NO_PAREJA;
	    }
	    
	 
	    // Termina el turno: retira las cartas si hicieron pareja o las oculta sino
	    public void resolverTurno() {
	        if (segunda == null) {
	            return;
	        }
	        if (turnoAcertado) {
	            primera.eliminar();
	            segunda.eliminar();
	        } else {
	            primera.ocultar();
	            segunda.ocultar();
	        }
	        primera = null;
	        segunda = null;

	        //para repartir un tablero nuevo
	        if (turnoAcertado) {
	            contarPareja();   
	        }
	    }
	    
	    // Estado general de la partida en el combate
	    public EstadoPartida getEstado() {
	        if (combate.derrota()) {
	            return EstadoPartida.DERROTA;
	        }
	        if (combate.victoria()) {
	            return EstadoPartida.VICTORIA;
	        }
	        return EstadoPartida.EN_CURSO;
	    }
	    
	    // Acceso a los personajes para mostrarlos en pantalla
	    public Heroe getHeroe() {
	        return combate.getHeroe();
	    }

	    public Enemigo getEnemigo() {
	        return combate.getEnemigo();
	    }
	}


