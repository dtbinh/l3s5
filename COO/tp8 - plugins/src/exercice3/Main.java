package exercice3;

import java.io.FilenameFilter;

import exercice2.ClassExtensionFilter;

/** Classe de test du FileChecker simple */
public class Main {

	/**
	 * méthode main de la classe
	 * @param args le tableau d'arguments
	 */
	public static void main(String[] args) {
		try {
			FilenameFilter fnf = new ClassExtensionFilter();
			FileChecker fc = new FileChecker(fnf,args[0]);
			FileListener fl = new DirectoryUpdateListener();
			fc.addFileListener(fl);
			fc.startChecking();
			while (true);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Usage : java -jar exercice3.jar dirName");
			System.out.println("        - dirName = le nom du répertoire de test");
		}
		catch (NullPointerException e) {
			System.out.println("Le répertoire spécifié n'existe pas");
		}
	}

}
