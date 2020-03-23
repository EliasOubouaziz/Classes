package fr.miage.toulouse.interfaceGraphique;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fr.miage.toulouse.dev.ChaineDeProd;
import fr.miage.toulouse.dev.LireFich;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ResultatOKControleur implements Initializable {
	
	@FXML
	private Button btnRetour;
	
	@FXML
	private Button btnExport;
	
	@FXML
	private BarChart<CategoryAxis, NumberAxis> barchart; 
	
	@FXML
	private CategoryAxis Axebas; 
	
	@FXML
	private NumberAxis Axegauche;

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
				
		recupdata();
	
	}
	
	public void recupdata() {
		XYChart.Series donnée = new XYChart.Series();
		donnée.setName("Premier ficier");
		ArrayList<ChaineDeProd> listChdP = LireFich.getListChdP();

		for (ChaineDeProd c : listChdP) {
			donnée.getData().add(new XYChart.Data<>(c.getId(), c.getActivation()));
		}
		barchart.getData().add(donnée);
		
		
	}
	
	public void show() {
		System.out.println("test");
	}
	
	public void retourMenu(ActionEvent e) throws IOException {
		System.out.println("Saisie Commande - clic sur btnRetour");

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
