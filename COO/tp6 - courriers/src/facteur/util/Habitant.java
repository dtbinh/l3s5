package facteur.util;

import facteur.courriers.Courrier;

/**
 * Classe permettant de representer un habitant
 */
public class Habitant {

	// le prenom de l'habitant
	/**
	 * @uml.property  name="prenom"
	 */
	private String prenom;

	// le nom de l'habitant
	/**
	 * @uml.property  name="nom"
	 */
	private String nom;

	// le solde du compte bancaire de l'habitant
	/**
	 * @uml.property  name="soldeCompte"
	 */
	private float soldeCompte;

	// l'adresse de l'habitant
	/**
	 * @uml.property  name="adresse"
	 * @uml.associationEnd  
	 */
	private Adresse adresse;

	/**
	 * constructeur pour les instances de la classe Habitant
	 * @param p le prenom de l'habitant
	 * @param n le nom de l'habitant
	 * @param sc le solde du compte bancaire de l'habitant
	 * @param a l'adresse de l'habitant
	 */
	public Habitant(String p, String n, float sc, Adresse a) {
		this.prenom = p;
		this.nom = n;
		this.soldeCompte = sc;
		this.adresse = a;
	}

	/**
	 * renvoie le prenom de l'habitant
	 * @return  le prenom de l'habitant
	 * @uml.property  name="prenom"
	 */
	public String getPrenom() {
		return this.prenom;
	}

	/**
	 * renvoie le nom de l'habitant
	 * @return  le nom de l'habitant
	 * @uml.property  name="nom"
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * renvoie l'adresse de l'habitant
	 * @return  l'adresse de l'habitant
	 * @uml.property  name="adresse"
	 */
	public Adresse getAdresse() {
		return this.adresse;
	}

	/**
	 * renvoie le solde du compte bancaire de l'habitant
	 * @return  le solde du compte bancaire de l'habitant
	 * @uml.property  name="soldeCompte"
	 */
	public float getSoldeCompte() {
		return this.soldeCompte;
	}

	/**
	 * l'habitant courant recoit le courrier en parametre
	 * @param c le courrier qui doit etre envoye a l'habitant courant
	 */
	public void recoitCourrier(Courrier<?> c) {
		c.action();
	}

	/**
	 * envoie le courrier en parametre
	 * @param c le courrier a envoyer
	 */
	public void envoieCourrier(Courrier<?> c) {
		this.adresse.getVille().posteLettre(c);
	}

	/**
	 * debiter a l'habitant le montant en parametre
	 * @param montant le montant a debiter a l'habitant
	 */
	public void debiter(float montant) {
		if (this.soldeCompte >= montant)
			this.soldeCompte -= montant;
	}

	/**
	 * credite l'habitant du montant en parametre
	 * @param montant le montant a crediter a l'habitant
	 */
	public void crediter(float montant) {
		this.soldeCompte += montant;
	}

	/**
	 * renvoie le nom de l'habitant
	 * @return le nom de l'habitant
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.nom;
	}

	/**
	 * compare l'objet en parametre a <code> this </code>
	 * @param o l'objet qu'on doit comparer a <code> this </code>
	 * @return <code> true </code> si <code> o </code> == <code> this </code>, <code> false </code> sinon
	 * @see java.lang.Object#equals(Object o)
	 */
	public boolean equals(Object o) {
		if (o instanceof Habitant) {
			Habitant h = (Habitant) o;
			return (h.getPrenom().equals(this.prenom) && h.getNom().equals(this.nom));
		}
		else {
			return false;
		}
	}

}
