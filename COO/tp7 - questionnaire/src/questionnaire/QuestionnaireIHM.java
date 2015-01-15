package questionnaire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import questionnaire.panels.AnswerPanel;

/** Class representing an IHM for the class Questionnaire */
public class QuestionnaireIHM {
	
	// the frame
	private JFrame frame;
	
	// the questionnaire which an IHM has to be created
	private Questionnaire questionnaire;
	
	// the list of the panels (a panel for every question)
	private List<JPanel> listPanels;
	
	// the points earned by the user after answering to the questionnaire
	private int pointsUser;
	
	// the summary of the questionnaire (which will be shown after validation of the questionnaire)
	private String summary;
	
	/**
	 * constructor for the instances of the class
	 * @param fileName which we extract the informations to initialize the questionnaire and create the IHM
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalArgumentException 
	 */
	public QuestionnaireIHM(String fileName) throws IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		this.frame = new JFrame("Questionnaire");
		this.questionnaire = new Questionnaire();
		this.questionnaire.initQuestionnaire(fileName);
		this.listPanels = new LinkedList<JPanel>();
		this.pointsUser = 0;
		this.summary = "";
	}
	
	/**
	 * create the IHM for the file which name is fileName
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalArgumentException 
	 */
	public void createIHM() throws IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		this.frame.setSize(900,450);
		this.frame.setLocationRelativeTo(null);
		this.frame.setLayout(new BorderLayout());
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel questionnaireContainer = new JPanel();
		questionnaireContainer.setLayout(new GridLayout(this.questionnaire.numberOfQuestions(),1));
		for (Question q : this.questionnaire.getQuestions()) {
			JPanel questionPanel = q.createMyQuestionPanel(this.questionnaire.getQuestions().indexOf(q)+1);
			AnswerPanel a = q.getAnswer().createMyAnswerPanel();
			JPanel answerPanel = a.getPanel();
			JPanel questionContainer = new JPanel();
			questionContainer.setLayout(new BorderLayout());
			questionContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			questionContainer.add(questionPanel,BorderLayout.WEST);
			questionContainer.add(answerPanel,BorderLayout.EAST);
			this.listPanels.add(answerPanel);
			questionnaireContainer.add(questionContainer);
		}
		this.frame.add(questionnaireContainer,BorderLayout.CENTER);
		JPanel validButtonContainer = new JPanel();
		JButton validButton = new JButton("Valider");
		validButton.addActionListener(new ValidationListener());
		validButtonContainer.add(validButton);
		this.frame.add(validButton,BorderLayout.SOUTH);
		this.frame.add(new JScrollPane(questionnaireContainer));
	}
	
	
	/**
	 * show the IHM (set the frame's visibility)
	 */
	public void showIHM() {
		this.frame.setVisible(true);
	}
	
	/**
	 * update the summary message by adding the answers' statements (user's and the correct answer) and the points earned to it
	 * @param q the question asked to the user
	 * @param s the answer given by the user
	 */
	public void updateSummary(Question q,String s) {
		String answerStatement = "";
		if (s.trim().equals("")) {
			answerStatement = "Aucune réponse";
		}
		else {
			answerStatement = s;
		}
		this.summary += (this.questionnaire.getQuestions().indexOf(q) + 1)+"/ "+q.getStatement()+
						"\nVOTRE RÉPONSE : "+answerStatement+" | BONNE RÉPONSE : "+q.getCorrectAnswerStatement();
		if (q.getAnswer().isCorrect(s)) {
			if (q.getNumberOfPoints() == 1) {
				this.summary += "  (+"+q.getNumberOfPoints()+" point)\n\n";
			}
			else {
				this.summary += "  (+"+q.getNumberOfPoints()+" points)\n\n";
			}
			this.pointsUser += q.getNumberOfPoints();
		}
		else {
			this.summary += "  (0 point)"+"\n\n";
		}
	}
	
	/**
	 * treats the answer given by the user
	 * @param tabComp the component's array (where we extract the answer)
	 * @param q the question asked to the user
	 */
	public void treatAnAnswer(Component[] tabComp,Question q) {
		if (tabComp[0] instanceof JTextField) {
			JTextField jtf = (JTextField) tabComp[0];
			this.updateSummary(q,jtf.getText());
		}
		else {
			if (tabComp[0] instanceof JSpinner) {
				JSpinner js = (JSpinner) tabComp[0];
				Integer n = (Integer) js.getValue();
				this.updateSummary(q,n.toString());
			}
			else {
				int i = 0;
				while (i < tabComp.length) {
					JRadioButton jbr = (JRadioButton) tabComp[i];
					if (jbr.isSelected()) {
						this.updateSummary(q,jbr.getText());
						return;
					}
					i++;
				}
				this.updateSummary(q,"");
			}
		}
	}
	
	/**
	 * treat all the answers given by the user
	 */
	public void treatQuestionnaire() {
		Iterator<JPanel> itPanels = this.listPanels.iterator();
		Iterator<Question> itQuestions = this.questionnaire.getQuestions().iterator();
		while (itPanels.hasNext()) {
			JPanel jp = itPanels.next();
			Question q = itQuestions.next();
			Component[] tabComp = jp.getComponents();
			this.treatAnAnswer(tabComp,q);
		}
		if (this.pointsUser == 0 || this.pointsUser == 1)
			this.summary += "\nVous avez eu "+this.pointsUser+" point sur "+this.questionnaire.numberOfPointsQuestionnaire()+" possibles.";
		else
			this.summary += "\nVous avez eu "+this.pointsUser+" points sur "+this.questionnaire.numberOfPointsQuestionnaire()+" possibles.";
	}
	
	/**
	 * main method of the class
	 * @param args the parameters' array
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void main(String[] args) throws IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		try {
			QuestionnaireIHM qihm = new QuestionnaireIHM("question_tolkien_2.txt");
			qihm.createIHM();
			qihm.showIHM();
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Usage : java -jar myquestionnaire-ihm.jar fileName");
			System.out.println("		- fileName = nom du fichier contenant les informations à extraire pour initialiser le questionnaire et créer l'IHM");
		}
	}
	
	
	/** Internal class representing the instructions to do following a click on the button "valider" */
	private class ValidationListener implements ActionListener {
		
		/**
		 * the instructions to do following a click on the button "valider"
		 * e the event corresponding to the click on the button "valider"
		 */
		public void actionPerformed(ActionEvent e) {
			QuestionnaireIHM.this.frame.dispose();
			QuestionnaireIHM.this.treatQuestionnaire();
			JOptionPane.showMessageDialog(null, "\n"+QuestionnaireIHM.this.summary,"Résultat du questionnaire",JOptionPane.INFORMATION_MESSAGE);
			QuestionnaireIHM.this.summary = "";
		}
		
	}

}
