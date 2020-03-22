package fr.miage.toulouse.dev;
import java.util.HashMap;


public class Commandes {
	
	
	private String id;
	private String nom;
	private double PrixUnit;
	private double Qte;
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
