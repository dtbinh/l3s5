package piscine.ressource;

/** Classe représentant un panier (ressource) */
public class Panier implements Ressource {

	@Override
	public String descriptionFournit() {
		return "un panier";
	}
	
	@Override
	public String descriptionLibere() {
		return "donne son panier";
	}

	@Override
	public String descriptionAttente() {
		return "demande un panier";
	}

}
