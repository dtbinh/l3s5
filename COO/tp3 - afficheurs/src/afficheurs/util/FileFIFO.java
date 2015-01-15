package afficheurs.util;

import java.util.List;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** Classe permettant de representer une file */
public class FileFIFO<Element> implements Iterable<Element> {

	// le contenu de la file
	private List<Element> contenu;

	// la largeur de la file
	private int largeur;

	// l'indice du premier element de la file
	private int tete;

	// le nombre de modifications de la file
	private int nbreModifs;

	/**
	 * constructeur pour les instances de la classe FileFIFO
	 * @param largeur la largeur de la file
	 * @param la valeur par defaut des elements de la file
	 */
	public FileFIFO(int largeur, Element valeur) {
		this.largeur = largeur;
		this.contenu = new ArrayList<Element>();
		for (int i = 0 ; i < largeur ; i++) {
			this.contenu.add(valeur);
		}
		this.nbreModifs = 0;
		this.tete = 0;
	}

	/**
	 * renvoie la largeur de la file
	 * @return la largeur de la file
	 */
	public int getLargeur() {
		return this.largeur;
	}

	/**
	 * force tous les elements a la valeur <code> v </code> passee en parametre
	 * @param v la valeur qui doit etre forcee aux elements
	 */
	public void raz(Element v) {
		for (int i = 0 ; i < this.contenu.size() ; i++) {
			this.contenu.set(i,v);
		}
		this.nbreModifs = 0;
	}

	/**
	 * ajoute un element a la file
	 * @param e l'element qu'on ajoute a la file
	 * @return l'element sorti de la file
	 */
	public Element ajoute(Element e) {
		Element ancienneTete = this.contenu.get(0);
		this.contenu.add(e);
		this.contenu.remove(0);
		this.nbreModifs++;
		return ancienneTete;
	}

	/**
	 * renvoie une chaine representant le contenu de la file
	 * @return une chaine representant le contenu de la file
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String s = "";
		for (Element e : this.contenu) {
			s += e.toString();
		}
		return s;
	}

	/**
	 * renvoie un iterateur sur la file
	 * @return un iterateur sur la file
	 */
	public Iterateur iterator() {
		return new Iterateur();
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/** Classe interne permettant de representer un iterateur sur la file */
	public class Iterateur implements Iterator<Element> {

		// la position courante de l'iterateur
		private int position;

		// le nombre de modifications de la file
		private int nbreModifs;

		/**
		 * constructeur pour les instances de la classe Iterateur
		 */
		public Iterateur() {
			this.position = FileFIFO.this.tete;
			this.nbreModifs = FileFIFO.this.nbreModifs;
		}

		/**
		 * verifie que la file n'a pas change depuis la creation de l'iterateur
		 * @exception ConcurrentModificationException si la file a ete modifiee avant la creation de l'iterateur
		 * @see java.util.ConcurrentModificationException()
		 */
		public void verifieModifs() throws ConcurrentModificationException {
			if (this.nbreModifs != FileFIFO.this.nbreModifs)
				throw new ConcurrentModificationException();
		}

		/**
		 * etudie si la fin de file est atteinte
		 * @return <code> false </code> si la fin de file est atteinte, <code> true </code> sinon
		 */
		public boolean hasNext() {
			this.verifieModifs();
			return (this.position < (FileFIFO.this.largeur - 1));
		}

		/**
		 * renvoie l'element suivant la position courante de l'iterateur
		 * @return l'element suivant la position courante de l'iterateur
		 * @exception NoSuchElementException si l'element pointe par l'iterateur n'a pas de suivant
		 * @see java.util.NoSuchElementException
		 */
		public Element next() throws NoSuchElementException {
			this.verifieModifs();
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			}
			else {
				return FileFIFO.this.contenu.get((this.position + 1) % FileFIFO.this.largeur);
			}
		}

		/**
		 * retire l'element pointe par l'iterateur
		 * @exception UnsupportedOperationException si l'iterateur ne supporte pas l'operation de suppression
		 * @see java.util.UnsupportedOperationException
		 */
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

	}

}
