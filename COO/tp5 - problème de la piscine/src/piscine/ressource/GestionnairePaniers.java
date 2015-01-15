package piscine.ressource;

/** Classe représentant un gestionnaire de paniers */
public class GestionnairePaniers extends GestionnaireRessources<Panier> {
	
	/**
	 * constructeur pour les instances de la classe GestionnairePaniers
	 * @param nbrePaniers le nombre de paniers
	 */
	public GestionnairePaniers(int nbrePaniers) {
		super(nbrePaniers);
	}

	@Override
	public Panier creerRessource() {
		return new Panier();
	}

}
