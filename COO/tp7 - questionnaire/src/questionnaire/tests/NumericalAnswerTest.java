package questionnaire.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import questionnaire.answers.NumericalAnswer;

/** Class representing a JUnit test for the class NumericalAnswer */
public class NumericalAnswerTest {

	/** tests the method isReceivable of the class NumericalAnswer */
	@Test
	public void testIsReceivable() {
		NumericalAnswer na = new NumericalAnswer("20");
		String user1Answer = "14";
		String user2Answer = "string";
		assertTrue("la réponse donnée par l'utilisateur 1 est-elle recevable ?",na.isReceivable(user1Answer));
		assertFalse("la réponse donnée par l'utilisateur 2 est-elle recevable ?",na.isReceivable(user2Answer));
	}

	/** tests the method isCorrect of the class NumericalAnswer */
	@Test
	public void testIsCorrect() {
		NumericalAnswer na = new NumericalAnswer("12");
		String user1Answer = "4";
		String user2Answer = "12";
		assertFalse("la réponse donnée par l'utilisateur 1 est-elle correcte ?",na.isCorrect(user1Answer));
		assertTrue("la réponse donnée par l'utilisateur 2 est-elle correcte ?",na.isCorrect(user2Answer));
	}

}
