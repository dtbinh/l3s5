package questionnaire;

import io.Input;

import questionnaire.answers.Answer;
import questionnaire.answers.AnswerFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/** Class representing a questionnaire */
public class Questionnaire {

	// the list of the questions
	private List<Question> questions;
	
	/**
	 * constructor for the instances of the class
	 */
	public Questionnaire() {
		this.questions = new LinkedList<Question>();
	}

	/**
	 * returns the list of the questions
	 * @return the list of the questions
	 */
	public List<Question> getQuestions() {
		return questions;
	}
	
	/**
	 * returns the number of questions in the questionnaire
	 * @return the number of questions in the questionnaire
	 */
	public int numberOfQuestions() {
		return this.questions.size();
	}
	
	/**
	 * add a question to the questionnaire
	 * @param q the question to add to the questionnaire
	 */
	public void addQuestion(Question q) {
		this.questions.add(q);
	}
	
	/**
	 * create the questions with the parameters
	 * @param questionStatement the statement of the question
	 * @param answerStatement the statement of the answer to the question
	 * @param answerClass the type of answer that has to be created
	 * @param points a string representing the number of points
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalArgumentException 
	 */
	public static Question createQuestion(String questionStatement, String answerStatement, String answerClass, String points) throws IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Answer<?> rep = AnswerFactory.buildAnswer(answerClass,answerStatement);
		try {
			int np = Integer.parseInt(points);
			return new Question(questionStatement,rep,np);
		}
		catch (NumberFormatException e) {
			throw new IllegalArgumentException("la chaine points doit representer un entier");
		}
	}
	
	/**
	 * initialize the questionnaire with the informations in the file <code> fileName </code>
	 * @param fileName the filename in which we extract the informations to initialize the questionnaire
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalArgumentException 
	 */
	public void initQuestionnaire(String fileName) throws IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		File source = new File(fileName);
		try {
			BufferedReader in = new BufferedReader(new FileReader(source));
			String line = "";
			while (line != null) {
				line = in.readLine();
				String eq = line;
				line = in.readLine();
				String er = line;
				line = in.readLine();
				String np = line;
				line = in.readLine();
				String cr = line;
				if (line != null) {
					Question q = Questionnaire.createQuestion(eq,er,cr,np);
					this.addQuestion(q);
				}
			}
			in.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("fichier "+fileName+" introuvable");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e) {}
	}
	
	/**
	 * returns the number of points of the questionnaire
	 * @return the number of points of the questionnaire
	 */
	public int numberOfPointsQuestionnaire() {
		int nop = 0;
		Iterator<Question> it = this.questions.iterator();
		while (it.hasNext()) {
			Question q = it.next();
			nop += q.getNumberOfPoints();
		}
		return nop;
	}
	
	/**
	 * submit the questionnaire to the user
	 * @throws IOException 
	 */
	public void ask() throws IOException {
		int nbrePoints = 0;
		int maxTempPoints = 0;
		System.out.println("DÉBUT DU QUESTIONNAIRE (Tapez \"quit\" pour quitter le questionnaire)\n");
		Iterator<Question> it = this.questions.iterator();
		while (it.hasNext()) {
			Question q = it.next();
			System.out.println(q.getStatement());
			System.out.print("("+q.getAnswer().getExpectedType()+") ");
			String s = Input.readLine();
			if (s.equals("quit")) {
				if (nbrePoints == 0 || nbrePoints == 1)
					System.out.println("\nVOUS AVEZ QUITTÉ LE QUESTIONNAIRE ! Vous aviez jusque-là "+nbrePoints+" point sur "+maxTempPoints+" possibles");
				else
					System.out.println("\nVOUS AVEZ QUITTÉ LE QUESTIONNAIRE ! Vous aviez jusque-là "+nbrePoints+" points sur "+maxTempPoints+" possibles");
				return;
			}
			maxTempPoints += q.getNumberOfPoints();
			while (!q.getAnswer().isReceivable(s)) {
				System.out.print("("+q.getAnswer().getExpectedType()+") ");
				s = Input.readLine();
				if (s.equals("quit")) {
					if (nbrePoints == 0 || nbrePoints == 1)
						System.out.println("\nVOUS AVEZ QUITTÉ LE QUESTIONNAIRE ! Vous aviez jusque-là "+nbrePoints+" point sur "+maxTempPoints+" possibles");
					else
						System.out.println("\nVOUS AVEZ QUITTÉ LE QUESTIONNAIRE ! Vous aviez jusque-là "+nbrePoints+" points sur "+maxTempPoints+" possibles");
					return;
				}
			}			
			if (q.getAnswer().isCorrect(s)) {
				System.out.println("correct ("+q.nopText()+")");
				nbrePoints += q.getNumberOfPoints();
			}
			else {
				if (!q.getAnswer().getClass().getName().equals("quizz.MultipleAnswers"))
					System.out.println("incorrect, la bonne réponse est : "+q.getCorrectAnswerStatement());
				else
					System.out.println("incorrect, les réponses acceptées sont : "+q.getCorrectAnswerStatement());
			}
			System.out.println();
		}
		System.out.print("FIN DU QUESTIONNAIRE ! ");
		if (nbrePoints == 0 || nbrePoints == 1)
			System.out.println("Vous avez eu "+nbrePoints+" point sur "+this.numberOfPointsQuestionnaire()+" possibles.");
		else
			System.out.println("Vous avez eu "+nbrePoints+" points sur "+this.numberOfPointsQuestionnaire()+" possibles.");
		
	}
	
	/**
	 * main method of the class
	 * @param args the parameters array
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
		try {
			Questionnaire q = new Questionnaire();
			q.initQuestionnaire(args[0]);
			q.ask();
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Usage : java -jar myquestionnaire.jar fileName");
			System.out.println("        - fileName = nom du fichier contenant les informations à extraire pour initialiser le questionnaire");
		}
	}

}
