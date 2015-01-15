package tourdefrance;

import java.util.Comparator;

/** Classe permettant de reprensenter un comparateur de points vert */
public class ComparateurPointsVert implements Comparator<Coureur> {

	/**
	 * compare deux coureurs en fonction de leur points vert
	 * @param c1 un coureur
	 * @param c2 un coureur
	 * @return entier negatif si <code> c1 </code> a plus de points vert que <code> c2 </code>, 0 si c1 et c2 ont le meme nombre de points vert, entier positif si <code> c2 </code> a plus de points vert que <code> c1 </code>
	 */
	public int compare(Coureur c1, Coureur c2) {
		return (TourDeFrance.lesResultats.get(c2).getPointsVert() - TourDeFrance.lesResultats.get(c1).getPointsVert());
	}

}
