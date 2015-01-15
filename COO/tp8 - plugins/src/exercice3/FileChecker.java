package exercice3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Timer;

/** Classe représentant un fileChecker ("surveillant de répertoire") */
public class FileChecker {
	
	// le timer
	private Timer timer;
	
	// le répertoire
	public static File directory;
	
	// le filtre
	private FilenameFilter filter;
	
	// les fichiers connus du répertoire
	private List<String> knownFileNames;
	
	// les listeners
	private List<FileListener> fileListeners;
	
	/**
	 * construit un file checker
	 * @param fnf le filtre
	 * @param directoryName le nom du répertoire
	 */
	public FileChecker(FilenameFilter fnf, String directoryName) {
		this.timer = new Timer(500,this.new DirectoryCheckListener());
		FileChecker.directory = new File(directoryName);
		this.filter = fnf;
		this.knownFileNames = new LinkedList<String>();
		this.fileListeners = new LinkedList<FileListener>();
	}
	
	/**
	 * ajoute un listener
	 * @param fl le listener à ajouter
	 */
	public void addFileListener(FileListener fl) {
		this.fileListeners.add(fl);
	}
	
	/**
	 * retire un listener
	 * @param fl le listener à retirer
	 */
	public void removeFileListener(FileListener fl) {
		this.fileListeners.remove(fl);
	}
	
	/**
	 * imforme tous les listener qu'un fichier a été ajouté
	 * @param fileName le nom du fichier ajouté
	 */
	public void fireFileAdded(String fileName) {
		FileEvent fe = new FileEvent(this,fileName);
		Iterator<FileListener> it = this.fileListeners.iterator();
		while(it.hasNext()) {
			it.next().fileAdded(fe);
		}
	}
	
	/**
	 * imforme tous les listener qu'un fichier a été supprimé
	 * @param fileName le nom du fichier supprimé
	 */
	public void fireFileRemoved(String fileName) {
		FileEvent fe = new FileEvent(this,fileName);
		Iterator<FileListener> it = this.fileListeners.iterator();
		while(it.hasNext()) {
			it.next().fileRemoved(fe);
		}
	}
	
	/**
	 * débute la surveillance du répertoire en démarrant le timer
	 */
	public void startChecking() {
		this.timer.start();
	}
	
	/** Classe interne chargée de regarder le contenu du répertoire à chaque délai du timer, et avertit les listeners si ce contenu a changé */
	private class DirectoryCheckListener implements ActionListener {
		
		/**
		 * regarder le contenu du répertoire à chaque délai du timer et avertit les listeners si le contenu a changé
		 */
		public void actionPerformed(ActionEvent e) {
			String[] files = FileChecker.directory.list(FileChecker.this.filter);
			List<String> removedFiles = new LinkedList<String>(FileChecker.this.knownFileNames);
			for (int i = 0 ; i < files.length ; i++) {
				String fileName = files[i];
				if (FileChecker.this.knownFileNames.contains(fileName)) {
					removedFiles.remove(fileName);
				}
				else {
					FileChecker.this.fireFileAdded(fileName);
					FileChecker.this.knownFileNames.add(fileName);
				}
			}
			Iterator<String> it = removedFiles.iterator();
			while (it.hasNext()) {
				String fileName = it.next();
				FileChecker.this.knownFileNames.remove(fileName);
				FileChecker.this.fireFileRemoved(fileName);
			}
		}

	}

}
