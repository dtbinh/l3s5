package exercice2;

import java.io.FilenameFilter;
import java.io.File;

/** Classe représentant un filtre qui n'accepte que les fichiers dont le nom commence par C */
public class StartsWithCFilter implements FilenameFilter {

	/**
	 * @param f le répertoire
	 * @param name le nom du fichier testé
	 * @return <code> true </code> si <code> name </code> commence par C, <code> false </code> sinon
	 */
	public boolean accept(File f, String name) {
		return name.startsWith("C");
	}

}
