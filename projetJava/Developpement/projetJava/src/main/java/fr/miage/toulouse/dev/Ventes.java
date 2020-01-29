package fr.miage.toulouse.dev;
import java.util.HashMap;

public class Ventes extends Commandes {
	private HashMap <Integer, Double> prix;
	private HashMap <Integer, Double> qte;
	private double prixE;
	private double qteE;
	private int idE;
	
	public Ventes(int idE, double prixE, double qtE) {
		this.prix.put(idE,prixE);
		this.qte.put(idE, qtE);
	}
	
	
}
