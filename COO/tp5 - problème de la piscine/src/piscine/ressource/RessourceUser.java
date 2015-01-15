package piscine.ressource;

/** Classe représentant un utilisateur de ressource */
public class RessourceUser<R extends Ressource> {
	
	// le nom de l'utilisateur
	private String nom;

	// La ressource utilisée
	protected R ressource;
	
	/**
	 * renvoie la ressource actuellement utilisée
	 * @return la ressource actuellement utilisée, <code> null </code> si aucune
	 */
	public R getRessource() {
		return this.ressource;
	}
	
	/** 
	 * fixe la ressource utilisée
	 * @param ressource la ressource
	 */
	public void setRessource(R ressource) {
		this.ressource = ressource;
	}
	
	/**
	 * réinitialise la ressource utilisée (mise à <code> null </code>)
	 */
	public void resetRessource() {
		this.ressource = null;		
	}
	
	/**
	 * renvoie le nom de l'utilisateur
	 * @return le nom de l'utilisateur
	 */
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * donne un nom à l'utilisateur
	 * @param s le nom donné à l'utilisateur
	 */
	public void setNom(String s) {
		this.nom = s;
	}

}
