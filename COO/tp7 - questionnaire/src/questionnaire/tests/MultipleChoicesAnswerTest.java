package questionnaire.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import questionnaire.answers.MultipleChoicesAnswer;

/** Class representing a JUnit test for the class MultipleChoicesAnswer */
public class MultipleChoicesAnswerTest {

	/** tests the method isReceivable of the class MultipleChoicesAnswer */
	@Test
	public void testIsReceivable() {
		MultipleChoicesAnswer mca = new MultipleChoicesAnswer("Bill | Bourricot | Robert | Jolly Jumper");
		String user1Answer = "Bourricot";
		String user2Answer = "Robert";
		String user3Answer = "Bob";
		assertTrue("la réponse donnée par l'utilisateur 1 est-elle recevable ?",mca.isReceivable(user1Answer));
		assertTrue("la réponse donnée par l'utilisateur 2 est-elle recevable ?",mca.isReceivable(user2Answer));
		assertFalse("la réponse donnée par l'utilisateur 3 est-elle recevable ?",mca.isReceivable(user3Answer));
	}

	/** tests the method isCorrect of the class MultipleChoicesAnswer */
	@Test
	public void testIsCorrect() {
		MultipleChoicesAnswer mca = new MultipleChoicesAnswer("Bill | Bourricot | Robert | Jolly Jumper");
		String user1Answer = "Bourricot";
		String user2Answer = "Robert";
		String user3Answer = "Bill";
		assertFalse("la réponse donnée par l'utilisateur 1 est-elle correcte ?",mca.isCorrect(user1Answer));
		assertFalse("la réponse donnée par l'utilisateur 2 est-elle correcte ?",mca.isCorrect(user2Answer));
		assertTrue("la réponse donnée par l'utilisateur 3 est-elle correcte ?",mca.isCorrect(user3Answer));
	}

	/** tests the method toString of the class MultipleChoicesAnswer */
	@Test
	public void testToString() {
		MultipleChoicesAnswer mca = new MultipleChoicesAnswer("Bill | Bourricot | Robert | Jolly Jumper");
		assertEquals("l'énoncé de la bonne réponse est-il correct ?",mca.toString(),"Bill");
	}

}
