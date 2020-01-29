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
		
		primaryStage.setTitle("Menu");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
