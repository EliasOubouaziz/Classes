package fr.miage.toulouse.interfaceGraphique;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import fr.miage.toulouse.dev.ChaineDeProd;
import fr.miage.toulouse.dev.LireFich;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

/**
 * Classe controleur de l'écran des chaines de productions
 * 
 * @author MARTRES Robin
 *
 */
public class ChaineProdControleur {
	
	@FXML
	private TextField total;

	@FXML
	private TableView<ChaineDeProd> tableCdp;

	@FXML
	private TableColumn<ChaineDeProd, String> colID;

	@FXML
	private TableColumn<ChaineDeProd, String> colNomC;

	@FXML
	private TableColumn<ChaineDeProd, Integer> colEtat;

	@FXML
	private TableColumn<ChaineDeProd, Double> colDetail;

	@FXML
	private Button btnRetour;

	
	

	@FXML
	public void initialize() {
		// Affecte les informations des chaines de prod au tableau
		tableCdp.setItems(getChainesDeProd());
		tableCdp.setEditable(true);

		colEtat.setCellFactory(TextFieldTableCell.<ChaineDeProd, Integer>forTableColumn(new IntegerStringConverter()));
		sommeCout();
	}
	
	public void EditEtat(TableColumn.CellEditEvent<ChaineDeProd, Integer> etatEdited) {
		ChaineDeProd Cdp = tableCdp.getSelectionModel().getSelectedItem();
		if(etatEdited.getNewValue() >= 0) {		
			Cdp.setActivation(etatEdited.getNewValue());
			double cout = Cdp.coutCdP(LireFich.getListVentes(),LireFich.getListAchats(), LireFich.getListElem(), etatEdited.getNewValue());
			System.out.println(cout);
			Cdp.setCout(cout);
			initialize();
		}
		else {
			Cdp.setActivation(0);
			initialize();
		}
	}
	/**
	 * getChainesDeProd
	 * 
	 * 
	 * @return la liste des Chaines de production à afficher
	 */
	private ObservableList<ChaineDeProd> getChainesDeProd() {
		
		// Récupère la liste des Elements chargés depuis le CSV
		ArrayList<ChaineDeProd> listChdP = LireFich.getListChdP();
		ObservableList<ChaineDeProd> ChDP = FXCollections.observableArrayList();

		for (ChaineDeProd c : listChdP) {
			ChDP.add(c);
			c.getActivation();
		}
		
		return ChDP;
	}
	
	private void sommeCout() {
		double somme=0;
		for (ChaineDeProd c : tableCdp.getItems()) {
			System.out.println(c.getId());
			somme = somme + c.getCout();
		}
		total.setText(""+somme);
		
	}

	/**
	 * Méthode appelée lors d'un clic sur le bouton retour qui fait revenir au menu
	 * principal
	 * 
	 * @param e event du clic
	 * @throws IOException Exception levée
	 */
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

	/**changementEtat
	 * 
	 * Lors de la modification de l'état d'une chaine de prod
	 * affecte le nouvel etat
	 * 
	 * @param e Evênement
	 */
	public void changementEtat(ActionEvent e) {
		System.out.println("MODIFICATION");
	}

}
