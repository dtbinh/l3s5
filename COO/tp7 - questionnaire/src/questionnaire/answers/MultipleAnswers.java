package questionnaire.answers;

import java.awt.Dimension;
import java.util.List;
import java.util.Arrays;

import javax.swing.JTextField;

import questionnaire.panels.AnswerPanel;
import questionnaire.panels.MultipleAnswersPanel;

/** Class representing multiple correct answers */
public class MultipleAnswers extends Answer<List<String>> {
	
	// the type of the answer
	private final String TYPE = this.correctAnswer.size()+" réponses possibles";
	
	// the separation character
	public static final String SEPARATION = ";";
	
	// the answer's statement's field (IHM)
	private JTextField answerStatementField;
	
	/**
	 * constructor for the instances of the class
	 * @param s the statement of the answer
	 */
	public MultipleAnswers(String s) {
		super(Arrays.asList(s.split(MultipleAnswers.SEPARATION)));
		this.answerStatementField = new JTextField();
	}
	
	@Override
	public String getExpectedType() {
		return this.TYPE;
	}

	@Override
	public boolean isReceivable(String s) {
		return true;
	}

	@Override
	public boolean isCorrect(String s) {
		for (String rep : this.correctAnswer) {
			if (rep.trim().toUpperCase().equals(s.trim().toUpperCase()))
				return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String s = "";
		int i = 0;
		while (i < this.correctAnswer.size() - 2) {
			s += this.correctAnswer.get(i).trim()+", ";
			i++;
		}
		s += this.correctAnswer.get(i++).trim()+" ou "+this.correctAnswer.get(i).trim();
		return s;
	}

	@Override
	public AnswerPanel createMyAnswerPanel() {
		this.answerStatementField.setPreferredSize(new Dimension(300,50));
		this.answerPanel.add(answerStatementField);
		return (AnswerPanel) new MultipleAnswersPanel(this.answerPanel);
	}
	
}
