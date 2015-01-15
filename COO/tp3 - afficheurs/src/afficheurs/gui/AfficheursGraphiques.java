package afficheurs.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import afficheurs.*;

/** Classe permettant de representer une interface graphique pour les afficheurs de message */
public class AfficheursGraphiques {

	// les afficheurs
	private Afficheur afficheurSimple, afficheurLatence, afficheurVitesse;

	// les zones de saisie du message pour les differents afficheurs
	private JTextField texteAfficheurSimple, texteAfficheurLatence, texteAfficheurVitesse;

	// les zones de texte pour les differents afficheurs
	private JLabel labelAfficheurSimple, labelAfficheurLatence, labelAfficheurVitesse;

	// les boutons pour les différents afficheurs
	private JButton boutonAfficheurSimple, boutonAfficheurLatence, boutonAfficheurVitesse;

	// les messages des afficheurs
	private String messageAfficheurSimple, messageAfficheurLatence, messageAfficheurVitesse;

	/**
	 * constructeur pour les instances de la classe AfficheursGraphiques
	 */
	public AfficheursGraphiques() {
		// initialisation des zones de saisie du message
		this.texteAfficheurSimple = new JTextField();
		this.texteAfficheurLatence = new JTextField();
		this.texteAfficheurVitesse = new JTextField();
		// initialisation des zones de texte
		this.labelAfficheurSimple = new JLabel("Message affiché : ");
		this.labelAfficheurLatence = new JLabel("Message affiché : ");
		this.labelAfficheurVitesse = new JLabel("Message affiché : ");
		// initialisation des boutons
		this.boutonAfficheurSimple = new JButton("Top");
		this.boutonAfficheurLatence = new JButton("Top");
		this.boutonAfficheurVitesse = new JButton("Top");
	}

	/**
	 * cree une interface graphique pour l'afficheur simple
	 */
	public void afficheurSimple() {
		// la fenetre de l'interface
		JFrame f = new JFrame("Afficheur simple");
		// la position de la fenetre
		f.setLocation(50, 300);
		// la taille de la fenetre (longueur, largeur)
		f.setSize(300, 100);
		// permet de quitter le programme sans Ctrl+C
		f.addWindowListener(new FermeWindowEvent());
		// mode de placement des elements et visibilite de la fenetre
		f.setLayout(new BorderLayout());
		f.setVisible(true);
		// ajout d'un evenement au bouton qui declenche le "Top"
		boutonAfficheurSimple.addActionListener(new AfficheurSimpleActionListener());
		// ajout du texte, du bouton et du label a la fenetre
		f.add(texteAfficheurSimple,BorderLayout.NORTH);
		f.add(boutonAfficheurSimple,BorderLayout.CENTER);
		f.add(labelAfficheurSimple,BorderLayout.SOUTH);
	}

	/**
	 * cree une interface graphique pour l'afficheur avec latence
	 */
	public void afficheurLatence() {
		// la fenetre de l'interface
		JFrame f = new JFrame("Afficheur avec latence");
		// la position de la fenetre
		f.setLocation(550, 300);
		// la taille de la fenetre (longueur, largeur)
		f.setSize(300, 100);
		// permet de quitter le programme sans Ctrl+C
		f.addWindowListener(new FermeWindowEvent());
		// mode de placement des elements et visibilite de la fenetre
		f.setLayout(new BorderLayout());
		f.setVisible(true);
		// ajout d'un evenement au bouton qui declenche le "Top"
		boutonAfficheurLatence.addActionListener(new AfficheurLatenceActionListener());
		// ajout du texte, du bouton et du label a la fenetre
		f.add(texteAfficheurLatence,BorderLayout.NORTH);
		f.add(boutonAfficheurLatence,BorderLayout.CENTER);
		f.add(labelAfficheurLatence,BorderLayout.SOUTH);
	}

	/**
	 * cree une interface graphique pour l'afficheur avec vitesse
	 */
	public void afficheurVitesse() {
		// la fenetre de l'interface
		JFrame f = new JFrame("Afficheur avec vitesse");
		// la position de la fenetre
		f.setLocation(1050, 300);
		// la taille de la fenetre (longueur, largeur)
		f.setSize(300, 100);
		// permet de quitter le programme sans Ctrl+C
		f.addWindowListener(new FermeWindowEvent());
		// mode de placement des elements et visibilite de la fenetre
		f.setLayout(new BorderLayout());
		f.setVisible(true);
		// ajout d'un evenement au bouton qui declenche le "Top"
		boutonAfficheurVitesse.addActionListener(new AfficheurVitesseActionListener());
		// ajout du texte, du bouton et du label a la fenetre
		f.add(texteAfficheurVitesse,BorderLayout.NORTH);
		f.add(boutonAfficheurVitesse,BorderLayout.CENTER);
		f.add(labelAfficheurVitesse,BorderLayout.SOUTH);
	}

