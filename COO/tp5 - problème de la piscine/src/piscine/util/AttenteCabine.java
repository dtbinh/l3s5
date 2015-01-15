package piscine.util;

import piscine.ressource.Cabine;
import piscine.ressource.RessourceUser;
import piscine.util.Attente;

/** Classe représentant l'attente d'un cabine */
public class AttenteCabine extends Attente {

	// l'utilisateur qui attend
	private RessourceUser<Cabine> utilisateur;
	
	/**
	 * constructeur pour les instances de la classe AttenteCabine
	 * @param tda le temps d'attente
	 * @param util l'utilisateur qui attend
	 */
	public AttenteCabine(int tda,RessourceUser<Cabine> util) {
		super(tda);
		this.utilisateur = util;
	}
	
	@Override
	public void faire() throws ActionTermineeException {
		if (this.estTerminee()) {
			throw new ActionTermineeException();
		}
		else {
			System.out.println(this.utilisateur.getNom()+" attend une cabine");
			this.tempsAttente--;
			if (this.tempsAttente == 0)
				this.etat = Etat.TERMINEE;
		}
	}

	
}
