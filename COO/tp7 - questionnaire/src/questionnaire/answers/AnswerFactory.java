package questionnaire.answers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/** Factory serving to build an Answer object */
public class AnswerFactory {

	/**
	 * build an Answer object, given the parameters
	 * @param className the class name of the created object
	 * @param answerText an answer text
	 * @return build the <code> className </code> object, with <code> answerText </code> as correct answer
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @see questionnaire.answers.Answer
	 */
	public static Answer<?> buildAnswer(String className, String answerText) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> c = Class.forName("questionnaire.answers."+className);
		Constructor<?> constr = c.getConstructor(String.class);
		return (Answer<?>) constr.newInstance(answerText);
	}

}
