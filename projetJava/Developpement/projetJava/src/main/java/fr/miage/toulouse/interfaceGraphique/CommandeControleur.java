package fr.miage.toulouse.interfaceGraphique;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class CommandeControleur implements Initializable {

	@FXML
	private Button btnRetour;

	@FXML
	private Button btnValiderComm;

	@FXML
	private TableColumn<?, ?> colCode;

	@FXML
	private TableColumn<?, ?> colNom;

	@FXML
	private TableColumn<?, ?> colPU;

	@FXML
	private TableColumn<?, ?> colQte;

	@FXML
	private TableColumn<?, ?> colPrixTot;

	@FXML
	private ToggleGroup commande;

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void retourMenu(ActionEvent e) throws IOException {
		System.out.println("Chargement - clic sur btnRetour");

		// Fermeture de la fenetre
		Stage fen = (Stage) btnRetour.getScene().getWindow();

		fen.close();

		// Chargement de la scène suivante
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/fr/miage/toulouse/interfaceGraphique/MenuScene.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();

		// Création de la nouvelle fenêtre
		Stage newFen = new Stage();
		newFen.setTitle("Menu");
		newFen.setScene(new Scene(root1));
		newFen.setResizable(false);
		newFen.show();
		newFen.centerOnScreen();

	}

}
