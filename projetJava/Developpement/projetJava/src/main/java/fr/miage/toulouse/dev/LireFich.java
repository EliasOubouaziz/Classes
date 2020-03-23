package fr.miage.toulouse.dev;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.io.FilenameFilter;

public class LireFich {

	/**
	 * Liste tous les elements chargés
	 */
	private static ArrayList<Element> listElem = new ArrayList<Element>();

	/**
	 * Liste toutes les chaines chargées
	 */
	private static ArrayList<ChaineDeProd> listChdP = new ArrayList<ChaineDeProd>();
	
	/**
	 * Liste de tous les prix d'achats chargés
	 */
	private static ArrayList<Achats> listAchats = new ArrayList<Achats>();
	
	
	/**
	 * Liste de tous les prix de ventes chargés 
	 */
	private static ArrayList<Ventes> listVentes = new ArrayList<Ventes>();	

	/**
	 * 
	 * Permet la lecture d'un fichier
	 * Appelle la méthode de lecture correspondante selon le nom du fichier
	 * 
	 * @param URL l'URL du fichier à lire
	 * @return
	 */
	public static Boolean[] LireFichier(String URL) {
		Boolean[] result = { false, false, false };
		Path fich = Paths.get(URL);
		System.out.println(fich);
		File fichiers = fich.toFile();
		System.out.println(fichiers);

		String[] nomFichiers = fichiers.list(new FilenameFilter() {
			public boolean accept(File directory, String fileName) {
				return fileName.endsWith(".csv");
			}
		});

		for (int i = 0; i < nomFichiers.length; i++) {
			String nm = nomFichiers[i];

			if (nm.equals("chaines.csv")) {
				System.out.println();
				System.out.println("Chaines de production : ");
				String nom = URL + '\\' + nomFichiers[i];
				LireFichierChaineProd(nom);
				result[0] = true;
			} else if (nm.equals("elements.csv")) {
				System.out.println();
				System.out.println("Elements :");
				String nom = URL + '\\' + nomFichiers[i];
				LireFichierElements(nom);
				result[1] = true;
			} else if (nm.equals("prix.csv")) {
				String nom = URL + '\\' + nomFichiers[i];
				System.out.println();
				System.out.println("Liste des Achats :");
				LireFichierAchats(nom);
				System.out.println();
				System.out.println("Liste des Ventes :");
				LireFichierVentes(nom);
				result[2] = true;
			}
		}
		return result;
	}

