import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 
 * This class creates the projectile
 *
 */
public class Projectile extends GameObject {
	public static final String IMG_FILE = "files/dollars2.png";
    public static final int SIZE = 30;
    private static BufferedImage img;

    /**
     * Constructor
     * @param vx - original x velocity
     * @param vy - original y velocity
     * @param px - original x position
     * @param py - original y position
     * @param height - height
     * @param width - width
     * @param courtWidth - x bounds
     * @param courtHeight - y bounds
     */
    public Projectile(int vx, int vy, int px, int py, int height, int width, int courtWidth, int courtHeight) {
        super(vx, vy, px, py, height, width, courtWidth, courtHeight);
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    
    /**
     * draws the projectile
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx(), this.getPy(), SIZE, SIZE, null);
    }

}