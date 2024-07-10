// Prathik Kumar and Hrithik Mallireddy
// 4/24/2023
// Game.java (Explorador Español)
// Working on:
// Week 1: Basic Start, Instructions, and Game panels with navigation CardLayout and null layout complete
// Navigation between panels are all working well, and we need to fill in the template we have laid out
// Hrithik was absent for most of Week 1 so Prathik did most of the program so far.
// Practicing: ImageIO, Components(JButtons, MenuBar), and different layouts, like border,
// grid, and flow layout. Practicing using handler classes as well

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyAdapter;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.awt.event.KeyEvent;

// Game holds the main panel and calls the panel class, CardHolder.
/// Prathik Kumar wrote this class.
public class Game
{
    public static void main(String[] args)
    {
        Game ss = new Game();
        ss.runIt();
    }

    public void runIt()
    {
        JFrame frame = new JFrame("Explorador Español");
        frame.setSize(750, 700);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        CardHolder ch = new CardHolder();
        frame.getContentPane().add(ch);
        frame.setVisible(true);
    }
}

// CardHolder calls all the other constructors and passes the CardLayout
// and an instance of this class so that we can manuever to different panels
// from every class in this program.
/// Prathik Kumar wrote this class.
class CardHolder extends JPanel
{
    // Calls all the other classes/panels, and passes the card layout object
    // and an instance of the class so every class can move between the panels
    public CardHolder()
    {
        CardLayout cards = new CardLayout();
        setLayout(cards);

        HomeButton hButton = new HomeButton(cards, this);
        HomeBar hBar = new HomeBar(cards, this);
        Starting st = new Starting(cards, this);
        Setting set = new Setting(cards, this);
        Lessons less = new Lessons(cards, this, hButton, hBar);
        Instructions inst = new Instructions(cards, this);
        Leader lead = new Leader(cards, this);
        GamePanel gamePanel = new GamePanel(cards, this);
        ScorePanel scorePanel = new ScorePanel(cards, this);
        gamePanel.setGameObjects(); //This is needed to set all assets/objects for game exeuction.
        Vocab vb = new Vocab(cards, this);
        Preterite pr = new Preterite(cards, this);
        Present pre = new Present(cards, this);
        Imperfect imp = new Imperfect(cards, this);

        add(st, "Home");
        add(set, "Settings");
        add(less, "Lessons");
        add(inst, "Instructions");
        add(lead, "Leaderboard");
        add(gamePanel, "Gameplay");
        add(scorePanel, "Score");
        add(vb, "Vocab");
        add(pr, "Preterite");
        add(pre, "Present");
        add(imp, "Imperfect");
    }
}

// Starting contains the home panel of our game. This panel contains all the
// other buttons that lead to the other panels in the program (settings, lessons
// instructions, leaderboard, play, quit).
/// Prathik Kumar wrote most of the constructor but Hrithik Mallireddy wrote the
/// ImageIO method and paintComponent(), as well as the fontChosen and fontSize
/// logic.
class Starting extends JPanel
{
    private CardLayout cards; // holds card layout
    private CardHolder panelHolder; // used to swap between panels in card layout
    private Image background; // background image

    private JTextField nameField;  // users enters name
    private JFrame nameFrame;  // frame that holds the popup
    private static String playerName;  // stores player name
    // has to be static
    private GameConstants gameConstants; // obj to get constans

    private String fontChosen; // font that is set in Settings
    private int fontSize; // size that is set in Settings

    // FVs so we can change the font and size in multiple methods
    JButton setting, lessons, instruc, lBoard, play, quit;
    JLabel title, secondTitle, nameLabel;

    // Creates a class with 6 buttons, where 5 of them go to different
    // panels and one of them closes the program. Also creates labels
    // which will serve as the title of the game
    public Starting (CardLayout cardsIn, CardHolder panelHolderIn)
    {
        cards = cardsIn;
        panelHolder = panelHolderIn;
        fontSize = 15;
        setLayout(null);
        gameConstants = new GameConstants();
        setting = new JButton();
        lessons = new JButton();
        instruc = new JButton();
        lBoard = new JButton();
        play = new JButton();
        quit = new JButton();
        title = new JLabel();
        secondTitle = new JLabel();
        nameLabel = new JLabel();

        // Creating new font
        Font font = new Font(fontChosen, Font.BOLD, fontSize + 5);
        // Getting the image
        background = gameConstants.getImage("background.jpg");

        // Creating buttons
        setting = new JButton("Settings");
        lessons = new JButton("Lessons");
        instruc = new JButton("Instructions");
        lBoard = new JButton("Leaderboard");
        play = new JButton("Play");
        quit = new JButton("Quit");

        // Setting font
        setting.setFont(font);
        lessons.setFont(font);
        instruc.setFont(font);
        lBoard.setFont(font);
        play.setFont(font);
        quit.setFont(font);

        // Setting bounds and adding buttons
        setting.setBounds(15, 400, 235, 120);
        instruc.setBounds(255, 400, 235, 120);
        lessons.setBounds(15, 520, 235, 120);
        play.setBounds(255, 520, 235, 120);
        lBoard.setBounds(495, 400, 235, 120);
        quit.setBounds(495, 520, 235, 120);

        // Adding action listeners for each button and adding buttons
        ButtonHandler bh = new ButtonHandler();
        setting.addActionListener(bh);
        instruc.addActionListener(bh);
        lessons.addActionListener(bh);
        play.addActionListener(bh);
        lBoard.addActionListener(bh);
        quit.addActionListener(bh);
        add(setting);
        add(instruc);
        add(lessons);
        add(play);
        add(lBoard);
        add(quit);

        // font used for title labels
        Font titleFont = new Font(fontChosen, Font.BOLD, fontSize + 25);

        // Label for the explorador part of the title
        title = new JLabel("Explorador");
        title.setFont(titleFont);
        title.setForeground(Color.YELLOW);
        title.setBounds(252, 150, 246, 100);
        add(title);

        // Label for the espanol part
        secondTitle = new JLabel("Español");
        secondTitle.setFont(titleFont);
        secondTitle.setForeground(Color.YELLOW);
        secondTitle.setBounds(287, 222, 176, 60);
        add(secondTitle);
        nameFrame = new JFrame("NameFrame");
    }

