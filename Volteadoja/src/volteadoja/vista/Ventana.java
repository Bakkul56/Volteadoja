package volteadoja.vista;

import javafx.application.Application;

//Modelo del juego
import volteadoja.logica.Juego;
import volteadoja.modelo.Carta;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;

// For layout usage
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

//Para Boder Plane

import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;

// Para GridPane

import javafx.scene.layout.GridPane;

// comportamiento de la carta
import volteadoja.logica.Resultado;

//Transicion
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class Ventana extends Application {
	
	private Stage stage;   // Se llega desde cualquier metodo
	
	@Override
	public void start(Stage stage) {
		
		this.stage = stage;
        stage.setTitle("Volteadoja");
        stage.setScene(crearMenu());
        stage.show();
	}
	
	// Metodo para crear escenas(Menu)
    private Scene crearMenu() {
        Label titulo = new Label("Volteadoja");
        Button btnStart = new Button("Comenzar");
        btnStart.setOnAction(e -> stage.setScene(crearJuego()));

        VBox menu = new VBox(20, titulo, btnStart);
        menu.setAlignment(Pos.CENTER);

        return new Scene(menu, 720, 900);
    }
    
    // Metodo para crear escenas(Menu)
    private Scene crearJuego() {
    	
    	Juego partida = new Juego();
    	
        VBox enemigos = new VBox(40,
                new Label("Goblin 1"),
                new Label("Dragon"),
                new Label("Goblin 2"));
        enemigos.setAlignment(Pos.CENTER);

        VBox heroes = new VBox(40,
                new Label("Mago"),
                new Label("Guerrero"));
        heroes.setAlignment(Pos.CENTER);
        
        
        BorderPane campo = new BorderPane();
        campo.setLeft(enemigos);
        campo.setRight(heroes);
        campo.setPadding(new Insets(80));
        
        Button btnSalir = new Button("Salir");
        btnSalir.setOnAction(e -> stage.setScene(crearMenu()));
        
        GridPane tablero = new GridPane();
        tablero.setHgap(8);
        tablero.setVgap(8);
        tablero.setAlignment(Pos.CENTER);
        tablero.setPadding(new Insets(20));
        
        //Llenamos el grid
        Button[][] botones = new Button[5][5];
        for (int fila = 0; fila < 5; fila++) {
            for (int columna = 0; columna < 5; columna++) {
            	final int f = fila;
            	final int c = columna;
            	

                Button btnCarta = new Button("?");
                btnCarta.setPrefSize(70, 95);
                btnCarta.setOnAction(e -> {
                    Resultado r = partida.seleccionar(f, c);
                    actualizarTablero(partida, botones);   // mostrar ya la carta volteada

                    if (r == Resultado.NO_PAREJA || r == Resultado.JOKER) {
                        PauseTransition pausa = new PauseTransition(Duration.seconds(1));
                        pausa.setOnFinished(ev -> {
                            partida.ocultarFallidas();
                            actualizarTablero(partida, botones);
                        });
                        pausa.play();
                    }
                });
   
                tablero.add(btnCarta, columna, fila);
                botones[fila][columna] = btnCarta; //Guardando en el array
            	
            }
        }
        
        BorderPane juego = new BorderPane();
        juego.setTop(btnSalir);
        juego.setCenter(campo);
        juego.setBottom(tablero);
        
        return new Scene(juego, 720, 900);
    }
    
    
    // Actualizamos todos los botones para que muestren su estado actual.
    private void actualizarTablero(Juego partida, Button[][] botones) {
        for (int fila = 0; fila < 5; fila++) {
            for (int columna = 0; columna < 5; columna++) {
                Carta carta = partida.getCarta(fila, columna);
                Button boton = botones[fila][columna];

                if (carta.isEliminada()) {
                    boton.setText("");
                    boton.setDisable(true);
                } else if (carta.isDescubierta()) {
                    boton.setText(carta.getNombre());
                } else {
                    boton.setText("?");
                }
            }
        }
    }
    
    
}
