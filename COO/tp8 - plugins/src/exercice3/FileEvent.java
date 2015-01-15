package exercice3;

import java.util.EventObject;

/** Classe représentant un événement de fichier (encapsulation du nom de fichier) */
public class FileEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;
	
	// le nom du fichier
	private String fileName;
	
	/**
	 * construit un objet FileEvent
	 * @param source l'object sur lequel l'événement a initialement eu lieu
	 * @param fn le nom du fichier
	 */
	public FileEvent(Object source,String fn) {
		super(source);
		this.fileName = fn;
	}
	
	/**
	 * renvoie le nom du fichier
	 * @return le nom du fichier
	 */
	public String getFileName() {
		return this.fileName;
	}

}
