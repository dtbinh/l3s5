package questionnaire;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import questionnaire.answers.Answer;

/** Class representing a question */
public class Question {

	// the statement of the question
	private String statement;
	
	// the answer to the question
	private Answer<?> answer;
	
	// the number of points of the question
	private int numberOfPoints;
	
	/**
	 * constructor for the instances of the class
	 * @param st the statement of the question
	 * @param a the answer to the question
	 * @param nop the number of points of the question
	 */
	public Question(String st, Answer<?> a, int nop) {
		this.statement = st;
		this.answer = a;
		this.numberOfPoints = nop;
	}
	
	/**
	 * returns the statement of the question
	 * @return the statement of the question
	 */
	public String getStatement() {
		return this.statement;
	}
	
	/**
	 * returns the answer to the question
	 * @return the answer to the question
	 */
	public Answer<?> getAnswer() {
		return this.answer;
	}
	
	/**
	 * returns the number of points of the question
	 * @return the number of points of the question
	 */
	public int getNumberOfPoints() {
		return this.numberOfPoints;
	}
	
	/**
	 * returns a text representing the number of points (Examples : 1 point, 2 points)
	 * @return a text representing the number of points (Examples : 1 point, 2 points)
	 */
	public String nopText() {
		if (this.numberOfPoints == 1)
			return "1 point";
		else
			return (this.numberOfPoints+" points");
	}
	
	/**
	 * returns the statement of the answer to the question
	 * @return the statement of the answer to the question
	 */
	public String getCorrectAnswerStatement() {
		return this.answer.toString();
	}
	
	/**
	 * create a panel for the question
	 * @return a panel for the question
	 */
	public JPanel createMyQuestionPanel(int order) {
		JPanel questionPanel = new JPanel();
		JLabel questionStatementLabel = new JLabel(order+") "+this.getStatement());
		questionStatementLabel.setHorizontalAlignment(SwingConstants.CENTER);
		questionStatementLabel.setVerticalAlignment(SwingConstants.CENTER);
		questionPanel.add(questionStatementLabel);
		return questionPanel;
	}

}
