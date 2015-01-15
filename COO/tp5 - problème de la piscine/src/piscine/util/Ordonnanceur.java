package piscine.util;

import java.util.ArrayList;
import java.util.List;

/** Classe représentant un ordonnanceur (action composée de sous-actions) */
public abstract class Ordonnanceur extends Action {

	// la liste des sous-actions
	protected List<Action> lesSousActions;
	
	/**
	 * constructeur pour les instances de la classe Ordonnanceur
	 */
	public Ordonnanceur() {
		super();
		this.lesSousActions = new ArrayList<Action>();
	}
	
	/**
	 * ajoute une action à l'ordonnanceur
	 * @param a l'action à ajouter
	 */
	public void ajouteAction(Action a) {
		this.lesSousActions.add(a);
	}
	
	/**
	 * renvoie le nombre de sous-actions de l'ordonnanceur
	 * @return le nombre de sous-actions de l'ordonnanceur
	 */
	public int nbreSousActions() {
		return this.lesSousActions.size();
	}
	
	@Override
	public abstract void faire() throws ActionTermineeException;

}
