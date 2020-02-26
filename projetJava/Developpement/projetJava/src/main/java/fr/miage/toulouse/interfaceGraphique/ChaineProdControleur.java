package fr.miage.toulouse.interfaceGraphique;

import java.io.IOException;
import java.util.ArrayList;

import fr.miage.toulouse.dev.ChaineDeProd;
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
import javafx.stage.Stage;

/**
 * Classe controleur de l'�cran des chaines de productions
 * 
 * @author MARTRES Robin
 *
 */
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
		// Affecte les informations des chaines de prod au tableau
		tableCdp.setItems(getChainesDeProd());
	}

	/**
	 * getChainesDeProd
	 * 
	 * 
	 * @return la liste des Chaines de production � afficher
	 */
	private ObservableList<ChaineDeProd> getChainesDeProd() {

		// R�cup�re la liste des Elements charg�s depuis le CSV
		ArrayList<ChaineDeProd> listChdP = LireFich.getListChdP();
		ObservableList<ChaineDeProd> ChDP = FXCollections.observableArrayList();

		for (ChaineDeProd c : listChdP) {
			ChDP.add(c);
		}
		return ChDP;
	}

	/**
	 * M�thode appel�e lors d'un clic sur le bouton retour qui fait revenir au menu
	 * principal
	 * 
	 * @param e event du clic
	 * @throws IOException Exception lev�e
	 */
	public void retourMenu(ActionEvent e) throws IOException {
		System.out.println("ChaineProd - clic sur btnRetour");

		// Fermeture de la fenetre
		Stage fen = (Stage) btnRetour.getScene().getWindow();

		fen.close();

		// Chargement de la sc�ne suivante
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/fr/miage/toulouse/interfaceGraphique/MenuScene.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();

		// Cr�ation de la nouvelle fen�tre
		Stage newFen = new Stage();
		newFen.setTitle("Menu");
		newFen.setScene(new Scene(root1));
		newFen.setResizable(false);
		newFen.show();
		newFen.centerOnScreen();

	}

	/**changementEtat
	 * 
	 * Lors de la modification de l'�tat d'une chaine de prod
	 * affecte le nouvel etat
	 * 
	 * @param e Ev�nement
	 */
	public void changementEtat(ActionEvent e) {
		System.out.println("MODIFICATION");
	}

}
