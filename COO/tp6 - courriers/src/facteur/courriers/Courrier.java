package facteur.courriers;

import facteur.contenus.Contenu;
import facteur.util.Habitant;

/**
 * Classe abstraite permettant de representer un courrier
 */
public abstract class Courrier<T extends Contenu> implements Contenu {
	
	// l'expediteur de la lettre
	/**
	 * @uml.property  name="expediteur"
	 * @uml.associationEnd  
	 */
	protected Habitant expediteur;

	// le destinataire de la lettre
	/**
	 * @uml.property  name="destinataire"
	 * @uml.associationEnd  
	 */
	protected Habitant destinataire;

	// le contenu de la lettre
	/**
	 * @uml.property  name="contenu"
	 */
	protected T contenu;

	/**
	 * constructeur pour les instances de la classe Courrier
	 * @param exp l'expediteur du courrier
	 * @param dest le destinataire du courrier
	 * @param cont le contenu du courrier
	 */
	public Courrier(Habitant exp, Habitant dest, T cont) {
		this.expediteur = exp;
		this.destinataire = dest;
		this.contenu = cont;
	}

	/**
	 * renvoie l'expediteur du courrier
	 * @return  l'expediteur du courrier
	 * @uml.property  name="expediteur"
	 */
	public Habitant getExpediteur() {
		return this.expediteur;
	}

	/**
	 * renvoie le destinataire du courrier
	 * @return  le destinataire du courrier
	 * @uml.property  name="destinataire"
	 */
	public Habitant getDestinataire() {
		return this.destinataire;
	}

	/**
	 * renvoie le contenu du courrier
	 * @return  le contenu du courrier
	 * @uml.property  name="contenu"
	 */
	public T getContenu() {
		return this.contenu;
	}

	/**
	 * renvoie le cout de l'envoi du courrier
	 * @return le cout de l'envoi du courrier
	 */
	public abstract float cout();
	
	/**
	 * effectue une action a la reception du courrier par le destinataire
	 */
	public abstract void action();

}
