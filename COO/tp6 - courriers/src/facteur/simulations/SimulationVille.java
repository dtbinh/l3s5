package facteur.simulations;

import facteur.util.Ville;
import facteur.courriers.Courrier;
import facteur.util.Habitant;
import facteur.util.UsineCourriers;
import java.util.Random;
import java.util.Iterator;
import java.util.List;

/**
 * Classe permettant de creer une simulation de distribution de courriers
 */
public class SimulationVille implements Simulation {

	// la ville dans laquelle doit se derouler la simulation
	/**
	 * @uml.property  name="ville"
	 * @uml.associationEnd  
	 */
	private Ville ville;

	// le nombre d'habitants de la ville
	private int nombreHabitants;

	// le nombre de jours sur lesquels doit s'étaler la simulation
	private int nombreDeJours;

	/**
	 * constructeur pour les instances de la classe SimulationVille
	 * @param v la ville sur laquelle on effectue la simulation
	 * @param nh le nombre d'habitants de la ville (generes au hasard)
	 * @param nj le nombre de jours sur lesquels doit s'étaler la simulation
	 */
	public SimulationVille(Ville v,int nh,int nj) {
		this.ville = v;
		this.nombreHabitants = nh;
		this.nombreDeJours = nj;
		v.ajouteHabitants(nh);
	}

	/**
	 * affiche la liste des habitants et leurs soldes
	 */
	public void habitantsEtSoldes() {
		Iterator<Habitant> it = Ville.habitants.iterator();
		while (it.hasNext()) {
			Habitant h = it.next();
			System.out.println(""+h+" -> Solde = "+h.getSoldeCompte()+" €");
		}
	}

	/**
	 * deroule la simulation de distribution de courriers tant qu'il y a des courriers a distribuer
	 */
	public void derouleSimulation() {
		System.out.println("---------------------------------------- LISTE DES HABITANTS ET LEURS SOLDES INITIAUX ----------------------------------------");
		System.out.println();
		this.habitantsEtSoldes();
		System.out.println();
		System.out.println("---------------------------------------- SIMULATION DE DISTRIBUTION DES COURRIERS --------------------------------------------");
		System.out.println();
		Random rand = new Random();
		for (int k = 1 ; k <= this.nombreDeJours ; k++) {
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% JOUR "+k+" %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
			for (int i = 1 ; i <= k ; i++) {
				Habitant expediteur = Ville.habitants.get(rand.nextInt(this.nombreHabitants));
				Habitant destinataire = Ville.habitants.get(rand.nextInt(this.nombreHabitants));
				while (destinataire.equals(expediteur)) {
					destinataire = Ville.habitants.get(rand.nextInt(this.nombreHabitants));
				}
				Courrier<?> c = UsineCourriers.construitCourrier(expediteur,destinataire,rand.nextInt(8));
				expediteur.envoieCourrier(c);
			}
			this.ville.distribueCourrier();
		}
		if (this.ville.courrierADistribuer()) {
			System.out.println("%%%%%%%%%%%%%%%%%% JOUR "+(this.nombreDeJours + 1)+" (RÉPONSES AUX LETTRES DE CHANGE, AUX RECOMMANDÉS ET AUX URGENTS DU DERNIER JOUR) %%%%%%%%%%%%%%%%%%%\n");
			this.ville.distribueCourrier();
		}
		System.out.println("---------------------------------------------------- FIN DE LA SIMULATION ----------------------------------------------------");
	}
	
	/**
	 * simule une distribution de courriers
	 * @param args le tableau des parametres
	 */
	public static void main(String[] args) {
		try {
			Ville v = new Ville();
			int nh = Integer.parseInt(args[0]);
			int nj = Integer.parseInt(args[1]);
			SimulationVille sm = new SimulationVille(v,nh,nj);
			sm.derouleSimulation();
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Usage : java courriers.SimulationVille nh nj");
			System.out.println("        - nh = le nombre d'habitants de la ville");
			System.out.println("        - nj = le nombre de jours sur lesquels doit s'étaler la simulation");
		}
	}

}
