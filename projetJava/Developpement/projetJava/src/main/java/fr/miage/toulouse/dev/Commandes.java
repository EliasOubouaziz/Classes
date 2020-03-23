package fr.miage.toulouse.dev;
import java.util.HashMap;


public class Commandes {
	
	
	/**
	 * L'ID d'une commande
	 */
	private String id;
	/**
	 * Le nom d'une commande
	 */
	private String nom;
	/**
	 * Le prix unitaire d'une commande
	 */
	private double PrixUnit;
	/**
	 * La quantité commandée
	 */
	private double Qte;
	/**
	 * Le prix total de la commande
	 */
	private double PrixTotal;
	
	
	
	public Commandes(String id, String nom, double PrixUnit, double Qte) {
		this.id =id;
		this.nom =nom;
		this.PrixUnit=PrixUnit;
		this.Qte=Qte;
		this.PrixTotal=this.PrixUnit*this.Qte;
		
	}
	
	
	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public double getPrixUnit() {
		return PrixUnit;
	}



	public void setPrixUnit(double prixUnit) {
		PrixUnit = prixUnit;
	}



	public double getQte() {
		return Qte;
	}



	public void setQte(double qte) {
		Qte = qte;
		this.PrixTotal=this.PrixUnit*this.Qte;
	}



	public double getPrixTotal() {
		return PrixTotal;
	}



	public void setPrixTotal(double prixTotal) {
		PrixTotal = prixTotal;
	}
}
