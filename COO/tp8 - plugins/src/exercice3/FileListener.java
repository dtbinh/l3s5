package exercice3;

import java.util.EventListener;

/** Interface representant un listener */
public interface FileListener extends EventListener {
	
	/**
	 * affiche un message quand un nouveau fichier a été ajouté
	 * @param fe l'événement correspondant à l'ajout du fichier
	 */
	public void fileAdded(FileEvent fe);
	
	/**
	 * affiche un message quand un fichier a été supprimé
	 * @param fe l'événement correspondant à l'ajout du fichier
	 */
	public void fileRemoved(FileEvent fe);

}
