package questionnaire.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import questionnaire.answers.SymbolicAnswer;

/** Class representing a JUnit test for the class SymbolicAnswer */
public class SymbolicAnswerTest {
	
	/** tests the method isReceivable of the class SymbolicAnswer */
	@Test
	public void testIsReceivable() {
		SymbolicAnswer sa = new SymbolicAnswer("Tolkien");
		String user1Answer = "tolkien";
		String user2Answer = "string";
		String user3Answer = "14";
		assertTrue("la réponse donnée par l'utilisateur 1 est-elle recevable ?",sa.isReceivable(user1Answer));
		assertTrue("la réponse donnée par l'utilisateur 2 est-elle recevable ?",sa.isReceivable(user2Answer));
		assertFalse("la réponse donnée par l'utilisateur 3 est-elle recevable ?",sa.isReceivable(user3Answer));
	}

	/** tests the method isCorrect of the class SymbolicAnswer */
	@Test
	public void testIsCorrect() {
		SymbolicAnswer sa = new SymbolicAnswer("Tolkien");
		String user1Answer = "Tolkien";
		String user2Answer = "tolkien";
		String user3Answer = "toLKIeN";
		String user4Answer = "nptk";
		assertTrue("la réponse donnée par l'utilisateur 1 est-elle correcte ?",sa.isCorrect(user1Answer));
		assertTrue("la réponse donnée par l'utilisateur 2 est-elle correcte ?",sa.isCorrect(user2Answer));
		assertTrue("la réponse donnée par l'utilisateur 3 est-elle correcte ?",sa.isCorrect(user3Answer));
		assertFalse("la réponse donnée par l'utilisateur 4 est-elle correcte ?",sa.isCorrect(user4Answer));
	}

}
