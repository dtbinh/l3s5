package piscine.util;

/** Classe représentant une attente (action prenant un certain temps à s'exécuter) */
public class Attente extends Action {
	
	// le temps d'attente (le temps que met l'action à s'exécuter)
	protected int tempsAttente;
	
	/**
	 * constructeur pour les instances de la classe Attente
	 * @param tda le temps d'attente
	 */
	public Attente(int tda) {
		super();
		this.tempsAttente = tda;
	}
	
	/**
	 * fait l'action
	 * @exception ActionTermineeException si l'action est déjà terminée
	 */
	@Override
	public void faire() throws ActionTermineeException {
		if (this.estTerminee()) {
			throw new ActionTermineeException();
		}
		else {
			this.tempsAttente--;
			if (this.tempsAttente == 0)
				this.etat = Etat.TERMINEE;
		}
	}

}
