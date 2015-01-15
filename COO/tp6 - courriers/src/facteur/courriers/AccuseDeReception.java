package facteur.courriers;

import facteur.contenus.Texte;
import facteur.contenus.Contenu;
import facteur.util.Habitant;

/** Classe permettant de representer un accuse de reception */
public class AccuseDeReception extends Courrier<Texte> {
	
	/**
	 * constructeur pour les instances de la classe AccuseDeReception
	 * @param expediteur l'expediteur de la lettre
	 * @param destinataire le destinataire de la lettre
	 */
	public AccuseDeReception(Habitant expediteur, Habitant destinataire) {
		super(expediteur,destinataire,new Texte("J'ai bien recu votre lettre"));
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
		this.expediteur.debiter(this.cout());
		System.out.println("ACCUSÉ DE RECEPTION {SIGNÉ ET ENVOYÉ} : "+this.expediteur+" -> "+this.destinataire+" [Coût d'envoi de l'accusé = "+this.cout()+" €]");
		System.out.println(this.expediteur+" : Solde avant envoi = "+(this.getExpediteur().getSoldeCompte() + this.cout())+" € | Solde après envoi = "+this.getExpediteur().getSoldeCompte()+" €\n");
	}

}
