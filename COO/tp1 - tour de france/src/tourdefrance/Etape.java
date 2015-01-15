package tourdefrance;

import java.util.*;

import tourdefrance.util.InitListeCoureurs;

/**
 */
public class Etape {

	private String path = "/data/tdf2013/";
	private static final String RACINE = "etape";
	private static final String EXTENSION = ".txt";


	/** Crée l'étape du numéro indiqué 
	 * @param numero le numéro d'ordre de l'étape dans la course
	 * @param path la racine du chemin de fichier de données
	 */
	public Etape(int numero, String path) {
		this.courue = false;
		this.numero = numero;
		this.path= path;
	}
	
	/**
	 * Pourrait aussi être gérée via les attributs resultats et abandons (à null)
	 */
	private boolean courue;
	private int numero;
	/** le numero de cet étape
	 * @return numero de cet étape
	 */
	public int getNumero() {
		return numero;
	}

	private Map<Coureur, Resultat> resultats;
	private List<Coureur> abandons;


	/** Fait disputer l'étape aux coureurs fournis.
	 * La table des résultats et les abandons sont mis à jour.
	 * Les résultats de l'étape sont lus dans le fichier PATH/RACINEnumero.EXTENSION.
	 * Le fichier est structuré ainsi :
	 * <ul><li>une première ligne de commentaires</li>
	 * <li> une ligne par coureur de la forme :  "numero_licence h m s pointsVert pointsMontage" ou "numero_licence" si abandon du coureur
	 * </li></ul> 
	 * Une ligne mal formée est ignorée.
	 * @param lc la liste des coureurs participants à l'étape
	 */
	public void disputer(List<Coureur> lc) {
		System.out.println("***************************************************");
		System.out.println("les coureurs disputent l'étape " + this.numero+ " ...");
		this.courue = true;
		this.resultats = new HashMap<Coureur, Resultat>();
		this.abandons = new ArrayList<Coureur>();

		Scanner scannerFichier = new Scanner(
				InitListeCoureurs.class.getResourceAsStream(this.path + RACINE
						+ this.numero + EXTENSION));
		// pour lire la première ligne de commentaire
		scannerFichier.nextLine();
		// lecture des lignes contenant les coureurs
		while (scannerFichier.hasNextLine()) {
			try {
				Scanner scannerLigne = new Scanner(scannerFichier.nextLine());
				String licence = scannerLigne.next();
				// recherche du coureur ayant ce numéro de licence
				Iterator<Coureur> it_coureur = lc.iterator();
				Coureur coureur = null;
				while (it_coureur.hasNext() && coureur == null) {
					Coureur c = it_coureur.next();
					if (c.getLicence().equals(licence)) {
						coureur = c;
					}
				}

				if (scannerLigne.hasNextInt()) {
					// le coureur n'a pas abandonné
					int h = scannerLigne.nextInt();
					int m = scannerLigne.nextInt();
					int s = scannerLigne.nextInt();
					int pointsVert = scannerLigne.nextInt();
					int pointsMontagne = scannerLigne.nextInt();
					this.resultats.put(coureur, new Resultat(new Temps(h, m, s), pointsVert, pointsMontagne));
				} else {
					// abandon
					this.abandons.add(coureur);
				}
			} catch (NoSuchElementException e) {
				// ligne mal formatée on l'ignore
			}
		}

	}

	/** Fournit la table associant à chaque coureur finissant l'étape son résultat dans l'étape.
	 * @return  la table associant à chaque coureur finissant l'étape son résultat dans l'étape.
	 * @throws EtapeNonCourueException si l'étape n'a pas encore été courue
	 */
	public Map<Coureur, Resultat> resultats() throws EtapeNonCourueException {
		if (!this.courue) {
			throw new EtapeNonCourueException();
		}
		return this.resultats;
	}

	/**  fournit la liste des coureurs ayant abandonné au cours de l'étape
	 * @return la liste des coureurs ayant abandonné au cours de l'étape
	 * @throws EtapeNonCourueException si l'étape n'a pas encore été courue
	 */
	public List<Coureur> abandons() throws EtapeNonCourueException {
		if (!this.courue) {
			throw new EtapeNonCourueException();
		}
		return this.abandons;
	}

	/** La chaîne "etape"+numero 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "etape "+this.numero;
	}
	
	
	// POUR ESSAYER 
	public static void main(String[] args) throws EtapeNonCourueException {
		List<Coureur> lc = InitListeCoureurs.SINGLETON.init("/data/coureurs.txt");

		Etape etape = new Etape(1,"/data/");
		etape.disputer(lc);

		for (Coureur c : etape.resultats().keySet()) {
			System.out.println(c + " -> " + etape.resultats().get(c));
		}
		for (Coureur c : etape.abandons()) {
			System.out.println(c + " a abandonne");
		}

	}

}
