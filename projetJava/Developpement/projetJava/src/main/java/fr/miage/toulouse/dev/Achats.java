package fr.miage.toulouse.dev;
import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;

public class Achats extends Commandes {
	protected HashMap <String, String> prix ;
	protected HashMap <String, Double> qte;

	
	
	public Achats () {
		this.prix = new HashMap <String, String>();
		this.qte = new HashMap <String, Double>();
	}
			
	 
	 
}
