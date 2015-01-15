package io;
import java.io.*;

/**
 * Classe pour illustrer le code pour la lecture dans et l'écriture depuis un fichier texte. 
 *
 */
public class IllustrationIO {

	/** Recopie le contenu du fichier texte source dans le fichier cible
	 * @param nomFichierSource fichier texte source
	 * @param nomFichierCible fichier texte destination (cible)
	 */
	public void copieFichierTexte(String nomFichierSource, String nomFichierCible) {
		// définition  des objets File associés au fichier
		File source = new File(nomFichierSource);
		File cible = new File(nomFichierCible);
		try {
			// définition de l'objet lecteur 
			BufferedReader in = new BufferedReader(new FileReader(source));
			// définition de l'objet écrivain
			PrintWriter out = new PrintWriter(new FileWriter(cible));
			String ligne = "";
			// tant qu'il y a des lignes
			while (ligne != null) {
				// le lecteur lit la ligne suivante dans le fichier source 
				ligne = in.readLine();
				// l'écrivain crit la ligne lue (non null) dans le fichier cible
				if (ligne != null) {
					out.println(ligne);
				}
			}
			// fermeture les fichiers
			out.close();
			in.close();			
		}
		catch (FileNotFoundException e) {
			System.out.println("fichier "+nomFichierSource+" introuvable");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** Lit le contenu ddu fichier texte et l'affiche sur la soortie standard
	 * @param nomFichierSource le fichier texte source
	 */ 
	public void afficheFichierTexte(String nomFichierSource) {
		File source = new File(nomFichierSource);
		try {
			BufferedReader in = new BufferedReader(new FileReader(source));
			String ligne = "";
			while (ligne != null) {
				ligne = in.readLine();
				if (ligne != null) {
					System.out.println(ligne);
				}
			}
			in.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("fichier "+nomFichierSource+" introuvable");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws IOException {
		
		new IllustrationIO().copieFichierTexte("question_tolkien.txt","output.txt");
		System.out.println("fichier 'output.txt' créé à partir de 'question_tolkien.txt'");
		System.out.println("En voici le contenu : ");
		System.out.println(" -DEBUT-------------------------------------- ");
		new IllustrationIO().afficheFichierTexte("output.txt");
		System.out.println(" -FIN---------------------------------------- ");
	}
}