    // This method is drawing the image
    /// Hrithik Mallireddy wrote this method.
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, 750, 700, this);

    }

    // Button Handler is the handler class for the six buttons on the
    // home screen.
    class ButtonHandler extends JPanel implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String command = evt.getActionCommand();
            if (command.equals("Settings"))
                cards.show(panelHolder, "Settings");
            else if (command.equals("Lessons"))
                cards.show(panelHolder, "Lessons");
            else if (command.equals("Instructions"))
                cards.show(panelHolder, "Instructions");
            else if (command.equals("Play"))
                namePrompt();
            else if (command.equals("Leaderboard"))
                cards.show(panelHolder, "Leaderboard");
            else if (command.equals("Quit"))
                System.exit(0);
        }
    }

    //This method is to prompt the user to enter their name before starting the game
    /// Prathik Kumar wrote this method.
    private void namePrompt()
    {
        //JFrame for the name prompt message
        nameFrame.setSize(400, 400);
        nameFrame.setLocationRelativeTo(null);

        //JPanel for the message and set layout to FlowLayout
        JPanel namePanel = new JPanel(new FlowLayout());
        Font font = new Font(fontChosen, Font.BOLD, fontSize + 1);
        nameLabel = new JLabel("Please enter your name to start the game.");
        nameLabel.setFont(font);
        namePanel.add(nameLabel);
        namePanel.setBackground(new Color(255, 204, 153)); // Light Orange

        // JPanel for the text field and set layout to FlowLayout
        JPanel textFieldPanel = new JPanel(new FlowLayout());
        nameField = new JTextField(30);
        textFieldPanel.add(nameField);
        textFieldPanel.setBackground(Color.CYAN);

        // Create a JPanel for the center panel and set layout to BorderLayout
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(namePanel, BorderLayout.NORTH);
        centerPanel.add(textFieldPanel, BorderLayout.CENTER);

        // Add the center panel to the frame
        nameFrame.add(centerPanel);
        addTextFieldKeyListener();
        // Set the frame visible
        nameFrame.setVisible(true);
    }

    //This to get name, write to scores.txt and also allow user to start game when "ENTER" key is pressed
    //Prathik Kumar wrote this method.
    private void addTextFieldKeyListener()
    {
        nameField.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent evt) {};

            public void keyPressed(KeyEvent evt)
            {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    playerName = nameField.getText();
                    cards.show(panelHolder, "Gameplay");
                    nameField.setText("");
                    nameFrame.dispose(); // Close the nameFrame
                }
            }

            @Override
            public void keyReleased(KeyEvent evt)
            {

            }
        });
    }

    //Getter method to give access to player name and append time
    public static String getPlayerName()
    {
        return playerName;
    }

    // setter methods that are called by Setting
    /// Hrithik Mallireddy did these.
    public void setFontChosen(String fontChosenIn)
    {
        fontChosen = fontChosenIn;
    }

    public void setFontSize(int fontSizeIn)
    {
        fontSize = fontSizeIn;
    }

    // changes font and size to fontChosen and fontSize that were chosen
    // in Settings
    /// Hrithik Mallireddy did this.
    public void changeFontandSize()
    {
        Font font = new Font(fontChosen, Font.BOLD, fontSize + 5);
        setting.setFont(font);
        lessons.setFont(font);
        instruc.setFont(font);
        lBoard.setFont(font);
        play.setFont(font);
        quit.setFont(font);

        Font titleFont = new Font(fontChosen, Font.BOLD, fontSize + 25);
        title.setFont(titleFont);
        secondTitle.setFont(titleFont);

        Font font1 = new Font(fontChosen, Font.BOLD, fontSize + 1);
        nameLabel.setFont(font1);
    }
}

// Creates menu bar containing the menu items home and settings
// In a sep class because it is more convenient to just call this class, and have the
// menu bar in all the classes
/// Prathik Kumar wrote this class.
class HomeBar extends JPanel
{
    private CardLayout cards; //holds card layout
    private CardHolder panelHolder; //used to swap between panels in card layout

    // Creates a menu bar with items home and settings
    public HomeBar(CardLayout cardsIn, CardHolder panelHolderIn)
    {
        cards = cardsIn;
        panelHolder = panelHolderIn;

        JMenuBar bar = new JMenuBar();
        Font font = new Font("Georgia", Font.BOLD, 22);
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        setLayout(new BorderLayout());
        JMenu name = new JMenu("Menu");
        JMenuItem home = new JMenuItem("Home");
        JMenuItem settings = new JMenuItem("Settings");
        name.add(home);
        home.setFont(font);
        name.setFont(font);
        settings.setFont(font);
        name.add(settings);
        bar.add(name);
        MenuHandler mh = new MenuHandler();
        home.addActionListener(mh);
        settings.addActionListener(mh);
        menuPanel.add(bar);
        menuPanel.setBackground(Color.YELLOW);
        add(menuPanel, BorderLayout.CENTER);
    }

    // Used to check which menu item the user clicked
    /// Prathik Kumar wrote this method.
    class MenuHandler extends JPanel implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String command = evt.getActionCommand();
            if (command.equals("Home"))
                cards.show(panelHolder, "Home");
            else if (command.equals("Settings"))
                cards.show(panelHolder, "Settings");
        }
    }
}

// Creates a home button, used for convience so we can call this class
// instead of having to make a new home button in every panel
/// Prathik Kumar wrote this class
class HomeButton extends JPanel
{
    private CardLayout cards; //holds card layout
    private CardHolder panelHolder; //used to swap between panels in card layout

