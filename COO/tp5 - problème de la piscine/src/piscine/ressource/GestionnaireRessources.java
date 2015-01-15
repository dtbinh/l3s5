package piscine.ressource;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/** Classe abstraite représentant un gestionnaire de ressources */
public abstract class GestionnaireRessources<R extends Ressource> {

	// les listes des ressources disponibles et occupées
	private List<R> ressourcesDisponibles, ressourcesOccupees;
	
	/**
	 * constructeur pour les instances de la classe GestionnaireRessources
	 * @param nbreRessources le nombre de ressources du gestionnaire
	 */
	public GestionnaireRessources(int nbreRessources) {
		this.ressourcesDisponibles = new ArrayList<R>();
		this.ressourcesOccupees = new ArrayList<R>();
		for (int i = 0 ; i < nbreRessources ; i++) {
			this.ressourcesDisponibles.add(this.creerRessource());
		}
	}
	
	/**
	 * renvoie une nouvelle ressource de type R
	 * @return une nouvelle ressource de type R
	 */
	public abstract R creerRessource();
	
	/**
	 * fournit une ressource
	 * @return la ressource prise
	 * @exception NoSuchElementException s'il n'y a pas de ressource disponible
	 */
	public R fournitRessource() throws NoSuchElementException {
		if (this.ressourcesDisponibles.isEmpty()) {
			throw new NoSuchElementException();
		}
		else {
			R ressource = this.ressourcesDisponibles.remove(0);
			this.ressourcesOccupees.add(ressource);
			return ressource;
		}
	}
	
	/**
	 * libère une ressource
	 * @param ressource la ressource à libérer
	 */
	public void libererRessource(R ressource) throws RessourceInvalideException {
		if (!this.ressourcesOccupees.contains(ressource)) {
			throw new RessourceInvalideException();
		}
		else {
			this.ressourcesOccupees.remove(ressource);
			this.ressourcesDisponibles.add(ressource);
		}
	}

}
