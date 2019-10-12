import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact
 * with one another. 
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

	// the state of the game logic
	private StartMenu menu;
	private Shooter penn;
	private ProjectileController c;
	private StageOne stageOne;
	private StageTwo stageTwo;
	private StageThree stageThree;
	private StageFour stageFour;
	private StageFive stageFive;
	private JLabel lives;
	private JLabel score;
	private JLabel name;
	public static String username;

	public static boolean playing = false; // whether the game is running
	public static int highscore = 0;
	private JLabel status; // Current status text, i.e. "Running..."

	// Game constants
	public static final int COURT_WIDTH = 600;
	public static final int COURT_HEIGHT = 600;
	public static final int PENN_VELOCITY = 8;

	// Update interval for timer, in milliseconds
	public static final int INTERVAL = 35;

	public GameCourt(JLabel status, JLabel lives, JLabel score, JLabel name) {
		// creates border around the court area, JComponent method
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start();

		// Enable keyboard focus on the court area.
		setFocusable(true);
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					penn.setVx(-PENN_VELOCITY);
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					penn.setVx(PENN_VELOCITY);
				} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					c.add(new Projectile(0, -10, penn.getPx(), penn.getPy(), 20, 20, COURT_WIDTH, COURT_HEIGHT));
				}
			}

			public void keyReleased(KeyEvent e) {
				penn.setVx(0);
				penn.setVy(0);
			}
		});
		username = "";
		menu = new StartMenu();
		this.addMouseListener(menu);
		this.status = status;
		this.lives = lives;
		this.score = score;
		this.name = name;
	}

	/**
	 * (Re-)set the game to its initial state.
	 */
	public void reset() {
		if (!username.equals("")) {
			writeHighScore();
		}
		lives.setText("Lives = 10");
		penn = new Shooter(COURT_WIDTH, COURT_HEIGHT);
		c = new ProjectileController();
		stageOne = new StageOne();
		stageTwo = new StageTwo();
		stageThree = new StageThree();
		stageFour = new StageFour();
		stageFive = new StageFive();
		playing = false;
		status.setText("Running...");
		score.setText("Scores = 0");
		name.setText("Username = ");
		highscore = 0;
		repaint();
		requestFocusInWindow();
	}

	/**
	 * Writes the scores, username, and date into a file after the game ends.
	 * This method is called when the reset button is hit.  
	 */
	private void writeHighScore() {
		String timeStamp = new SimpleDateFormat("M-d-y H:m:s").format(Calendar.getInstance().getTime());
		BufferedWriter wr = null;
		try {
			HighScore score = new HighScore(username, timeStamp, highscore + penn.getHealth());
			wr = new BufferedWriter(new FileWriter(StartMenu.highscoreFile, true));
			wr.append((score.getUsername() + "||" + score.getDate() + "||" + score.getHighScore() + "\n"));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				wr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method is called every time the timer defined in the constructor
	 * triggers. Checks all end game/end level conditions and facilitates the transition 
	 * between levels
	 */
	void tick() {
		if (playing) {
			name.setText("Username = " + username);
			score.setText("Score = " + (highscore + penn.getHealth()));
			penn.move();
			if (!stageOne.isComplete()) {
				stageOne.tick();
				if (stageOne.hasReachedBottom()) {
					updateHealth();
				}
			}
			if (stageOne.isComplete() && penn.getHealth() != 0) {
				stageTwo.tick();
				if (stageTwo.hasReachedBottom()) {
					updateHealth();
				}
			}
			if (stageTwo.isComplete() && penn.getHealth() != 0) {
				stageThree.tick();
				if (stageThree.hasReachedBottom()) {
					updateHealth();
				}
			}
			if (stageThree.isComplete() && penn.getHealth() != 0) {
				stageFour.tick();
				if (stageFour.hasReachedBottom()) {
					updateHealth();
				}
			}
			if (stageFour.isComplete() && penn.getHealth() != 0) {
				stageFive.tick();
				if (stageFive.hasReachedBottom()) {
					updateHealth();
				}
			}
			if (penn.isDead()) {
				playing = false;
				status.setText("You lose!");
				score.setText("Score = " + (highscore + penn.getHealth()));
			}
			if (stageFive.isComplete()) {
				playing = false;
				status.setText("YOU WIN!!!");
			}
			repaint();
		}
	}

	/**
	 * Updates the health of the shooter and also sets the 
	 * lives to the appropriate number 
	 */
	private void updateHealth() {
		penn.decHealth();
		lives.setText("Lives = " + penn.getHealth());
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// only display at beginning
		if (!playing && penn.getHealth() != 0 && !stageFive.isComplete()) {
			menu.draw(g);
		} else {
			if (!stageOne.isComplete()) {
				stageOne.draw(g);
			}
			if (stageOne.isComplete()) {
				stageTwo.draw(g);
			}
			if (stageTwo.isComplete()) {
				stageThree.draw(g);
			}
			if (stageThree.isComplete()) {
				stageFour.draw(g);
			}
			if (stageFour.isComplete()) {
				stageFive.draw(g);
			}
			penn.draw(g);
			c.draw(g);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}
}
