 // Prathik Kumar and Hrithik Mallireddy
// 5/4/2023
// GamePanel.java (Explorador Español)
// Working on:
// Week 2: Basic Start, Instructions, and Game panels with navigation CardLayout and null layout complete
// Navigation between panels are all working well, and we need to fill in the template we have laid out
// Week 3: Collision is added to this class.
// Hrithik was absent for most of Week 1 so Prathik did most of the program.
// Practicing: ImageIO, Components(JButtons, MenuBar), and different layouts, like border,
// grid, and flow layout. Practicing using handler classes as well

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Font;

public class GamePanel extends JPanel implements KeyListener, MouseListener, ActionListener
{
    private Image boyDown1; // boy down version 1, standing image
    private Image boyDown2; // boy down version 2, running image

    private Image boyLeft1; // boy left version 1, runnning image
    private Image boyLeft2; // boy left version 2, standing image

    private Image boyRight1; // boy right version 1, running image
    private Image boyRight2; // boy right version 2, standing image

    private Image boyUp1; // boy up version 1, running image
    private Image boyUp2; // boy up version 2, standing image

    private Image character; // holds character based on keys pressed

    private int xPos; // xPos of image
    private int yPos; // yPos of image

    private boolean leftChecker; // checks if left key was last pressed
    private boolean upChecker; // checks if up key was last pressed
    private boolean rightChecker; // checks if right key was last pressed
    private boolean downChecker; // checks if down key was last pressed

    private int velocity; // used for sprint, work in progress

    private Timer timer; // timer needed for animations
    private String direction; // direction stores last saved direction the user went in

    private int spriteNum, spriteCounter; // used to keep track of sprite

    private GameConstants gameConstants = new GameConstants(); // reusing contsatns across for drawing map.
    private MapLayOut mapLayOut;  // instance of MapLayOut class
    private int mapLayOutX, mapLayOutY; // x and y of the tile

    private Coin coins[] = new Coin[20]; //To display multiple coins across multiple levels
    private DisplayGameAssets displayGameAssets; // instance of DisplayGameAssets class

    private Collision collisionChecker;  // instance of Collision class to call methdos
    private boolean collisionOn;  // boolean that checks if collision is true so
    // that movement can stop or start
    private int locationX, locationY;  // vars that add or subtract to check how
    // far the user is from the spawn position

    private CardLayout cards; //holds card layout
    private CardHolder panelHolder; //used to swap between panels in card layout

    private Image home; // home button to navigate to home panel

    private Image exit; // exit button to navigate to quit the game

    private int currrentLevel = 0; //stores current level of the game, default value is 1.
    //FV initialzed for adding player name and timer to the file when level 4 is completed.
    // applies to the below ones
    private Starting starting;
    private String nameScores[][];
    private int allPlayers;
    private ScoreTracker scoreTracker;
    //Integrated timer code that is provided in the class.
    private TimerRunner timerRunner;
    private boolean gameRunning;
    private int score; // int that tracks how many questions the user got right
    // ints that track how many questions the user got asked for the score panel
    // at the end
    private int totalNouns, totalPresent, totalPreterite, totalImperfect;
    // ints that track how many questions the user got right for each subject
    // for the score panel at the end
    private int rightNouns, rightPresent, rightPreterite, rightImperfect;

    public GamePanel(CardLayout cardsIn, CardHolder panelHolderIn)
    {
        currrentLevel = currrentLevel + 1;

        gameRunning = true;
        scoreTracker = new ScoreTracker();
        timerRunner = new TimerRunner();
        starting = new Starting(cardsIn, panelHolderIn);
        timerRunner.startTimer();
        nameScores = new String[scoreTracker.getAllPlayers()][2];
        allPlayers = 0;
        collisionChecker = new Collision(this, currrentLevel); //need to have current level logic here.
        leftChecker = false;
        displayGameAssets = new DisplayGameAssets(this, currrentLevel);
        mapLayOut = new MapLayOut(this, currrentLevel);
        
        //Default Values in the begiining for mapLayOut
        mapLayOutX = gameConstants.getTileSize() * 23;
        setMapLayOutX(mapLayOutX);
        mapLayOutY = gameConstants.getTileSize() * 21;
        setMapLayOutY(mapLayOutY);

        locationX = 0;
        locationY = 0;

        spriteNum = 1;
        spriteCounter = 0;
        direction = "";

        cards = cardsIn;
        panelHolder = panelHolderIn;

        boyUp1 = gameConstants.getImage("Player_Up1.png");
        boyUp2 = gameConstants.getImage("Player_Up2.png");

        boyDown1 = gameConstants.getImage("Player_Down1.png");
        boyDown2 = gameConstants.getImage("Player_Down2.png");

        boyLeft1 = gameConstants.getImage("Player_Left1.png");
        boyLeft2 = gameConstants.getImage("Player_Left2.png");

        boyRight1 = gameConstants.getImage("Player_Right1.png");
        boyRight2 = gameConstants.getImage("Player_Right2.png");
        character = boyUp2;

        addKeyListener(this);
        addMouseListener(this);
        timer = new Timer(250, this);

        home = gameConstants.getImage("home.png");
        exit = gameConstants.getImage("exit.png");
    }

