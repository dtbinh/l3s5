package facteur.courriers;

import facteur.contenus.Texte;
import facteur.util.Habitant;

/** Classe permettant de representer une lettre de remerciement */
public class LettreDeRemerciement extends Courrier<Texte> {
	
	/**
	 * constructeur pour les instances de la classe LettreDeRemerciement
	 * @param expediteur l'expediteur de la lettre
	 * @param destinataire le destinataire de la lettre
	 */
	public LettreDeRemerciement(Habitant expediteur, Habitant destinataire) {
		super(expediteur,destinataire,new Texte("Merci beaucoup pour l'argent !"));
	}
	
	/**
	 * renvoie le cout de l'envoi de la lettre
	 * @return le cout de l'envoi de la lettre
	 */
	@Override
	public float cout() {
		return 0.5f;
	}
	
	/**
	 * effectue une action a la reception du courrier par le destinataire
	 */
	@Override
	public void action() {
		if (this.getExpediteur().getSoldeCompte() < this.cout()) {
			System.out.println("LETTRE DE REMERCIEMENT {NON ENVOYÉE} : "+this.expediteur+" -> "+this.destinataire+" [Motif : solde de l'expéditeur insuffisant]\n");
		}
		else {
			this.expediteur.debiter(this.cout());
			System.out.println("LETTRE DE REMERCIEMENT {ENVOYÉE} : "+this.expediteur+" -> "+this.destinataire+" [Coût d'envoi de la lettre = "+this.cout()+" €]");
			System.out.println(this.getExpediteur()+" : Solde avant envoi = "+(this.getExpediteur().getSoldeCompte() + this.cout())+" € | Solde après envoi = "+this.getExpediteur().getSoldeCompte()+" €\n");
		}	
	}

}
