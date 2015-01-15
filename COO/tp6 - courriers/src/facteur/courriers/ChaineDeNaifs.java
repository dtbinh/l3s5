package facteur.courriers;

import facteur.contenus.ListeHabitants;
import facteur.util.HabitantNaif;
import java.util.List;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * Classe permettant de representer une chaine de naifs
 */
public class ChaineDeNaifs extends Courrier<ListeHabitants> {

	// la liste des 10 destinataires qui doivent recevoir la lettre avec les 4 noms et la procedure a suivre (pour l'impression)
	/**
	 * @uml.property  name="listeDestinataires"
	 * @uml.associationEnd  
	 */
	private ListeHabitants listeDestinataires;

	/**
	 * constructeur pour les instances de la classe ChaineDeNaifs
	 * @param expediteur l'expediteur de la lettre
	 * @param destinataire le destinataire de la lettre
	 * @param listeHabitants la liste des habitants de la lettre
	 */
	public ChaineDeNaifs(HabitantNaif expediteur,HabitantNaif destinataire,List<HabitantNaif> listeHabitants) {
		super(expediteur,destinataire,new ListeHabitants(listeHabitants));
	}

	/**
	 * renvoie le cout de la lettre
	 * @return le cout de la lettre
	 */
	@Override
	public float cout() {
		return 0.5f;
	}

	/**
	 * met a jour la liste des 10 destinataires
	 */
	public void modifListeDestinataires(ListeHabitants lh) {
		this.listeDestinataires = lh;
	}

	/**
	 * @return
	 * @uml.property  name="listeDestinataires"
	 */
	public ListeHabitants getListeDestinataires() {
		return this.listeDestinataires;
	}

	/**
	 * ajoute un habitant a la liste
	 * @param h l'habitant a ajouter
	 */
	public boolean ajoute(HabitantNaif h) {
		return this.getContenu().getLaListe().add(h);
	}

	/**
	 * renvoie la taille de la liste
	 * @return la taille de la liste
	 */
	public int taille() {
		return this.getContenu().getLaListe().size();
	}

	/**
	 * supprime l'habitant a l'indice <code> i </code> de la liste
	 * @param i l'indice de l'haitant a supprimer
	 */
	public void supprime(int i) {
		Iterator<HabitantNaif> it = this.getContenu().getLaListe().iterator();
		this.getContenu().getLaListe().remove(it.next());
	}

	/**
	 * affiche un accuse de reception d'une chaine de naifs
	 * @param h l'expediteur de la chaine
	 * @param lcn le contenu de la chaine
	 * @param lh la liste des destinataires
	 */
	public static void recu(HabitantNaif h, ListeHabitants lcn, ListeHabitants lh) {
		System.out.println("CHAINE DE NAIFS {ENVOYE} par "+h);
		System.out.print("Liste des destinataires : "); lh.afficheListe();
		System.out.print("Noms figurant sur la lettre : "); lcn.afficheListe(); System.out.println();
	}

	/**
	 * effectue une action a la reception de la lettre par le destinataire
	 */
	@Override
	public void action() {
		HabitantNaif h = (HabitantNaif) this.destinataire;
		float probaAleatoire = (float) Math.random();
		h.repondAUneChaine(this,probaAleatoire);
	}

}
