package fr.miage.toulouse.interfaceGraphique;

import java.io.File;
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
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ChargementControleur implements Initializable {

	@FXML
	private TextField tfElem;
	@FXML
	private TextField tfChaine;
	@FXML
	private TextField tfCommande;

	@FXML
	private Button btnParcoursElem;
	@FXML
	private Button btnParcoursChaine;
	@FXML
	private Button btnParcoursComm;

	@FXML
	private Button btnElem;
	@FXML
	private Button btnChaine;
	@FXML
	private Button btnCommande;

	@FXML
	private Button btnRetour;

	final FileChooser fileChooser = new FileChooser();

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

	public void parcourirElement(ActionEvent e) {
		tfElem.clear();
		File file = fileChooser.showOpenDialog((Stage) btnParcoursElem.getScene().getWindow());
		if (file != null) {
			String url = file.getAbsolutePath();
			tfElem.setText(url);
		}
	}

	public void parcourirChaine(ActionEvent e) {
		tfChaine.clear();
		File file = fileChooser.showOpenDialog((Stage) btnParcoursChaine.getScene().getWindow());
		if (file != null) {
			String url = file.getAbsolutePath();
			tfChaine.setText(url);
		}else {
			System.out.println("cherchez un fichier à charger");
		}
	}

	public void parcourirCommande(ActionEvent e) {
		tfCommande.clear();
		File file = fileChooser.showOpenDialog((Stage) btnParcoursComm.getScene().getWindow());
		if (file != null) {
			String url = file.getAbsolutePath();
			tfCommande.setText(url);
		}
	}

	public void chargerElements(ActionEvent e) {
		System.out.println("Chargement - clic sur btnChargerElem");
		String url = "";
		url = tfElem.getText();
		if (url != "") {
			fr.miage.toulouse.dev.LireFich.LireFichierElements(url);
		}else {
			System.out.println("cherchez un fichier à charger");
		}
	}
	
	public void chargerChaine(ActionEvent e) {
		System.out.println("Chargement - clic sur btnChargerChaine");
		String url = "";
		url = tfChaine.getText();
		System.out.println(url);
		if (url != "") {
			fr.miage.toulouse.dev.LireFich.LireFichierChaineProd(url);
		}else {
			System.out.println("cherchez un fichier à charger");
		}
	}
	
	public void chargerCommande(ActionEvent e) {
		System.out.println("Chargement - clic sur btnChargerComm");
		String url = "";
		url = tfCommande.getText();
		System.out.println(url);
		if (url != "") {
			fr.miage.toulouse.dev.LireFich.LireFichierAchats(url);
			fr.miage.toulouse.dev.LireFich.LireFichierVentes(url);
		}else {
			System.out.println("cherchez un fichier à charger");
		}
	}
}
