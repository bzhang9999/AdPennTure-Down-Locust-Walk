import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 
 * This class constructs the shooter, which the player controls
 *
 */
public class Shooter extends GameObject {
	public static final String IMG_FILE = "files/pennLogo.png";
    public static final int SIZE = 40;
    public static final int INIT_POS_X = 270;
    public static final int INIT_POS_Y = 560;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;
    public static final int HEALTH = 10;
    private int health;
    private static BufferedImage img;


    /**
     * Constructor
     * @param courtWidth - x bound of shooter
     * @param courtHeight - y bound of shooter
     */
    public Shooter(int courtWidth, int courtHeight) {
        super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        this.health = 10;
    }
    
    /**
     * Gets the health of the shooter
     * @return health of shooter
     */
    public int getHealth() {
    	return this.health;
    }
    
    /**
     * Sets the health of the shooter
     * @param health - health of shooter
     */
    public void setHealth(int health) {
    	this.health = health;
    }
    
    /**
     * Decrements the health of the shooter by 1
     */
    public void decHealth() {
    	setHealth(this.getHealth() - 1);
    }
    
    /**
     * Determines whether the health of the shooter
     * is at zero or not
     * @return boolean - is the shooter dead?
     */
    public boolean isDead() {
    	if (this.getHealth() == 0) {
    		return true;
    	}
    	return false;
    }

    
    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);
    }
}
