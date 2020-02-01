package fr.miage.toulouse.interfaceGraphique;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ChargementControleur implements Initializable {
	
	@FXML
	private TextField tfElem;
	@FXML
	private TextField tfChaine;
	@FXML
	private TextField tfCommande;
	
	@FXML
	private Button btnParcoursElem;
	@FXML
	private Button btnParcoursChaine;
	@FXML
	private Button btnParcoursComm;
	
	@FXML
	private Button btnElem;
	@FXML
	private Button btnChaine;
	@FXML
	private Button btnCommande;

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
