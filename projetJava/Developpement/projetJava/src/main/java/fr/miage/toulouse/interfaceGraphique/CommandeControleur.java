package fr.miage.toulouse.interfaceGraphique;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import fr.miage.toulouse.dev.ChaineDeProd;
import fr.miage.toulouse.dev.Commandes;
import fr.miage.toulouse.dev.Element;
import fr.miage.toulouse.dev.LireFich;
import fr.miage.toulouse.dev.Ventes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

public class CommandeControleur {

	@FXML
	private Button btnRetour;

	@FXML
	private Button btnValiderCommande;

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

	/**
	 * Initialisation du tableau des commandes
	 * 
	 */
	public void initialize() {
		// TODO Auto-generated method stub
		tableComm.setItems(getCommandes());
		tableComm.setEditable(true);

		colQte.setCellFactory(TextFieldTableCell.<Commandes, Double>forTableColumn(new DoubleStringConverter()));
	}

	/**
	 * Permet l'édition des quantités
	 * 
	 * @param etatEdited colonne des Quantités
	 */
	public void EditQte(TableColumn.CellEditEvent<Commandes, Double> etatEdited) {
		Commandes Comm = tableComm.getSelectionModel().getSelectedItem();
		if (etatEdited.getNewValue() >= 0) {
			Comm.setQte(etatEdited.getNewValue());

			initialize();
		} else {
			Comm.setQte(0);
			initialize();
		}
	}

	/**
	 * Getter de la liste des commandes
	 * 
	 * @return la liste des commandes
	 */
	private ObservableList<Commandes> getCommandes() {

		// Récupère la liste des Elements chargés depuis le CSV
		ArrayList<Commandes> listComm = ChaineDeProd.getlistCommandes();
		ObservableList<Commandes> Comm = FXCollections.observableArrayList();

		for (Commandes c : listComm) {
			Comm.add(c);
		}

		return Comm;
	}

	/**
	 * Permet de retourner au menu
	 * 
	 * @param e Action lors du clic sur le bouton
	 * @throws IOException Erreur sur lévênement
	 */
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
	
	public void actualiserStock() {
		ArrayList<Element> ListElem = LireFich.getListElem();
		for(Commandes cmd : ChaineDeProd.getlistCommandes()) {
			for(Element elem : ListElem) {
				if(cmd.getId().equals(elem.getId())) {
					elem.setQte(cmd.getQte()+elem.getQte());
				}
			}
		}
		ChaineDeProd.vidercommandes();
		
	}
	
	public void actualiserStocketlancer() {
		ArrayList<Element> ListElem = LireFich.getListElem();
		for(Commandes cmd : ChaineDeProd.getlistCommandes()) {
			for(Element elem : ListElem) {
				if(cmd.getId().equals(elem.getId())) {
					double quantité=elem.getQte();
					elem.setQte(cmd.getQte()+elem.getQte());
					elem.setQte(elem.getQte()-(cmd.getQte()+quantité));
				}
			}
		}
		
		ChaineDeProd.vidercommandes();
		
		for(ChaineDeProd cdp : LireFich.getListChdP()) {
			Set listKeys1 = cdp.getSortie().keySet();
			Iterator iterateur1 = listKeys1.iterator(); 
			while (iterateur1.hasNext()) {
				Object key1 = iterateur1.next();
				for(Element elem : ListElem) {
					if(key1.toString().equals(elem.getId())) {
						elem.setQte(cdp.getSortie().get(key1).doubleValue()*cdp.getActivation());
					}
				}
	
			}
			cdp.setActivation(0);
			double cout = cdp.coutCdP(LireFich.getListVentes(), LireFich.getListAchats(), LireFich.getListElem(), 0);
			cdp.setCout(cout);
			cdp.setActivation(0);
			cdp.setTemps(cdp.getTempsinit());
			cdp.setPersNonQualifie(cdp.getPersNonQualifieinit());
			cdp.setPersQualifie(cdp.getPersQualifieinit());
		}
				
		
	}
	
	public void clicValider(ActionEvent e) throws IOException {
		System.out.println("clic sur btnValider - popup");
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Valider");
		alert.setHeaderText("Sauvegarde des achats, voulez-vous lancer les chaines de production ?");

		ButtonType oui = new ButtonType("OUI");
		ButtonType non = new ButtonType("NON");
		ButtonType annuler = new ButtonType("ANNULER");

		// Remove default ButtonTypes
		alert.getButtonTypes().clear();

		alert.getButtonTypes().addAll(oui, non, annuler);

		// option != null.
		Optional<ButtonType> option = alert.showAndWait();

		if (option.get() == null) {
			System.out.println("NULL");
		} else if (option.get() == oui) {
			System.out.println("OUI");
			actualiserStocketlancer();
			
			// Fermeture de la fenetre
			Stage fen = (Stage) btnRetour.getScene().getWindow();

			fen.close();

			// Chargement de la scène suivante
			FXMLLoader fxmlLoader = new FXMLLoader(
					getClass().getResource("/fr/miage/toulouse/interfaceGraphique/ChaineProdScene.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();

			// Création de la nouvelle fenêtre
			Stage newFen = new Stage();
			newFen.setTitle("Gestion Chianes de Production");
			newFen.setScene(new Scene(root1));
			newFen.setResizable(false);
			newFen.show();
			newFen.centerOnScreen();
			
		} else if (option.get() == non) {
			System.out.println("NON");
			actualiserStock();
			// Fermeture de la fenetre
			Stage fen = (Stage) btnRetour.getScene().getWindow();

			fen.close();

			// Chargement de la scène suivante
			FXMLLoader fxmlLoader = new FXMLLoader(
					getClass().getResource("/fr/miage/toulouse/interfaceGraphique/ChaineProdScene.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();

			// Création de la nouvelle fenêtre
			Stage newFen = new Stage();
			newFen.setTitle("Gestion Chianes de Production");
			newFen.setScene(new Scene(root1));
			newFen.setResizable(false);
			newFen.show();
			newFen.centerOnScreen();
			
		} else if (option.get() == annuler) {
			System.out.println("Annuler");
		} else {
			System.out.println("rien");
		}
	}

}
