package piscine.ressource;

/** Classe représentant une cabine (ressource) */
public class Cabine implements Ressource {

	@Override
	public String descriptionFournit() {
		return "une cabine";
	}
	
	@Override
	public String descriptionLibere() {
		return "sort de sa cabine";
	}
	
	@Override
	public String descriptionAttente() {
		return "attend une cabine";
	}

}
