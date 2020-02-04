package fr.miage.toulouse.dev;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LireFich {

	
	public static void LireFichierAchats(String URL) {
		Achats ach = new Achats();
		Path orderPath = Paths.get(URL);
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
	
	public static void LireFichierVentes(String URL) {
		Ventes vte = new Ventes();
		Path orderPath = Paths.get(URL);
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
	
	
	
	public static void LireFichierElements(String URL) {
		Charset  charset = Charset.forName("ISO-8859-1");
		Path orderPath = Paths.get(URL);
        List<String> lines = null; //null mean no value by default
        try {
            lines = Files.readAllLines(orderPath, charset);
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
            String nom = String.valueOf(split[1]);
            double qte = Double.valueOf(split[2]);
            String unite = String.valueOf(split[3]);
	        Element elm = new Element(id,nom,qte,unite);
                      
			System.out.println (id+"=>"+nom + " "+ qte + " "+unite);
		}
	}
	
	
	
	
	
	public static void LireFichierChaineProd(String URL) {
		Charset  charset = Charset.forName("ISO-8859-1");
		Path orderPath = Paths.get(URL);
        List<String> lines = null; //null mean no value by default
        try {
            lines = Files.readAllLines(orderPath, charset);
        } catch (IOException e) {
            System.out.println("Impossible de lire le fichier des chaines");
        }
        if (lines.size() < 2) {
            System.out.println("Il n'y a pas de chaines dans le fichier");
            return;
        }
        
        for (int i = 1; i < lines.size(); i++) {
        	String info = "";
        	
            String[] split = lines.get(i).split(";");
            String id = String.valueOf(split[0]);
            String nom = String.valueOf(split[1]);
            ChaineDeProd cdp = new ChaineDeProd(id,nom);
            
            String[] splitEntree = split[2].split("/");
            for (int j = 0; j < splitEntree.length; j++) {
            	String[] splitElemEntree = splitEntree[j].split(",");
            	String idE = String.valueOf(splitElemEntree[0]);
            	double qteE = Double.valueOf(splitElemEntree[1]);
            	cdp.entree.put(idE,qteE);
            }
            
            String[] splitSortie = split[3].split("/");
            for (int j = 0; j < splitSortie.length; j++) {
            	String[] splitElemSortie = splitSortie[j].split(",");
            	String idE = String.valueOf(splitElemSortie[0]);
            	double qteE = Double.valueOf(splitElemSortie[1]);
            	cdp.sortie.put(idE,qteE);
            }
            
            info = info + id + " => "+ nom + " ";
            
    		Set<String> listKeys= cdp.entree.keySet();
    		Iterator<String> iterateur=listKeys.iterator();
    		while(iterateur.hasNext())
    		{
    			Object key= iterateur.next();
    			info = info +" + "+key + "*"+cdp.entree.get(key) ;
    		}
    		
    		info = info + " => ";
            
    		Set<String> listKeys2= cdp.sortie.keySet();
    		Iterator<String> iterateur2=listKeys2.iterator();
    		while(iterateur2.hasNext())
    		{
    			Object key= iterateur2.next();
    			info = info +key + "*"+cdp.sortie.get(key)+" " ;
    		}
	        
                      
			System.out.println (info);
		}
	}
	
}
