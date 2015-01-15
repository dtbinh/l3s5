package piscine.util;

import piscine.ressource.Panier;
import piscine.ressource.RessourceUser;

/** Classe représentant une attente correspondant à la demande d'un panier */
public class DemandePanier extends Attente {

	// l'utilisateur qui attend
	private RessourceUser<Panier> utilisateur;
		
	/**
	 * constructeur pour les instances de la classe DemandePanier
	 * @param tda le temps d'attente
	 * @param util l'utilisateur qui attend
	 */
	public DemandePanier(int tda,RessourceUser<Panier> util) {
		super(tda);
		this.utilisateur = util;
	}
		
	@Override
	public void faire() throws ActionTermineeException {
		if (this.estTerminee()) {
			throw new ActionTermineeException();
		}
		else {
			System.out.println(this.utilisateur.getNom()+" demande un panier");
			this.tempsAttente--;
			if (this.tempsAttente == 0)
				this.etat = Etat.TERMINEE;
		}
	}

}
