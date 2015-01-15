package exercice2;

import java.io.FilenameFilter;
import java.io.File;

/** Classe représentant un filtre qui n'accepte que les fichiers d'extension .class */
public class ClassExtensionFilter implements FilenameFilter {

	/**
	 * @param f le répertoire
	 * @param name le nom du fichier testé
	 * @return <code> true </code> si <code> name </code> est d'extension .class, <code> false </code> sinon
	 */
	public boolean accept(File f, String name) {
		return name.endsWith(".class");
	}

}
