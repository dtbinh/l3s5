package tourdefrance;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.NoSuchElementException;
import java.util.Collections;

/** Classe permettant de representer le Tour de France */
public class TourDeFrance {

	// la liste des etapes
	private List<Etape> lesEtapes;

	// la liste des coureurs et leurs resultats
	static Map<Coureur,Resultat> lesResultats;

	// la liste des coureurs ayant abandonne
	private List<Coureur> lesAbandons;

	/**
	 * constructeur pour les instances de la classe TourDeFrance
	 * @param le la liste des etapes
	 * @param lc la liste des coureurs
	 */
	public TourDeFrance(List<Etape> le, List<Coureur> lc) {
		this.lesAbandons = new ArrayList<Coureur>();
		this.lesEtapes = le;
		this.lesResultats = new HashMap<Coureur,Resultat>();
		for (Coureur c : lc) {
			this.lesResultats.put(c,new Resultat(new Temps(),0,0));
		}
	}

	/**
	 * renvoie la liste des coureurs ayant abandonne la course
	 * @return la liste des coureurs ayant abandonne la course
	 */
	public List<Coureur> coureursAyantAbandonne() {
		return this.lesAbandons;
	}

	/**
	 * renvoie la liste des coureurs encore en course
	 * @return la liste des coureurs encore en course
	 */
	public List<Coureur> coureursEncoreEnCourse() {
		Set<Coureur> setCoureurs = this.lesResultats.keySet();
		List<Coureur> encoreEnCourse = new ArrayList<Coureur>();
		for (Coureur c : setCoureurs) {
			encoreEnCourse.add(c);
		}
		return encoreEnCourse;
	}

	/**
	 * dispute la course
	 * @exception EtapeNonCourueException si une etape n'a pas ete courue
	 */
	public void disputeCourse() throws EtapeNonCourueException {
		// parcours de la liste des etapes
		for (Etape e : lesEtapes) {
			Set<Coureur> lc = this.lesResultats.keySet();
			List<Coureur> lesCoureurs = new ArrayList<Coureur>();
			for (Coureur c : lc) {
				lesCoureurs.add(c);
			}
			// on fait disputer l'etape a chaque coureur
			e.disputer(lesCoureurs);
			// ajout des abandons de l'etape dans la liste des abandons
			this.lesAbandons.addAll(e.abandons());
			// suppression des coureurs ayant abandonne de la liste des resultats
			for (Coureur c : this.lesAbandons) {
				this.lesResultats.remove(c);
			}
			// mise a jour des resultats des coureurs encore en course
			for (Coureur c : this.coureursEncoreEnCourse()) {
				this.lesResultats.get(c).cumule(e.resultats().get(c));
			}
			this.palmares();
		}
	}

	/**
	 * renvoie la liste des coureurs encore en course triée par ordre croissant des temps
	 * @return la liste des coureurs encore en course triée par ordre croissant des temps
	 */
	public List<Coureur> classementGeneral() {
		List<Coureur> lc = this.coureursEncoreEnCourse();
		Collections.sort(lc,new ComparateurTemps());
		return lc;
	}

	/**
	 * renvoie le coureur detenteur du maillot jaune
	 * @return le coureur detenteur du maillot jaune
	 */
	public Coureur maillotJaune() {
		return this.classementGeneral().get(0);
	}

	/**
	 * renvoie le meilleur jeune (detenteur du maillot blanc) parmi les coureurs en course
	 * @return le meilleur jeune (detenteur du maillot blanc) parmi les coureurs en course
	 * @exception NoSuchElementException si aucun jeune n'est en course
	 */
	public Coureur meilleurJeune() throws NoSuchElementException {
		List<Coureur> lesJeunes = new ArrayList<Coureur>();
		for (Coureur c : this.coureursEncoreEnCourse()) {
			if (c.getAge() < 25)
				lesJeunes.add(c);
		}
		if (lesJeunes.size() == 0) {
			throw new java.util.NoSuchElementException();
		}
		else {
			Collections.sort(lesJeunes,new ComparateurTemps());
			return lesJeunes.get(0);
		}
	}

	/**
	 * renvoie le coureur detenteur du maillot vert
	 * @return le coureur detenteur du maillot vert
	 */
	public Coureur maillotVert() {
		List<Coureur> lc = this.coureursEncoreEnCourse();
		Collections.sort(lc,new ComparateurPointsVert());
		return lc.get(0);
	}

	/**
	 * renvoie le coureur detenteur du maillot a pois de meilleur grimpeur
	 * @return le coureur detenteur du maillot a pois de meilleur grimpeur
	 */
	public Coureur maillotMontagne() {
		List<Coureur> lc = this.coureursEncoreEnCourse();
		Collections.sort(lc,new ComparateurPointsMontagne());
		return lc.get(0);
	}

	/**
	 * affiche les coureurs detenteurs de chacun des maillots
	 */
	public void palmares() {
		System.out.println("Maillot jaune {leader} : "+this.maillotJaune()+" [Temps : "+this.lesResultats.get(this.maillotJaune()).getTemps()+"]");
		System.out.println("Maillot blanc {meilleur jeune} : "+this.meilleurJeune()+" [Temps : "+this.lesResultats.get(this.meilleurJeune()).getTemps()+"]");
		System.out.println("Maillot vert {meilleur sprinteur} : "+this.maillotVert()+" [Points : "+this.lesResultats.get(this.maillotVert()).getPointsVert()+"]");
		System.out.println("Maillot a pois {meilleur grimpeur} : "+this.maillotMontagne()+" [Points : "+this.lesResultats.get(this.maillotMontagne()).getPointsMontagne()+"]");
	}

}
