package questionnaire.answers;

import java.awt.Dimension;

import javax.swing.JTextField;

import questionnaire.panels.AnswerPanel;
import questionnaire.panels.SymbolicAnswerPanel;

/** Class representing a symbolic answer */
public class SymbolicAnswer extends Answer<String> {
	
	// the type of the answer
	private static final String TYPE = "symbolique";
	
	// the answer's statement's field (IHM)
	private JTextField answerStatementField;
	
	/**
	 * constructor for the instances of the class
	 * @param s the statement of the answer
	 */
	public SymbolicAnswer(String s) {
		super(s);
		this.answerStatementField = new JTextField();
	}

	@Override
	public String getExpectedType() {
		return SymbolicAnswer.TYPE;
	}

	@Override
	public boolean isReceivable(String s) {
		try {
			Integer.parseInt(s);
			return false;
		}
		catch (NumberFormatException e) {
			if (s.trim().toUpperCase().equals("OUI") || s.trim().toUpperCase().equals("NON"))
				return false;
			else
				return true;
		}
	}

	@Override
	public boolean isCorrect(String s) {
		return (this.toString().trim().toUpperCase().equals(s.toUpperCase()));
	}
	
	@Override
	public String toString() {
		return this.correctAnswer.toString();
	}
	
	
	@Override
	public AnswerPanel createMyAnswerPanel() {
		this.answerStatementField.setPreferredSize(new Dimension(300,50));
		this.answerPanel.add(answerStatementField);
		return (AnswerPanel) new SymbolicAnswerPanel(this.answerPanel);
	}

}
