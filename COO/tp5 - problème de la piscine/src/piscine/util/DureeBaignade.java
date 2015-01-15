package piscine.util;

import piscine.ressource.Panier;
import piscine.ressource.RessourceUser;

/** Classe représentant le temps que met un utilisateur pour se baigner */
public class DureeBaignade extends Attente {
	
	// l'utilisateur qui se baigne
	private RessourceUser<Panier> utilisateur;
	
	private boolean messageAffiche;
	
	/**
	 * constructeur pour les instances de la classe TempsDeshabillement
	 * @param tda le temps que met l'utilisateur pour se déshabiller
	 * @param util l'utilisateur
	 */
	public DureeBaignade(int tda,RessourceUser<Panier> util) {
		super(tda);
		this.utilisateur = util;
		this.messageAffiche = false;
	}
	
	@Override
	public void faire() throws ActionTermineeException {
		if (this.estTerminee()) {
			throw new ActionTermineeException();
		}
		else {
			if (this.messageAffiche == false) {
				System.out.println(this.utilisateur.getNom()+" se baigne");
				this.messageAffiche = true;
			}
			this.tempsAttente--;
			if (this.tempsAttente == 0) {
				System.out.println(this.utilisateur.getNom()+" sort de l'eau");
				this.etat = Etat.TERMINEE;
			}
		}
	}

}
