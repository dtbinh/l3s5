package tourdefrance.util;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import tourdefrance.Coureur;

public class InitListeCoureurs {

	/**
	 * Seule instance pour la classe InitListeCoureurs
	 */
	public static final InitListeCoureurs SINGLETON = new InitListeCoureurs();
	
	private InitListeCoureurs() {}
	
	/** lecture d'un fichier texte contenant les données de création des coureurs.
	 * On suppose le fichier formaté ainsi :
	 *  <ul><li>une première ligne de commentaire</li>
	 *  <li>les autres lignes contiennent chacune les données pour un coureur sous la forme nom:String prenom:String numeroLicence:String age:int
	 * @param filename
	 * @return
	 */
	public List<Coureur> init(String filename) {
		Scanner scannerFichier = new Scanner(InitListeCoureurs.class.getResourceAsStream(filename));
		List<Coureur> resultat = new ArrayList<Coureur>();
		
		// pour lire la première ligne de commentaire
		scannerFichier.nextLine();
		// lecture des lignes contenant les coureurs
		while (scannerFichier.hasNextLine()) {
			try {
				Scanner scannerLigne = new Scanner(scannerFichier.nextLine());
				String nom = scannerLigne.next();
				String prenom = scannerLigne.next();
				String licence = scannerLigne.next();
				int age = scannerLigne.nextInt();
				resultat.add(new Coureur(nom, prenom, licence,age));
			}
			catch (NoSuchElementException e) {
				// ligne mal formatée ne pas créer de coureur;
			}
		}
		
		return resultat;		 
	}
	
	// POUR ESSAYER - fait office de méthode de test
	public static void main(String[] args) {
		List<Coureur> lc = InitListeCoureurs.SINGLETON.init("/data/coureurs.txt");
		for(Coureur c : lc) {
			System.out.println(c);
		}
	}
}
