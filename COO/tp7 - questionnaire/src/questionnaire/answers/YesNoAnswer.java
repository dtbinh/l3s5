package questionnaire.answers;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import questionnaire.panels.AnswerPanel;
import questionnaire.panels.YesNoAnswerPanel;

/** Class representing a "yes-no" answer */
public class YesNoAnswer extends Answer<String> {

	// the type of the answer
	private static final String TYPE = "oui/non";
	
	// the two buttons corresponding to the answers
	private JRadioButton yesButton, noButton;
	
	/**
	 * constructor for the instances of the class
	 * @param s the answer's statement
	 */
	public YesNoAnswer(String s) {
		super(s);
		this.yesButton = new JRadioButton("oui");
		this.noButton = new JRadioButton("non");
	}
	
	@Override
	public String getExpectedType() {
		return YesNoAnswer.TYPE;
	}
	
	@Override
	public boolean isReceivable(String s) {
		String s2 = Character.toUpperCase(s.trim().charAt(0))+s.trim().substring(1).toLowerCase();
		return (s2.equals("Oui") || s2.equals("Non"));
	}

	@Override
	public boolean isCorrect(String s) {
		return this.toString().equals(s.trim().toLowerCase());
	}
	
	@Override
	public String toString() {
		return this.correctAnswer.toString();
	}
	
	@Override
	public AnswerPanel createMyAnswerPanel() {
		this.answerPanel.setLayout(new GridLayout(2,1));
		ButtonGroup bg = new ButtonGroup();
		bg.add(this.yesButton); bg.add(this.noButton);
		this.answerPanel.add(this.yesButton); this.answerPanel.add(this.noButton);
		return (AnswerPanel) new YesNoAnswerPanel(this.answerPanel);
	}

}
