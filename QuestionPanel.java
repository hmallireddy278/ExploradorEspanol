import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// QuestionPanel makes the frame and calls Question.
/// Hrithik Mallireddy wrote this whole class.
public class QuestionPanel extends JPanel
{
	private GamePanel gp;

	public QuestionPanel(GamePanel gpIn)
	{
		gp = gpIn;
	}
	
	// runner() makes the frame and adds Question.
	public void runner()
	{
		JFrame frame = new JFrame("Question");
		frame.setSize(500, 500);				
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE); 
		frame.setLocation(150, 150);
		frame.setResizable(false);
		Question question = new Question(gp); 		
		frame.getContentPane().add(question);		
		frame.setVisible(true);
	}
}

// Question makes the frame. Next week, we plan to add this panel as a pop-up,
// hence the smaller frame size and location at (150, 150). This panel will be
// opened when the user walks over a coin. It is nested in GamePanel so we can
// change the score variable without it resetting.
/// Hrithik Mallireddy wrote this whole class.
class Question extends JPanel
{
    // Various Scanner objects for text files for questions and answers
    private Scanner inFileNQ, inFileNA, inFilePresentQ, inFilePresentA;
    private Scanner inFilePreteriteQ, inFilePreteriteA, inFileIQ, inFileIA;
     
    private String[] nouns, present, preterite, imperfect; // arrays for the questions
    private String[] nounsAnswers, presentAnswers, preteriteAnswers, imperfectAnswers;
    // arrays for the answers
    private int questionNum, whichTense;  // ints that use Math.random to generate
    // a random question for the user
    private JTextField conjugationTF, definitionTF;  // text fields for the user
    // to enter answers
    private JLabel actualQuestion;  // JLabel that has contains the question word
    // or phrase
    private int level; // int that tracks the level for question generation
    private GamePanel gp;  // GamePanel obj to add to various scores
    private int counterD, counterC;  // counter vars that prevent getting
    // multiple points from one question

    public Question(GamePanel gpIn)  //  constructor
    {
        // D&I vars to default vals
        if(level == 0)
            level = 1;
        counterD = counterC = 0;
        conjugationTF = new JTextField("");
        gp = gpIn;

        // initializing arrays to needed length
        nouns = new String[20];
        nounsAnswers = new String[20];
        present = new String[16];
        presentAnswers = new String[32];
        preterite = new String[16];
        preteriteAnswers = new String[32];
        imperfect = new String[12];
        imperfectAnswers = new String[24];

        // opening the files and adding it to Scanner
        inFileNQ = openInputFile("NounQuestions.txt");
        inFileNA = openInputFile("NounAnswers.txt");
        inFilePresentQ = openInputFile("PresentQuestions.txt");
        inFilePresentA = openInputFile("PresentAnswers.txt");
        inFilePreteriteQ = openInputFile("PreteriteQuestions.txt");
        inFilePreteriteA = openInputFile("PreteriteAnswers.txt");
        inFileIQ = openInputFile("ImperfectQuestions.txt");
        inFileIA = openInputFile("ImperfectAnswers.txt");
    
        // adding the questions/answers to array
        nouns = addToArray(inFileNQ, 20);
        nounsAnswers = addToArray(inFileNA, 20);
        present = addToArray(inFilePresentQ, 16);
        presentAnswers = addToArray(inFilePresentA, 32);
        preterite = addToArray(inFilePreteriteQ, 16);
        preteriteAnswers = addToArray(inFilePreteriteA, 32);
        imperfect = addToArray(inFileIQ, 12);
        imperfectAnswers = addToArray(inFileIA, 24);
        
        // adding things to the panel
        setLayout(new BorderLayout());
        setBackground(Color.CYAN);
        
        // JLabel that says "Question" at the top
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        add(northPanel, BorderLayout.NORTH);
        JLabel questionName = new JLabel("Question");
        Font font = new Font("Serif", Font.BOLD, 30);
        questionName.setFont(font);
        northPanel.add(questionName);

        // adds the text fields for the user to enter answers
        // checks for if subject is noun or not, because you don't need conjugation
        // text field if the word is a noun
        JPanel southPanel = new JPanel();
        add(southPanel, BorderLayout.SOUTH);
        if(level == 1)
            whichTense = 1;
        else if(level == 2)
            whichTense = (int)(Math.random() * 2 + 1);
        else if(level == 3)
            whichTense = (int)(Math.random() * 3 + 1);
        else if(level == 4)
            whichTense = (int)(Math.random() * 4 + 1);
        if(whichTense != 1)
        {
            southPanel.setLayout(new GridLayout(1, 2));
            conjugationTF = new JTextField("Enter the conjugation here.");
            southPanel.add(conjugationTF);
        }
        else
            setLayout(new FlowLayout());
        definitionTF = new JTextField("Enter the definition here.");
        southPanel.add(definitionTF);

        // instructions and the question
        JPanel centerPanel = new JPanel();
        add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new GridLayout(2, 1));
        JTextArea instructions = new JTextArea(getInstructions());
        centerPanel.add(instructions);
        actualQuestion = new JLabel("");
        getQuestion();
        JPanel questionPanel = new JPanel();
        centerPanel.add(questionPanel);
        questionPanel.setLayout(new FlowLayout());
        questionPanel.add(actualQuestion);

