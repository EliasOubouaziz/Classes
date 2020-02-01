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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuControleur implements Initializable {

	@FXML
	private AnchorPane rootPane;

	@FXML
	private Button btnQuit;

	@FXML
	private Button btnChargmnt;

	@FXML
	private Button btnEtatStock;

	@FXML
	private Button btnGestionProd;

	@FXML
	private Button btnSaisieComm;

	@FXML
	private Button btnAfficheResult;

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void quitter(ActionEvent e) {
		System.out.println("Menu - Clic sur btnQuit, Fermeture du menu");

		Stage stage = (Stage) btnQuit.getScene().getWindow();
		// Ferme la fenêtre
		stage.close();

	}

	public void versChargement(ActionEvent e) throws IOException {
		System.out.println("Menu - Clic sur btnChargmnt, Changement de fenêtre");

		// Fermeture de la fenetre
		Stage fen = (Stage) btnChargmnt.getScene().getWindow();

		fen.close();

		// Chargement de la scène suivante
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/fr/miage/toulouse/interfaceGraphique/ChargementScene.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();

		// Création de la nouvelle fenêtre
		Stage newFen = new Stage();
		newFen.setTitle("Chargement des données");
		newFen.setScene(new Scene(root1));
		newFen.setResizable(false);
		newFen.show();
		newFen.centerOnScreen();

		// AnchorPane pane =
		// FXMLLoader.lneoad(getClass().getResource("/fr/miage/toulouse/interfaceGraphique/ChargementScene.fxml"));
		// rootPane.getChildren().setAll(pane);
	}

	public void versEtatStock(ActionEvent e) throws IOException {
		System.out.println("Menu - Clic sur btnEtatStock, Changement de fenêtre");

		// Fermeture de la fenetre
		Stage fen = (Stage) btnEtatStock.getScene().getWindow();

		fen.close();

		// Chargement de la scène suivante
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/fr/miage/toulouse/interfaceGraphique/StockScene.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();

		// Création de la nouvelle fenêtre
		Stage newFen = new Stage();
		newFen.setTitle("Etat des Stocks");
		newFen.setScene(new Scene(root1));
		newFen.setResizable(false);
		newFen.show();
		newFen.centerOnScreen();
	}

}
