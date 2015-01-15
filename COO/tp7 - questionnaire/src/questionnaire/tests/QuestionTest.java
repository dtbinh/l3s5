package questionnaire.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import questionnaire.Question;
import questionnaire.answers.*;

/** Class representing a JUnit test for the class Question */
public class QuestionTest {
	
	/** tests the method getStatement of the class Question */
	@Test
	public void testGetStatement() {
		Question q = new Question("Dans quelle ville sommes-nous ?",null,0);
		assertEquals("L'énoncé de la question est-il valide ?",q.getStatement(),"Dans quelle ville sommes-nous ?");
		assertFalse("L'énoncé de la question est-il invalide ?",q.getStatement().equals("Dans quelle ville-sommes nous ?"));
	}

	/** tests the methods getAnswer and equals of the class Question */
	@Test
	public void testGetAnswerAndEquals() {
		Question q = new Question("Dans quelle ville sommes-nous ?",new SymbolicAnswer("Lille"),1);
		Answer<?> a = new SymbolicAnswer("Lille");
		Answer<?> b = new SymbolicAnswer("Bordeaux");
		Answer<?> c = new NumericalAnswer("14");
		String s = "Test";
		assertTrue("La réponse à la question est-elle valide ?",q.getAnswer().equals(a));
		assertFalse("La réponse à la question est-elle valide ?",q.getAnswer().equals(b));
		assertFalse("La réponse à la question est-elle valide ?",q.getAnswer().equals(c));
		assertFalse("La réponse à la question est-elle valide ?",q.getAnswer().equals(s));
	}

	/** tests the methods getNumberOfPoints and equals of the class Question */
	@Test
	public void testNumberOfPoints() {
		Question q = new Question("Dans quelle ville sommes-nous ?",new SymbolicAnswer("Lille"),1);
		assertTrue("Le nombre de points de la question est-il correct ?",q.getNumberOfPoints() == 1);
		assertFalse("Le nombre de points de la question est-il incorrect ?",q.getNumberOfPoints() == 2);
	}

	/** tests the methods getCorrectAnswerStatement of the class Question */
	@Test
	public void testGetCorrectAnswerStatement() {
		Question q = new Question("Dans quelle ville sommes-nous ?",new SymbolicAnswer("Lille"),1);
		assertEquals("L'énoncé de la réponse est-il correct ?",q.getCorrectAnswerStatement(),"Lille");
	}

}
