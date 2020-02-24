package fr.miage.toulouse.interfaceGraphique;

import java.io.IOException;
import fr.miage.toulouse.dev.ChaineDeProd;
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
import javafx.stage.Stage;

public class ChaineProdControleur {

	@FXML
	private TableView<ChaineDeProd> tableCdp;

	@FXML
	private TableColumn<ChaineDeProd, String> colID;

	@FXML
	private TableColumn<ChaineDeProd, String> colNomC;

	@FXML
	private TableColumn<ChaineDeProd, Integer> colEtat;

	@FXML
	private TableColumn<?, ?> colDetail;

	@FXML
	private Button btnRetour;

	@FXML
	public void initialize() {
		tableCdp.setItems(getChainesDeProd());
	}

	private ObservableList<ChaineDeProd> getChainesDeProd() {
		ObservableList<ChaineDeProd> ChDP = FXCollections.observableArrayList();
		ChDP.add(new ChaineDeProd("1", "A1"));
		ChDP.add(new ChaineDeProd("2", "A2"));
		return ChDP;
	}

	public void retourMenu(ActionEvent e) throws IOException {
		System.out.println("ChaineProd - clic sur btnRetour");

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