    // Creates a home button
    public HomeButton(CardLayout cardsIn, CardHolder panelHolderIn)
    {
        cards = cardsIn;
        panelHolder = panelHolderIn;
        JButton home = new JButton("Home");
        ButtonHandler bh = new ButtonHandler();
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        setLayout(new BorderLayout());
        home.addActionListener(bh);
        buttonPanel.add(home);
        buttonPanel.setBackground(Color.YELLOW);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    // Used to go to home panel if user clicked home button
    /// Prathik Kumar wrote this method
    class ButtonHandler extends JPanel implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String command = evt.getActionCommand();
            cards.show(panelHolder, "Home");
        }
    }
}

// Class settings contains a slider determining font size, and radio buttons
// determining which font the user can use
/// Hrithik Mallireddy wrote this class.
class Setting extends JPanel implements ActionListener, ChangeListener
{
    private String fontChosen;  // String that tracks font chosen
    private int fontSize;  // int that tracks font size
    private CardLayout cards; //holds card layout
    private CardHolder panelHolder; //used to swap between panels in card layout
    private HomeButton hButton; // button thats sent to Lessons
    private HomeBar hBar; // bar thats sent to lessons
    JLabel settingLabel;  // FV so it can be changed in changeFontandSize()

    public Setting(CardLayout cardsIn, CardHolder panelHolderIn)
    {
        cards = cardsIn;
        panelHolder = panelHolderIn;
        setLayout(new BorderLayout());
        hBar = new HomeBar(cards, panelHolder);
        add(hBar, BorderLayout.WEST);
        settingLabel = new JLabel();

        fontChosen = "Georgia";
        fontSize = 15;
        callAllMethods();

        settingLabel = new JLabel("Settings");
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Font font = new Font(fontChosen, Font.BOLD, fontSize + 30);
        settingLabel.setFont(font);
        labelPanel.add(settingLabel);
        labelPanel.setBackground(Color.YELLOW);
        add(labelPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        add(centerPanel, BorderLayout.CENTER);
        JPanel centerRightPanel = new JPanel(new GridLayout(5, 1));
        JSlider fontSlider = new JSlider(JSlider.VERTICAL, 10, 30, 15);
        fontSlider.setMajorTickSpacing(5);
        fontSlider.setPaintTicks(true);
        fontSlider.setLabelTable(fontSlider.createStandardLabels(5));
        fontSlider.setPaintLabels(true);
        centerPanel.add(fontSlider);
        centerPanel.add(centerRightPanel);

        JLabel instructions = new JLabel("Choose a font");
        centerRightPanel.add(instructions);

        JRadioButton arial = new JRadioButton("Arial");
        JRadioButton timesNewRoman = new JRadioButton("Times New Roman");
        JRadioButton georgia = new JRadioButton("Georgia");
        JRadioButton serif = new JRadioButton("Serif");

        ButtonGroup buttons = new ButtonGroup();
        buttons.add(arial);
        buttons.add(timesNewRoman);
        buttons.add(georgia);
        buttons.add(serif);

        centerRightPanel.add(arial);
        centerRightPanel.add(timesNewRoman);
        centerRightPanel.add(georgia);
        centerRightPanel.add(serif);

        arial.addActionListener(this);
        timesNewRoman.addActionListener(this);
        georgia.addActionListener(this);
        serif.addActionListener(this);
        fontSlider.addChangeListener(this);
    }

    public void actionPerformed(ActionEvent evt)
    {
        fontChosen = evt.getActionCommand();
        setFontChosen(fontChosen);
        callAllMethods();
    }

    public void stateChanged(ChangeEvent evt)
    {
        JSlider slider = (JSlider)evt.getSource();
        fontSize = slider.getValue();
        setFontSize(fontSize);
        callAllMethods();
    }

    // setter methods that are called by Settings
    /// Hrithik Mallireddy did this.
    public void setFontChosen(String fontChosenIn)
    {
        fontChosen = fontChosenIn;
    }

    public void setFontSize(int fontSizeIn)
    {
        fontSize = fontSizeIn;
    }

    // changes font and size to fontChosen and fontSize that were chosen
    // in Settings
    /// Hrithik Mallireddy did this.
    public void changeFontandSize()
    {
        Font font = new Font(fontChosen, Font.BOLD, fontSize + 30);
        settingLabel.setFont(font);
    }

    // Sets the fontChosen and fontSize in all classes and calls changeFontAndSize()
    // in all classes. Calls constructors at various points to run them.
    public void callAllMethods()
    {
        setFontChosen(fontChosen);
        setFontSize(fontSize);
        changeFontandSize();
        Starting st = new Starting(cards, panelHolder);
        st.setFontChosen(fontChosen);
        st.setFontSize(fontSize);
        st.changeFontandSize();
        Instructions inst = new Instructions(cards, panelHolder);
        inst.setFontChosen(fontChosen);
        inst.setFontSize(fontSize);
        inst.changeFontandSize();
        Leader lead = new Leader(cards, panelHolder);
        lead.setFontChosen(fontChosen);
        lead.setFontSize(fontSize);
        lead.changeFontandSize();
        Vocab vb = new Vocab(cards, panelHolder);
        vb.setFontChosen(fontChosen);
        vb.setFontSize(fontSize);
        vb.changeFontandSize();
        Preterite pr = new Preterite(cards, panelHolder);
        pr.setFontChosen(fontChosen);
        pr.setFontSize(fontSize);
        pr.changeFontandSize();
        Present pre = new Present(cards, panelHolder);
        pre.setFontChosen(fontChosen);
        pre.setFontSize(fontSize);
        pre.changeFontandSize();
        Imperfect imp = new Imperfect(cards, panelHolder);
        imp.setFontChosen(fontChosen);
        imp.setFontSize(fontSize);
        imp.changeFontandSize();
        ScorePanel sp = new ScorePanel(cards, panelHolder);
        sp.setFontChosen(fontChosen);
        sp.setFontSize(fontSize);
    }
}

// Lessons class holding the perterite, present tense, imperfect, and vocab buttons
// these buttons lead to different panels that contain text areas teaching different
// concepts
/// Prathik Kumar wrote this class.
class Lessons extends JPanel
{
    private CardLayout cards; //holds card layout
    private CardHolder panelHolder; //used to swap between panels in card layout
    private HomeButton hButton; //used to store the home button obj, so homebutton can be added to this panel
    private String fontChosen; // font that is set in Settings
    private int fontSize; // size that is set in Settings

