package tourdefrance;

/** Classe permettant de representer le resultat d'un coureur */
public class Resultat {

	// le temps du coureur
	private Temps temps;

	// le nombre de points vert du coureur
	private int pointsVert;

	// le nombre de points montagne du coureur
	private int pointsMontagne;

	/**
	 * constructeur pour les instances de la classe Resultat
	 * @param t le temps du coureur
	 * @param pointsVert le nombre de points vert du coureur
	 * @param pointsMontagne le nombre de points montagne du coureur
	 */
	public Resultat(Temps t, int pointsVert, int pointsMontagne) {
		this.temps = t;
		this.pointsVert = pointsVert;
		this.pointsMontagne = pointsMontagne;
	}

	/**
	 * renvoie le temps du coureur
	 * @return le temps du coureur
	 */
	public Temps getTemps() {
		return this.temps;
	}

	/**
	 * renvoie le nombre de points vert du coureur
	 * @return le nombre de points vert du coureur
	 */
	public int getPointsVert() {
		return this.pointsVert;
	}

	/**
	 * renvoie le nombre de points montagne du coureur
	 * @return le nombre de points montagne du coureur
	 */
	public int getPointsMontagne() {
		return this.pointsMontagne;
	}

	/**
	 * cumule le resultat en parametre au resultat courant
	 * @param r le resultat a cumuler au resultat courant
	 */
	public void cumule(Resultat r) {
		this.temps.ajouteTemps(r.getTemps());
		this.pointsVert += r.getPointsVert();
		this.pointsMontagne += r.getPointsMontagne();
	}

	/**
	 * renvoie le temps du coureur
	 * @return le temps du coureur
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return (""+this.temps+"");
	}

}
