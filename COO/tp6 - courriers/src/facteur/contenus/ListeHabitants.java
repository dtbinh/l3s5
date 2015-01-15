package facteur.contenus;

import facteur.util.HabitantNaif;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * Classe permettant de representer une liste d'habitants
 */
public class ListeHabitants implements Contenu {

	// la liste des habitants
	/**
	 * @uml.property  name="laListe"
	 */
	List<HabitantNaif> laListe;

	/**
	 * constructeur pour les instances de la classe ListeHabitants
	 * @param lh la liste des habitants
	 */
	public ListeHabitants(List<HabitantNaif> lh) {
		this.laListe = lh;
	}

	/**
	 * renvoie la liste des habitants
	 * @return  la liste des habitants
	 * @uml.property  name="laListe"
	 */
	public List<HabitantNaif> getLaListe() {
		return this.laListe;
	}

	/**
	 * ajoute un habitant a la liste
	 * @param h l'habitant a ajouter
	 */
	public boolean ajoute(HabitantNaif h) {
		return this.laListe.add(h);
	}

	/**
	 * ajoute tous les elements de la liste en parametre a this
	 * @param l la liste dont on doit ajouter les elements a this
	 */
	public void ajoute(ListeHabitants l) {
		this.laListe.addAll(l.getLaListe());
	}

	/**
	 * renvoie la taille de la liste
	 * @return la taille de la liste
	 */
	public int taille() {
		return this.laListe.size();
	}

	/**
	 * supprime l'habitant a l'indice <code> i </code> de la liste
	 * @param i l'indice de l'haitant a supprimer
	 */
	public void supprime(int i) {
		this.laListe.remove(i);
	}

	/**
	 * affiche les noms des habitants de la liste
	 */
	public void afficheListe() {
		Iterator<HabitantNaif> it = this.laListe.iterator();
		String s = new String("");
		while (it.hasNext()) {
			HabitantNaif h = it.next();
			s += h+" | ";
		}
		System.out.println(s.substring(0,s.length() - 2));
	}

}
