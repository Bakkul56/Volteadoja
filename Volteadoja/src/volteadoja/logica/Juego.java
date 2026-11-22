package volteadoja.logica;

import java.util.Scanner;
import volteadoja.modelo.Carta;
import volteadoja.modelo.Mazo;
import volteadoja.modelo.Heroe;
import volteadoja.modelo.Enemigo;

public class Juego {
	
	 private Tablero tablero;
	 private Scanner scanner;
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
	        
	        scanner = new Scanner(System.in);

	        turnos = 0;
	    }

	    // Iniciar partida Start duel
	    public void iniciar() {

	        System.out.println("=== VOLTEADOJA ===");
	        System.out.println("Encuentra las 12 parejas.");

	        while (!tablero.todasLasParejasEncontradas()) {

	            tablero.mostrarTablero();

	            System.out.println("\nTurno: " + (turnos + 1));

	            Carta carta1 = seleccionarCarta("primera");
	            carta1.voltear();

	            tablero.mostrarTablero();

	            Carta carta2 = seleccionarCarta("segunda");
	            carta2.voltear();

	            tablero.mostrarTablero();

	            // Comprobar resultado no como las votaciones en colombia
	            if (carta1.esPareja(carta2)) {

	                System.out.println("\n¡Encontraste una pareja!");
	                System.out.println("Habilidad: "
	                        + carta1.getNombre());

	                carta1.eliminar();
	                carta2.eliminar();

	            } else if (carta1.getId() == 0
	                    || carta2.getId() == 0) {

	                System.out.println("\n¡Encontraste el JOKER!");

	                // El efecto especial se programará después activo el efecto de mi mago del tiempo
	                carta1.ocultar();
	                carta2.ocultar();

	            } else {

	                System.out.println("\nNo son pareja.");

	                carta1.ocultar();
	                carta2.ocultar();
	                
	                //Ataque enemigo al fallar
	                combate.turnoEnemigo();
	                
	             
	            }

	            turnos++;
	        }

	        System.out.println("\n¡Felicidades!");
	        System.out.println("Encontraste todas las parejas.");
	        System.out.println("Turnos utilizados: " + turnos);
	    }

	    // Seleccionar una carta yo te invoco mago oscuro
	    private Carta seleccionarCarta(String orden) {

	        while (true) {

	            System.out.println(
	                "\nSelecciona la " + orden + " carta."
	            );

	            int fila = leerNumero("Fila (1-5): ");
	            int columna = leerNumero("Columna (1-5): ");

	            Carta carta = tablero.getCarta(
	                fila - 1, columna - 1
	            );

	            if (carta == null) {

	                System.out.println("Posicion invalida.");

	            } else if (carta.isEliminada()) {

	                System.out.println("Esa carta ya fue eliminada.");

	            } else if (carta.isDescubierta()) {

	                System.out.println("Esa carta ya esta descubierta.");

	            } else {

	                return carta;
	            }
	        }
	    }

	    // Leer números evitando errores de entrada pos pos pos
	    private int leerNumero(String mensaje) {

	        while (true) {

	            System.out.print(mensaje);

	            if (scanner.hasNextInt()) {
	                return scanner.nextInt();
	            }

	            System.out.println("Debes ingresar un numero.");
	            scanner.next();
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


