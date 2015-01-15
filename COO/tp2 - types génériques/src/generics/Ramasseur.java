package generics;
import java.util.*;

/** definit des objets specialises dans le ramassage d'objets d'un type T donne
 * un objet T "tenu" a la fois
 */
public class Ramasseur<T> {

	// le nom du ramasseur
	private String name;

	// l'objet porte par le ramasseur
	private T monObjet;

	/**
	 * constructeur pour les instances de la classe Ramasseur
	 * @param name le nom du ramasseur
	 */
    public Ramasseur(String name) {
		this.name = name;
		this.monObjet = null;
    }

    // ATTRIBUTS a DEFINIR

    // METHODES a DEFINIR
    // toString 
    // prend : pour prendre un objet de type T (si aucun de "tenu")
    // getMonObjet : pour connaitre l'objet "porte" (null si saucun)
    // donneA : donne l'objet porte a un autre ramasseur compatible 
    // depose: depose l'objet "tenu"

    /**
     * renvoie l'objet porte par le ramasseur
     * @return l'objet porte par le ramasseur
     */
    public T getMonObjet() {
    	return this.monObjet;
    }


    /**
     * prend l'objet en parametre
     * @param o l'objet a prendre (de type T)
     * @exception IllegalStateException si le ramasseur porte deja un objet
     */
    public void prend(T o) throws IllegalStateException {
    	if (this.monObjet == null)
    		this.monObjet = o;
    	else
    		throw new IllegalStateException();
	}

	/**
	 * depose l'objet porte (ne se passe rien si le ramasseur ne porte pas d'objet car l'objet porte est deja egal a null)
	 */
	public void depose() {
		this.monObjet = null;
	}

	/**
	 * le ramasseur courant donne l'objet qu'il porte a un autre ramasseur compatible
	 * @param r le ramasseur qui doit recevoir l'objet
	 * @exception IllegalStateException si le ramasseur <code> r </code> porte deja un objet
	 */
	public void donneA(Ramasseur<? super T> r) {
		if (r.getMonObjet() == null) {
			r.prend(this.monObjet);
			this.monObjet = null;
		}
		else {
			throw new IllegalStateException();
		}
	}

	/**
	 * renvoie le nom du ramasseur
	 * @return le nom du ramasseur
	 * @see java.lang.Object#toString(java.lang.String)
	 */
	public String toString() {
		return this.name;
	}

    public static void main(String[] args) {
	
	Carotte c1 = new Carotte(1);
	Carotte c2 = new Carotte(2);
	Carotte c3 = new Carotte(3);
	Pomme p1 = new Pomme(1);
	Pomme p2 = new Pomme(2);
	
 	Ramasseur<Carotte> ramasseCarotte1 = new Ramasseur<Carotte>("ramasse-carotte-1");
 	Ramasseur<Carotte> ramasseCarotte2 = new Ramasseur<Carotte>("ramasse-carotte-2");
 	Ramasseur<Pomme> ramassePomme1 = new Ramasseur<Pomme>("ramasse-pomme-1");
	// attention ici le type d'objets ramasses est Legume :
	Ramasseur<Legume> ramasseLegume = new Ramasseur<Legume>("ramasse-legume");


 	ramasseCarotte1.prend(c3);
	System.out.println(ramasseCarotte1);
	// NE COMPILE PAS
	// ramasseCarotte2.prend(p1);
	
	// NE COMPILE PAS
	// ramasseCarotte1.donneA(ramassePomme1);

	ramasseCarotte1.donneA(ramasseLegume);
	// NE COMPILE PAS
	// ramassePomme1.donneA(ramasseLegume);

 	ramasseCarotte1.prend(c1);
	ramasseCarotte1.donneA(ramasseCarotte2);
	System.out.println(ramasseCarotte1);
	System.out.println(ramasseCarotte2);
	ramasseCarotte1.prend(c2);
	try {
	    ramasseCarotte1.donneA(ramasseCarotte2);
	} catch(IllegalStateException e) {
	    System.out.println("*** exception : "+ramasseCarotte2+" porte deja qque chose");
	    System.out.println(" * "+ e.getMessage());
	}


	ramassePomme1.prend(p2);
	System.out.println(ramassePomme1);
	try {
	    ramassePomme1.prend(p1);
	} catch(IllegalStateException e) {
	    System.out.println("*** exception : "+ramassePomme1+" porte deja qque chose");
	    System.out.println(" * "+e.getMessage());
	}
	ramassePomme1.depose();
	System.out.println(ramassePomme1);
	ramassePomme1.prend(p1);
	
     }
}
