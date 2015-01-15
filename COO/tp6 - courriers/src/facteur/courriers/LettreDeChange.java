package facteur.courriers;

import facteur.contenus.Argent;
import facteur.util.Habitant;
import facteur.contenus.Texte;

/** Classe permettant de representer une lettre de change */
public class LettreDeChange extends Courrier<Argent> {

	/**
	 * constructeur pour les instances de la classe LettreDeChange
	 * @param expediteur l'expediteur de la lettre
	 * @param destinataire le destinataire de la lettre
	 * @param montant le contenu de la lettre
	 */
	public LettreDeChange(Habitant expediteur, Habitant destinataire, float montant) {
		super(expediteur,destinataire,new Argent(montant));
	}

	/**
	 * renvoie le cout de l'envoi de la lettre
	 * @return le cout de l'envoi de la lettre
	 */
	@Override
	public float cout() {
		return (1 + (0.01f * this.getContenu().getMontant()));
	}

	/**
	 * effectue une action a la reception du courrier par le destinataire
	 */
	@Override
	public void action() {
		if (this.expediteur.getSoldeCompte() < this.getContenu().getMontant() + this.cout()) {
			System.out.println("LETTRE DE CHANGE {NON ENVOYÉE} : "+this.expediteur+" -> "+this.destinataire+" [Montant = "+this.getContenu().getMontant()+" €] [Motif : solde de l'expéditeur insuffisant]\n");
		}
		else {
			this.getExpediteur().debiter(this.getContenu().getMontant() + this.cout());
			this.getDestinataire().crediter(this.getContenu().getMontant());
			System.out.println("LETTRE DE CHANGE {ENVOYÉE} : "+this.expediteur+" -> "+this.destinataire+" [Montant = "+this.getContenu().getMontant()+" €] [Coût d'envoi de la lettre = "+this.cout()+" €]");
			System.out.println(this.getExpediteur()+" : Solde avant envoi = "+(this.getExpediteur().getSoldeCompte() + this.cout() + this.getContenu().getMontant())+" € | Solde après envoi (coût d'envoi de la lettre inclus) = "+this.getExpediteur().getSoldeCompte()+" €");
			System.out.println(this.getDestinataire()+" : Solde avant réception = "+(this.getDestinataire().getSoldeCompte() - this.getContenu().getMontant())+" € | Solde après réception = "+this.getDestinataire().getSoldeCompte()+" €\n");
			Courrier<Texte> l = new LettreDeRemerciement(this.destinataire,this.expediteur);
			this.destinataire.envoieCourrier(l);
		}
	}

}
