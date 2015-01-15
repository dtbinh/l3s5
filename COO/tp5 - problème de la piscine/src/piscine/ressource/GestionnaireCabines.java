package piscine.ressource;

/** Classe représentant un gestionnaire de cabines */
public class GestionnaireCabines extends GestionnaireRessources<Cabine> {

	/**
	 * constructeur pour les instances de la classe GestionnaireCabines
	 * @param nbreCabines le nombre de cabines
	 */
	public GestionnaireCabines(int nbreCabines) {
		super(nbreCabines);
	}
	
	@Override
	public Cabine creerRessource() {
		return new Cabine();
	}

}
