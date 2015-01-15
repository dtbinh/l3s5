package facteur.simulations;

import facteur.util.Ville;
import facteur.util.HabitantNaif;
import facteur.util.Habitant;
import facteur.util.UsineHabitantsNaifs;
import facteur.contenus.ListeHabitants;
import java.util.Random;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

/** Classe permettant de creer une simulation de chaines de naifs */
public class SimulationCDN implements Simulation {

	// la ville dans laquelle doit se derouler la simulation
	private Ville ville;

	// le nombre d'habitants de la ville
	private int nombreHabitants;

	// le nombre de jours sur lesquels doit s'étaler la simulation
	private int nombreDeJours;

	/**
	 * constructeur pour les instances de la classe SimulationCDN
	 * @param v la ville sur laquelle on effectue la simulation
	 * @param nh le nombre d'habitants de la ville (generes au hasard)
	 */
	public SimulationCDN(Ville v,int nh) {
		this.ville = v;
		this.nombreHabitants = nh;
		v.ajouteHabitantsNaifs(nh);
	}

	/**
	 * courrier initiateur de la simulation
	 */
	public void courrierInitiateur() {
		Random rand = new Random();
		HabitantNaif expediteurInitial = Ville.habitantsNaifs.get(rand.nextInt(this.nombreHabitants));
		while (expediteurInitial.getSoldeCompte() < 29.2) {
			expediteurInitial = Ville.habitantsNaifs.get(rand.nextInt(this.nombreHabitants));
		}
		ListeHabitants lh = new ListeHabitants(new LinkedList<HabitantNaif>()); lh.ajoute(expediteurInitial);
		ListeHabitants listeDestinataires = UsineHabitantsNaifs.construitListeHabitantsNaifs(3,lh);
		lh.ajoute(listeDestinataires);
		ListeHabitants chaineInitiale = UsineHabitantsNaifs.construitListeHabitantsNaifs(3,lh);
		chaineInitiale.ajoute(expediteurInitial);
		expediteurInitial.envoieChainesDeNaifs(chaineInitiale,listeDestinataires);
	}

	/**
	 * affiche la liste des habitants et leurs soldes
	 */
	public void habitantsEtSoldes() {
		Iterator<HabitantNaif> it = Ville.habitantsNaifs.iterator();
		while (it.hasNext()) {
			Habitant h = it.next();
			System.out.println(""+h+" -> Solde = "+h.getSoldeCompte()+" €");
		}
	}

	/**
	 * deroule la simulation de chaines de naifs initiees par un courrier
	 */
	public void derouleSimulation() {
		System.out.println("---------------------------------------- LISTE DES HABITANTS ET LEURS SOLDES INITIAUX ----------------------------------------");
		System.out.println();
		this.habitantsEtSoldes();
		System.out.println();
		System.out.println("---------------------------------------- SIMULATION DES CHAINES DE NAIFS --------------------------------------------");
		System.out.println();
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% JOUR 0 (COURRIER INITIATEUR) %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
		this.courrierInitiateur();
		int k = 1;
		while (this.ville.courrierADistribuer()) {
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% JOUR "+k+" %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
				this.ville.distribueCourrier();
				k++;
		}
		System.out.println("---------------------------------------------------- FIN DE LA SIMULATION ----------------------------------------------------");
	}

	public static void main(String[] args) {
		try {
			Ville v = new Ville();
			int nh = Integer.parseInt(args[0]);
			Simulation scdn = new SimulationCDN(v,nh);
			scdn.derouleSimulation();
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Usage : java courriers.SimulationCDN nh");
			System.out.println("        - nh = le nombre d'habitants de la ville");
		}
	}

}
