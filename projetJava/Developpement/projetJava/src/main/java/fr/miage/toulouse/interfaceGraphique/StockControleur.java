package fr.miage.toulouse.interfaceGraphique;

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
		//Affecte les �l�ments dans le tableau
		tableStock.setItems(getElements());

	}

	/**getElements 
	 * 
	 * @return la liste des El�ments � afficher dans le tableau des stocks
	 */
	private ObservableList<Element> getElements() {
		
		//R�cup�re la liste des Elements charg�s depuis le CSV
		ArrayList<Element> listElem = LireFich.getListElem();
		ObservableList<Element> elems = FXCollections.observableArrayList();
		
		for(Element e : listElem) {
			elems.add(e);
		}
		
		return elems;
	}

	public void versChargement(ActionEvent e) throws IOException {
		System.out.println("Stock - Clic sur btnChargmnt, Changement de fen�tre");

		// Fermeture de la fenetre
		Stage fen = (Stage) btnChargmnt.getScene().getWindow();

		fen.close();

		// Chargement de la sc�ne suivante
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/fr/miage/toulouse/interfaceGraphique/ChargementScene.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();

		// Cr�ation de la nouvelle fen�tre
		Stage newFen = new Stage();
		newFen.setTitle("Chargement des donn�es");
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

		// Chargement de la sc�ne suivante
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/fr/miage/toulouse/interfaceGraphique/MenuScene.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();

		// Cr�ation de la nouvelle fen�tre
		Stage newFen = new Stage();
		newFen.setTitle("Chargement des donn�es");
		newFen.setScene(new Scene(root1));
		newFen.setResizable(false);
		newFen.show();
		newFen.centerOnScreen();
	}

}
