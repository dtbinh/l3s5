package facteur.util;

/** Classe permettant de representer une rue */
public class Rue {

	// le nom de la rue
	private String nom;

	/**
	 * constructeur pour les instances de la classe Rue
	 * @param n le nom de la rue
	 */
	public Rue(String n) {
		this.nom = n;
	}
	
	/**
	 *  renvoie le nom de la rue
	 *  @return le nom de la rue
	 *  @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.nom;
	}

}
