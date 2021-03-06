package fr.miage.toulouse.interfaceGraphique;

import java.io.File;
import java.io.FileWriter;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

/**
 * Classe controleur de l'�cran des chaines de productions
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
	private TableColumn<ChaineDeProd, Integer> colTemps;
	@FXML
	private TableColumn<ChaineDeProd, Integer> colNonQualif;
	@FXML
	private TableColumn<ChaineDeProd, Integer> colQualif;
	@FXML
	private Button btnRetour;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnCharger;

	@FXML
	private Button btnValider;

	// S�lecteur de fichier
	final FileChooser fileChooser = new FileChooser();

	/**
	 * M�thode appel�e lors de l'initialisation de la page. Remplit les tableaux
	 * 
	 */
	@FXML
	public void initialize() {
		// Affecte les informations des chaines de prod au tableau
		tableCdp.setItems(getChainesDeProd());
		tableCdp.setEditable(true);

		colEtat.setCellFactory(TextFieldTableCell.<ChaineDeProd, Integer>forTableColumn(new IntegerStringConverter()));
		sommeCout();
	}

	/**
	 * M�thode permettant d'�diter la colonne du niveau d'activation de la chaine de
	 * production
	 * 
	 * @param etatEdited colonne du niveau d'activation
	 */
	public void EditEtat(TableColumn.CellEditEvent<ChaineDeProd, Integer> etatEdited) {
		ChaineDeProd Cdp = tableCdp.getSelectionModel().getSelectedItem();
		if (etatEdited.getNewValue() >= 0) {
			Cdp.CommandeSup(LireFich.getListAchats(), LireFich.getListElem(), Cdp.getActivation());
			Cdp.setActivation(etatEdited.getNewValue());
			double cout = Cdp.coutCdP(LireFich.getListVentes(), LireFich.getListAchats(), LireFich.getListElem(),
					etatEdited.getNewValue());
			System.out.println(cout);
			Cdp.setCout(cout);
			Cdp.setPersNonQualifie(Cdp.getPersNonQualifieinit() * etatEdited.getNewValue());
			Cdp.setPersQualifie(Cdp.getPersQualifieinit() * etatEdited.getNewValue());
			Cdp.setTemps(Cdp.getTempsinit() * etatEdited.getNewValue());
			initialize();
		} else {
			Cdp.setActivation(0);
			Cdp.setTemps(Cdp.getTempsinit());
			Cdp.setPersNonQualifie(Cdp.getPersNonQualifieinit());
			Cdp.setPersQualifie(Cdp.getPersQualifieinit());
			initialize();
		}
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
			c.getActivation();
		}

		return ChDP;
	}

	/**
	 * M�thode permettant d'afficher le cout total
	 * 
	 */
	private void sommeCout() {
		double somme = 0;
		for (ChaineDeProd c : tableCdp.getItems()) {

			somme = somme + c.getCout();
		}
		total.setText("" + somme);

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

	/**
	 * changementEtat
	 * 
	 * Lors de la modification de l'�tat d'une chaine de prod affecte le nouvel etat
	 * 
	 * @param e Ev�nement
	 */
	public void changementEtat(ActionEvent e) {
		System.out.println("MODIFICATION");
	}

	/**
	 * M�thode permettant la sauvegarde dans un fichier csv de l'�tat des chaines de
	 * production
	 * 
	 * @param e Action du clic sur le bouton sauvegarder
	 */
	public void sauvegarde(ActionEvent e) {
		System.out.println("ChaineProd - clic sur btnSave");

		// Set extension filter for text files
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV Files (*.csv)", "*.csv");
		fileChooser.getExtensionFilters().add(extFilter);

		// S�lection du fichier � enregistrer
		File file = fileChooser.showSaveDialog((Stage) btnSave.getScene().getWindow());

		// S'il n'est pas null, enregistre
		if (file != null) {

			String url = file.getAbsolutePath();

			// r�cup�re la liste des chaines de production
			ObservableList<ChaineDeProd> listChdP = this.getChainesDeProd();

			File file2 = new File(url);
			FileWriter fw;

			try {

				fw = new FileWriter(file2);
				fw.write("Code;Nom;Entree code,qte;Sortie code,qte;Niveau activation\n");
				for (ChaineDeProd cdp : listChdP) {
					fw.write(cdp.getId() + ";");
					fw.write(cdp.getNom() + ";");
					fw.write(cdp.getEntree() + ";");
					fw.write(cdp.getSortie() + ";");
					fw.write(cdp.getActivation() + "\n");
				}

				fw.close();

			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}

	}

	/**
	 * M�thode permettant de passer � la page de comparaison
	 * 
	 * @param e
	 * @throws IOException
	 */
	public void versChargement(ActionEvent e) throws IOException {
		System.out.println("ChaineProd - clic sur btnCharger");

		// Fermeture de la fenetre
		Stage fen = (Stage) btnCharger.getScene().getWindow();

		fen.close();

		// Chargement de la sc�ne suivante
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/fr/miage/toulouse/interfaceGraphique/Comparaison.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();

		// Cr�ation de la nouvelle fen�tre
		Stage newFen = new Stage();
		newFen.setTitle("Comparaison");
		newFen.setScene(new Scene(root1));
		newFen.setResizable(false);
		newFen.show();
		newFen.centerOnScreen();

	}

	/**
	 * 
	 * @param e
	 * @throws IOException 
	 */
	public void clicValidation(ActionEvent e) throws IOException {
		System.out.println("Clic sur btnValider - Popup Validation");

		// Fermeture de la fenetre
		Stage fen = (Stage) btnValider.getScene().getWindow();

		fen.close();

		// Chargement de la sc�ne suivante
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/fr/miage/toulouse/interfaceGraphique/CommandeScene.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();

		// Cr�ation de la nouvelle fen�tre
		Stage newFen = new Stage();
		newFen.setTitle("Comparaison");
		newFen.setScene(new Scene(root1));
		newFen.setResizable(false);
		newFen.show();
		newFen.centerOnScreen();

		
	}

}
