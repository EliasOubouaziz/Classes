package fr.miage.toulouse.interfaceGraphique;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Menu extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		//Lit le fichier xml
		Parent root = FXMLLoader.load(getClass().getResource("/fr/miage/toulouse/interfaceGraphique/MenuScene.fxml"));
		
		//titre de la fenetre
		primaryStage.setTitle("Menu");
		
		// La fenetre est rédimensionnable ou non
		primaryStage.setResizable(false);
		
		//On affecte la scene
		primaryStage.setScene(new Scene(root));
		
		//Affichage et centrage
		primaryStage.show();
		primaryStage.centerOnScreen();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
