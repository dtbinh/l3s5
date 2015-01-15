package questionnaire.panels;

import javax.swing.JPanel;

/** Abstract factory method serving to create the appropriate answer panel for the class */ 
public abstract class AnswerPanel {

	// the panel
	protected JPanel panel;
	
	/**
	 * constructor for the instances of the class
	 * @param p the panel
	 */
	public AnswerPanel(JPanel p) {
		this.panel = p;
	}
	
	/**
	 * returns the panel
	 * @return the panel
	 */
	public JPanel getPanel() {
		return this.panel;
	}
	
}
