package exercice4;

import java.io.File;
import java.io.FilenameFilter;

/** Classe représentant un filtre qui n'accepte que les classes qui sont des plugins */
public class PluginFilter implements FilenameFilter {

	/**
	 * @param f le répertoire
	 * @param name le nom du fichier testé
	 * @return <code> true </code> si <code> name </code> est un plugin, <code> false </code> sinon
	 */
	@Override
	public boolean accept(File f, String name) {
		try {
			if (!name.endsWith(".class")) {
				return false;
			}
			Class<?> c = Class.forName(name.substring(0,name.length() - 6));
			if (c.isInterface()) {
				return false;
			}
			if (!extensions.Extension.class.isAssignableFrom(c)) {
				return false;
			}
			if (c.getPackage() != null) {
				return false;
			}
			c.getConstructor();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		catch (ClassFormatError e) {
			return false;
		}
	}

}
