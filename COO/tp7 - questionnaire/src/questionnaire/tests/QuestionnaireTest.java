package questionnaire.tests;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import questionnaire.Question;
import questionnaire.Questionnaire;

/** Class representing a JUnit test for the class Questionnaire */
public class QuestionnaireTest {

	/** tests the methods getNumberOfQuestions and addQuestion and equals of the class Questionnaire */
	@Test
	public void testNumberOfQuestionsAndAddQuestion() {
		Questionnaire quest = new Questionnaire();
		Question q1 = new Question("",null,0);
		Question q2 = new Question("",null,0);
		Question q3 = new Question("",null,0);
		Question q4 = new Question("",null,0);
		quest.addQuestion(q1); quest.addQuestion(q2); quest.addQuestion(q3); quest.addQuestion(q4);
		assertEquals("le nombre de questions est-il valide ?",quest.numberOfQuestions(),4);
	}
	
	/** tests the method createQuestion of the class Questionnaire */
	@Test
	public void testCreateQuestion() throws IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Question q1 = Questionnaire.createQuestion("Quel est le nom de l'auteur du Seigneur des Anneaux ?","Tolkien","SymbolicAnswer","1");
		assertEquals("L'énoncé de la question créée est-il valide ?",q1.getStatement(),"Quel est le nom de l'auteur du Seigneur des Anneaux ?");
		assertEquals("L'énoncé de la bonne réponse est-il valide ?",q1.getCorrectAnswerStatement(),"Tolkien");
		assertEquals("Le nombre de points de la question ?",q1.getNumberOfPoints(),1);
		Question q2 = Questionnaire.createQuestion("Comment s'appelle le poney qui accompagne la compagnie jusqu'à la Moria ?","Bill | Bourricot | Robert | Jolly Jumper","MultipleChoicesAnswer","3");
		assertEquals("L'énoncé de la question créée est-il valide ?",q2.getStatement(),"Comment s'appelle le poney qui accompagne la compagnie jusqu'à la Moria ?");
		assertEquals("L'énoncé de la bonne réponse est-il valide ?",q2.getCorrectAnswerStatement(),"Bill");
		assertEquals("Le nombre de points de la question ?",q2.getNumberOfPoints(),3);
	}

	/** tests the method initQuestionnaire of the class Questionnaire */
	@Test
	public void testInitQuestionnaire() throws IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Questionnaire quest = new Questionnaire();
		Questionnaire quest2 = new Questionnaire();
		quest.initQuestionnaire("question_tolkien.txt");
		quest2.initQuestionnaire("question_tolkien_2.txt");
		assertEquals("le nombre de points du questionnaire est-il valide ?",quest.numberOfQuestions(),5);
		assertEquals("le nombre de points du questionnaire est-il valide ?",quest2.numberOfQuestions(),7);
	}

	/** tests the method numberOfPointsQuestionnaire of the class Questionnaire */
	@Test
	public void testNumberOfPointsQuestionnaire() {
		Questionnaire quest = new Questionnaire();
		Question q1 = new Question("",null,1);
		Question q2 = new Question("",null,2);
		Question q3 = new Question("",null,3);
		Question q4 = new Question("",null,4);
		quest.addQuestion(q1); quest.addQuestion(q2); quest.addQuestion(q3); quest.addQuestion(q4);
		assertEquals("le nombre de points du questionnaire est-il valide ?",quest.numberOfPointsQuestionnaire(),10);
	}

}
