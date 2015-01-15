package tourdefrance;

/** Classe permettant de representer le temps d'un coureur */
public class Temps implements java.lang.Comparable<Temps> {

	// le nombre d'heures
	private int heures;

	// le nombre de minutes
	private int minutes;

	// le nombre de secondes
	private int secondes;

	/**
	 * constructeur pour les instances de la classe Temps
	 */
	public Temps() {
		this.heures = 0;
		this.minutes = 0;
		this.secondes = 0;
	}

	/**
	 * constructeur pour les objets de la classe Temps
	 * @param h le nombre d'heures
	 * @param m le nombre de minutes
	 * @param s le nombre de secondes
	 */
	public Temps(int h, int m, int s) {
		this.heures = h + (m / 60);
		this.minutes = (m % 60) + (s / 60);
		this.secondes = (s % 60);
	}

	/**
	 * renvoie le nombre d'heures
	 * @return le nombre d'heures
	 */
	public int getHeures() {
		return this.heures;
	}

	/**
	 * renvoie le nombre de minutes
	 * @return le nombre de minutes
	 */
	public int getMinutes() {
		return this.minutes;
	}

	/**
	 * renvoie le nombre de secondes
	 * @return le nombre de secondes
	 */
	public int getSecondes() {
		return this.secondes;
	}

	/**
	 * convertit le temps en secondes
	 * @return le temps en secondes
	 */
	public int enSecondes() {
		return ((this.heures * 3600) + (this.minutes * 60) + (this.secondes));
	}

	/**
	 * compare deux objets de type Temps
	 * @param t le temps a comparer au temps courant
	 * @return la difference de temps entre <code> this </code> et <code> t </code>
	 * @exception NullPointerException si <code> t == null </code>
	 */
	public int compareTo(Temps t) throws NullPointerException {
		return (this.enSecondes() - t.enSecondes());
	}

	/**
	 * ajoute le temps en parametre au temps courant
	 * @param t le temps a ajouter au temps courant
	 */
	public void ajouteTemps(Temps t) {
		this.heures += t.getHeures();
		this.minutes += t.getMinutes();
		this.secondes += t.getSecondes();
		this.heures = this.heures + (this.minutes / 60);
		this.minutes = (this.minutes % 60) + (this.secondes / 60);
		this.secondes = (this.secondes % 60);
	}

	/**
	 * renvoie le temps sous la forme d'une chaine de caracteres (HHH:MM:SS)
	 * @return le temps sous la forme d'une chaine de caracteres (HHH:MM:SS)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return String.format("%d:%02d:%02d",this.heures,this.minutes,this.secondes);
	}

	/**
	 * renvoie le hashCode du temps
	 * @return le hashCode du temps
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return this.enSecondes();
	}

	/**
	 * verifie l'egalite entre l'objet en parametre au temps courant
	 * @param o l'objet dont on doit verifier l'egalite avec <code> this </code>
	 * @return <code> true </code> si <code> this </code> = <code> o </code>, <code> false </code> sinon
	 */
	public boolean equals(Object o) {
		if (o instanceof Temps) {
			Temps t = (Temps) o;
			return (this == t);
		}
		else {
			return false;
		}
	}

}
