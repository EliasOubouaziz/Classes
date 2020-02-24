package fr.miage.toulouse.interfaceGraphique;

import java.io.IOException;
import fr.miage.toulouse.dev.Element;
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
	public void initialize() {
		//Affecte les éléments dans le tableau
		tableStock.setItems(getElements());

	}

	private ObservableList<Element> getElements() {
		
		ObservableList<Element> elems = FXCollections.observableArrayList();
		elems.add(new Element("1", "Pain", 10.0, "kg"));
		elems.add(new Element("2", "Acier", 10.0, "tonnes"));
		return elems;
	}

	public void versChargement(ActionEvent e) throws IOException {
		System.out.println("Stock - Clic sur btnChargmnt, Changement de fenêtre");

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
	}

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

}
