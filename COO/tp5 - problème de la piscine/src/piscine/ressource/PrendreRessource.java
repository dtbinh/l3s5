package piscine.ressource;

import java.util.NoSuchElementException;

import piscine.util.Action;
import piscine.util.ActionTermineeException;
import piscine.util.Etat;

/** Classe représentant une action consistant à prendre une ressource */
public class PrendreRessource<R extends Ressource> extends Action {
	
	// le gestionnaire de ressources qui va fournir la ressource
		private GestionnaireRessources<R> gestionnaireRessources;
		
		// l'utilisateur qui va récupérer la ressource fournie par le gestionnaire de ressources
		private RessourceUser<R> utilisateurRessource;
	
	/**
	 * constructeur pour les instances de la classe PrendreRessource
	 * @param gr le gestionnaire de ressources qui va fournir la ressource
	 * @param ru l'utilisateur qui va récupérer la ressource fournie par le gestionnaire de ressources
	 */
	public PrendreRessource(GestionnaireRessources<R> gr,RessourceUser<R> ru) {
		this.gestionnaireRessources = gr;
		this.utilisateurRessource = ru;
	}

	@Override
	public void faire() throws ActionTermineeException {
		if (this.estTerminee()) {
			throw new ActionTermineeException();
		}
		else {
			try {
				R ressourceFournie = this.gestionnaireRessources.fournitRessource();
				this.utilisateurRessource.setRessource(ressourceFournie);
				System.out.println(this.utilisateurRessource.getNom()+" prend "+ressourceFournie.descriptionFournit());
				this.etat = Etat.TERMINEE;
			} catch (NoSuchElementException e) {}
		}
	}

}