    // This page holds four different buttons for now that lead to different subtopics of the
    // concept we are trying to teach. Each button leads to a different panel which
    // will have info on the concept
    public Lessons (CardLayout cardsIn, CardHolder panelHolderIn, HomeButton hButtonIn, HomeBar hBarIn)
    {
        setBackground(Color.YELLOW);
        setLayout(new BorderLayout());
        cards = cardsIn;
        panelHolder = panelHolderIn;
        hButton = hButtonIn;
        setBackground(Color.YELLOW);
        add(hButton, BorderLayout.SOUTH);
        Font font = new Font(fontChosen, Font.BOLD, fontSize + 10);
        JLabel lessonHead = new JLabel("Lessons");
        JPanel centered = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lessonHead.setFont(font);
        centered.add(lessonHead);
        centered.setBackground(Color.YELLOW);
        add(centered, BorderLayout.NORTH);

        JPanel lessonButtons = new JPanel(new GridLayout(2, 2, 30, 30));
        JButton vocab = new JButton("Vocab");
        JButton preterite = new JButton("Preterite");
        JButton imperfect = new JButton("Imperfect");
        JButton present = new JButton("Present");
        vocab.setFont(font);
        preterite.setFont(font);
        imperfect.setFont(font);
        present.setFont(font);
        ButtonHandler bh = new ButtonHandler();
        vocab.addActionListener(bh);
        preterite.addActionListener(bh);
        imperfect.addActionListener(bh);
        present.addActionListener(bh);

        lessonButtons.add(vocab);
        lessonButtons.add(preterite);
        lessonButtons.add(imperfect);
        lessonButtons.add(present);
        lessonButtons.setBackground(Color.YELLOW);
        add(lessonButtons, BorderLayout.CENTER);
    }

    // Handles the buttons, checks which button was clicked
    /// Prathik Kumar wrote this method
    class ButtonHandler extends JPanel implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String command = evt.getActionCommand();
            if (command.equals("Vocab"))
                cards.show(panelHolder, "Vocab");
            else if (command.equals("Preterite"))
                cards.show(panelHolder, "Preterite");
            else if (command.equals("Imperfect"))
                cards.show(panelHolder, "Imperfect");
            else if (command.equals("Present"))
                cards.show(panelHolder, "Present");
        }
    }

    // setter methods that are called by Settings  
    /// Hrithik Mallireddy did this.
    public void setFontChosen(String fontChosenIn)
    {
        fontChosen = fontChosenIn;
    }

    public void setFontSize(int fontSizeIn)
    {
        fontSize = fontSizeIn;
    }
}
// This is a lesson panel that will hold a text area with the lesson info
/// Prathik Kumar wrote this class
class Vocab extends JPanel
{
    private CardLayout cards; //holds card layout
    private CardHolder panelHolder; //used to swap between panels in card layout
    private String fontChosen; // font that is set in Settings
    private int fontSize; // size that is set in Settings
    private JLabel vocLabel;  // FV so it can be changed

    // Generic lesson page with a text area in the middle that has to be filled in later
    // We have a back to lessons page button as well
    public Vocab(CardLayout cardsIn, CardHolder panelHolderIn)
    {
        cards = cardsIn;
        panelHolder = panelHolderIn;
        setBackground(Color.YELLOW);
        vocLabel = new JLabel("Vocab");
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Font font = new Font(fontChosen, Font.BOLD, fontSize + 30);
        vocLabel.setFont(font);
        labelPanel.add(vocLabel);
        labelPanel.setBackground(Color.YELLOW);
        add(labelPanel, BorderLayout.NORTH);

        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Information info = new Information();
        info.setVocab();
        String vocabIn = info.getVocab();
        JTextArea ta = new JTextArea(vocabIn, 30, 50);
        JScrollPane sp = new JScrollPane();
        ta.add(sp);
        textPanel.setBackground(Color.YELLOW);
        textPanel.add(ta);
        add(textPanel, BorderLayout.CENTER);

        JButton lesson = new JButton("Back to Lessons");
        ButtonHandler bh = new ButtonHandler();
        lesson.addActionListener(bh);

        add(lesson, BorderLayout.SOUTH);
    }

    // setter methods that are called by Settings
    /// Hrithik Mallireddy did this.
    public void setFontChosen(String fontChosenIn)
    {
        fontChosen = fontChosenIn;
    }

    public void setFontSize(int fontSizeIn)
    {
        fontSize = fontSizeIn;
    }

    // changes font and size to fontChosen and fontSize that were chosen
    // in Settings
    /// Hrithik Mallireddy did this.
    public void changeFontandSize()
    {
        Font font = new Font(fontChosen, Font.BOLD, fontSize + 30);
        vocLabel.setFont(font);
    }

    // Checks if the lesson page button was clicked
    /// Prathik Kumar wrote this method
    class ButtonHandler extends JPanel implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String command = evt.getActionCommand();
            if (command.equalsIgnoreCase("Back to Lessons"))
            {
                cards.show(panelHolder, "Lessons");
            }
        }
    }
}

