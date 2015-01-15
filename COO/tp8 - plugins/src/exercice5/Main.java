package exercice5;

/** Classe de test de l'application dynamique de plugins */
public class Main {

	/**
	 * méthode main de la classe
	 * @param args le tableau d'arguments
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		MyExtendableEditor a = new MyExtendableEditor("extensions");
		a.initApplication();
		a.showApplication();
		a.startApplication();
		while (true);
	}

}
