package facteur.courriers;

import facteur.contenus.Texte;

/**
 * Classe permettant de representer un courrier recommande
 */
public class CourrierRecommande extends DecorateurCourrier {

	// le courrier decore
	/**
	 * @uml.property  name="courrierDecore"
	 * @uml.associationEnd  
	 */
	Courrier<?> courrierDecore;

	/**
	 * constructeur pour les instances de la classe CourrierRecommande
	 * @param c le courrier a envoyer en recommande
	 */
	public CourrierRecommande(Courrier<?> c) {
		super(c);
		this.courrierDecore = c;
	}

	/**
	 * renvoie le courrier decore
	 * @return  le courrier decore
	 * @uml.property  name="courrierDecore"
	 */
	public Courrier<?> getCourrierDecore() {
		return this.courrierDecore;
	}
	
	/**
	 * renvoie le cout de l'envoi du courrier
	 * @return le cout de l'envoi du courrier
	 */
	@Override
	public float cout() {
		return (this.courrierDecore.cout() + (0.15f * this.courrierDecore.cout())); 
	}
	
	/**
	 * effectue une action a la reception du courrier par le destinataire
	 */
	@Override
	public void action() {
		if (this.courrierDecore instanceof LettreSimple) {
			if (this.expediteur.getSoldeCompte() < this.cout()) {
				System.out.println("LETTRE SIMPLE {NON ENVOYÉE EN RECOMMANDÉ} : "+this.expediteur+" -> "+this.destinataire+" [Motif : solde de l'expéditeur insuffisant]\n");
			}
			else {
				this.expediteur.debiter(this.cout());
				System.out.println("LETTRE SIMPLE {ENVOYÉE EN RECOMMANDÉ} : "+this.expediteur+" -> "+this.destinataire+" [Coût d'envoi de la lettre = "+this.cout()+" €]");
				System.out.println(this.getExpediteur()+" : Solde avant envoi = "+(this.getExpediteur().getSoldeCompte() + this.cout())+" € | Solde après envoi = "+this.getExpediteur().getSoldeCompte()+" €\n");
				Courrier<Texte> a = new AccuseDeReception(this.destinataire,this.expediteur);
				this.destinataire.envoieCourrier(a);
			}
		}
		else {
			LettreDeChange ldc = (LettreDeChange) this.courrierDecore;
			if (this.expediteur.getSoldeCompte() < (ldc.getContenu().getMontant() + this.cout())) {
				System.out.println("LETTRE DE CHANGE {NON ENVOYÉE EN RECOMMANDÉ} : "+this.expediteur+" -> "+this.destinataire+" [Montant = "+ldc.getContenu().getMontant()+" €] [Motif : solde de l'expéditeur insuffisant]\n");
			}
			else {
				this.expediteur.debiter(ldc.getContenu().getMontant() + this.cout());
				this.destinataire.crediter(ldc.getContenu().getMontant());
				System.out.println("LETTRE DE CHANGE {ENVOYÉE EN RECOMMANDÉ} : "+this.expediteur+" -> "+this.destinataire+" [Montant = "+ldc.getContenu().getMontant()+" €] [Coût d'envoi de la lettre = "+this.cout()+" €]");
				System.out.println(this.expediteur+" : Solde avant envoi = "+(this.expediteur.getSoldeCompte() + this.cout() + ldc.getContenu().getMontant())+" € | Solde après envoi (coût d'envoi de la lettre inclus) = "+this.expediteur.getSoldeCompte()+" €");
				System.out.println(this.destinataire+" : Solde avant réception = "+(this.destinataire.getSoldeCompte() - ldc.getContenu().getMontant())+" € | Solde après réception = "+this.destinataire.getSoldeCompte()+" €\n");
				Courrier<Texte> a = new AccuseDeReception(this.destinataire,this.expediteur);
				this.destinataire.envoieCourrier(a);
				Courrier<Texte> l = new LettreDeRemerciement(this.destinataire,this.expediteur);
				this.destinataire.envoieCourrier(l);
			}
		}
	} 

}
