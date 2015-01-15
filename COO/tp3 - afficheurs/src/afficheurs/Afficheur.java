package afficheurs;

import afficheurs.util.FileFIFO;

/** Classe permettant un afficheur de message */
public class Afficheur {

	// l'ecran (sous forme de file)
	private FileFIFO<Character> ecran;

	// la largeur de l'ecran
	private int largeur;

	// le message a afficher
	private String message;

	// le curseur de l'ecran
	private int curseur;

	/**
	 * constructeur pour les instances de la classe Afficheur
	 * @param l la largeur de l'ecran
	 */
	public Afficheur(int l) {
		this.message = " ";
		this.largeur = l;
		this.ecran = new FileFIFO<Character>(l,' ');
		this.curseur = 0;
	}

	/**
	 * modifie le message a afficher
	 * @param m le nouveau message a afficher
	 * @exception IllegalArgumentException si le message n'est pas defini
	 * @see java.lang.IllegalArgumentException
	 */
	public void setMessage(String m) {
		if ((this.message.length() == 0) || (this.message == null)) {
			throw new IllegalArgumentException();
		}
		else {
			this.message = m;
			this.curseur = 0;
		}
	}

	/**
	 * decale le message d'un caractere vers la gauche
	 */
	public void decale() {
		this.ecran.ajoute(this.message.charAt(this.curseur++));
		if (this.curseur == this.message.length())
			this.curseur = 0;
	}

	/**
	 * renvoie ce qui s'affiche sur l'ecran
	 * @return ce qui s'affiche sur l'ecran
	 * @see java.lang.String#toString()
	 */
	public String toString() {
		return this.ecran.toString();
	}

}
