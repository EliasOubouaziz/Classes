package fr.miage.toulouse.dev;
import java.util.HashMap;

public class Ventes extends Commandes {
	protected HashMap <String, String> prix ;
	protected HashMap <String, Double> qte;

	
	
	public Ventes () {
		this.prix = new HashMap <String, String>();
		this.qte = new HashMap <String, Double>();
	}
	
	
}
