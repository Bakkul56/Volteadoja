package volteadoja.vista;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class Ventana extends Application {
	
	@Override
	public void start(Stage stage) {
		
		Label titulo = new Label("Volteadoja");
		Scene escena = new Scene(titulo, 800, 600);
		

		stage.setTitle("Volteadoja");
		stage.setScene(escena);
		
		stage.show();
	}

}
