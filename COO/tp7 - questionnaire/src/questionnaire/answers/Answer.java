package questionnaire.answers;

import javax.swing.JPanel;

import questionnaire.panels.AnswerPanel;

/** Abstract class representing an answer */
public abstract class Answer<T> {
	
	// the correct answer(s)
	protected T correctAnswer;
	
	// the panel for the answer (IHM)
	protected JPanel answerPanel;
	
	/**
	 * constructor for the instances of the abstract class
	 * @param ca the correct answer(s)
	 */
	public Answer(T ca) {
		this.correctAnswer = ca;
		this.answerPanel = new JPanel();
	}
	
	/**
	 * returns the expected type of the answer
	 * @return the expected type of the answer
	 */
	public abstract String getExpectedType();
	
	/**
	 * checks if the parameter and the correct answer have the same type
	 * @param s an answer statement
	 * @return <code> true </code> if <code> s </code> and <code> this </code> have the same type, <code> false </code> if not
	 */
	public abstract boolean isReceivable(String s);
	
	/**
	 * checks if the parameter is a correct answer
	 * @param s an answer statement
	 * @return <code> true </code> if <code> s </code> is a correct answer,<code> false </code> if not
	 */
	public abstract boolean isCorrect(String s);
	
	/**
	 * create the appropriate answer panel for the class
	 * @return the appropriate answer panel for the class
	 */
	public abstract AnswerPanel createMyAnswerPanel();
	
	/**
	 * returns the statement of the answer
	 * @return the statement of the answer
	 */
	public abstract String toString();
	
	/**
	 * checks if the object in parameter is equal to <code> this </code>
	 * @param o the object which will be compared to <code> this </code>
	 * @return <code> true </code> if <code> o == this </code>, <code> false </code> otherwise
	 */
	public boolean equals(Object o) {
		if (o instanceof Answer) {
			Answer<?> a = (Answer<?>) o;
			return this.toString().equals(a.toString());
		}
		else {
			return false;
		}
	}
	
}
