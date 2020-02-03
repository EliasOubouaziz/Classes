package fr.miage.toulouse.dev;

import java.util.HashMap;

public class ChaineDeProd {

	private String id;
	private String nom;
	protected HashMap <String, Double> entree;
	protected HashMap <String, Double> sortie;
	private int activation;
	
	public ChaineDeProd(String id,String nom) {
		this.id = id;
		this.nom = nom;
		this.entree = new HashMap <String, Double>();
		this.sortie = new HashMap <String, Double>();
		this.activation = 0;
	}
}
