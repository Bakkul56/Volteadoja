package volteadoja.vista;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;

// For layout usage
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class Ventana extends Application {
	
	@Override
	public void start(Stage stage) {
		
		Label titulo = new Label("Volteadoja");
		
		//Layout use
		Button btnStart = new Button("Start");
		VBox menu = new VBox(20, titulo, btnStart);
		menu.setAlignment(Pos.CENTER);
		Scene escena = new Scene(menu, 720, 900);		
		

		stage.setTitle("Volteadoja");
		stage.setScene(escena);
		
		stage.show();
	}

}
