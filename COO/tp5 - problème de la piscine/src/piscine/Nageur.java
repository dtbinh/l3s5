package piscine;

import piscine.ressource.Cabine;
import piscine.ressource.GestionnaireRessources;
import piscine.ressource.LibererRessource;
import piscine.ressource.Panier;
import piscine.ressource.PrendreRessource;
import piscine.ressource.RessourceUser;
import piscine.util.ActionTermineeException;
import piscine.util.AttenteCabine;
import piscine.util.DemandePanier;
import piscine.util.DureeBaignade;
import piscine.util.Scenario;
import piscine.util.TempsDeshabillement;
import piscine.util.TempsRhabillement;

/** Classe représentant un nageur */
public class Nageur extends Scenario {
	
	// l'utilisateur nageur
	private RessourceUser<Panier> utilisateurPanier;
	
	//
	private RessourceUser<Cabine> utilisateurCabine;
	
	// le gestionnaire de paniers à qui s'adresser
	@SuppressWarnings("unused")
	private GestionnaireRessources<Panier> gestionnairePaniers;
	
	// le gestionnaire de cabines à qui s'adresser
	@SuppressWarnings("unused")
	private GestionnaireRessources<Cabine> gestionnaireCabines;
	
	// le temps qui lui est nécessaire pour se déshabiller
	private TempsDeshabillement tempsDeshabillement;
	
	// la durée pendant laquelle il va se baigner
	private DureeBaignade dureeBaignade;
	
	// le temps qui lui est nécessaire pour se rhabiller
	private TempsRhabillement tempsRhabillement;
	
	/**
	 * constructeur pour les objets de la classe Nageur
	 * @param s son nom
	 * @param gp le gestionnaire de paniers à qui s'adresser
	 * @param gc le gestionnaire de cabines à qui s'adresser
	 * @param td le temps qui lui est nécessaire pour se déshabiller
	 * @param db la durée pendant laquelle il va se baigner
	 * @param tr le temps qui lui est nécessaire pour se rhabiller
	 */
	public Nageur(String s, GestionnaireRessources<Panier> gp, GestionnaireRessources<Cabine> gc, int td, int db, int tr) {
		this.utilisateurPanier = new RessourceUser<Panier>();
		this.utilisateurPanier.setNom(s);
		this.utilisateurCabine = new RessourceUser<Cabine>();
		this.utilisateurCabine.setNom(s);
		this.tempsDeshabillement = new TempsDeshabillement(td,this.utilisateurCabine);
		this.dureeBaignade = new DureeBaignade(db,this.utilisateurPanier);
		this.tempsRhabillement = new TempsRhabillement(tr,this.utilisateurCabine);
		// demande un panier
		this.ajouteAction(new DemandePanier(1,this.utilisateurPanier));
		// prend un panier
		this.ajouteAction(new PrendreRessource<Panier>(gp,this.utilisateurPanier));
		// attend une cabine
		this.ajouteAction(new AttenteCabine(1,this.utilisateurCabine));
		// prend une cabine
		this.ajouteAction(new PrendreRessource<Cabine>(gc,this.utilisateurCabine));
		// se déshabille
		this.ajouteAction(this.tempsDeshabillement);
		// sort de sa cabine
		this.ajouteAction(new LibererRessource<Cabine>(gc,this.utilisateurCabine));
		// se baigne
		this.ajouteAction(this.dureeBaignade);
		// attend une cabine
		this.ajouteAction(new AttenteCabine(1,this.utilisateurCabine));
		// prend une cabine
		this.ajouteAction(new PrendreRessource<Cabine>(gc,this.utilisateurCabine));
		// se rhabille
		this.ajouteAction(this.tempsRhabillement);
		// sort de sa cabine
		this.ajouteAction(new LibererRessource<Cabine>(gc,this.utilisateurCabine));
		// donne son panier
		this.ajouteAction(new LibererRessource<Panier>(gp,this.utilisateurPanier));
	}
	
	/**
	 * le nageur demande un panier
	 * @throws ActionTermineeException 
	 */
	public void faire() throws ActionTermineeException {
		super.faire();
	}

}