    /// Prathik Kumar wrote this method
    // Shifts images based on key pressed before by user
    public void actionPerformed(ActionEvent e)
    {
        if (leftChecker)
        {
            character = boyLeft2;
            leftChecker = false;
        }
        else if (downChecker)
        {
            character = boyDown1;
            downChecker = false;
        }
        else if (upChecker)
        {
            character = boyUp2;
            upChecker = false;
        }
        else if (rightChecker)
        {
            character = boyRight2;
            rightChecker = false;
        }
        repaint();
    }

    // getter method for coin array
    /// Prathik Kumar did this
    public Coin[] getCoins()
    {
        return coins;
    }

    //Common Method for setting up all game objects, example coin etc.
    /// Prathik Kumar did this
    public void setGameObjects()
    {
        displayGameAssets.setUpGameObjects();
    }

    // draws coins, background, and character
    /// Prathik Kumar did this
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBackground(Color.GREEN);
        Graphics2D g2 = (Graphics2D) g;
        mapLayOut.draw(g2); //Draw tiles and then player not to hide character and coins

        //Drawn Coins in the Map, at random position, Place holder
        for (int i = 0; i < coins.length; i ++ )
        {
            if (coins[i]!=null)
                coins[i].draw(g2, this);
        }

        //Home and Exit buttons
        g2.drawImage(home, 650, 600, gameConstants.getTileSize(), gameConstants.getTileSize(), null);
        g2.drawImage(exit, 700, 600, gameConstants.getTileSize(), gameConstants.getTileSize(), null);

