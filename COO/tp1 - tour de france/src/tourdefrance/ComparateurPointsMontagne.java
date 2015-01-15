package tourdefrance;

import java.util.Comparator;

/** Classe permettant de reprensenter un comparateur de points montagne */
public class ComparateurPointsMontagne implements Comparator<Coureur> {

	/**
	 * compare deux coureurs en fonction de leur points montagne
	 * @param c1 un coureur
	 * @param c2 un coureur
	 * @return entier negatif si <code> c1 </code> a plus de points montagne que <code> c2 </code>, 0 si c1 et c2 ont le meme nombre de points montagne, entier positif si <code> c2 </code> a plus de points montagne que <code> c1 </code>
	 */
	public int compare(Coureur c1, Coureur c2) {
		return (TourDeFrance.lesResultats.get(c2).getPointsMontagne() - TourDeFrance.lesResultats.get(c1).getPointsMontagne());
	}

}