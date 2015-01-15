package exercice3;

/** Listener chargé d'afficher un message quand un fichier est ajouté ou retiré du répertoire d'exploitation */
public class DirectoryUpdateListener implements FileListener {

	/**
	 * affiche un message quand un nouveau fichier a été ajouté
	 * @param fe l'événement correspondant à l'ajout du fichier
	 */
	@Override
	public void fileAdded(FileEvent fe) {
		System.out.println("nouveau .class : "+fe.getFileName()+" détecté");
	}
	
	/**
	 * affiche un message quand un fichier a été supprimé
	 * @param fe l'événement correspondant à l'ajout du fichier
	 */
	@Override
	public void fileRemoved(FileEvent fe) {
		System.out.println(".class : "+fe.getFileName()+" supprimé détecté");
	}

}
