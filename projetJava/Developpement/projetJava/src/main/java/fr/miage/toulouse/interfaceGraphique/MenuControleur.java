package fr.miage.toulouse.interfaceGraphique;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuControleur implements Initializable {

	@FXML
	private AnchorPane rootPane;

	@FXML
	private Button btnQuit;

	@FXML
	private Button btnChargmnt;

	@FXML
	private Button btnEtatStock;

	@FXML
	private Button btnGestionProd;

	/**Ferme l'application
	 * @param e clic sur le bouton
	 */
	public void quitter(ActionEvent e) {
		System.out.println("Menu - Clic sur btnQuit, Fermeture du menu");

		Stage stage = (Stage) btnQuit.getScene().getWindow();
		// Ferme la fen�tre
		stage.close();

	}

	/**Redirection vers la page de chargement
	 * @param e
	 * @throws IOException
	 */
	public void versChargement(ActionEvent e) throws IOException {
		System.out.println("Menu - Clic sur btnChargmnt, Changement de fen�tre");

		// Fermeture de la fenetre
		Stage fen = (Stage) btnChargmnt.getScene().getWindow();

		fen.close();

		// Chargement de la sc�ne suivante
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/fr/miage/toulouse/interfaceGraphique/ChargementMainScene.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();

		// Cr�ation de la nouvelle fen�tre
		Stage newFen = new Stage();
		newFen.setTitle("Chargement des donn�es");
		newFen.setScene(new Scene(root1));
		newFen.setResizable(false);
		newFen.show();
		newFen.centerOnScreen();
	}

	/**Redirection vers l'etat des stocks
	 * @param e
	 * @throws IOException
	 */
	public void versEtatStock(ActionEvent e) throws IOException {
		System.out.println("Menu - Clic sur btnEtatStock, Changement de fen�tre");

		// Fermeture de la fenetre
		Stage fen = (Stage) btnEtatStock.getScene().getWindow();

		fen.close();

		// Chargement de la sc�ne suivante
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/fr/miage/toulouse/interfaceGraphique/StockScene.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();

		// Cr�ation de la nouvelle fen�tre
		Stage newFen = new Stage();
		newFen.setTitle("Etat des Stocks");
		newFen.setScene(new Scene(root1));
		newFen.setResizable(false);
		newFen.show();
		newFen.centerOnScreen();

	}

	/**Redirection vers gestion des chaines de prod
	 * @param e
	 * @throws IOException
	 */
	public void versChaineProd(ActionEvent e) throws IOException {
		System.out.println("Menu - Clic sur btnGestionProd, Changement de fen�tre");

		// Fermeture de la fenetre
		Stage fen = (Stage) btnEtatStock.getScene().getWindow();

		fen.close();

		// Chargement de la sc�ne suivante
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/fr/miage/toulouse/interfaceGraphique/ChaineProdScene.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();

		// Cr�ation de la nouvelle fen�tre
		Stage newFen = new Stage();
		newFen.setTitle("Gestion des Chaines de Production");
		newFen.setScene(new Scene(root1));
		newFen.setResizable(false);
		newFen.show();
		newFen.centerOnScreen();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
