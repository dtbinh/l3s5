package facteur.util;

import facteur.courriers.Courrier;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe permettant de representer une ville
 */
public class Ville {

	// la liste des habitants de la ville
	/**
	 * @uml.property  name="habitants"
	 */
	public static List<Habitant> habitants;

	// la boite a lettres de la ville
	/**
	 * @uml.property  name="boiteALettres"
	 */
	private List<Courrier<?>> boiteALettres;

	// la sacoche du facteur
	/**
	 * @uml.property  name="sacocheFacteur"
	 */
	private List<Courrier<?>> sacocheFacteur;

	// la liste des habitants naifs de la ville
	/**
	 * @uml.property  name="habitantsNaifs"
	 */
	public static List<HabitantNaif> habitantsNaifs;

	/**
	 * constructeur pour les instances de la classe Ville
	 */
	public Ville() {
		this.habitants = new ArrayList<Habitant>();
		this.boiteALettres = new LinkedList<Courrier<?>>();
		this.sacocheFacteur = new LinkedList<Courrier<?>>();
		this.habitantsNaifs = new ArrayList<HabitantNaif>();
	}

	/**
	 * renvoie la liste des habitants de la ville
	 * @return  la liste des habitants de la ville
	 * @uml.property  name="habitants"
	 */
	public List<Habitant> getHabitants() {
		return this.habitants;
	}

	/**
	 * renvoie le nombre d'habitants de la ville
	 * @return le nombre d'habitants de la ville
	 */
	public int nbreHabitants() {
		return this.habitants.size();
	}

	/**
	 * renvoie la liste des courriers a distribuer (boite aux lettres)
	 * @return  la liste des courriers a distribuer (boite aux lettres)
	 * @uml.property  name="boiteALettres"
	 */
	public List<Courrier<?>> getBoiteALettres() {
		return this.boiteALettres;
	}

	/**
	 * renvoie la sacoche du facteur
	 * @return  la sacoche du facteur
	 * @uml.property  name="sacocheFacteur"
	 */
	public List<Courrier<?>> getSacocheFacteur() {
		return this.sacocheFacteur;
	}

	/**
	 * renvoie la liste des habitants naifs de la ville
	 * @return  la liste des habitants naifs de la ville
	 * @uml.property  name="habitantsNaifs"
	 */
	public List<HabitantNaif> getHabitantsNaifs() {
		return Ville.habitantsNaifs;
	}

	/**
	 * renvoie le nombre d'habitants naifs de la ville
	 * @return le nombre d'habitants naifs de la ville
	 */
	public static int nbreHabitantsNaifs() {
		return Ville.habitantsNaifs.size();
	}

	/**
	 * ajoute un habitant a la ville
	 * @param h l'habitant a ajouter a la ville
	 */
	public void ajouteHabitant(Habitant h) {
		this.habitants.add(h);
	}

	/**
	 * poste le courrier en parametre dans la boite aux lettres
	 * @param c le courrier a poster
	 */
	public void posteLettre(Courrier<?> c) {
		this.boiteALettres.add(c);
	}

	/**
	 * distribue le courrier de la boite aux lettres
	 */
	public void distribueCourrier() {
		Iterator<Courrier<?>> it1 = this.boiteALettres.iterator();
		while (it1.hasNext()) {
			Courrier<?> c = it1.next();
			this.sacocheFacteur.add(c);
		}
		this.boiteALettres.clear();
		Iterator<Courrier<?>> it2 = this.sacocheFacteur.iterator();
		while (it2.hasNext()) {
			Courrier<?> c = it2.next();
			c.getDestinataire().recoitCourrier(c);
		}
		this.sacocheFacteur.clear();
	}

	/**
	 * verifie s'il y a du courrier a distribuer
	 * @return renvoie <code> true </code> s'il y a du courrier a distribuer, <code> false </code> sinon
	 */
	public boolean courrierADistribuer() {
		return (!this.boiteALettres.isEmpty());
	}

	/**
	 * ajoute <code> n </code> habitants au hasard dans la ville
	 * @param n le nombre d'habitants a ajouter
	 */
	public void ajouteHabitants(int n) {
		Random rand = new Random();
		for (int i = 1 ; i <= n ; i++) {
			Habitant h = new Habitant("Prenom"+i,"Nom"+i,rand.nextInt(50),new Adresse(rand.nextInt(100),new Rue(""),this));
			this.habitants.add(h);
		}
	}

	/**
	 * ajoute <code> n </code> habitants naifs au hasard dans la ville
	 * @param n le nombre d'habitants naifs a ajouter
	 */
	public void ajouteHabitantsNaifs(int n) {
		Random rand = new Random();
		for (int i = 1 ; i <= n ; i++) {
			float probaReponse = (float) Math.random();
			HabitantNaif h = new HabitantNaif("Prenom"+i,"Nom"+i,rand.nextInt(50),new Adresse(rand.nextInt(100),new Rue(""),this),probaReponse);
			this.habitantsNaifs.add(h);
		}
	}

}
