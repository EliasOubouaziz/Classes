package fr.miage.toulouse.dev;
import java.util.HashMap;


public abstract class Commandes {
	protected HashMap <Integer, Element> commande;
	
	public Commandes() {
		this.commande = new HashMap <Integer, Element>();
	}
}
