package exercice4;

import java.io.FilenameFilter;

import exercice3.FileChecker;
import exercice3.FileListener;

/** Classe de test du FileChecker de plugins */
public class Main {

	/**
	 * méthode main de la classe
	 * @param args le tableau d'arguments
	 */
	public static void main(String[] args) {
		FilenameFilter fnf = new PluginFilter();
		FileChecker fc = new FileChecker(fnf,"extensions");
		FileListener fl = new SimplePluginObserver();
		fc.addFileListener(fl);
		fc.startChecking();
		while (true);
	}
}
