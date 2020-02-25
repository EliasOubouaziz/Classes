package fr.miage.toulouse.dev;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


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
	
	public double calculRentabilite(String URL) {
		double qte;
		double somme=0;
		Achats ach = new Achats();
		Path orderPath = Paths.get(URL);
		List<String> lines = null;
		try {
			lines = Files.readAllLines(orderPath);
		} catch (IOException e) {
			System.out.println("Impossible de lire le fichier des commandes");
		}
		for(int i = 1; i<lines.size(); i++) {
			String[] split = lines.get(i).split(";");
			String idE = String.valueOf(split[0]);
			String prixE = String.valueOf(split[1]);
			if(prixE.lastIndexOf("NA")==-1) {
					ach.prix.put(idE, prixE);
			}
		}
		
		
		Set listKeys = this.entree.keySet(); // Obtenir la liste des clés
		Iterator iterateur = listKeys.iterator();
		// Parcourir les clés 
		while (iterateur.hasNext()) {
			Object key = iterateur.next();
			qte=this.entree.get(key);
			Set listKeys2 = ach.prix.keySet(); // Obtenir la liste des clés
			Iterator iterateur2 = listKeys2.iterator();
			while (iterateur2.hasNext()) {
				Object key2 = iterateur.next();
				if(key.equals(key2)) {
					somme = somme + qte * Double.parseDouble(ach.prix.get(key));
				}
			}
		}
		
		return somme;
	}

	public String getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public HashMap<String, Double> getEntree() {
		return entree;
	}

	public HashMap<String, Double> getSortie() {
		return sortie;
	}

	public int getActivation() {
		return activation;
	}
}
