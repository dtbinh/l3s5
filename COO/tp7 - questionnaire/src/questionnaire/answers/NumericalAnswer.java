package questionnaire.answers;

import java.awt.Dimension;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import questionnaire.panels.AnswerPanel;
import questionnaire.panels.SymbolicAnswerPanel;

/** Class representing a numerical answer */
public class NumericalAnswer extends Answer<String> {
	
	// the type of the answer
	private static final String TYPE = "numerique";
	
	// the spinner for the answer (for the IHM)
	private JSpinner answerSpinner;
	
	/**
	 * constructor for the instances of the class
	 * @param s the statement of the answer
	 */
	public NumericalAnswer(String s) {
		super(s);
		this.answerSpinner = new JSpinner(new SpinnerNumberModel());
	}
	
	@Override
	public String getExpectedType() {
		return NumericalAnswer.TYPE;
	}

	@Override
	public boolean isReceivable(String s) {
		try {
			Integer.parseInt(s);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public boolean isCorrect(String s) {
		return this.toString().equals(s);
	}
	
	@Override
	public String toString() {
		return this.correctAnswer.toString();
	}
	
	@Override
	public AnswerPanel createMyAnswerPanel() {
		this.answerSpinner.setPreferredSize(new Dimension(75,50));
		this.answerPanel.add(this.answerSpinner);
		return new SymbolicAnswerPanel(this.answerPanel);
	}

}
