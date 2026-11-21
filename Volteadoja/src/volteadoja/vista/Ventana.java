package volteadoja.vista;

import javafx.application.Application;
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
        
        Label tablero = new Label("Card field goes here");

        BorderPane juego = new BorderPane();
        juego.setTop(btnSalir);
        juego.setCenter(campo);
        juego.setBottom(tablero);
        
        return new Scene(juego, 720, 900);
    }

}
