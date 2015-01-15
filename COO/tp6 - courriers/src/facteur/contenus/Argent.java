package facteur.contenus;

/**
 * Classe permettant de representer de l'argent
 */
public class Argent implements Contenu {
	
	// le montant
	/**
	 * @uml.property  name="montant"
	 */
	private float montant;

	/**
	 * constructeur pour les instances de la classe Argent
	 * @param mont le montant
	 */
	public Argent(float mont) {
		this.montant = mont;
	}
	
	/**
	 * renvoie le montant
	 * @return  le montant
	 * @uml.property  name="montant"
	 */
	public float getMontant() {
		return this.montant;
	}

}
