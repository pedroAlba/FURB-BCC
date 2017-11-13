package application;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application{


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		URL urlTela = this.getClass().getClassLoader().getResource("TelaPrincipal.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(urlTela);
		Parent mainParent = fxmlLoader.load();
		Scene scene = new Scene(mainParent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(false);


		String css = this.getClass().getClassLoader().getResource("Estilo.css").toExternalForm();
		scene.getStylesheets().add(css);

		stage.show();
	}
}
