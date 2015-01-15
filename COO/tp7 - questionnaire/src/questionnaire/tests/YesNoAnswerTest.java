package questionnaire.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import questionnaire.answers.YesNoAnswer;

/** Class representing a JUnit test for the class YesNoAnswer */
public class YesNoAnswerTest {

	/** tests the method isReceivable of the class YesNoAnswer */
	@Test
	public void testIsReceivable() {
		YesNoAnswer yna = new YesNoAnswer("oui");
		String user1Answer = "oui";
		String user2Answer = "string";
		String user3Answer = "5";
		String user4Answer = "NoN";
		assertTrue("la réponse donnée par l'utilisateur 1 est-elle recevable ?",yna.isReceivable(user1Answer));
		assertFalse("la réponse donnée par l'utilisateur 2 est-elle recevable ?",yna.isReceivable(user2Answer));
		assertFalse("la réponse donnée par l'utilisateur 3 est-elle recevable ?",yna.isReceivable(user3Answer));
		assertTrue("la réponse donnée par l'utilisateur 4 est-elle recevable ?",yna.isReceivable(user4Answer));
	}

	/** tests the method isCorrect of the class YesNoAnswer */
	@Test
	public void testIsCorrect() {
		YesNoAnswer yna = new YesNoAnswer("non");
		String user1Answer = "nON";
		String user2Answer = "non";
		String user3Answer = "Non";
		String user4Answer = "oui";
		assertTrue("la réponse donnée par l'utilisateur 1 est-elle correcte ?",yna.isCorrect(user1Answer));
		assertTrue("la réponse donnée par l'utilisateur 2 est-elle correcte ?",yna.isCorrect(user2Answer));
		assertTrue("la réponse donnée par l'utilisateur 3 est-elle correcte ?",yna.isCorrect(user3Answer));
		assertFalse("la réponse donnée par l'utilisateur 4 est-elle correcte ?",yna.isCorrect(user4Answer));
	}

}
