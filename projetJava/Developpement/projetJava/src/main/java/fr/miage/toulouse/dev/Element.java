package fr.miage.toulouse.dev;

public class Element {
	/**
	 * L'ID d'un element
	 */
	private String id;
	/**
	 * Le nom d'un element
	 */
	private String nom;
	/**
	 * La quantite en stock d'un element
	 */
	private double qte;
	/**
	 * l'unite de mesure d'un element
	 */
	private String unite;
	
	public Element(String id, String nom, double qte, String unite) {
		this.id=id;
		this.nom=nom;
		this.qte=qte;
		this.unite=unite;
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

	public double getQte() {
		return qte;
	}

	public void setQte(double qte) {
		this.qte = qte;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	
	
}
