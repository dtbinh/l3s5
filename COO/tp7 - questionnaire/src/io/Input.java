
package io;

import java.util.Scanner;

/**
 * Classe utilitaire pour lire sur l'entrée standard
 *
 */
public class Input {

	/**
	 * un scanner qui lit l'entrée standard
	 */
	private static final Scanner scanner = new Scanner(System.in);
	
	/**
	 * @return la prochaine chaîne de caractères lues sur l'entrée standard (NB : l'espace est un séparateur de chaîne)
	 * @throws java.io.IOException
	 */
	public static String readString() throws java.io.IOException {
		return scanner.next();
	}

	/**
	 * @return La prochaine ligne de texte lue sur la sortie standard
	 * @throws java.io.IOException
	 */
	public static String readLine() throws java.io.IOException {
		return scanner.nextLine();
	}
	/**
	 * @return le prochain entier sur l'entrée standard
	 * @throws java.io.IOException si la chaîne llue n'est pas un entier
	 */
	public static int readInt() throws java.io.IOException {	
		return scanner.nextInt();
	}

	public static void main(String[] args) {
		try {
			System.out.print(" chaine : ? ");
			String chaineLue = Input.readString();
			System.out.println("lue  => " + chaineLue);
			
			System.out.print(" int : ? ");
			int intLu = Input.readInt();
			System.out.println("lue  => " + intLu);
		} catch (java.io.IOException e) {
			throw new RuntimeException(e);
		}
	}
}// Input
