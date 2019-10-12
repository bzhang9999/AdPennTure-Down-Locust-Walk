import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 
 * This class constructs the squirrel enemy.
 *
 */
public class Squirrel extends GameObject {
	int[] randomVx = { -2, 2 };
	public static final String IMG_FILE = "files/evilSquirrel.png";
	public static final int SIZE = 50;
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 2;
	private static BufferedImage img;

	/**
	 * Constructor
	 * 
	 * @param courtWidth
	 *            - horizontal bound of the enemy
	 * @param py
	 *            - initial y position of the enemy
	 */
	public Squirrel(int courtWidth, int py) {
		super(INIT_VEL_X, INIT_VEL_Y, (int) (Math.random() * (600 - 0)) + 0, py, SIZE, SIZE, courtWidth, 12000);
		try {
			if (img == null) {
				img = ImageIO.read(new File(IMG_FILE));
			}
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);
	}

}
