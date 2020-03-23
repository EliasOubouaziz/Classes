package fr.miage.toulouse.dev;
import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;

public class Achats {
	/**
	 * L'ID d'un element
	 */
	private String id;
	/**
	 * Le prix d'achat d'un élément
	 */
	private String prix;
	


	
	

	public Achats (String id, String prix) {
		this.id = id;
		this.prix = prix;
	}
	
	public String getID() {
		return id;
	}
	public String getPrix() {
		return prix;
	}

	 
}
