package fr.miage.toulouse.interfaceGraphique;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuControleur implements Initializable {

	@FXML
	private Button btnQuit;
	
	@FXML
	private Button btnChargmnt;
	
	@FXML
	private Button btnEtatStock;
	
	@FXML
	private Button btnGestionProd;
	
	@FXML
	private Button btnSaisieComm;
	
	@FXML
	private Button btnAfficheResult;

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void quitter(ActionEvent e) {
		System.out.println("Clic sur btnQuit");
		
		Stage stage = (Stage) btnQuit.getScene().getWindow();
		// do what you have to do
		stage.close();
		
	}

}
