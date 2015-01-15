package exercice2;

import java.io.File;
import java.io.FilenameFilter;

/** Classe chargée d'afficher les fichiers d'un, afficher ses fichiers dont le nom commence par C et ceux qui sont d'extension .class */
public class PrintFiles {

	// le nom du répertoire
	private File directory;

	/**
	 * construit l'afficheur de fichiers
	 * @param cheminRep le chemin du répertoire
	 */
	public PrintFiles(String cheminRep) {
		this.directory = new File(cheminRep);
	}

	/**
	 * affiche tous les fichiers du répertoire
	 */
	public void listeFichiersRepertoire() {
		System.out.println("Fichiers du répertoire :");
		String[] files = this.directory.list();
		for (int i = 0 ; i < files.length ; i++) {
			System.out.println(files[i]);
		}
		System.out.println();
	}

	/**
	 * affiche les fichiers du répertoire dont le nom commence par C
	 */
	public void afficheFichiersCommencantParC() {
		System.out.println("Fichiers dont le nom commence par C :");
		FilenameFilter fnf = new StartsWithCFilter();
		String[] files = this.directory.list();
		for (int i = 0 ; i < files.length ; i++) {
			if (fnf.accept(this.directory,files[i]))
				System.out.println(files[i]);
		}
		System.out.println();
	}

	/**
	 * affiche les fichiers du répertoire qui sont d'extension .class
	 */
	public void afficheFichiersAvecExtensionClass() {
		System.out.println("Fichiers avec l'extension .class :");
		FilenameFilter fnf = new ClassExtensionFilter();
		String[] files = this.directory.list();
		for (int i = 0 ; i < files.length ; i++) {
			if (fnf.accept(this.directory,files[i]))
				System.out.println(files[i]);
		}
		System.out.println();
	}

	/**
	 * méthode main de la classe
	 * @param args le tableau des arguments
	 */
	public static void main(String[] args) {
		try {
			PrintFiles af = new PrintFiles(args[0]);
			af.listeFichiersRepertoire();
			af.afficheFichiersCommencantParC();
			af.afficheFichiersAvecExtensionClass();
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Usage : java -jar exercice2.jar dirName");
			System.out.println("        - dirName = le nom du répertoire de test");
		}
		catch (NullPointerException e) {
			System.out.println("Le répertoire spécifié n'existe pas");
		}
	}

}