        //Player
        g2.drawImage(character, gameConstants.getScreenX(), gameConstants.getScreenY(), gameConstants.getTileSize()+15, gameConstants.getTileSize()+15, null);
        g2.dispose();

    }

    /// Hrithik did the collision logic in these methods and the keyboard logic
    public void keyPressed(KeyEvent evt)
    {
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_SHIFT)
        {
            if (evt.isShiftDown())
                velocity = 5;
            else
                velocity = 0;
        }
        else if (key == KeyEvent.VK_UP)
        {
            if (!evt.isShiftDown())
                velocity = 0;
            if(!collisionOn)
                locationY -= 8;
            setMapLayOutY(mapLayOutY);
            direction = "up";
            setMapLayOutY(mapLayOutY);
            repaint();
        }
        else if (key == KeyEvent.VK_RIGHT)
        {
            if (!evt.isShiftDown())
                velocity = 0;
            if(!collisionOn)
                locationX += 8;
            setMapLayOutX(mapLayOutX);
            direction = "right";
            setMapLayOutX(mapLayOutX);
            repaint();
        }
        else if (key == KeyEvent.VK_DOWN)
        {
            if (!evt.isShiftDown())
                velocity = 0;
            if(!collisionOn)
                locationY += 8;
            setMapLayOutY(mapLayOutY);
            setMapLayOutY(mapLayOutY);
            direction = "down";
            repaint();
        }
        else if (key == KeyEvent.VK_LEFT)
        {
            if (!evt.isShiftDown())
                velocity = 0;
            if(!collisionOn)
                locationX -= 8;
            setMapLayOutX(mapLayOutX);
            mapLayOutX -= velocity;
            setMapLayOutX(mapLayOutX);
            direction = "left";
            repaint();
        }

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_UP ||
                key == KeyEvent.VK_DOWN || key == KeyEvent.VK_LEFT)
        {
            if (direction.equals("left"))
            {
                leftChecker = true;
                character = boyLeft1;
                timer.start();
                repaint();
                collisionChecker.setDirection(direction);
            }
            else if (direction.equals("right"))
            {
                rightChecker = true;
                character = boyRight1;
                timer.start();
                repaint();
                collisionChecker.setDirection(direction);
            }
            else if (direction.equals("up"))
            {
                upChecker = true;
                character = boyUp1;
                timer.start();
                repaint();
                collisionChecker.setDirection(direction);
            }
            else if (direction.equals("down"))
            {
                downChecker = true;
                character = boyDown2;
                timer.start();
                repaint();
                collisionChecker.setDirection(direction);
            }
        }

        // checking tile collision
        collisionOn = false;
        collisionOn = collisionChecker.checkTile();
        collisionChecker.checkCoin();

        // if collision is false, player can move
        if(collisionOn == false)
        {
            timer.start();
            if(direction.equals("up"))
                mapLayOutY -= 8;
            else if(direction.equals("down"))
                mapLayOutY += 8;
            else if(direction.equals("right"))
                mapLayOutX += 8;
            else if(direction.equals("left"))
                mapLayOutX -= 8;
        }
    }

    public void keyReleased(KeyEvent evt) {}

    /// Hrithik did this
    public void keyTyped(KeyEvent evt)
    {
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_SHIFT)
        {
            if (evt.isShiftDown())
                velocity = 5;
            else
                velocity = 0;
        }

        else if (key == KeyEvent.VK_UP)
        {
            if (!evt.isShiftDown())
                velocity = 0;
            locationY -= velocity;
            direction = "up";
            repaint();
        }
        else if (key == KeyEvent.VK_RIGHT)
        {
            if (!evt.isShiftDown())
                velocity = 0;
            direction = "right";
            locationX += velocity;
            repaint();

        }
        else if (key == KeyEvent.VK_DOWN)
        {
            if (!evt.isShiftDown())
                velocity = 0;
            setMapLayOutY(mapLayOutY);
            setMapLayOutY(mapLayOutY);
            direction = "down";
            locationY += velocity;
            repaint();
        }
        else if (key == KeyEvent.VK_LEFT)
        {
            if (!evt.isShiftDown())
                velocity = 0;
            setMapLayOutX(mapLayOutX);
            setMapLayOutX(mapLayOutX);
            direction = "left";
            locationX -= velocity;
            repaint();
        }

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_UP ||
                key == KeyEvent.VK_DOWN || key == KeyEvent.VK_LEFT)
        {
            if (direction.equals("left"))
            {
                leftChecker = true;
                character = boyLeft1;
                timer.start();
                repaint();
            }
            else if (direction.equals("right"))
            {
                rightChecker = true;
                character = boyRight1;
                timer.start();
                repaint();
            }
            else if (direction.equals("up"))
            {
                upChecker = true;
                character = boyUp1;
                timer.start();
                repaint();
            }
            else if (direction.equals("down"))
            {
                downChecker = true;
                character = boyDown2;
                timer.start();
                repaint();
            }
        }
    }

    public void mousePressed(MouseEvent evt)
    {
        requestFocusInWindow();
    }

    public void mouseExited(MouseEvent evt) {}

    //Prathik Kumar did this to navigate to home panel and exit the game
    public void mouseClicked(MouseEvent evt)
    {
        int clickX = evt.getX();
        int clickY = evt.getY();
        int homeX = 650;
        int homeY = 600;
        int tileSize = gameConstants.getTileSize();
        if (clickX >= homeX && clickX <= homeX + tileSize &&
                clickY >= homeY && clickY <= homeY + tileSize)
        {
            cards.show(panelHolder, "Home");

        }
        clickX = evt.getX();
        clickY = evt.getY();
        int exitX = 700;
        int exitY = 600;
        if (clickX >= exitX && clickX <= exitX + tileSize &&
                clickY >= exitY && clickY <= exitY + tileSize)
        {
            exitPrompt();
        }
    }

    public void mouseReleased(MouseEvent evt) {}
    public void mouseEntered(MouseEvent evt) {}

    // setter and getter methods to pass variables back and forth from other classes
    /// Hrithik Mallireddy did these.
    public int getMapLayOutX()
    {
        return mapLayOutX;
    }
    public int getMapLayOutY()
    {
        return mapLayOutY;
    }

    public int getLocationX()
    {
        return locationX;
    }

    public int getLocationY()
    {
        return locationY;
    }

    public int getVelocity()
    {
        return velocity;
    }

    public void setMapLayOutX (int mapLayOutX)
    {
        this.mapLayOutX = mapLayOutX;
    }

    public void setMapLayOutY (int mapLayOutY)
    {
        this.mapLayOutY = mapLayOutY;
    }

    public int getCurrrentLevel()
    {
        return currrentLevel;
    }

    public void setCurrrentLevel(int currrentLevel)
    {
        this.currrentLevel = currrentLevel;
    }

    public int getScore()
    {
        return score;
    }

    public void setTotalNouns()
    {
        totalNouns++;
    }

    public int getTotalNouns()
    {
        return totalNouns;
    }

    public void setTotalPresent()
    {
        totalPresent++;
    }

    public int getTotalPresent()
    {
        return totalPresent;
    }

    public void setTotalPreterite()
    {
        totalPreterite++;
    }

    public int getTotalPreterite()
    {
        return totalPreterite;
    }

    public void setTotalImperfect()
    {
        totalImperfect++;
    }

    public int getTotalImperfect()
    {
        return totalImperfect;
    }

    public void setRightNouns()
    {
        rightNouns++;
    }

    public int getRightNouns()
    {
        return rightNouns;
    }

    public void setRightPresent()
    {
        rightPresent++;
    }

    public int getRightPresent()
    {
        return rightPresent;
    }

    public void setRightPreterite()
    {
        rightPreterite++;
    }

    public int getRightPreterite()
    {
        return rightPreterite;
    }

    public void setRightImperfect()
    {
        rightImperfect++;
    }

    public int getRightImperfect()
    {
        return rightImperfect;
    }

    // On top of adding to score, it checks the score and changes the level
    // if needed.
    public void addToScore()
    {
        score++;

        // level logic
        /// Hrithik did the first three and Prathik did the last one.
        if(getScore() == 5 && currrentLevel == 1)
        {
            setCurrrentLevel(2);
            ScorePanel scorePanel = new ScorePanel(cards, panelHolder);
            cards.show(panelHolder, "Score");
            score = 0;
            mapLayOutX = gameConstants.getMapLayOutX();
            mapLayOutY = gameConstants.getMapLayOutY();
            locationX = locationY = 0;
            DisplayGameAssets dga = new DisplayGameAssets(this, currrentLevel);
            dga.setUpGameObjects();
            collisionChecker = new Collision(this, currrentLevel);
            MapLayOut mp = new MapLayOut(this, currrentLevel);
            repaint();
        }
        else if (getScore() == 10 && currrentLevel == 2)
        {
            setCurrrentLevel(3);
            ScorePanel scorePanel = new ScorePanel(cards, panelHolder);
            cards.show(panelHolder, "Score");
            score = 0;
            mapLayOutX = gameConstants.getMapLayOutX();
            mapLayOutY = gameConstants.getMapLayOutY();
            locationX = locationY = 0;
            DisplayGameAssets dga = new DisplayGameAssets(this, currrentLevel);
            dga.setUpGameObjects();
            collisionChecker = new Collision(this, currrentLevel);
            GamePanel gp = new GamePanel(cards, panelHolder);
            MapLayOut mp = new MapLayOut(this, currrentLevel);
            mp.getTileImage();
            mp.loadMap("level3.txt", 3);
            repaint();
        }
        else if (getScore() == 15 && currrentLevel == 3)
        {
            setCurrrentLevel(4);
            ScorePanel scorePanel = new ScorePanel(cards, panelHolder);
            cards.show(panelHolder, "Score");
            score = 0;
            mapLayOutX = gameConstants.getMapLayOutX();
            mapLayOutY = gameConstants.getMapLayOutY();
            locationX = locationY = 0;
            DisplayGameAssets dga = new DisplayGameAssets(this, currrentLevel);
            dga.setUpGameObjects();
            collisionChecker = new Collision(this, currrentLevel);
            GamePanel gp = new GamePanel(cards, panelHolder);
            MapLayOut mp = new MapLayOut(this, currrentLevel);
            mp.getTileImage();
            mp.loadMap("level4.txt", 4);
            repaint();
        }
        else if(getScore() >= 20 && gameRunning) 
        {
            //Read data from scores.txt
            nameScores = scoreTracker.loadScores();
            allPlayers = scoreTracker.getAllPlayers();
            ScorePanel scorePanel = new ScorePanel(cards, panelHolder);
            cards.show(panelHolder, "Score");
            timerRunner.stopTimer();
            nameScores[allPlayers][0] = starting.getPlayerName();
            nameScores[allPlayers][1] = timerRunner.getTimeDifference()+"";
            //Write player information to the file.
            scoreTracker.writeToFile(nameScores, allPlayers+1);
            gameRunning = false;
        }
    }

    //Prathik Kumar did this to warn user before exitting.
    public void exitPrompt()
    {
        // create a JFrame for the warning message
        JFrame warningFrame = new JFrame("Warning");
        warningFrame.setSize(400, 400);
        warningFrame.setLocationRelativeTo(null);
        // create a JPanel for the message and add it to the JFrame
        JPanel warningPanel = new JPanel();
        Font font = new Font("Arial", Font.BOLD, 16);
        JLabel warningLabel = new JLabel("Are you sure you want to exit? You will lose your data.");
        warningPanel.setFont(font);
        warningPanel.add(warningLabel);
        warningPanel.setBackground(Color.YELLOW);
        warningFrame.add(warningPanel);

        // create buttons for the user to choose whether to exit or not
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                gameRunning = false;
                System.exit(0);
            }
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                warningFrame.dispose();
            }
        });

        // add the buttons to the warning panel
        warningPanel.add(exitButton);
        warningPanel.add(cancelButton);
        warningFrame.setVisible(true);
    }
}
