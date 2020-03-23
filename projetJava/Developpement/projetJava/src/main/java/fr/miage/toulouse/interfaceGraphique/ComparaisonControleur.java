package fr.miage.toulouse.interfaceGraphique;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
				getClass().getResource("/fr/miage/toulouse/interfaceGraphique/ChaineProdScene.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();

		// Création de la nouvelle fenêtre
		Stage newFen = new Stage();
		newFen.setTitle("Menu");
		newFen.setScene(new Scene(root1));
		newFen.setResizable(false);
		newFen.show();
		newFen.centerOnScreen();

	}

	public void chargementGauche(ActionEvent e) throws IOException {
		System.out.println("Comparaison - clic sur btnGauche");

		File file = fileChooser.showOpenDialog((Stage) btnChargerG.getScene().getWindow());
		if (file != null) {
			String url = file.getAbsolutePath();
			ObservableList<ChaineDeProd> listCG = lireSave(url);

			tableCdpG.setItems(listCG);
			sommeCout();
		}
	}

	private ObservableList<ChaineDeProd> lireSave(String url) {

		Charset charset = Charset.forName("ISO-8859-1");
		Path orderPath = Paths.get(url);
		List<String> lines = null; // null mean no value by default
		try {
			lines = Files.readAllLines(orderPath, charset);
		} catch (IOException e) {
			System.out.println("Impossible de lire le fichier des chaines");
		}
		if (lines.size() < 2) {
			System.out.println("Il n'y a pas de chaines dans le fichier");
			return null;
		}

		ObservableList<ChaineDeProd> listChdp = FXCollections.observableArrayList();

		for (int i = 1; i < lines.size(); i++) {

			String[] split = lines.get(i).split(";");
			String id = String.valueOf(split[0]);
			String nom = String.valueOf(split[1]);
			int activation = Integer.valueOf(split[4]);
			ChaineDeProd cdp = new ChaineDeProd(id, nom, 0, 0, 0);
			cdp.setActivation(activation);

			String colEntree = split[2];
			colEntree = colEntree.substring(1, colEntree.length() - 1);

			String[] splitEntree = colEntree.split(", ");
			for (int j = 0; j < splitEntree.length; j++) {
				String[] splitElemEntree = splitEntree[j].split("=");
				String idE = String.valueOf(splitElemEntree[0]);
				double qteE = Double.valueOf(splitElemEntree[1]);
				cdp.getEntree().put(idE, qteE);
			}

			String colSortie = split[3];
			colSortie = colSortie.substring(1, colSortie.length() - 1);
			System.out.println(colSortie);

			String[] splitSortie = colSortie.split(", ");
			for (int j = 0; j < splitSortie.length; j++) {
				String[] splitElemSortie = splitSortie[j].split("=");
				String idE = String.valueOf(splitElemSortie[0]);
				double qteE = Double.valueOf(splitElemSortie[1]);
				cdp.getSortie().put(idE, qteE);
			}

			Set<String> listKeys = cdp.getEntree().keySet();
			Iterator<String> iterateur = listKeys.iterator();
			while (iterateur.hasNext()) {
				Object key = iterateur.next();
			}

			Set<String> listKeys2 = cdp.getSortie().keySet();
			Iterator<String> iterateur2 = listKeys2.iterator();
			while (iterateur2.hasNext()) {
				Object key = iterateur2.next();
			}

			double cout = cdp.coutCdP(LireFich.getListVentes(), LireFich.getListAchats(), LireFich.getListElem(),
					activation);
			cdp.setCout(cout);
			System.out.println("COUT : " + cdp.getCout());
			
			System.out.println(cdp.getEntree().toString());

			listChdp.add(cdp);

		}

		return listChdp;
	}
	
	private void sommeCout() {
		double somme = 0;
		for (ChaineDeProd c : tableCdpG.getItems()) {

			somme = somme + c.getCout();
		}
		totalGauche.setText("" + somme);

	}

}
