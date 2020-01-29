package fr.miage.toulouse.interfaceGraphique;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControleurTestFx implements Initializable {

	@FXML
	private Button boutonTest;

	@FXML
	private Label labelTest;

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void clicBoutonTest(ActionEvent ev) {
		System.out.println("Bouton cliqué");

		labelTest.setText("BIEN JOUE");
	}

}
