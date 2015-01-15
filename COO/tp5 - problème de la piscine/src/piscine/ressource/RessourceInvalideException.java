package piscine.ressource;

/** Classe représentant une exception qui se déclenche lorsqu'on tente de libérer une ressource non présente dans la liste des ressources occupées
 * @see piscine.ressource.GestionnaireRessources#libererRessource(Ressource)
 */
public class RessourceInvalideException extends Exception {

	private static final long serialVersionUID = 1L;

}
