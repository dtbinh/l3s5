package piscine.util;

/** Classe représentant une action */
public abstract class Action {

	// l'état de l'action
	protected Etat etat;
	
	/**
	 * constructeur pour les instances de la classe Action
	 */
	public Action() {
		this.etat = Etat.NON_TERMINEE;
	}
	
	/**
	 * fait l'action
	 * @exception ActionTermineeException si l'action est déjà terminée
	 */
	public abstract void faire() throws ActionTermineeException;

	/**
	 * renvoie <code> true </code> si l'action est terminée, <code> false </code> sinon
	 * @return <code> true </code> si l'action est terminée, <code> false </code> sinon
	 */
	public boolean estTerminee() {
		return (this.etat == Etat.TERMINEE);
	}

}
