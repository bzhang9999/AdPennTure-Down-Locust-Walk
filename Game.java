
// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game  implements Runnable {
    public void run() {

        // Top-level frame in which game components live
        final JFrame frame = new JFrame("AD-PENN-TURE DOWN LOCUST WALK");
        frame.setLocation(300, 300);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);
        final JLabel lives = new JLabel("Lives = 10");
        final JLabel score = new JLabel("Score = 0");
        final JLabel name = new JLabel("Username = ");

        // Main playing area
        final GameCourt court = new GameCourt(status, lives, score, name);
        frame.add(court, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               court.reset();
            }
        });
        control_panel.add(reset);
        control_panel.add(lives);
        control_panel.add(score);
        control_panel.add(name);
      
        // Put the frame on the screen
        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        court.reset();
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}