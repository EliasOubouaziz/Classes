package fr.miage.toulouse.interfaceGraphique;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestFx extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			//Lit le fichier xml
			Parent root = FXMLLoader.load(getClass().getResource("/fr/miage/toulouse/interfaceGraphique/SceneTestFX.fxml"));
			
			primaryStage.setTitle("Salut les amis");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
