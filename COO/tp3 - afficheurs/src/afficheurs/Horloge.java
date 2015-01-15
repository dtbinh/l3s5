package afficheurs;

public class Horloge {

	private Afficheur afficheur;

	public Horloge(Afficheur a) {
		this.afficheur = a;
	}

	public void tester(int nbTop) {
		String message = "abcd";
		this.afficheur.setMessage(message);
		for (int i = 0 ; i < nbTop ; i++) {
			this.afficheur.decale();
			System.out.println("<<"+this.afficheur+">>");
		}
	}

	public static void main(String[] args) {
		Horloge h1 = new Horloge(new Afficheur(5));
		h1.tester(8);
		System.out.println("");
		Horloge h2 = new Horloge(new Latence(5,3));
		h2.tester(8);
		System.out.println("");
		Horloge h3 = new Horloge(new Vitesse(5,3,2));
		h3.tester(8);
	}

}
