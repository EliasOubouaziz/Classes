package fr.miage.toulouse.dev;
import java.util.HashMap;


public abstract class Commandes {
	protected HashMap <String, Element> commande;
	
	public Commandes() {
		this.commande = new HashMap <String, Element>();
	}
}
