package fr.miage.toulouse.dev;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.beans.property.SimpleIntegerProperty;

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
	private double cout;
	
	public ChaineDeProd(String id,String nom) {
		this.id = id;
		this.nom = nom;
		this.entree = new HashMap <String, Double>();
		this.sortie = new HashMap <String, Double>();
		this.activation = 0;
		this.cout = 0;
	}
	
	
	
	public double coutCdP(ArrayList<Ventes> ListVentes, ArrayList<Achats> ListAchats, ArrayList<Element> ListElem, int NvActiv) {
		double qteBesoin;
		double qtePossede;
		double somme=0;
		if(NvActiv<0) {
			System.out.println("Veuillez saisir un niveau d'activation >= 0");
			return 0;
		} else if(NvActiv==0) {
			return 0;
		} else {
			// Parcours les elements de la chaine de prod
			Set listKeys = this.entree.keySet(); 
			Iterator iterateur = listKeys.iterator(); 
			for (Ventes vte : ListVentes) {
				System.out.println(vte.getPrix().values().toString().substring(1,5));
			}
			
			while (iterateur.hasNext()) {
				Object key = iterateur.next();
				qteBesoin = NvActiv;
						for(Element elem : ListElem) {
							for (Achats ach : ListAchats) {
								//si les 3 éléments correspondent
								if (ach.getID().equals(key.toString()) && elem.getId().toString().equals(key.toString())) {
									
									// et que la quantité en stock est insuffisante
									if(elem.getQte()<qteBesoin) {
										somme = somme - (qteBesoin-elem.getQte()) * Double.parseDouble(ach.getPrix());
									}	
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

	public double getCout() {
		return cout;
	}

	public void setCout(double cout) {
		this.cout = cout;
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

	public void setActivation(int activation) {
		this.activation = activation;
	}
} 