// This is a lesson panel that will hold a text area with the lesson info
// Lesson is about the Preterite tense in spanish, we will show patterns needed for it
/// Prathik Kumar wrote this class.
class Preterite extends JPanel
{
    private CardLayout cards; //holds card layout
    private CardHolder panelHolder; //used to swap between panels in card layout
    private String fontChosen; // font that is set in Settings
    private int fontSize; // size that is set in Settings
    private JLabel pretLabel; // FV so it can be used in multiple methods

    // Generic lesson page with a text area in the middle that has to be filled in later
    // We have a back to lessons page button as well
    public Preterite(CardLayout cardsIn, CardHolder panelHolderIn)
    {
        cards = cardsIn;
        panelHolder = panelHolderIn;
        setBackground(Color.YELLOW);
        pretLabel = new JLabel("Preterite");
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Font font = new Font(fontChosen, Font.BOLD, fontSize + 30);
        pretLabel.setFont(font);
        labelPanel.add(pretLabel);
        labelPanel.setBackground(Color.YELLOW);
        add(labelPanel, BorderLayout.NORTH);

        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Information info = new Information();
        info.setPreterite();
        String preterite = info.getPreterite();
        JTextArea ta = new JTextArea(preterite, 30, 50);
        JScrollPane sp = new JScrollPane();
        ta.add(sp);
        textPanel.setBackground(Color.YELLOW);
        textPanel.add(ta);
        add(textPanel, BorderLayout.CENTER);

        JButton lesson = new JButton("Back to Lessons");
        ButtonHandler bh = new ButtonHandler();
        lesson.addActionListener(bh);

        add(lesson, BorderLayout.SOUTH);
    }

    // Checks if the lesson page button was clicked
    /// Prathik Kumar wrote this method
    class ButtonHandler extends JPanel implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String command = evt.getActionCommand();
            if (command.equalsIgnoreCase("Back to Lessons"))
            {
                cards.show(panelHolder, "Lessons");
            }
        }
    }

    // setter methods that are called by Settings
    /// Hrithik Mallireddy did this.
    public void setFontChosen(String fontChosenIn)
    {
        fontChosen = fontChosenIn;
    }

    public void setFontSize(int fontSizeIn)
    {
        fontSize = fontSizeIn;
    }

    // changes font and size to fontChosen and fontSize that were chosen
    // in Settings
    /// Hrithik Mallireddy did this.
    public void changeFontandSize()
    {
        Font font = new Font(fontChosen, Font.BOLD, fontSize + 30);
        pretLabel.setFont(font);
    }
}

// This is a lesson panel that will hold a text area with the lesson info
// Lesson is about Imperfect tense in spanish, we will show patterns needed for it
/// Prathik Kumar wrote this class.
class Imperfect extends JPanel
{
    private CardLayout cards; //holds card layout
    private CardHolder panelHolder; //used to swap between panels in card layout
    private String fontChosen; // font that is set in Settings
    private int fontSize; // size that is set in Settings
    private JLabel impLabel;  // FV so it can be used in multiple methods

    // Generic lesson page with a text area in the middle that has to be filled in later
    // We have a back to lessons page button as well
    public Imperfect(CardLayout cardsIn, CardHolder panelHolderIn)
    {
        cards = cardsIn;
        panelHolder = panelHolderIn;
        setBackground(Color.YELLOW);
        impLabel = new JLabel("Imperfect");
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Font font = new Font(fontChosen, Font.BOLD, fontSize + 30);
        impLabel.setFont(font);
        labelPanel.add(impLabel);
        labelPanel.setBackground(Color.YELLOW);
        add(labelPanel, BorderLayout.NORTH);

        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Information info = new Information();
        info.setImperfect();
        String imperfect = info.getImperfect();
        JTextArea ta = new JTextArea(imperfect, 30, 50);
        JScrollPane sp = new JScrollPane();
        ta.add(sp);
        textPanel.setBackground(Color.YELLOW);
        textPanel.add(ta);
        add(textPanel, BorderLayout.CENTER);

        JButton lesson = new JButton("Back to Lessons");
        ButtonHandler bh = new ButtonHandler();
        lesson.addActionListener(bh);

        add(lesson, BorderLayout.SOUTH);
    }

    // Checks if the lesson page button was clicked
    /// Prathik Kumar wrote this method
    class ButtonHandler extends JPanel implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String command = evt.getActionCommand();
            if (command.equalsIgnoreCase("Back to Lessons"))
            {
                cards.show(panelHolder, "Lessons");
            }
        }
    }

    // setter methods that are called by Settings
    /// Hrithik Mallireddy did this.
    public void setFontChosen(String fontChosenIn)
    {
        fontChosen = fontChosenIn;
    }

    public void setFontSize(int fontSizeIn)
    {
        fontSize = fontSizeIn;
    }

    // changes font and size to fontChosen and fontSize that were chosen
    // in Settings
    /// Hrithik Mallireddy did this.
    public void changeFontandSize()
    {
        Font font = new Font(fontChosen, Font.BOLD, fontSize + 30);
        impLabel.setFont(font);
    }
}

// This is a lesson panel that will hold a text area with the lesson info
// Lesson is about Present tense in spanish, we will show patterns needed for it
/// Prathik Kumar wrote this class.
class Present extends JPanel
{
    private CardLayout cards; //holds card layout
    private CardHolder panelHolder; //used to swap between panels in card layout
    private String fontChosen; // font that is set in Settings
    private int fontSize; // size that is set in Settings
    private JLabel presLabel; // FV so it can be changed in myltiple methods

