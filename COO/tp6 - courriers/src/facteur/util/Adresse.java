package facteur.util;

/**
 * Classe permettant de representer une adresse
 */
public class Adresse {

	// le numero du batiment
	/**
	 * @uml.property  name="numero"
	 */
	private int numero;

	// la rue
	/**
	 * @uml.property  name="rue"
	 * @uml.associationEnd  
	 */
	private Rue rue;

	// la ville
	/**
	 * @uml.property  name="ville"
	 * @uml.associationEnd  
	 */
	private Ville ville;

	/**
	 * constructeur pour les instances de la classe Adresse
	 * @param n le numero du batiment
	 * @param r la rue
	 * @param v la ville
	 */
	public Adresse(int n, Rue r, Ville v) {
		this.numero = n;
		this.rue = r;
		this.ville = v;
	}

	/**
	 * renvoie le numero du batiment
	 * @return   le numero du batiment
	 * @uml.property  name="numero"
	 */
	public int getNumero() {
		return this.numero;
	}

	/**
	 * renvoie la rue
	 * @return   la rue
	 * @uml.property  name="rue"
	 */
	public Rue getRue() {
		return this.rue;
	}

	/**
	 * renvoie la ville
	 * @return   la ville
	 * @uml.property  name="ville"
	 */
	public Ville getVille() {
		return this.ville;
	}

	/**
	 * modifie le numero du batiment
	 * @param newNumero   le nouveau numero
	 * @uml.property  name="numero"
	 */
	public void setNumero(int newNumero) {
		this.numero = newNumero;
	}

	/**
	 * modifie la rue
	 * @param newRue   la nouvelle rue
	 * @uml.property  name="rue"
	 */
	public void setRue(Rue newRue) {
		this.rue = newRue;
	}

	/**
	 * modifie la ville
	 * @param newVille   la nouvelle ville
	 * @uml.property  name="ville"
	 */
	public void setVille(Ville newVille) {
		this.ville = newVille;
	}

	/**
	 * compare l'objet en parametre a <code> this </code>
	 * @param o l'objet qu'on doit comparer a <code> this </code>
	 * @return <code> true </code> si <code> o </code> == <code> this </code>, <code> false </code> sinon
	 * @see java.lang.Object#equals(Object o)
	 */
	public boolean equals(Object o) {
		if (o instanceof Adresse) {
			Adresse a = (Adresse) o;
			return (a.getNumero() == this.numero && a.getRue().toString().equals(this.rue.toString()));
		}
		else {
			return false;
		}
	}

}