	/**
	 * methode main qui permet de tester notre classe
	 */
	public static void main(String[] args) {
		AfficheursGraphiques afficheursGraphiques = new AfficheursGraphiques();
		afficheursGraphiques.afficheurSimple();
		afficheursGraphiques.afficheurLatence();
		afficheursGraphiques.afficheurVitesse();
	}


	// ******************************************************* CLASSES INTERNES *******************************************************

	/** Classe interne qui gere l'action a effectuer suite au clic sur le bouton Top de l'afficheur simple */
	class AfficheurSimpleActionListener implements ActionListener {

		/**
		 * gere l'action a effectuer suite au clic sur le bouton Top de l'afficheur simple
		 * @param e l'evenement
		 */
		public void actionPerformed(ActionEvent e) {
			if (!AfficheursGraphiques.this.texteAfficheurSimple.getText().equals(AfficheursGraphiques.this.messageAfficheurSimple)) {
				AfficheursGraphiques.this.messageAfficheurSimple = AfficheursGraphiques.this.texteAfficheurSimple.getText();
				AfficheursGraphiques.this.afficheurSimple = new Afficheur(AfficheursGraphiques.this.messageAfficheurSimple.length() + 1);
				AfficheursGraphiques.this.afficheurSimple.setMessage(AfficheursGraphiques.this.messageAfficheurSimple);
			}
			AfficheursGraphiques.this.afficheurSimple.decale();
			AfficheursGraphiques.this.labelAfficheurSimple.setText("Message affiché : <<"+AfficheursGraphiques.this.afficheurSimple+">>");
		}

	}

	/** Classe interne qui gere l'action a effectuer suite au clic sur le bouton Top de l'afficheur avec latence */
	class AfficheurLatenceActionListener implements ActionListener {

		/**
		 * gere l'action a effectuer suite au clic sur le bouton Top de l'afficheur avec latence
		 * @param e l'evenement
		 */
		public void actionPerformed(ActionEvent e) {
			if (!AfficheursGraphiques.this.texteAfficheurLatence.getText().equals(AfficheursGraphiques.this.messageAfficheurLatence)) {
				AfficheursGraphiques.this.messageAfficheurLatence = AfficheursGraphiques.this.texteAfficheurLatence.getText();
				AfficheursGraphiques.this.afficheurLatence = new Latence(AfficheursGraphiques.this.messageAfficheurLatence.length() + 1,AfficheursGraphiques.this.messageAfficheurLatence.length() - 1);
				AfficheursGraphiques.this.afficheurLatence.setMessage(AfficheursGraphiques.this.messageAfficheurLatence);
			}
			AfficheursGraphiques.this.afficheurLatence.decale();
			AfficheursGraphiques.this.labelAfficheurLatence.setText("Message affiché : <<"+AfficheursGraphiques.this.afficheurLatence+">>");
		}

	}

	/** Classe interne qui gere l'action a effectuer suite au clic sur le bouton Top de l'afficheur avec vitesse */
	class AfficheurVitesseActionListener implements ActionListener {

		/**
		 * gere l'action a effectuer suite au clic sur le bouton Top de l'afficheur avec vitesse
		 * @param e l'evenement
		 */
		public void actionPerformed(ActionEvent e) {
			if (!AfficheursGraphiques.this.texteAfficheurVitesse.getText().equals(AfficheursGraphiques.this.messageAfficheurVitesse)) {
				AfficheursGraphiques.this.messageAfficheurVitesse = AfficheursGraphiques.this.texteAfficheurVitesse.getText();
				AfficheursGraphiques.this.afficheurVitesse = new Vitesse(AfficheursGraphiques.this.messageAfficheurVitesse.length() + 1,AfficheursGraphiques.this.messageAfficheurVitesse.length() - 1,AfficheursGraphiques.this.messageAfficheurVitesse.length() - 2);
				AfficheursGraphiques.this.afficheurVitesse.setMessage(AfficheursGraphiques.this.messageAfficheurVitesse);
			}
			AfficheursGraphiques.this.afficheurVitesse.decale();
			AfficheursGraphiques.this.labelAfficheurVitesse.setText("Message affiché : <<"+AfficheursGraphiques.this.afficheurVitesse+">>");
		}

	}

	/** Classe interne permettant de gerer la fermeture de l'application lorsqu'on ferme une fenetre */
	class FermeWindowEvent extends WindowAdapter {

		public void windowClosing(java.awt.event.WindowEvent e) {
			System.exit(0);
		}

	}

}
