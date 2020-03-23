package fr.miage.toulouse.interfaceGraphique;

import java.io.File;
import java.io.IOException;

import fr.miage.toulouse.dev.ChaineDeProd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class ComparaisonControleur {

	@FXML
	private TextField totalGauche;

	@FXML
	private TextField totalDroite;

	@FXML
	private Button btnRetour;
	
	@FXML
	private Button btnChargerG;
	
	@FXML
	private Button btnChargerD;

	// Tableau de Gauche
	@FXML
	private TableView<ChaineDeProd> tableCdpG;

	@FXML
	private TableColumn<ChaineDeProd, String> colIdG;

	@FXML
	private TableColumn<ChaineDeProd, String> colNomG;

	@FXML
	private TableColumn<ChaineDeProd, Integer> colEtatG;

	@FXML
	private TableColumn<ChaineDeProd, Double> colCoutG;

	// Tableau de Droite
	@FXML
	private TableView<ChaineDeProd> tableCdpD;

	@FXML
	private TableColumn<ChaineDeProd, String> colIdD;

	@FXML
	private TableColumn<ChaineDeProd, String> colNomD;

	@FXML
	private TableColumn<ChaineDeProd, Integer> colEtatD;

	@FXML
	private TableColumn<ChaineDeProd, Double> colCoutD;
	
	// Sélecteur de fichier
	final FileChooser fileChooser = new FileChooser();

	/**
	 * Méthode appelée lors d'un clic sur le bouton retour qui fait revenir au menu
	 * principal
	 * 
	 * @param e event du clic
	 * @throws IOException Exception levée
	 */
	public void retourMenu(ActionEvent e) throws IOException {
		System.out.println("Comparaison - clic sur btnRetour");

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
	
	private void chargementGauche(ActionEvent e) {
		File file = fileChooser.showOpenDialog((Stage) btnChargerG.getScene().getWindow());
		if (file != null) {
			String url = file.getAbsolutePath();
		}
	}

}
