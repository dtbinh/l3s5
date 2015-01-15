package facteur.util;

import facteur.courriers.ChaineDeNaifs;
import facteur.courriers.LettreDeChange;
import facteur.courriers.Courrier;
import facteur.contenus.Argent;
import facteur.contenus.ListeHabitants;
import java.util.List;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Classe permettant de representer des habitants naifs
 */
public class HabitantNaif extends Habitant {

	// la probabilite que l'habitant reponde a une lettre de chaine de naifs
	/**
	 * @uml.property  name="probaReponse"
	 */
	private float probaReponse;
	
	// le nombre de fois que l'habitant a repondu a une lettre de chaine de naifs
	private int nbreReponses;

	// permet de savoir si l'habitant a repondu a la derniere lettre de chaine de naifs qu'il a recue
	private boolean aRepondu;

	// le gain total de l'habitant dans les chaines de naifs
	/**
	 * @uml.property  name="gainTotal"
	 */
	private int gainTotal;

	// le nombre d'echecs depuis le dernier gain
	/**
	 * @uml.property  name="echecsSuccessifs"
	 */
	private int echecsSuccessifs;

	/**
	 * constructeur pour les instances de la classe HabitantNaif
	 * @param prenom le prenom de l'habitant
	 * @param nom le nom de l'habitant
	 * @param soldeCompte le solde du compte en banque de l'habitant
	 * @param adresse l'adresse de l'habitant
	 * @param pr la probabilite que l'habitant reponse a une lettre de chaine de naifs recue
	 */
	public HabitantNaif(String prenom, String nom, float soldeCompte, Adresse adresse,float pr) {
		super(prenom,nom,soldeCompte,adresse);
		this.nbreReponses = 0;
		this.aRepondu = true;
		this.echecsSuccessifs = 0;
		this.gainTotal = 0;
		if (pr < 0 || pr > 1)
			throw new IllegalArgumentException("Probabilité de réponse invalide");
		else
			this.probaReponse = pr;
	}

	/**
	 * renvoie le gain total de l'habitant
	 * @return  le gain total de l'habitant
	 * @uml.property  name="gainTotal"
	 */
	private int getGainTotal() {
		return this.gainTotal;
	}

	/**
	 * ajoute <code> g </code> au gain total
	 */
	public void ajouteGain(int g) {
		this.gainTotal += g;
	}

	/**
	 * renvoie le nombre d'echecs successifs de l'habitant
	 * @return  le nombre d'echecs successifs de l'habitant
	 * @uml.property  name="echecsSuccessifs"
	 */
	public int getEchecsSuccessifs() {
		return this.echecsSuccessifs;
	}

	/**
	 * reinitialise le nombre d'echecs successifs de l'habitant a 0 (suite a un gain)
	 */
	public void razEchecsSuccessifs() {
		this.echecsSuccessifs = 0;
	}

	/**
	 * renvoie la probabilite de reponse de l'habitant
	 * @return  la probabilite de reponse de l'habitant
	 * @uml.property  name="probaReponse"
	 */
	public float getProbaReponse() {
		return this.probaReponse;
	}

	/**
	 * envoie une lettre de change d'un montant de 5€ a chacun des habitants de la liste en parametre
	 * @param lh la liste contenant les habitants destinataires
	 */
	public void envoieLettresDeChange(ListeHabitants lh) {
		Iterator<HabitantNaif> it = lh.getLaListe().iterator();
		while (it.hasNext()) {
			HabitantNaif h = it.next();
			Courrier<Argent> c = new LettreDeChange(this,h,5);
			this.envoieCourrier(c);
			h.ajouteGain(5);
			h.razEchecsSuccessifs();
		}
	}

	/**
	 * envoie une lettre de chaine de naifs contenant la liste <code> lcn </code> a tous les habitants de la liste <code> lh </code>
	 * @param lcn la liste qui doit etre dans la chaine de naifs a envoyer
	 * @param lh la liste des habitants qui doivent recevoir la chaine de naifs
	 */
	public void envoieChainesDeNaifs(ListeHabitants lcn, ListeHabitants lh) {
		Iterator<HabitantNaif> it = lh.getLaListe().iterator();
		while (it.hasNext()) {
			HabitantNaif h = it.next();
			ChaineDeNaifs c = new ChaineDeNaifs(this,h,lcn.getLaListe());		
			c.modifListeDestinataires(lh);
			this.envoieCourrier(c);
			this.debiter(c.cout());
		}
		ChaineDeNaifs.recu(this,lcn,lh);
	}

	/**
	 * gere la reponse a une lettre de chaine de naifs
	 * @param cdn la lettre recue
	 * @param proba le seuil de probabilite
	 */
	public boolean repondAUneChaine(ChaineDeNaifs cdn, float proba) {
		// 25.2 = coût total des lettres de change (+ les montants) + coût total des chaines de naifs
		if (proba < this.probaReponse && this.aRepondu) {
			if (this.getSoldeCompte() < 29.2) {
				System.out.println(this+" ne peut pas répondre à la chaîne de "+cdn.getExpediteur()+" car son solde est insuffisant\n");
				this.aRepondu = false;
				return false;
			}
			else {
				if (this.echecsSuccessifs < 5) {
					this.envoieLettresDeChange(cdn.getContenu());
					cdn.supprime(0);
					cdn.ajoute(this);
					ListeHabitants lh = new ListeHabitants(new LinkedList<HabitantNaif>()); lh.ajoute(this);
					ListeHabitants listeDestinataires = UsineHabitantsNaifs.construitListeHabitantsNaifs(3,lh);
					this.envoieChainesDeNaifs(cdn.getContenu(),listeDestinataires);
					return true;
				}
				else {
					this.aRepondu = false;
					return false;
				}
			}
		}
		else {
			System.out.println(this+" n'a pas envie de repondre à la chaine envoyée par "+cdn.getExpediteur()+"\n");
			this.aRepondu = false;
			return false;
		}
	}

}
