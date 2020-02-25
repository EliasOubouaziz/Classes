package fr.miage.toulouse.dev;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.io.IOException;
import java.nio.charset.Charset;
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
	
	public double calculCoutChaineDeProd(String URLprix, String URLelements) {
		double qteBesoin;
		double qtePossede;
		double somme=0;
		// Créer une HashMap avec les prix par id d'élément
		Achats ach = new Achats();
		Path orderPath = Paths.get(URLprix);
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
		//Créé une HashMap qui récupère les qte des elemnts en stock
		HashMap<String,Double> elem = new HashMap<String,Double>();
		
		Charset charset = Charset.forName("ISO-8859-1");
		Path orderPath2 = Paths.get(URLelements);
		List<String> lines2 = null; // null mean no value by default
		try {
			lines2 = Files.readAllLines(orderPath2, charset);
		} catch (IOException e) {
			System.out.println("Impossible de lire le fichier des élements");
		}

		for (int i = 1; i < lines2.size(); i++) {
			String[] split2 = lines2.get(i).split(";");
			String id = String.valueOf(split2[0]);
			double qte = Double.valueOf(split2[2]);
			elem.put(id, qte);
		}	
		
		// Parcours les elements de la chaine de prod
		Set listKeys = this.entree.keySet(); 
		Iterator iterateur = listKeys.iterator(); 
		while (iterateur.hasNext()) {
			Object key = iterateur.next();
			qteBesoin = this.entree.get(key);
			
			// Parcours les elements de l'objet achat 
			Set listKeys2 = ach.prix.keySet(); 
			Iterator iterateur2 = listKeys2.iterator();
			while (iterateur2.hasNext()) {
				Object key2 = iterateur2.next();
				
				//Parcours les elements en stock
				Set listKeys3 = elem.keySet(); 
				Iterator iterateur3 = listKeys3.iterator();
				while (iterateur3.hasNext()) {
					Object key3 = iterateur3.next();
					qtePossede = elem.get(key3);
						//si les 3 éléments correspondent
						if(key.equals(key2) && key.equals(key3)) {
							// et que la quantité en stock est insuffisante
							if(qtePossede<qteBesoin) {
								//on rachete seulement la qte nécéssaire
								somme = somme + (qteBesoin-qtePossede) * Double.parseDouble(ach.prix.get(key));
							}
						}
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
