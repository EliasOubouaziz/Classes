package fr.miage.toulouse.interfaceGraphique;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Classe chargementControleur controleur de l'écran de chargement d'un fichier
 * 
 * @author MARTRES Robin
 *
 */
public class ChargementControleur {

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

	// Sélecteur de fichier
	final FileChooser fileChooser = new FileChooser();

	/**
	 * Méthode retourMenu :
	 * 
	 * Lors d'un clic sur le bouton retour, renvoie à la page de chargement
	 * principale
	 * 
	 * @param e Event clic sur btnRetour
	 * @throws IOException Exception
	 */
	public void retourMenu(ActionEvent e) throws IOException {
		System.out.println("Chargement - clic sur btnRetour");

		// Fermeture de la fenetre
		Stage fen = (Stage) btnRetour.getScene().getWindow();
		fen.close();

		// Chargement de la scène suivante
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/fr/miage/toulouse/interfaceGraphique/ChargementMainScene.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();

		// Création de la nouvelle fenêtre
		Stage newFen = new Stage();
		newFen.setTitle("Menu");
		newFen.setScene(new Scene(root1));
		newFen.setResizable(false);

		// Affichage de la fenêtre
		newFen.show();
		newFen.centerOnScreen();

	}

	/**
	 * Méthode parcourirElement :
	 * 
	 * Lors d'un clic sur le bouton parcourir (...) affiche l'url du fichier choisi
	 * 
	 * @param e Event clic sur bouton
	 */
	public void parcourirElement(ActionEvent e) {
		// Vide le champ texte
		tfElem.clear();

		// Sélection du fichier à charger
		File file = fileChooser.showOpenDialog((Stage) btnParcoursElem.getScene().getWindow());
		// S'il n'est pas null, récupére son url et l'affiche dans le champ texte
		if (file != null) {
			String url = file.getAbsolutePath();
			tfElem.setText(url);
		}
	}

	/**
	 * Méthode parcourirChaine :
	 * 
	 * Lors d'un clic sur le bouton parcourir chaine de prod (...) affiche l'url du
	 * fichier choisi
	 * 
	 * @param e
	 */
	public void parcourirChaine(ActionEvent e) {
		tfChaine.clear();
		File file = fileChooser.showOpenDialog((Stage) btnParcoursChaine.getScene().getWindow());
		if (file != null) {
			String url = file.getAbsolutePath();
			tfChaine.setText(url);
		} else {
			System.out.println("cherchez un fichier à charger");
		}
	}

	/**
	 * Méthode permettant d'aller chercher le fichier csv des commandes
	 * 
	 * @param e action lors du clic sur le bouton parcourrir
	 */
	public void parcourirCommande(ActionEvent e) {
		tfCommande.clear();
		File file = fileChooser.showOpenDialog((Stage) btnParcoursComm.getScene().getWindow());
		if (file != null) {
			String url = file.getAbsolutePath();
			tfCommande.setText(url);
		}
	}

	/**
	 * Méthode chargerElements qui charge dans l'application les élements depuis le
	 * fichier csv
	 * 
	 * @param e action lors du clic sur le bouton parcourrir
	 */
	public void chargerElements(ActionEvent e) {
		System.out.println("Chargement - clic sur btnChargerElem");
		String url = "";
		url = tfElem.getText();
		if (url != "") {
			fr.miage.toulouse.dev.LireFich.LireFichierElements(url);
		} else {
			System.out.println("cherchez un fichier à charger");
		}
	}

	/**
	 * Charge l'url du fichier csv des chaines de production dans le textfield
	 * correspondant
	 * 
	 * @param e
	 */
	public void chargerChaine(ActionEvent e) {
		System.out.println("Chargement - clic sur btnChargerChaine");
		String url = "";
		url = tfChaine.getText();
		System.out.println(url);
		if (url != "") {
			fr.miage.toulouse.dev.LireFich.LireFichierChaineProd(url);
		} else {
			System.out.println("cherchez un fichier à charger");
		}
	}

	/**
	 * Charge l'url du fichier csv des commandes dans le textfield correspondant
	 * 
	 * @param e
	 */
	public void chargerCommande(ActionEvent e) {
		System.out.println("Chargement - clic sur btnChargerComm");
		String url = "";
		url = tfCommande.getText();
		System.out.println(url);
		if (url != "") {
			fr.miage.toulouse.dev.LireFich.LireFichierAchats(url);
			fr.miage.toulouse.dev.LireFich.LireFichierVentes(url);
		} else {
			System.out.println("cherchez un fichier à charger");
		}
	}
}
