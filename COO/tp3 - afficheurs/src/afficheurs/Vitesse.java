package afficheurs;

/** Classe permettant de reprensenter la vitesse de decalage d'un afficheur */
public class Vitesse extends Latence {

	// la vitesse de decalage
	private int vitesse;

	/**
	 * constructeur pour les instances de la classe Vitesse
	 * @param largeur la largeur de l'ecran
	 * @param latence la latence de l'afficheur
	 * @param vitesse la vitesse de l'afficheur
	 */
	public Vitesse(int largeur, int latence, int vitesse) {
		super(largeur,latence);
		this.vitesse = vitesse;
	}

	/**
	 * decale le message
	 */
	public void decale() {
		for (int i = 0 ; i < this.vitesse ; i++) {
			super.decale();
		}
	}

}
