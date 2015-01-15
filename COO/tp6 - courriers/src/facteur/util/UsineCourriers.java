package facteur.util;

import java.util.Random;
import facteur.courriers.*;

/** Classe permettant de representer une factory pour la classe Courrier */
public class UsineCourriers {

	/**
	 * construit un objet Courrier en fonction d'un habitant expediteur, un habitant destinataire et l'entier passe en parametre
	 * @param expediteur l'expediteur du courrier
	 * @param destinataire le destinataire du courrier
	 * @param n un entier qui permet de reconnaitre le type du courrier
	 * @return un objet Courrier fonction de <code> expediteur </code>, <code> destinataire </code> et <code> n </code>
	 * @exception IllegalArgumentException si n > 7 (n'est pas dans le cas des 8 types de courrier)
	 */
	public static Courrier<?> construitCourrier(Habitant expediteur, Habitant destinataire, int n) throws IllegalArgumentException {
		Random rand = new Random();
		switch (n) {
			case 0 : return new LettreSimple(expediteur,destinataire,"");
			case 1 : return new LettreDeChange(expediteur,destinataire,rand.nextInt(15));
			case 2 : return new CourrierRecommande(new LettreSimple(expediteur,destinataire,""));
			case 3 : return new CourrierRecommande(new LettreDeChange(expediteur,destinataire,rand.nextInt(15)));
			case 4 : return new CourrierUrgent(new LettreSimple(expediteur,destinataire,""));
			case 5 : return new CourrierUrgent(new LettreDeChange(expediteur,destinataire,rand.nextInt(15)));
			case 6 : return new CourrierUrgent(new CourrierRecommande(new LettreSimple(expediteur,destinataire,"")));
			case 7 : return new CourrierUrgent(new CourrierRecommande(new LettreDeChange(expediteur,destinataire,rand.nextInt(15))));
			default : throw new IllegalArgumentException("n doit être supérieur ou égal à 0 et inférieur à 8");
		}
	}

}
