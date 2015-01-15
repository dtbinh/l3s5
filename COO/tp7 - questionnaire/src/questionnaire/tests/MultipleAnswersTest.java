package questionnaire.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import questionnaire.answers.MultipleAnswers;

/** Class representing a JUnit test for the class MultipleAnswers */
public class MultipleAnswersTest {

	/** tests the method isReceivable of the class MultipleAnswers */
	@Test
	public void testIsReceivable() {
		MultipleAnswers ma = new MultipleAnswers("Frodo ; Sam");
		String user1Answer = "Frodo";
		String user2Answer = "string";
		assertTrue("la réponse donnée par l'utilisateur 1 est-elle recevable ?",ma.isReceivable(user1Answer));
		assertTrue("la réponse donnée par l'utilisateur 2 est-elle recevable ?",ma.isReceivable(user2Answer));
	}

	/** tests the method isCorrect of the class MultipleAnswers */
	@Test
	public void testIsCorrect() {
		MultipleAnswers ma = new MultipleAnswers("Frodo ; Sam");
		String user1Answer = "frodo";
		String user2Answer = "sam";
		String user3Answer = "string";
		assertTrue("la réponse donnée par l'utilisateur 1 est-elle correcte ?",ma.isCorrect(user1Answer));
		assertTrue("la réponse donnée par l'utilisateur 2 est-elle correcte ?",ma.isCorrect(user2Answer));
		assertFalse("la réponse donnée par l'utilisateur 2 est-elle correcte ?",ma.isCorrect(user3Answer));
	}

}
