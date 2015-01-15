package tourdefrance;

/** Classe permettant de representer un coureur */
public class Coureur {

	// son nom
	private String nom;

	// son prenom
	private String prenom;

	// son numero de licence
	private String numLicence;

	// son age
	private int age;

	/**
	 * constructeur pour les instances de la classe Coureur
	 * @param n le nom du coureur
	 * @param p le prenom du coureur
	 * @param l le numero de licence du coureur
	 * @param a l'age du coureur
	 */
	public Coureur(String n, String p, String l, int a) {
		if (a <= 0) {
			throw new IllegalArgumentException("Age incorrect");
		}
		else {
			this.nom = n;
			this.prenom = p;
			this.numLicence = l;
			this.age = a;
		}
	}

	/**
	 * renvoie le nom du coureur
	 * @return le nom du coureur
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * renvoie le prenom du coureur
	 * @return le prenom du coureur
	 */
	public String getPrenom() {
		return this.prenom;
	}

	/**
	 * renvoie le numero de licence du coureur
	 * @return le numero de licence du coureur
	 */
	public String getLicence() {
		return this.numLicence;
	}

	/**
	 * renvoie l'age du coureur
	 * @return l'age du coureur
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * renvoie le nom complet du coureur
	 * @return le nom complet du coureur
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return (this.prenom+" "+this.nom.toUpperCase());
	}

	/**
	 * renvoie le hashCode du coureur
	 * @return le hashCode du coureur
	 */
	public int hashCode() {
		return this.numLicence.hashCode();
	}

	/**
	 * verifie l'egalite entre l'objet en parametre et le temps courant
	 * @param o l'objet dont on verifie l'egalite avec le coureur courant
	 * @return <code> true </code> si les deux objets sont egaux, <code> false </code> sinon
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (o instanceof Coureur) {
			Coureur c = (Coureur) o;
			return this == c;
		}
		else {
			return false;
		}
	}

}
