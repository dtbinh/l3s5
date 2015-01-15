package afficheurs;

import afficheurs.util.FileFIFO;

/** Classe permettant de representer un afficheur de message avec latence */
public class Latence extends Afficheur {

	// la latence de l'afficheur
	private int latence;

	/**
	 * constructeur pour les instances de la classe Latence
	 * @param largeur la largeur de l'ecran
	 * @param latence la latence de l'afficheur
	 */
	public Latence(int largeur, int latence) {
		super(largeur);
		this.latence = latence;
	}

	/**
	 * modifie le message a afficher
	 * @param m le nouveau message a afficher (avec la latence)
	 * @exception IllegalArgumentException si le message n'est pas defini
	 * @see java.lang.IllegalArgumentException
	 */
	public void setMessage(String m) {
		String messageAvecLatence = m;
		for (int i = 0 ; i < this.latence ; i++) {
			messageAvecLatence += " " ;
		}
		super.setMessage(messageAvecLatence);
	}

}
