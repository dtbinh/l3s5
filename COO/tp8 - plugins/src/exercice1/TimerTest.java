package exercice1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.Timer;

/** Class permettant de tester la classe javax.swing.Timer en affichant la date et l'heure d'aujourd'hui à chaque délai */
public class TimerTest {

	/**
	 * méthode main de la classe
	 * @param args le tableau des arguments
	 */
	public static void main(String[] args) {
		TimerTest tt = new TimerTest();
		Timer t = new Timer(1000,tt.new PrintDateListener());
		t.start();
		while (true);
	}
	
	/** Classe interne représentant une action consistant à afficher la date et l'heure d'aujourd'hui */
	class PrintDateListener implements ActionListener {
		
		/**
		 * fait l'action (affiche la date et l'heure)
		 * @param e l'événement qui correspond à l'écoulement d'un délai
		 */
		public void actionPerformed(ActionEvent e) {
			System.out.println(Calendar.getInstance().getTime());
		}

	}

}
