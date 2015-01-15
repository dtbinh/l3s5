package facteur.simulations;

/** Classe abstraite permettant de representer une simulation */
public interface Simulation {

	/**
	 * affiche la liste des habitants et leurs soldes
	 */
	public void habitantsEtSoldes();

	/**
	 * deroule la simulation tant qu'il y a des courriers a distribuer
	 */
	public void derouleSimulation();

}
