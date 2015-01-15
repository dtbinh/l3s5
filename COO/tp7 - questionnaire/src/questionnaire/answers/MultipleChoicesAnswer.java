package questionnaire.answers;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import questionnaire.panels.AnswerPanel;
import questionnaire.panels.MultipleChoicesAnswerPanel;

/** Class representing a multiple-choice answer */
public class MultipleChoicesAnswer extends Answer<List<String>> {
	
	// the type of the answer
	private final String TYPE = this.mixList();
	
	// the separation character
	public static final String SEPARATION = "\\|";
	
	/**
	 * constructor for the instances of the class
	 * @param s the statement of the answer
	 */
	public MultipleChoicesAnswer(String s) {
		super(Arrays.asList(s.trim().split(MultipleChoicesAnswer.SEPARATION)));
	}
	
	/**
	 * returns a mixed copy of the list of the possible answers
	 * @return a mixed copy of the list of the possible answers
	 */
	public String mixList() {
		List<String> mixedList = new ArrayList<String>();
		mixedList.addAll(this.correctAnswer);
		String s = "";
		while (mixedList.get(0).trim().equals("Bill")) {
			Collections.shuffle(mixedList);
		}
		for (String rep : mixedList) {
			s += rep.trim()+"  |  ";
		}
		return s.substring(0,s.length() - 5);
	}

	@Override
	public String getExpectedType() {
		return this.TYPE;
	}

	@Override
	public boolean isReceivable(String s) {
		for (String rep : this.correctAnswer) {
			if (rep.trim().toUpperCase().equals(s.trim().toUpperCase()))
				return true;
		}
		return false;
	}

	@Override
	public boolean isCorrect(String s) {
		return this.toString().trim().toUpperCase().equals(s.trim().toUpperCase());
	}
	
	@Override
	public String toString() {
		return this.correctAnswer.get(0).trim();
	}

	@Override
	public AnswerPanel createMyAnswerPanel() {
		ButtonGroup bg = new ButtonGroup();
		String[] choicesArray = this.getExpectedType().split(MultipleChoicesAnswer.SEPARATION);
		switch (choicesArray.length % 2) {
			case 0 : this.answerPanel.setLayout(new GridLayout(choicesArray.length / 2,choicesArray.length / 2));
			default : this.answerPanel.setLayout(new GridLayout(choicesArray.length / 2,(choicesArray.length / 2) + 1));
		}
		for (String s : choicesArray) {
			JRadioButton button = new JRadioButton(s.trim());
			bg.add(button);
			this.answerPanel.add(button);
		}
		return (AnswerPanel) new MultipleChoicesAnswerPanel(this.answerPanel);
	}
	
}
