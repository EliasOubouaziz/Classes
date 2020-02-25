package fr.miage.toulouse.interfaceGraphique;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ChargementMainControleur {

	@FXML
	private Button btnRetour;

	@FXML
	private Button btnChFic;

	@FXML
	private Button btnChargementDossier;

	@FXML
	private Label resChDossier;

	// File chooser permettant de récupérer l'url du dossier
	final DirectoryChooser dChooser = new DirectoryChooser();

	public void retourMenu(ActionEvent e) throws IOException {
		System.out.println("ChargementMain - clic sur btnRetour");

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

	public void versChargementFichier(ActionEvent e) throws IOException {
		System.out.println("ChargementMain - clic sur btnChFic");

		// Fermeture de la fenetre
		Stage fen = (Stage) btnChFic.getScene().getWindow();

		fen.close();

		// Chargement de la scène suivante
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/fr/miage/toulouse/interfaceGraphique/ChargementScene.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();

		// Création de la nouvelle fenêtre
		Stage newFen = new Stage();
		newFen.setTitle("Chargement Fichier");
		newFen.setScene(new Scene(root1));
		newFen.setResizable(false);
		newFen.show();
		newFen.centerOnScreen();

	}

	public void chargementDossier(ActionEvent e) throws IOException {
		System.out.println("ChargementMain - clic sur btnChargementDossier");

		// File chooser

		// Affecte les paramètres
		configurationDChooser(dChooser);

		// On récupère le répertoire
		File dir = dChooser.showDialog((Stage) btnChargementDossier.getScene().getWindow());
		if (dir != null) {
			String urlD = dir.getAbsolutePath();
			resChDossier.setText("Le dossier à bien été trouvé à l'adresse : " + urlD);

			Boolean[] res = fr.miage.toulouse.dev.LireFich.LireFichier(urlD);
			
			String msg = "Les fichiers suivant ont été chargés : \n";
			if(res[0]) {
				msg += "chaines | ";
			}
			if(res[1]) {
				msg += "elements | ";
			}
			if(res[2]) {
				msg += "prix | ";
			}
			resChDossier.setText(msg);

		} else {
			resChDossier.setText("Erreur - Le dossier choisi est null");
		}

	}

	private void configurationDChooser(DirectoryChooser d) {
		// Définit le titre de la fenêtre de sélection
		d.setTitle("Sélectionnez un dossier");
		// Définit le répertoire initial
		//d.setInitialDirectory(new File(System.getProperty("user.home")));
	}

}
