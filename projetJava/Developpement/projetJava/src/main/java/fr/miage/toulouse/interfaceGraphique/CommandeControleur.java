package fr.miage.toulouse.interfaceGraphique;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fr.miage.toulouse.dev.ChaineDeProd;
import fr.miage.toulouse.dev.Commandes;
import fr.miage.toulouse.dev.LireFich;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class CommandeControleur implements Initializable {

	@FXML
	private Button btnRetour;

	@FXML
	private Button btnValiderComm;
	
	@FXML
	private TableView<Commandes> tableComm;

	@FXML
	private TableColumn<Commandes, String> colCode;

	@FXML
	private TableColumn<Commandes, String> colNom;

	@FXML
	private TableColumn<Commandes, Double> colPU;

	@FXML
	private TableColumn<Commandes, Double> colQte;

	@FXML
	private TableColumn<Commandes, Double> colPrixTot;

	@FXML
	private ToggleGroup commande;

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		tableComm.setItems(getCommandes());
		tableComm.setEditable(true);
	}
	
	private ObservableList<Commandes> getCommandes() {
		
		// Récupère la liste des Elements chargés depuis le CSV
		ArrayList<Commandes> listComm = ChaineDeProd.getlistCommandes();
		ObservableList<Commandes> Comm = FXCollections.observableArrayList();

		for (Commandes c : listComm) {
			Comm.add(c);
		}
		
		return Comm;
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