    // Generic lesson page with a text area in the middle that has to be filled in later
    // We have a back to lessons page button as well
    public Present(CardLayout cardsIn, CardHolder panelHolderIn)
    {
        cards = cardsIn;
        panelHolder = panelHolderIn;
        setBackground(Color.YELLOW);
        presLabel = new JLabel("Present");
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Font font = new Font(fontChosen, Font.BOLD, fontSize + 30);
        presLabel.setFont(font);
        labelPanel.add(presLabel);
        labelPanel.setBackground(Color.YELLOW);
        add(labelPanel, BorderLayout.NORTH);

        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Information info = new Information();
        info.setPresent();
        String present = info.getPresent();
        JTextArea ta = new JTextArea(present, 30, 50);
        JScrollPane sp = new JScrollPane();
        ta.add(sp);
        textPanel.setBackground(Color.YELLOW);
        textPanel.add(ta);
        add(textPanel, BorderLayout.CENTER);

        JButton lesson = new JButton("Back to Lessons");
        ButtonHandler bh = new ButtonHandler();
        lesson.addActionListener(bh);

        add(lesson, BorderLayout.SOUTH);
    }


    // setter methods that are called by Settings
    /// Hrithik Mallireddy did this.
    public void setFontChosen(String fontChosenIn)
    {
        fontChosen = fontChosenIn;
    }

    public void setFontSize(int fontSizeIn)
    {
        fontSize = fontSizeIn;
    }

    // Checks if the lesson page button was clicked
    /// Prathik Kumar wrote this method
    class ButtonHandler extends JPanel implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String command = evt.getActionCommand();
            if (command.equalsIgnoreCase("Back to Lessons"))
            {
                cards.show(panelHolder, "Lessons");
            }
        }
    }

    // changes font and size to fontChosen and fontSize that were chosen
    // in Settings
    /// Hrithik Mallireddy did this.
    public void changeFontandSize()
    {
        Font font = new Font(fontChosen, Font.BOLD, fontSize + 30);
        presLabel.setFont(font);
    }
}

// This class creates the Instructions panel.Instructions will use a getter and setter method (Information)
// to recieve the instructions the user will read before playing the game.
/// Prathik Kumar wrote this class.
class Instructions extends JPanel
{
    private String fontChosen; // font that is set in Settings
    private int fontSize; // size that is set in Settings
    // FVs so they can be changed in multiple methods
    JLabel instrucLabel;
    JTextArea ta;

    // Creating Instructions JLabel as a heading, creating text area that will hold
    // instructions later, and adding menu bar
    public Instructions(CardLayout cardsIn, CardHolder panelHolderIn)
    {
        CardLayout cards = cardsIn;
        CardHolder panelHolder = panelHolderIn;
        HomeBar hb = new HomeBar(cards, panelHolder);
        instrucLabel = new JLabel();
        ta = new JTextArea();
        setLayout(new BorderLayout());
        add(hb, BorderLayout.WEST);

        //JPanel textPanel = new JPanel(new GridLayout(1, 1));
        Information info = new Information();
        info.setInstructions();
        String present = info.getInstructions();
        ta = new JTextArea(present, 30, 50);
        Font text = new Font("Georgia", Font.PLAIN, 20);
        ta.setFont(text);
        JScrollPane sp = new JScrollPane(ta);
        add(sp);
        add(ta, BorderLayout.CENTER);
        
        instrucLabel = new JLabel("Instructions");
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Font font = new Font("Georgia", Font.BOLD, 45);
        instrucLabel.setFont(font);
        labelPanel.add(instrucLabel);
        labelPanel.setBackground(Color.YELLOW);
        add(labelPanel, BorderLayout.NORTH);
    }


    // setter methods that are called by Settings
    /// Hrithik Mallireddy did this.
    public void setFontChosen(String fontChosenIn)
    {
        fontChosen = fontChosenIn;
    }

    public void setFontSize(int fontSizeIn)
    {
        fontSize = fontSizeIn;
    }

    // changes font and size to fontChosen and fontSize that were chosen
    // in Settings
    /// Hrithik Mallireddy did this.
    public void changeFontandSize()
    {
        Font font = new Font(fontChosen, Font.BOLD, fontSize + 30);
        instrucLabel.setFont(font);
        Font font1 = new Font(fontChosen, font.PLAIN, fontSize);
        ta.setFont(font1);
    }
}

// This is the leaderboard panel that will display the text file
// with all the leaderboard information. There is a JLabel with the title
// of the panel.
/// Prathik Kumar wrote this class and did the leaderboard panel.
class Leader extends JPanel
{
    private String nameScores[][]; //Two columns, one for player, and one for time
    private ScoreTracker scoreTracker; // obj to class to call methods
    private String fontChosen; // font that is set in Settings
    private int fontSize; // size that is set in Settings
    private JLabel leaderLabel; // FV so it can be changed in changeFontAndSize()

    // Creating JLabel called Leaderboard as a heading for the Leaderboard panel
    // Also adding menu bar to panel
    public Leader(CardLayout cardsIn, CardHolder panelHolderIn)
    {
        scoreTracker = new ScoreTracker();
        leaderLabel = new JLabel();
        nameScores = new String[scoreTracker.getAllPlayers()+1][2];
        CardLayout cards = cardsIn;
        CardHolder panelHolder = panelHolderIn;
        HomeBar hb = new HomeBar(cards, panelHolder);
        setLayout(new BorderLayout());
        add(hb, BorderLayout.WEST);
        JLabel leaderLabel = new JLabel("Leaderboard");
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Font font = new Font(fontChosen, Font.BOLD, fontSize + 30);
        leaderLabel.setFont(font);
        labelPanel.add(leaderLabel);
        labelPanel.setBackground(Color.YELLOW);
        add(labelPanel, BorderLayout.NORTH);
        nameScores = scoreTracker.loadScores();
        add(displayLeaderBoard(nameScores, scoreTracker.getAllPlayers()), BorderLayout.CENTER);
    }

    // setter methods that are called by Settings
    /// Hrithik Mallireddy did this.
    public void setFontChosen(String fontChosenIn)
    {
        fontChosen = fontChosenIn;
    }

    public void setFontSize(int fontSizeIn)
    {
        fontSize = fontSizeIn;
    }

