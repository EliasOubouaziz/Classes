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
	
	private static ArrayList<Commandes> listCommandes = new ArrayList<Commandes>();
	
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

			while (iterateur.hasNext()) {
				Object key = iterateur.next();
				qteBesoin = NvActiv;
						for(Element elem : ListElem) {
							for (Achats ach : ListAchats) {
								//si les 3 éléments correspondent
								if (ach.getID().equals(key.toString()) && elem.getId().toString().equals(key.toString())) {
									
									// et que la quantité en stock est insuffisante
									if(elem.getQte()<qteBesoin*this.entree.get(key)) {
										somme = somme - ((qteBesoin*this.entree.get(key)-elem.getQte()) * Double.parseDouble(ach.getPrix()));
										
										boolean présent = false;
										for(Commandes cmd : listCommandes) {
											if(cmd.getId().equals(key)) {
												présent = true;
												cmd.setQte(cmd.getQte()+(qteBesoin*this.entree.get(key)-elem.getQte()));
											}
										}
										if(!présent) {
											Commandes cmd = new Commandes(key.toString(),elem.getNom(),Double.parseDouble(ach.getPrix()),(qteBesoin*this.entree.get(key)-elem.getQte()));
											listCommandes.add(cmd);
										}
									
									
									
									}	
								}
							}
						}						
				}
			
				Set listKeys1 = this.sortie.keySet();
				Iterator iterateur1 = listKeys1.iterator(); 
				while (iterateur1.hasNext()) {
					Object key1 = iterateur1.next();
					for (Ventes vte : ListVentes) {

						if (vte.prix.keySet().toString().substring(1,5).equals(key1.toString())) {
							double prix = Double.parseDouble(vte.prix.values().toString().substring(1,vte.prix.values().toString().length()-1));
							somme = somme + (prix*this.sortie.get(key1)*NvActiv);
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
	
	public static ArrayList<Commandes> getlistCommandes(){
		return listCommandes;
	}
} 
