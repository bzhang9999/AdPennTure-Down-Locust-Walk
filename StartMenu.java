import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * 
 * This class handles all interactions with the start menu. 
 *
 */
public class StartMenu extends MouseAdapter {
	public static final String IMG_FILE = "files/locust.jpg";
	public static final String instructionFile = "files/instructions.txt";
	public static final String highscoreFile = "files/highscores.txt";
	private static BufferedImage img;
	private final int xCoord = GameCourt.COURT_WIDTH / 2 - 90;
	private Rectangle start = new Rectangle(xCoord, 200, 150, 70);
	private Rectangle instructions = new Rectangle(xCoord, 300, 150, 70);
	private Rectangle highscores = new Rectangle(xCoord, 400, 150, 70);
	private Rectangle quit = new Rectangle(xCoord, 500, 150, 70);
	private JTextArea txInstructions;
	private JTextArea txScores;

	/**
	 * Constructor
	 */
	public StartMenu() {
		try {
			if (img == null) {
				img = ImageIO.read(new File(IMG_FILE));
			}
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
	}

	/**
	 * Draws the main menu
	 * @param g
	 */
	public void draw(Graphics g) {

		g.drawImage(img, 0, 0, 600, 600, null);
		Font f = new Font("Serif", Font.BOLD, 30);
		g.setColor(Color.WHITE);
		g.setFont(f);
		g.drawString("AD-PENN-TURE DOWN LOCUST WALK", 10, 75);
		g.setColor(Color.RED);
		g.fillRect(start.x, start.y, start.width, start.height);
		g.fillRect(instructions.x, instructions.y, instructions.width, instructions.height);
		g.fillRect(highscores.x, highscores.y, highscores.width, highscores.height);
		g.fillRect(quit.x, quit.y, quit.width, quit.height);
		Font f2 = new Font("Comic Sans", Font.BOLD, 25);
		g.setFont(f2);
		g.setColor(Color.WHITE);
		g.drawString("PLAY", start.x + 45, start.y + 45);
		Font instruc = new Font("Comics Sans", Font.BOLD, 15);
		g.setFont(instruc);
		g.drawString("INSTRUCTIONS", instructions.x + 20, instructions.y + 45);
		g.drawString("HIGHSCORES", highscores.x + 20, highscores.y + 45);
		g.setFont(f2);
		g.drawString("QUIT", quit.x + 45, quit.y + 45);

	}

	/**
	 * Detects which button the user clicked on and executes the 
	 * appropriate commands.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		// Only listen for event if the game is not playing
		if (GameCourt.playing == false) {
			// play
			if (mouseX >= start.x && mouseX <= start.x + start.width && mouseY >= start.y
					&& mouseY <= start.y + start.height) {
				GameCourt.username = JOptionPane.showInputDialog(null, "Please enter a username." + "\n"
						+ "If you do not enter a username, a name will be generated.");
				// if no input, generate random username. Must replace || because that is the file separator
				if (GameCourt.username == null || GameCourt.username.equals("")) {
					GameCourt.username = "User" + (int) (Math.random() * (1000 - 0));
				} else if (GameCourt.username.contains("||")) {
					GameCourt.username = GameCourt.username.replace('|', '0');
				}
				GameCourt.playing = true;
			}
			// instructions
			if (mouseX >= instructions.x && mouseX <= instructions.x + instructions.width && mouseY >= instructions.y
					&& mouseY <= instructions.y + instructions.height) {
				loadInstructions();
				JFrame tf = new JFrame();
				tf.setPreferredSize(new Dimension(600, 500));
				tf.add(txInstructions);
				tf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				tf.pack();
				tf.setVisible(true);
			}
			// highscores
			if (mouseX >= highscores.x && mouseX <= highscores.x + highscores.width && mouseY >= highscores.y
					&& mouseY <= highscores.y + highscores.height) {
				readHighScores();
				JFrame tf2 = new JFrame();
				tf2.setPreferredSize(new Dimension(800, 500));
				tf2.add(txScores);
				tf2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				tf2.pack();
				tf2.setVisible(true);
			}

			// quit
			if (mouseX >= quit.x && mouseX <= quit.x + quit.width && mouseY >= quit.y
					&& mouseY <= quit.y + quit.height) {
				System.exit(0);
			}
		}
	}

	/**
	 * Helper method that loads the instructions when the 
	 * instruction button is clicked. 
	 */
	private void loadInstructions() {
		txInstructions = new JTextArea();
		txInstructions.setEditable(false);
		txInstructions.setLineWrap(true);
		txInstructions.setWrapStyleWord(true);
		BufferedReader inst = null;
		try {
			inst = new BufferedReader(new FileReader(instructionFile));
			String line = inst.readLine();
			while (line != null) {
				// }
				txInstructions.append("  " + line + "  " + "\n");
				line = inst.readLine();
			}
			// }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				inst.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * helper method that reads in a score file when the high scores
	 * button is clicked. This method also sorts the file via high 
	 * score and formats the presentation of the high scores.
	 */
	private void readHighScores() {
		txScores = new JTextArea();
		txScores.setEditable(false);
		txScores.setLineWrap(true);
		txScores.setWrapStyleWord(true);
		List<HighScore> highscores = new ArrayList<>();
		BufferedReader h = null;
		try {
			h = new BufferedReader(new FileReader(highscoreFile));
			String line = h.readLine();
			// this accounts for if the initial file is empty
			if (line == null || line.length() == 0) {
				txScores.append(null);
			} else {
				while (line != null) {
					String[] split = line.split("\\|\\|");
					HighScore hs = new HighScore(split[0], split[1], Integer.parseInt(split[2]));
					highscores.add(hs);
					line = h.readLine();
				}
				Collections.sort(highscores);
				txScores.append("\t\t\t" + "HIGHSCORES" + "\n\n");
				txScores.append("\t" + "RANKINGS" + "\t" + "USERNAME" + "\t" + "DATE AND TIME" + "\t" + "SCORE" + "\n");
				txScores.append("\t-----------------------------------------------------\n");
				for (int i = 0; i < highscores.size(); i++) {
					txScores.append("\t" + (i + 1) + "\t" + highscores.get(i).getUsername() + "\t"
							+ highscores.get(i).getDate() + "\t" + highscores.get(i).getHighScore() + "\n");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				h.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