    // changes font and size to fontChosen and fontSize that were chosen
    // in Settings
    /// Hrithik Mallireddy did this.
    public void changeFontandSize()
    {
        Font font = new Font(fontChosen, Font.BOLD, fontSize + 30);
        leaderLabel.setFont(font);
    }

    //Displays data in the name and time format in the leaderboard panel
    private JPanel displayLeaderBoard(String[][] nameScores, int rows)
    {
        int topleaders = 5;
        if (rows <5)
        {
            topleaders = rows;
        }
        JPanel dataPanel = new JPanel(new GridLayout(topleaders+1, 2));
        JLabel nameLabel = new JLabel("Player Name");
        JLabel timeLabel = new JLabel("Player Score");
        dataPanel.add(nameLabel);
        dataPanel.add(timeLabel);

        for (int i = 0; i < topleaders; i++)
        {
            nameLabel = new JLabel(nameScores[i][0]);
            timeLabel = new JLabel(nameScores[i][1]);
            dataPanel.add(nameLabel);
            dataPanel.add(timeLabel);
        }
        return dataPanel;
    }
}

// This class will have the main gameplay section of our game (the forest explorer)
// For now we just have the JMenu the user can use to exit the game
/// Prathik Kumar wrote this class.
class Gameplay extends JPanel
{
    private Image boyDown1; // boy down version 1
    private Image boyDown2; // boy down version 2
    private Image boyLeft1; // boy left version 1
    private Image boyLeft2; // boy left version 2

    private Image boyRight1; // boy right version 1
    private Image boyRight2; // boy right version 2

    private Image boyUp1; // boy up version 1
    private Image boyUp2; // boy up version 2
    private GameConstants gameConstants = new GameConstants();
    public Gameplay(CardLayout cardsIn, CardHolder panelHolderIn)
    {
        CardLayout cards = cardsIn;
        CardHolder panelHolder = panelHolderIn;
        JLabel gameLabel = new JLabel("Game");
        JPanel gamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //gameLabel.setFont(font);
        gamePanel.add(gameLabel);
        gamePanel.setBackground(Color.YELLOW);
        add(gamePanel, BorderLayout.NORTH);
        boyUp1 = gameConstants.getImage("boy_up_1.png");
    }

    // This method is drawing the image
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(boyUp1, 0, 0, 10, 20, this);
    }
}

// This panel shows the total score after a level is completed. The user switches
// back to GamePanel to continue the next level. But, if the user is on the last level,
// they go to the home page.
/// Hrithik Mallireddy wrote this class.
class ScorePanel extends JPanel implements ActionListener
{
    private CardLayout cards; //holds card layout
    private CardHolder panelHolder; //used to swap between panels in card layout
    private GamePanel gp; // obj to use getter methods
    private String fontChosen; // font that is set in Settings
    private int fontSize; // size that is set in Settings

    public ScorePanel(CardLayout cardsIn, CardHolder panelHolderIn)
    {
        cards = cardsIn;
        panelHolder = panelHolderIn;
        gp = new GamePanel(cards, panelHolder);
        Font font = new Font(fontChosen, Font.BOLD, fontSize + 30);
        setLayout(new BorderLayout());
        JTextArea stats = new JTextArea();
        JLabel congrats = new JLabel("Congratulations on completing level " + gp.getCurrrentLevel() + ".");
        congrats.setFont(font);
        add(congrats, BorderLayout.NORTH);
        if(gp.getCurrrentLevel() == 1)
            stats = new JTextArea("\n\nLEVEL " + gp.getCurrrentLevel() + "\n\nSCORE: 5" + "\n\nNOUNS: 5/5");
        else if(gp.getCurrrentLevel() == 2)
            stats = new JTextArea("\n\nLEVEL " + gp.getCurrrentLevel() + "\n\nSCORE: 10\n\nNOUNS: " +
                gp.getRightNouns() + "/" + gp.getTotalNouns() + "\n\nPRESENT: " + gp.getRightPresent() + "/" + gp.getTotalPresent());
        else if(gp.getCurrrentLevel() == 3)
            stats = new JTextArea("\n\nLEVEL " + gp.getCurrrentLevel() + "\n\nSCORE: 15\n\nNOUNS: " +
                gp.getRightNouns() + "/" + gp.getTotalNouns() + "\n\nPRESENT: " + gp.getRightPresent() + "/" + gp.getTotalPresent() +
                "\n\nPRETERITE: " + gp.getRightPreterite() + "/" + gp.getTotalPreterite());
        else if(gp.getCurrrentLevel() == 4)
            stats = new JTextArea("\n\nLEVEL " + gp.getCurrrentLevel() + "\n\nSCORE: 20\n\nNOUNS: " +
                gp.getRightNouns() + "/" + gp.getTotalNouns() + "\n\nPRESENT: " + gp.getRightPresent() + "/" + gp.getTotalPresent() +
                "\n\nPRETERITE: " + gp.getRightPreterite() + "/" + gp.getTotalPreterite() + "\n\nIMPERFECT: " +
                gp.getRightImperfect() + "/" + gp.getTotalImperfect());
        add(stats, BorderLayout.CENTER);
        stats.setFont(font);
        JButton nextLevel = new JButton();
        if(gp.getCurrrentLevel() != 4)
            nextLevel = new JButton("Next Level");
        else
            nextLevel = new JButton("Back to Home");
        nextLevel.addActionListener(this);
        add(nextLevel, BorderLayout.SOUTH);
    }

    // setter methods called by settings
    /// Hrithik Mallireddy did this.
    public void setFontChosen(String fontChosenIn)
    {
        fontChosen = fontChosenIn;
    }

    public void setFontSize(int fontSizeIn)
    {
        fontSize = fontSizeIn;
    }

    public void actionPerformed(ActionEvent evt)
    {
        String button = evt.getActionCommand();
        if(button.equals("Next Level"))
            cards.show(panelHolder, "Gameplay");
        else if(button.equals("Back to Home"))
            cards.show(panelHolder, "Starting");
    }
}

