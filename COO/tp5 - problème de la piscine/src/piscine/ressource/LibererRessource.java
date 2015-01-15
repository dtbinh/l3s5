package piscine.ressource;

import piscine.util.Action;
import piscine.util.ActionTermineeException;
import piscine.util.Etat;

/** Classe représentant une action consistant à libérer une ressource */
public class LibererRessource<R extends Ressource> extends Action {
	
	// le gestionnaire de ressources qui va récupérer la ressource libérée par l'utilisateur
	private GestionnaireRessources<R> gestionnaireRessources;
	
	// l'utilisateur qui libère la ressource
	private RessourceUser<R> utilisateurRessource;
	
	/**
	 * constructeur pour les instances de la classe LibererRessource
	 * @param gr le gestionnaire de ressources qui va récupérer la ressource libérée par l'utilisateur
	 * @param ru l'utilisateur qui libère la ressource
	 */
	public LibererRessource(GestionnaireRessources<R> gr,RessourceUser<R> ru) {
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
				R ressourceLiberee = this.utilisateurRessource.getRessource();
				this.utilisateurRessource.resetRessource();
				this.gestionnaireRessources.libererRessource(ressourceLiberee);
				System.out.println(this.utilisateurRessource.getNom()+" "+ressourceLiberee.descriptionLibere()); 
				this.etat = Etat.TERMINEE;
			} catch (RessourceInvalideException e) {}
		}
	}

}
