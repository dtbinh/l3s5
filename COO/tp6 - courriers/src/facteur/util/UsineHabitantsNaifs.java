package facteur.util;

import facteur.contenus.ListeHabitants;
import java.util.Random;
import java.util.HashSet;
import java.util.LinkedList;

/** Classe permettant de representer une factory pour la classe ListeHabitants */
public class UsineHabitantsNaifs {

	/**
	 * construit un objet ListeHabitants de taille <code> n </code>
	 * @param n la taille de la liste
	 * @param lh une liste dont les elements de la liste doivent etre differents de ceux de la liste renvoyee
	 * @exception IllegalArgumentException si n < 0
	 * @return un objet ListeHabitants de taille <code> n </code>
	 */
	public static ListeHabitants construitListeHabitantsNaifs(int n, ListeHabitants lh) {
		Random rand = new Random();
		ListeHabitants liste = new ListeHabitants(new LinkedList<HabitantNaif>());
		while (liste.taille() < n) {
			HabitantNaif unHabitant = Ville.habitantsNaifs.get(rand.nextInt(Ville.habitantsNaifs.size()));
			while (lh.getLaListe().contains(unHabitant)) {
				unHabitant = Ville.habitantsNaifs.get(rand.nextInt(Ville.habitantsNaifs.size()));
			}
			liste.ajoute(unHabitant);
		}
		return liste;
	}

}
