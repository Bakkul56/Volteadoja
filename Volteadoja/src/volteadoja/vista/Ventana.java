package volteadoja.vista;

import volteadoja.modelo.Heroe;
import volteadoja.modelo.Enemigo;

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

// estados carta y partida
import volteadoja.logica.Resultado;
import volteadoja.logica.EstadoPartida; 

//Transicion
import javafx.animation.PauseTransition;
import javafx.util.Duration;

// Para el css
import javafx.scene.Parent;

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
        titulo.getStyleClass().add("titulo");
        Button btnStart = new Button("Comenzar");
        btnStart.setOnAction(e -> stage.setScene(crearJuego()));

        VBox menu = new VBox(20, titulo, btnStart);
        menu.setAlignment(Pos.CENTER);

        return nuevaEscena(menu);
    }
    
    // Metodo para crear escenas(Menu)
    private Scene crearJuego() {
    	
    	Juego partida = new Juego();
    	
        Label lblEnemigo = new Label();
        Label lblHeroe = new Label();
        lblEnemigo.getStyleClass().add("personaje");
        lblHeroe.getStyleClass().add("personaje");
        
        actualizarCampo(partida, lblHeroe, lblEnemigo);   // texto inicial
        
        
        BorderPane campo = new BorderPane();
        campo.setLeft(lblEnemigo);
        campo.setRight(lblHeroe);
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
                btnCarta.getStyleClass().add("carta");
                btnCarta.setOnAction(e -> {
                    Resultado r = partida.seleccionar(f, c);
                    actualizarTablero(partida, botones);   // mostrar ya la carta volteada
                    actualizarCampo(partida, lblHeroe, lblEnemigo);
                    
                    EstadoPartida estado = partida.getEstado();              
                    if (estado == EstadoPartida.VICTORIA) {                  
                        stage.setScene(crearFin("Victoria! El enemigo fue derrotado."));
                        return;
                    }
                    if (estado == EstadoPartida.DERROTA) {                   
                        stage.setScene(crearFin("Derrota..."));
                        return;
                    }
                    if (r == Resultado.NO_PAREJA) {
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
        campo.getStyleClass().add("campo");
        juego.setTop(btnSalir);
        juego.setCenter(campo);
        juego.setBottom(tablero);
        
        return nuevaEscena(juego);
    }
    
    
    // Actualizamos todos los botones para que muestren su estado actual.
    private void actualizarTablero(Juego partida, Button[][] botones) {
        for (int fila = 0; fila < 5; fila++) {
            for (int columna = 0; columna < 5; columna++) {
                Carta carta = partida.getCarta(fila, columna);
                Button boton = botones[fila][columna];

                // Quitar la clase por defecto se vuelve a poner si está boca arriba
                boton.getStyleClass().remove("descubierta");
                
                if (carta.isEliminada()) {
                    boton.setText("");
                    boton.setDisable(true);
                } else if (carta.isDescubierta()) {
                    boton.setText(carta.getNombre());
                    boton.setDisable(false);
                    boton.getStyleClass().add("descubierta");
                } else {
                    boton.setText("?");
                    boton.setDisable(false);
                }
            }
        }
    }
    
    // Muestra nombre y vida actual de cada personaje
    private void actualizarCampo(Juego partida, Label lblHeroe, Label lblEnemigo) {
        Heroe h = partida.getHeroe();
        Enemigo en = partida.getEnemigo();

        lblHeroe.setText(h.getNombre()
                + "\nHP " + h.getVidaActual() + "/" + h.getVidaMaxima()
                + "\nATQ " + h.getAtaque()
                + "\nDEF " + h.getDefensa());

        lblEnemigo.setText(en.getNombre()
                + "\nHP " + en.getVidaActual() + "/" + en.getVidaMaxima()
                + "\nATQ " + en.getAtaque()
                + "\nDEF " + en.getDefensa());
    }
    
    // Pantalla final: muestra el resultado y vuelve al menu
    private Scene crearFin(String mensaje) {
        Label texto = new Label(mensaje);
        Button btnMenu = new Button("Volver al menu");
        btnMenu.setOnAction(e -> stage.setScene(crearMenu()));

        VBox fin = new VBox(20, texto, btnMenu);
        fin.setAlignment(Pos.CENTER);

        return nuevaEscena(fin);
    }
    
    // Crea una escena con la hoja de estilos aplicada
    private Scene nuevaEscena(Parent raiz) {
        Scene escena = new Scene(raiz, 720, 900);
        escena.getStylesheets().add(
                getClass().getResource("estilos.css").toExternalForm());
        return escena;
    }
    
}