        // adding handler class to the two text fields
        ConjugationHandler ch = new ConjugationHandler();
        DefinitionHandler dh = new DefinitionHandler();
        conjugationTF.addActionListener(ch);
        definitionTF.addActionListener(dh);
    }

    // openInputFile() opens the various .txt files for questions and answers and
    // adds it to a Scanner object.
    public Scanner openInputFile(String inFileName)
    {
        File inFile = new File(inFileName);
        Scanner input = null;
        try
        {
            input = new Scanner(inFile);
        }
        catch(IOException e)
        {
            System.err.println("\n\nERROR: Cannot find " + inFileName + " file.\n\n");
            e.printStackTrace();
        }
        return input;
    }
    
    // addToArray() recieves the Scanner and length of the array, and returns
    // the array with all of the words. It's easier to make it a method instead
    // of manually adding to each array in the constructor.
    public String[] addToArray(Scanner inFile, int arrayLength)
    {
        String[] array = new String[arrayLength];
        String line = "";
        int num = 0;
        while(inFile.hasNext())
        {
            line = inFile.nextLine();
            array[num] = line;
            num++;
        }
        return array;
    }
    
    // getInstructions() returns the instructions. It makes it easier to make a
    // method instead of writing it where the text area is initialized.
    public String getInstructions()
    {
        return "Please enter the definition if the word is a noun, or the " +
            "conjugation and definition\n if the word is a verb, and use the right " + 
            "verb tense as specified.";
    }

    public void setLevel(int levelIn)
    {
        this.level = levelIn;
    }

    // getQuestion() uses Math.random to randomize the subject (present, noun, etc)
    // and again to randomize which question in that .txt file. It adds the question
    // to a JLabel, actualQuestion.
    public void getQuestion()
    {
        int lengthOfArray = 0;
        questionNum = 0;
        if(whichTense == 1)  // noun
        {
            lengthOfArray = nouns.length - 1;
            questionNum = (int)(Math.random() * lengthOfArray);
            actualQuestion.setText(nouns[questionNum]);
        }
        else if(whichTense == 2) // present
        {
            lengthOfArray = present.length - 1;
            questionNum = (int)(Math.random() * lengthOfArray);
            actualQuestion.setText(present[questionNum]);
        }
        else if(whichTense == 3) // preterite
        {
            lengthOfArray = preterite.length - 1;
            questionNum = (int)(Math.random() * lengthOfArray);
            actualQuestion.setText(preterite[questionNum]);
        }
        else if(whichTense == 4) // imperfect
        {
            lengthOfArray = imperfect.length - 1;
            questionNum = (int)(Math.random() * lengthOfArray);
            actualQuestion.setText(imperfect[questionNum]);
        }
    }

    // ConjugationHandler is the handler class for the conjugation answer text field.
    // It checks what answer array to use and makes the text field green or red
    // based on if the answer is right or wrong.
    class ConjugationHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String answer = evt.getActionCommand();
            if(whichTense == 2)  // present
            {
                if(presentAnswers[questionNum * 2].equalsIgnoreCase(answer) && counterC != 1)
                {
                    conjugationTF.setBackground(Color.GREEN);
                    gp.addToScore();
                    gp.setRightPresent();
                    counterC++;
                }
                else
                    conjugationTF.setBackground(Color.RED);
                gp.setTotalPresent();
            }
            else if(whichTense == 3)  // preterite
            {
                if(preteriteAnswers[questionNum * 2].equalsIgnoreCase(answer) && counterC != 1)
                {
                    conjugationTF.setBackground(Color.GREEN);
                    gp.addToScore();
                    gp.setRightPreterite();
                    counterC++;
                }
                else
                    conjugationTF.setBackground(Color.RED);
                gp.setTotalPreterite();
            }
            else if(whichTense == 4)  // imperfect
            {
                if(imperfectAnswers[questionNum * 2].equalsIgnoreCase(answer) && counterC != 1)
                {
                    conjugationTF.setBackground(Color.GREEN);
                    gp.addToScore();
                    gp.setRightImperfect();
                    counterC++;
                }
                else
                    conjugationTF.setBackground(Color.RED);
                gp.setTotalImperfect();
            }
        }
    }

    // DefinitionHandler is the handler class for the definition answer text field.
    // It checks what answer array to use and makes the text field green or red
    // based on if the answer is right or wrong.
    class DefinitionHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String answer = evt.getActionCommand();
            if(whichTense == 1)  // nouns
            {
                if(nounsAnswers[questionNum].equalsIgnoreCase(answer) && counterD != 1)
                {
                    definitionTF.setBackground(Color.GREEN);
                    gp.addToScore();
                    gp.setRightNouns();
                    counterD++;
                }
                else
                    definitionTF.setBackground(Color.RED);
                gp.setTotalNouns();
            }
            else if(whichTense == 2)  // present
            {
                if(presentAnswers[questionNum * 2 + 1].equalsIgnoreCase(answer) && counterD != 1)
                {
                    definitionTF.setBackground(Color.GREEN);
                    gp.addToScore();
                    gp.setRightPresent();
                    counterD++;
                }
                else
                    definitionTF.setBackground(Color.RED);
                gp.setTotalPresent();
            }
            else if(whichTense == 3)  // preterite
            {
                if(preteriteAnswers[questionNum * 2 + 1].equalsIgnoreCase(answer) && counterD != 1)
                {
                    definitionTF.setBackground(Color.GREEN);
                    gp.addToScore();
                    gp.setRightPreterite();
                    counterD++;
                }
                else
                    definitionTF.setBackground(Color.RED);
                gp.setTotalPreterite();
            }
            else if(whichTense == 4)  // imperfect
            {
                if(imperfectAnswers[questionNum * 2 + 1].equalsIgnoreCase(answer) && counterD != 1)
                {
                    definitionTF.setBackground(Color.GREEN);
                    gp.addToScore();
                    gp.setRightImperfect();
                    counterD++;
                }
                else
                    definitionTF.setBackground(Color.RED);
                gp.setTotalImperfect();
            }
        }
    }
}