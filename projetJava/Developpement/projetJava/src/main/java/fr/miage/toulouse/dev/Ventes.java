package fr.miage.toulouse.dev;
import java.util.HashMap;

public class Ventes extends Commandes {
	protected HashMap <String, String> prix ;
	protected HashMap <String, Double> qte;

	
	
	public Ventes () {
		this.prix = new HashMap <String, String>();
		this.qte = new HashMap <String, Double>();
	}



	public HashMap<String, String> getPrix() {
		return prix;
	}



	public void setPrix(HashMap<String, String> prix) {
		this.prix = prix;
	}
	
	
	
}
