import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class constructs the CanadaGoose object
 */
public class CanadaGoose extends GameObject {
	public static final String IMG_FILE = "files/goose.png";
    public static final int SIZE = 40;
    public static final int INIT_POS_X = 270;
    public static final int INIT_POS_Y = 600;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 4;
    private static BufferedImage img;

    /**
     * Constructor
     * @param courtWidth - horizontal bound of the enemy
     * @param py - initial y position of the enemy
     */
    public CanadaGoose(int courtWidth, int py) {
        super(INIT_VEL_X, INIT_VEL_Y, (int) (Math.random() * (600 - 0)) + 0, py, SIZE, SIZE,
        		courtWidth, 12000);
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    
    /**
     * draws the enemy
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);
    }

}
