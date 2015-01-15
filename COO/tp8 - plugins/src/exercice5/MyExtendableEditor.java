package exercice5;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import exercice3.FileChecker;
import exercice3.FileEvent;
import exercice3.FileListener;
import exercice4.PluginFilter;
import extensions.Extension;

/** Classe représentant une application dynamique de gestion de plugins */
public class MyExtendableEditor {
	
	// la fenêtre de l'application
	private JFrame frame;
	
	// le champ de texte de l'application
	private JTextArea textArea;
	
	// la barre des menus
	private JMenuBar menuBar;
	
	// les menus File, Tools et Help
	private JMenu fileMenu, toolsMenu, helpMenu;
	
	// le FileChecker
	private FileChecker checker;
	
	// le listener chargé d'ajouter ou de retirer les plugins du menu Tools
	private FileListener listener;

	/**
	 * construit l'application
	 * @param dirName le nom du répertoire
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public MyExtendableEditor(String dirName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		this.frame = new JFrame("My Extendable Editor");
		this.textArea = new JTextArea();
		this.menuBar = new JMenuBar();
		this.fileMenu = new JMenu("File");
		this.toolsMenu = new JMenu("Tools");
		this.helpMenu = new JMenu("Help");
		FilenameFilter fnf = new PluginFilter();
		this.checker = new FileChecker(fnf,dirName);
		this.listener = new ExtensionListener();
		this.checker.addFileListener(listener);
	}
	
	/**
	 * initialise les items du menu File
	 */
	public void initFileMenuItems() {
		JMenuItem clearTextArea = new JMenuItem("New");
		clearTextArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyExtendableEditor.this.textArea.setText("");
			}
		});
		JMenuItem openFile = new JMenuItem("Open");
		openFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser jfc = new JFileChooser(FileChecker.directory);
					if (jfc.showOpenDialog(null)  == JFileChooser.APPROVE_OPTION) {
						File selectedFile = jfc.getSelectedFile();
						FileReader fr = new FileReader(selectedFile);
						char[] fileContent = new char[(int)selectedFile.length()];
						fr.read(fileContent);
						MyExtendableEditor.this.textArea.setText(new String(fileContent));
					}
				}
				catch (Exception ex) {}
			}
		});
		JMenuItem exitApplicationListener = new JMenuItem("Exit");
		exitApplicationListener.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		this.fileMenu.add(clearTextArea);
		this.fileMenu.add(openFile);
		this.fileMenu.add(exitApplicationListener);
	}
	
	/**
	 * initialise l'application (fixe les tailles et les fonctionnalités de ses composantes)
	 */
	public void initApplication() {
		this.frame.setSize(880,430);
		this.frame.setLocationRelativeTo(null);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.textArea.setBorder(BorderFactory.createEtchedBorder());
		this.menuBar.add(this.fileMenu);
		this.menuBar.add(this.toolsMenu);
		this.menuBar.add(this.helpMenu);
		this.frame.add(this.menuBar,BorderLayout.NORTH);
		this.frame.add(this.textArea,BorderLayout.CENTER);
		this.initFileMenuItems();
		this.frame.add(new JScrollPane(this.textArea));
	}
	
	/**
	 * rend visible la fenêtre de l'application
	 */
	public void showApplication() {
		this.frame.setVisible(true);
	}
	
	/**
	 * commence la surveillance du répertoire en démarrant le timer
	 */
	public void startApplication() {
		this.checker.startChecking();
	}
	
	/** Classe interne représentant le listener chargé d'ajouter ou supprimer de l'application les items correspondant aux plugins ajoutés ou supprimés du répertoire */
	private class ExtensionListener implements FileListener {
		
		/**
		 * ajoute l'item correspondant dans l'application lorsqu'un plugin a été ajouté
		 */
		@Override
		public void fileAdded(FileEvent fe) {
			try {
				String fileName = fe.getFileName();
				String className = fileName.substring(0,fileName.length() - 6);
				Class<?> pluginClass = Class.forName(className);
				final Extension newPlugin = (Extension) pluginClass.newInstance();
				final JMenuItem newPluginItemTool = new JMenuItem(newPlugin.getLabel());
				newPluginItemTool.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String contentBeforeTransformation = MyExtendableEditor.this.textArea.getText();
						String contentAfterTransformation = newPlugin.transformer(contentBeforeTransformation);
						MyExtendableEditor.this.textArea.setText(contentAfterTransformation);
					}
				});
				final JMenuItem newPluginItemHelp = new JMenuItem(newPlugin.getLabel());
				newPluginItemHelp.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null,newPlugin.helpMessage(),"Help",JOptionPane.INFORMATION_MESSAGE);
					}
				});
				MyExtendableEditor.this.toolsMenu.add(newPluginItemTool);
				MyExtendableEditor.this.helpMenu.add(newPluginItemHelp);
			}
			catch (Exception e) {}
		}

		/**
		 * ajoute l'item correspondant de l'application lorsqu'un plugin a été retiré
		 */
		@Override
		public void fileRemoved(FileEvent fe) {
			try {
				String fileName = fe.getFileName();
				String className = fileName.substring(0,fileName.length() - 6);
				Class<?> pluginClass = Class.forName(className);
				Extension deletedPlugin = (Extension) pluginClass.newInstance();
				Component[] toolsItems = MyExtendableEditor.this.toolsMenu.getMenuComponents();
				for (int i = 0 ; i < toolsItems.length ; i++) {
					JMenuItem jmi = (JMenuItem) toolsItems[i];
					if (jmi.getText().equals(deletedPlugin.getLabel()))
						MyExtendableEditor.this.toolsMenu.remove(jmi);
				}
				Component[] helpItems = MyExtendableEditor.this.helpMenu.getMenuComponents();
				for (int i = 0 ; i < helpItems.length ; i++) {
					JMenuItem jmi = (JMenuItem) helpItems[i];
					if (jmi.getText().equals(deletedPlugin.getLabel()))
						MyExtendableEditor.this.helpMenu.remove(jmi);
				}
			}
			catch (Exception e) {}
		}
		
	}

}
