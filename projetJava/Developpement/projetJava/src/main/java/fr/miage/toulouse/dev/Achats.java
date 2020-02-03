package fr.miage.toulouse.dev;
import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;

public class Achats extends Commandes {
	private HashMap <String, String> prix ;
	private HashMap <String, Double> qte;

	
	
	public Achats () {
		this.prix = new HashMap <String, String>();
		this.qte = new HashMap <String, Double>();
	}
	

	
	
	
	 public void read() {
	        
		 	Path orderPath = Paths.get("prix.csv");
		 	String info="";
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
		            	this.prix.put(idE, prixE);
		            	this.qte.put(idE,qteE);
			        }
	        	}    
	            
	            
	        }
	        
					
			Set listKeys= this.qte.keySet();  // Obtenir la liste des clés
    		Iterator iterateur=listKeys.iterator();
    		// Parcourir les clés et afficher les entrées de chaque clé;
    		while(iterateur.hasNext())
    		{
    			Object key= iterateur.next();
    			System.out.println (key+"=>"+this.prix.get(key)+"€ x "+this.qte.get(key) );
    		}
	    }




	

	public static void main(String[] args) {
		
		Achats ach = new Achats();
		ach.read();
		
	}
		
	 
	 
}
