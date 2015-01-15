package facteur.contenus;

/**
 * Classe permettant de reprensenter du contenu textuel
 */
public class Texte implements Contenu {

	// le texte
	/**
	 * @uml.property  name="texte"
	 */
	private String texte;

	/**
	 * constructeur pour les instances de la classe Texte
	 * @param t le texte
	 */
	public Texte(String t) {
		this.texte = t;
	}
	
	/**
	 * renvoie le texte
	 * @return  le texte
	 * @uml.property  name="texte"
	 */
	public String getTexte() {
		return this.texte;
	}

}
