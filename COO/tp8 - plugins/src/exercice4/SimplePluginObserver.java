package exercice4;

import exercice3.FileEvent;
import exercice3.FileListener;

/** Listener qui affiche un message lors de l'ajout ou de la suppression d'un plugin dans le répertoire */
public class SimplePluginObserver implements FileListener {

	/**
	 * affiche un message quand un nouveau fichier a été ajouté
	 * @param fe l'événement correspondant à l'ajout du fichier
	 */
	@Override
	public void fileAdded(FileEvent fe) {
		String nomPlugin = fe.getFileName();
		System.out.println("nouveau plugin : "+nomPlugin.substring(0,nomPlugin.length() - 6)+" détecté");
	}
	
	/**
	 * affiche un message quand un fichier a été supprimé
	 * @param fe l'événement correspondant à l'ajout du fichier
	 */
	@Override
	public void fileRemoved(FileEvent fe) {
		String nomPlugin = fe.getFileName();
		System.out.println("plugin : "+nomPlugin.substring(0,nomPlugin.length() - 6)+" supprimé détecté");
	}

}