	/**
	 * 
	 * Lis le fichier prix.csv et récupère les éléments qui ont un prix d'achat
	 * 
	 * @param URL l'URL du fichier à lire
	 */
	public static void LireFichierAchats(String URL) {
		
		Path orderPath = Paths.get(URL);
		List<String> lines = null; // null mean no value by default
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
			if (prixE.lastIndexOf("NA") == -1) {
					Achats ach = new Achats(idE, prixE);
					listAchats.add(ach);
					System.out.println(idE + "=>" + prixE + "€ ");
				}

		}
		
	}

	/**
	 * 
	 * lis le fichier prix.csv et récupère les élements qui ont un prix de vente
	 * 
	 * @param URL l'URL du fichier à lire
	 */
	public static void LireFichierVentes(String URL) {
		Ventes vte = new Ventes();
		Path orderPath = Paths.get(URL);
		List<String> lines = null; // null mean no value by default
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
				if (qteE >= 0) {
					double prixEdouble = Double.parseDouble(prixE);
					vte.prix.put(idE, prixEdouble);
					vte.qte.put(idE, qteE);
					Ventes listevte = new Ventes();
					listevte.prix.put(idE, prixEdouble);
					listevte.qte.put(idE, qteE);
					listVentes.add(listevte);
				}
			}
			
		}
		
		Set listKeys = vte.qte.keySet(); // Obtenir la liste des clés
		Iterator iterateur = listKeys.iterator();
		// Parcourir les clés et afficher les entrées de chaque clé;
		while (iterateur.hasNext()) {
			Object key = iterateur.next();
			System.out.println(key + "=>" + vte.prix.get(key) + "€ x " + vte.qte.get(key));
		}
	}

	
	/**
	 * 
	 * Lis et récupère tous les éléments du fichier elements.csv 
	 * 
	 * @param URL l'URL du fichier à lire
	 */
	public static void LireFichierElements(String URL) {
		Charset charset = Charset.forName("ISO-8859-1");
		Path orderPath = Paths.get(URL);
		List<String> lines = null; // null mean no value by default
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
			Element elm = new Element(id, nom, qte, unite);
			listElem.add(elm);
			System.out.println(id + "=>" + nom + " " + qte + " " + unite);
		}
	}

	/**
	 * 
	 * Lis et récupère tous les éléments du fichier chaines.csv
	 * 
	 * @param URL l'URL du fichier à lire
	 */
	public static void LireFichierChaineProd(String URL) {
		Charset charset = Charset.forName("ISO-8859-1");
		Path orderPath = Paths.get(URL);
		List<String> lines = null; // null mean no value by default
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
			int temps = Integer.valueOf(split[4]);
			int persNonQualifie = Integer.valueOf(split[5]);
			int persQualifie = Integer.valueOf(split[6]);
			ChaineDeProd cdp = new ChaineDeProd(id, nom, temps,persNonQualifie,persQualifie);

			String[] splitEntree = split[2].split("/");
			for (int j = 0; j < splitEntree.length; j++) {
				String[] splitElemEntree = splitEntree[j].split(",");
				String idE = String.valueOf(splitElemEntree[0]);
				double qteE = Double.valueOf(splitElemEntree[1]);
				cdp.entree.put(idE, qteE);
			}

			String[] splitSortie = split[3].split("/");
			for (int j = 0; j < splitSortie.length; j++) {
				String[] splitElemSortie = splitSortie[j].split(",");
				String idE = String.valueOf(splitElemSortie[0]);
				double qteE = Double.valueOf(splitElemSortie[1]);
				cdp.sortie.put(idE, qteE);
			}

			info = info + id + " => " + nom + " ";

			Set<String> listKeys = cdp.entree.keySet();
			Iterator<String> iterateur = listKeys.iterator();
			while (iterateur.hasNext()) {
				Object key = iterateur.next();
				info = info + " + " + key + "*" + cdp.entree.get(key);
			}

			info = info + " => ";

			Set<String> listKeys2 = cdp.sortie.keySet();
			Iterator<String> iterateur2 = listKeys2.iterator();
			while (iterateur2.hasNext()) {
				Object key = iterateur2.next();
				info = info + key + "*" + cdp.sortie.get(key) + " ";
			}
			listChdP.add(cdp);
			//if(i==1) {
			//	System.out.println(cdp.coutCdP("C:\\Users\\Elias\\Documents\\GitHub\\Classes\\projetJava\\Developpement\\projetJava\\prix.csv", "C:\\Users\\Elias\\Documents\\GitHub\\Classes\\projetJava\\Developpement\\projetJava\\elements.csv", 500));;
			//}
			//System.out.println(info);

		}
	}

	/**
	 * @return la liste de tous les elements du fichier elements.csv
	 */
	public static ArrayList<Element> getListElem() {
		return LireFich.listElem;
	}
	
	/**
	 * @return la liste de tous les éléments du fichier prix.csv
	 * ayant un prix d'achat
	 */
	public static ArrayList<Achats> getListAchats() {
		return LireFich.listAchats;
	}
	
	/**
	 * @return la liste de tous les éléments du fichier prix.csv
	 * ayant un prix de vente
	 */
	public static ArrayList<Ventes> getListVentes() {
		return LireFich.listVentes;
	}

	/**
	 * @return la liste de tous les éléments du fichier chaines.csv
	 */
	public static ArrayList<ChaineDeProd> getListChdP() {
		return LireFich.listChdP;
	}


}
	