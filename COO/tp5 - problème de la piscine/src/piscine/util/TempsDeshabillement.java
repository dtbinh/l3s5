package piscine.util;

import piscine.ressource.Cabine;
import piscine.ressource.RessourceUser;

/** Classe représentant le temps que met un utilisateur pour se déshabiller */
public class TempsDeshabillement extends Attente {
	
	// l'utilisateur qui se déshabille
	private RessourceUser<Cabine> utilisateur;
	
	private boolean messageAffiche;
			
	/**
	 * constructeur pour les instances de la classe TempsDeshabillement
	 * @param tda le temps que met l'utilisateur pour se déshabiller
	 * @param util l'utilisateur
	 */
	public TempsDeshabillement(int tda,RessourceUser<Cabine> util) {
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
				System.out.println(this.utilisateur.getNom()+" se déshabille");
				this.messageAffiche = true;
			}
			this.tempsAttente--;
			if (this.tempsAttente == 0)
				this.etat = Etat.TERMINEE;
		}
	}

}
