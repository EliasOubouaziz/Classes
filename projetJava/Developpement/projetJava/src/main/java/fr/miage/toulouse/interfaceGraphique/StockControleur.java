package fr.miage.toulouse.interfaceGraphique;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import fr.miage.toulouse.dev.Element;
import fr.miage.toulouse.dev.LireFich;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StockControleur {

	@FXML
	private BorderPane border;

	@FXML
	private Button btnRetour;

	@FXML
	private Button btnChargmnt;

	@FXML
	private TableView<Element> tableStock;

	@FXML
	private TableColumn<Element, String> colCode;

	@FXML
	private TableColumn<Element, String> colNom;

	@FXML
	private TableColumn<Element, Double> colStock;

	@FXML
	private TableColumn<Element, String> colUnit;

	@FXML
	private Button btnSave;

	// Sélecteur de fichier
	final FileChooser fileChooser = new FileChooser();

	/**
	 * Initialise les éléments du tableau
	 * 
	 */
	@FXML
	public void initialize() {
		// Affecte les éléments dans le tableau
		tableStock.setItems(getElements());

	}

	/**
	 * getElements
	 * 
	 * @return la liste des Eléments à afficher dans le tableau des stocks
	 */
	private ObservableList<Element> getElements() {

		// Récupère la liste des Elements chargés depuis le CSV
		ArrayList<Element> listElem = LireFich.getListElem();
		ObservableList<Element> elems = FXCollections.observableArrayList();

		for (Element e : listElem) {
			elems.add(e);
		}

		return elems;
	}

	/**
	 * Redirection vers la page de chargement
	 * 
	 * @param e
	 * @throws IOException
	 */
	public void versChargement(ActionEvent e) throws IOException {
		System.out.println("Stock - Clic sur btnChargmnt, Changement de fenêtre");

		// Fermeture de la fenetre
		Stage fen = (Stage) btnChargmnt.getScene().getWindow();

		fen.close();

		// Chargement de la scène suivante
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/fr/miage/toulouse/interfaceGraphique/ChargementMainScene.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();

		// Création de la nouvelle fenêtre
		Stage newFen = new Stage();
		newFen.setTitle("Chargement des données");
		newFen.setScene(new Scene(root1));
		newFen.setResizable(false);
		newFen.show();
		newFen.centerOnScreen();
	}

	/**
	 * Redirection vers le menu
	 * 
	 * @param e
	 * @throws IOException
	 */
	public void retourMenu(ActionEvent e) throws IOException {
		System.out.println("Stock - clic sur btnRetour");

		// Fermeture de la fenetre
		Stage fen = (Stage) btnRetour.getScene().getWindow();

		fen.close();

		// Chargement de la scène suivante
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/fr/miage/toulouse/interfaceGraphique/MenuScene.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();

		// Création de la nouvelle fenêtre
		Stage newFen = new Stage();
		newFen.setTitle("Chargement des données");
		newFen.setScene(new Scene(root1));
		newFen.setResizable(false);
		newFen.show();
		newFen.centerOnScreen();
	}

	/**
	 * Sauvegarde dans un fichier csv les stocks
	 * 
	 * @param e
	 */
	public void sauvegarde(ActionEvent e) {
		System.out.println("ChaineProd - clic sur btnSave");

		// Set extension filter for text files
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV Files (*.csv)", "*.csv");
		fileChooser.getExtensionFilters().add(extFilter);

		// Sélection du fichier à enregistrer
		File file = fileChooser.showSaveDialog((Stage) btnSave.getScene().getWindow());

		// S'il n'est pas null, enregistre
		if (file != null) {

			String url = file.getAbsolutePath();

			// récupère la liste des chaines de production
			ObservableList<Element> listElem = this.getElements();

			File file2 = new File(url);
			FileWriter fw;

			try {

				fw = new FileWriter(file2);
				fw.write("Code;Nom;Quantité;Unité\n");
				for (Element elem : listElem) {
					fw.write(elem.getId() + ";");
					fw.write(elem.getNom() + ";");
					fw.write(elem.getQte() + ";");
					fw.write(elem.getUnite() + "\n");
				}

				fw.close();

			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}

	}

}
