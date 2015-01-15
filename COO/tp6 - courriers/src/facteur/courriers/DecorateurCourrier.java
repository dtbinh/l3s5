package facteur.courriers;

/** Classe permettant de decorer un courrier */
public abstract class DecorateurCourrier extends Courrier<Courrier<?>> {

	/**
	 * constructeur pour les instances de la classe DecorateurCourrier
	 * @param c le courrier a decorer
	 */
	public DecorateurCourrier(Courrier<?> c) {
		super(c.getExpediteur(),c.getDestinataire(),c);
	}
	
	/**
	 * renvoie le cout de l'envoi du courrier
	 * @return le cot de l'envoi du courrier
	 */
	public abstract float cout();
	
	/**
	 * effectue une action a la reception du courrier par le destinataire
	 */
	public abstract void action();

}
