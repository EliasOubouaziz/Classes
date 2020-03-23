package fr.miage.toulouse.dev;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class ChaineDeProd {

	/**
	 * l'ID d'une chaine de prod
	 */
	private String id;
	/**
	 * Le nom d'une chaine de prod
	 */
	private String nom;
	/**
	 * Les elements et leur quantite necessaire à la fabrication
	 */
	protected HashMap <String, Double> entree;
	/**
	 * Les elements et leur quantite produits 
	 */
	protected HashMap <String, Double> sortie;
	/**
	 * Le nombre de fois ou la production de la chaine va être effectuée
	 */
	private int activation;
	/**
	 * Le cout d'une chaine de production
	 */
	private double cout;
	/**
	 * temps necessaire à la production de la chaine
	 */
	private int tempsinit;
	/**
	 * Nombre de personnes non qualifiées necessaire a la production
	 */
	private int persNonQualifieinit;
	/**
	 * Nombre de personnes qualifiées necessaire a la production
	 */
	private int persQualifieinit;
	/**
	 * temps necessaire à la production de la chaine d'activation 
	 * selon le niveau d'activation
	 */
	private int temps;
	/**
	 * Nombre de personnes non qualifiées necessaire a la production
	 * selon le niveau d'activation
	 */
	private int persNonQualifie;
	/**
	 * Nombre de personnes qualifiées necessaire a la production
	 * selon le niveau d'activation
	 */
	private int persQualifie;
	
	/**
	 * Liste les commandes enregistrées
	 */
	private static ArrayList<Commandes> listCommandes = new ArrayList<Commandes>();
	
	public ChaineDeProd(String id,String nom, int temps, int persNonQualifie, int persQualifie) {
		this.id = id;
		this.nom = nom;
		this.entree = new HashMap <String, Double>();
		this.sortie = new HashMap <String, Double>();
		this.activation = 0;
		this.cout = 0;
		this.temps = temps;
		this.persNonQualifie = persNonQualifie;
		this.persQualifie = persQualifie;
		this.tempsinit = temps;
		this.persNonQualifieinit = persNonQualifie;
		this.persQualifieinit = persQualifie;
	}
	
	
	
	



	/**
	 * Calcule le cout de production pour une chaine de production
	 * 
	 * @param ListVentes la liste des élèments avec leur prix de vente
	 * @param ListAchats la liste des élèments avec leur prix d'achat
	 * @param ListElem  la liste de tous les éléments
	 * @param NvActiv le niveau d'activation désiré pour une chaine de production
	 * @return le cout de production pour une chaine de production
	 */
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
										if(cmd.getId().equals(key.toString())) {
												présent = true;
												cmd.setQte(cmd.getQte()+(qteBesoin*this.entree.get(key)));

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
	
	/**
	 * Calcule le cout de production pour une chaine de production utiliser pour comprarer
	 * 
	 * @param ListVentes la liste des élèments avec leur prix de vente
	 * @param ListAchats la liste des élèments avec leur prix d'achat
	 * @param ListElem  la liste de tous les éléments
	 * @param NvActiv le niveau d'activation désiré pour une chaine de production
	 * @return le cout de production pour une chaine de production
	 */
	public double coutCdPComparaison(ArrayList<Ventes> ListVentes, ArrayList<Achats> ListAchats, ArrayList<Element> ListElem, int NvActiv) {
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
	
	
	
	
	
	
	
	
	/**
	 * 
	 * Supprime les commandes qui sont liées à une chaine de prod quand 
	 * le niveau d'activation change
	 * 
	 * @param ListAchats la liste des élèments avec leur prix d'achat
	 * @param ListElem  la liste de tous les éléments
	 * @param NvActiv le niveau d'activation désiré pour une chaine de production
	 */
	public void CommandeSup( ArrayList<Achats> ListAchats, ArrayList<Element> ListElem, int NvActiv) {
		double qteBesoin;
		double qtePossede;
		double somme=0;
		if(NvActiv>0) {
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
										Commandes elemsup = null;
										for(Commandes cmd : listCommandes) {
											System.out.println(key.toString());
											if(cmd.getId().equals(key.toString())) {
												
												cmd.setQte(cmd.getQte()-(qteBesoin*this.entree.get(key)));
												
												if(cmd.getQte()<=0) {
													elemsup=cmd;
												}
											}	
										}
										if(elemsup!=null) {
											listCommandes.remove(listCommandes.indexOf(elemsup));
											System.out.println("sup");
										}

								}
							}
						}						
				}
			}
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
	
	
	public int getTemps() {
		return temps;
	}



	public void setTemps(int temps) {
		this.temps = temps;
	}



	public int getPersNonQualifie() {
		return persNonQualifie;
	}



	public void setPersNonQualifie(int persNonQualifie) {
		this.persNonQualifie = persNonQualifie;
	}

	public int getPersQualifie() {
		return persQualifie;
	}

	public void setPersQualifie(int persQualifie) {
		this.persQualifie = persQualifie;
	}

	public int getTempsinit() {
		return tempsinit;
	}

	public int getPersNonQualifieinit() {
		return persNonQualifieinit;
	}

	public int getPersQualifieinit() {
		return persQualifieinit;
	}
	
} 