// data class
/// Prathik did this.
class Information
{
    private String vocab; // for holding all the vocab lesson information
    private String present; // for holding all the present tense lesson info
    private String preterite; // for holding all the preterite tense lesson info
    private String imperfect; // for holding all the imperfect tense information
    private String instructions; // for holding all the instructions text

    public Information()
    {
    }

    public String getVocab()
    {
        return vocab;
    }

    public void setVocab()
    {
        String heading = "Below is the vocab list necessary for this game.\n"
                + "As you can see, there are one or more spanish words followed by\n"
                + "the corresponding english definition.\n\n\n\n";
        String lessons = "artístico, artística: artist\n"
                + "atrevido, atrevida: daring\n"
                + "bueno, buena: good\n"
                + "deportista: sports minded\n"
                + "estudioso, estudiosa: studious\n"
                + "gracioso, graciosa: funny\n"
                + "impaciente: impatient\n"
                + "inteligente: intelligent\n"
                + "ordenado, ordenada: organized\n"
                + "paciente: patient\n"
                + "perezoso, perezosa: lazy\n"
                + "serio, seria: serious\n"
                + "simpático, simpática: nice, friendly\n"
                + "sociable: social\n"
                + "talentoso, talentosa: talented\n"
                + "trabajador, trabajadora: hardworking\n"
                + "¿Cómo eres?: What are you like\n"
                + "¿Cómo es?: What is he/she like?\n";
        vocab = heading + lessons;
    }

    public String getPresent()
    {
        return present;
    }

    public void setPresent()
    {
        String heading = "This is the template for verb conjugation in present tense.\n\n\n";

        String arVerbs = "First, seperate the stem from the rest of the verb (ex. habl seperated from hablar)\n"
                + "What should go before the dash is the stem form of the verb.\n"
                + "yo\t -o\n"
                + "tú\t-as\n"
                + "él/ella/Ud.\t-a\n"
                + "Nosotros/as\t-amos\n"
                + "Vosotros/as\t -áis\n"
                + "Ellos/ellas/Uds. -an\n";

        String exampleHeading = "\n\nExample of this conjugation is below\n\n";

        String arExample = "yo hablo\n"
                + "tú hablas\n"
                + "él/ella/Ud habla\n"
                + "Nosotros/as hablamos\n"
                + "Vosotros/as habláis\n"
                + "Ellos/ellas/Uds. hablan\n";

        present = heading + arVerbs + exampleHeading + arExample;
    }

    public String getPreterite()
    {
        return preterite;
    }

    public void setPreterite()
    {
        String heading = "This is the template for verb conjugation in present tense.\n\n\n";

        String arVerbs = "First, seperate the stem from the rest of the verb (ex. habl seperated from hablar)\n"
                + "What should go before the dash is the stem form of the verb.\n"
                + "yo\t -é\n"
                + "tú\t-aste\n"
                + "él/ella/Ud.\t-ó\n"
                + "Nosotros/as\t-amos\n"
                + "Vosotros/as\t -asteis\n"
                + "Ellos/ellas/Uds. -aron\n";

        String exampleHeading = "\n\nExample of this conjugation is below\n\n";

        String arExample = "yo hablé\n"
                + "tú hablaste\n"
                + "él/ella/Ud habló\n"
                + "Nosotros/as hablamos\n"
                + "Vosotros/as hablasteis\n"
                + "Ellos/ellas/Uds. hablaron\n";

        preterite = heading + arVerbs + exampleHeading + arExample;
    }

    public String getImperfect()
    {
        return imperfect;
    }

    public void setImperfect()
    {
        String heading = "This is the template for verb conjugation in imperfect tense.\n\n\n";

        String arVerbs = "First, seperate the stem from the rest of the verb (ex. habl seperated from hablar)\n"
                + "What should go before the dash is the stem form of the verb.\n"
                + "yo\t -aba\n"
                + "tú\t-abas\n"
                + "él/ella/Ud.\t-aba\n"
                + "Nosotros/as\t-ábamos\n"
                + "Vosotros/as\t -abais\n"
                + "Ellos/ellas/Uds. -aban\n";

        String exampleHeading = "\n\nExample of this conjugation is below\n\n";

        String arExample = "yo hablaba\n"
                + "tú hablabas\n"
                + "él/ella/Ud hablaba\n"
                + "Nosotros/as hablábamos\n"
                + "Vosotros/as hablabais\n"
                + "Ellos/ellas/Uds. hablaban\n";

        imperfect = heading + arVerbs + exampleHeading + arExample;
    }
    
    public String getInstructions()
    {
        return instructions;
    }
    
    public void setInstructions()
    {
        String instr = "You, the player, will spawn in the middle of a forest, and multiple coins will be\nscattered "
            + "around the map for you to search for. A pop-up window containing the\nquestion will appear "
            + "when you collect a coin. This window will contain a subject and then the unconjugated verb."
            + "The player must conjugate it and\nget the meaning correct to get two points. If you get a "
            + "question wrong or do not \nknow how to do a question, you can click the show solution button\n"
            + "to tell you the answer, but you will not be awarded any points.\nYour points total is "
            + "incrementing and in the top right corner of your screen.\nAfter you get a certain amount of "
            + "points, you will be transferred\nto a random spot in the next level (this is repeated for four "
            + "levels).\nAfter a coin is interacted with, it disappears, so you can’t get an infinite score.\nYou "
            + "could lose if you get too many questions wrong\nand cannot get enough points. In this case, "
            + "your game will automatically end\nafter all the coins are gathered. Each level will have a "
            + "progressively harder\nmap to navigate. After you finish all four levels, your time\ntaken will be "
            + "recorded and saved onto the leaderboard. ";
        instructions = instr;
    }
}

