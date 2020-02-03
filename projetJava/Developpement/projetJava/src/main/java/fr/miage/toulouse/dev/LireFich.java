package fr.miage.toulouse.dev;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LireFich {

	
	public static void LireFichier(Achats ach) {
		Path orderPath = Paths.get("prix.csv");
        List<String> lines = null; //null mean no value by default
        try {
            lines = Files.readAllLines(orderPath);
        } catch (IOException e) {
            System.out.println("Impossible de lire le fichier des commandes");
        }
        if (lines.size() < 2) {
            System.out.println("Il n'y a pas de commande dans le fichier");
            return;
        }
        
        for (int i = 1; i < lines.size(); i++) {
            String[] split = lines.get(i).split(";");
            String idE = String.valueOf(split[0]);
            String prixE = String.valueOf(split[1]);
            double qteE = Double.valueOf(split[3]);
	        if (prixE.lastIndexOf("NA") == -1) {
            	if ( qteE > 0) {
	            	ach.prix.put(idE, prixE);
	            	ach.qte.put(idE,qteE);
		        }
        	}    
            
            
        }		
		Set listKeys= ach.qte.keySet();  // Obtenir la liste des clés
		Iterator iterateur=listKeys.iterator();
		// Parcourir les clés et afficher les entrées de chaque clé;
		while(iterateur.hasNext())
		{
			Object key= iterateur.next();
			System.out.println (key+"=>"+ach.prix.get(key)+"€ x "+ach.qte.get(key) );
		}
	}
	
	public static void LireFichier(Ventes vte) {
		Path orderPath = Paths.get("prix.csv");
        List<String> lines = null; //null mean no value by default
        try {
            lines = Files.readAllLines(orderPath);
        } catch (IOException e) {
            System.out.println("Impossible de lire le fichier des commandes");
        }
        if (lines.size() < 2) {
            System.out.println("Il n'y a pas de commande dans le fichier");
            return;
        }
        
        for (int i = 1; i < lines.size(); i++) {
            String[] split = lines.get(i).split(";");
            String idE = String.valueOf(split[0]);
            String prixE = String.valueOf(split[2]);
            double qteE = Double.valueOf(split[3]);
	        if (prixE.lastIndexOf("NA") == -1) {
            	if ( qteE > 0) {
	            	vte.prix.put(idE, prixE);
	            	vte.qte.put(idE,qteE);
		        }
        	}    
            
            
        }		
		Set listKeys= vte.qte.keySet();  // Obtenir la liste des clés
		Iterator iterateur=listKeys.iterator();
		// Parcourir les clés et afficher les entrées de chaque clé;
		while(iterateur.hasNext())
		{
			Object key= iterateur.next();
			System.out.println (key+"=>"+vte.prix.get(key)+"€ x "+vte.qte.get(key) );
		}
	}
	
	
	
	public static void LireFichier() {
		Path orderPath = Paths.get("elements.csv");
        List<String> lines = null; //null mean no value by default
        try {
            lines = Files.readAllLines(orderPath);
        } catch (IOException e) {
            System.out.println("Impossible de lire le fichier des élements");
        }
        if (lines.size() < 2) {
            System.out.println("Il n'y a pas d'élements dans le fichier");
            return;
        }
        
        for (int i = 1; i < lines.size(); i++) {
            String[] split = lines.get(i).split(";");
            String id = String.valueOf(split[0]);
            String nom = String.valueOf(split[2]);
            double qte = Double.valueOf(split[2]);
            String unite = String.valueOf(split[3]);
	        Element elm = new Element(id,nom,qte,unite);
                      
			System.out.println (id+"=>"+nom + " "+ qte + " "+unite);
		}
	}
	
	

	
	
	
	
	
	public static void main(String[] args) {
		
		final Achats ach = new Achats();
		final Ventes vte = new Ventes();
		LireFichier(ach);
		LireFichier(vte);
		LireFichier();

		
		
	}
}
