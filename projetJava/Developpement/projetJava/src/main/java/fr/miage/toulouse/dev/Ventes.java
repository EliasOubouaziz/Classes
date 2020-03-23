package fr.miage.toulouse.dev;
import java.util.HashMap;

public class Ventes{
	/**
	 * Identifiant d'un élément et son prix de vente associé
	 */
	protected HashMap <String, Double> prix ;
	/**
	 * 
	 */
	protected HashMap <String, Double> qte;

	
	
	public Ventes () {
		this.prix = new HashMap <String, Double>();
		this.qte = new HashMap <String, Double>();
	}



	public HashMap<String, Double> getPrix() {
		return prix;
	}



	public void setPrix(HashMap<String, Double> prix) {
		this.prix = prix;
	}
	
	
	
}
