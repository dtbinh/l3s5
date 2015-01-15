package tourdefrance;

import java.util.Comparator;

/** Classe permettant de representer un comparateur de temps */
public class ComparateurTemps implements Comparator<Coureur> {

	/**
	 * compare deux coureurs en fonction de leur temps
	 * @param c1 un coureur
	 * @param c2 un coureur
	 * @return entier positif si <code> c1 </code> a ete plus rapide que <code> c2 </code>, 0 si c1 et c2 ont le meme temps, entier positif si <code> c2 </code> est plus rapide que <code> c1 </code>
	 */
	public int compare(Coureur c1, Coureur c2) {
		Temps t1 = TourDeFrance.lesResultats.get(c1).getTemps();
		Temps t2 = TourDeFrance.lesResultats.get(c2).getTemps();
		return t1.compareTo(t2);
	}

}
