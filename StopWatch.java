// This is a simple version of a timer to simulate a stop watch.
// Prathik Kumar did this whole class.

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Timer;

public class StopWatch extends JPanel
{
    public static void main ( String [] args )
    {
        StopWatch sw = new StopWatch();
        sw.run();

    }

    // run() makes the frame.
    public void run()
    {
        JFrame frame = new JFrame("Stop watch example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TimerRunner tr = new TimerRunner();
        frame.getContentPane().add(tr);
        frame.setSize(400, 300);
        frame.setLocation(0, 0);
        frame.setVisible(true);
    }
}

class TimerRunner extends JPanel
{
    private Timer timer;
    private Font font;
    private int tenthSec;
    private int elapsedMinutes;
    private double secondsDecimal, secondsDisplay;
    private boolean running;
    private double startTime;
    private double endTime;

    public TimerRunner()
    {
        initialValues();
        setBackground(Color.GRAY);

        TimerHandler th = new TimerHandler();
        timer = new Timer ( 100, th );

    }

    // initializes the values
    public void initialValues()
    {
        tenthSec = elapsedMinutes = 0;
        secondsDecimal = secondsDisplay = 0.0;
        running = false;
    }

    public void paintComponent ( Graphics g )
    {
        super.paintComponent (g);
        g.setColor ( Color.WHITE );
        g.setFont ( font );

        secondsDecimal = tenthSec / 10.0;
        secondsDisplay = secondsDecimal % 60;
        elapsedMinutes = (int)secondsDecimal / 60;

        g.drawString ( "Running:  " + elapsedMinutes + " minutes " +
                String.format("%.1f", secondsDisplay) + " seconds" , 20, 140 );
    }

    // starts stopwatch
    public void startTimer()
    {
        // Start the stopwatch timer
        startTime = System.currentTimeMillis();
        running = true;
        timer.start();
    }

    // ends stopwatch
    public void stopTimer()
    {
        endTime = System.currentTimeMillis();
        // Stop the stopwatch timer
        running = false;
        timer.stop();
    }

    // Calculate the elapsed time in seconds
    public double getTimeDifference()
    {
        return (endTime - startTime) / 1000.0;
    }

    class TimerHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String command = evt.getActionCommand();
            if ( running )
                tenthSec++;
            repaint ( );
        }
    }
}

