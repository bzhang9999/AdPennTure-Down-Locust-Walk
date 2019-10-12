import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

/**
 * This class controls interactions with the projectiles, such as collisions
 * and animations. 
 *
 */
public class ProjectileController {
	public static LinkedList<GameObject> p = new LinkedList<>();
	
	/**
	 * Constructor
	 */
	public ProjectileController() {
		p = new LinkedList<>();
	}

	
	/**
	 * draws the projectile
	 * @param g
	 */
	public void draw(Graphics g) {
		for (int i = 0; i < p.size(); i++) {
			GameObject proj = p.get(i);
			proj.draw(g);
		}
	}

	/**
	 * 
	 * @param g - the object being tested for
	 * @param list - list of possible objects that could colide
	 * @return the index number of the collided object in the list
	 */
	public static int collision(GameObject g, List<GameObject> list) {
		int a = -1;
		for (int i = 0; i < list.size(); i++) {
			if (g.intersects(list.get(i))) {
				return i;
			}
		}
		return a;
	}

    /**
     * Adds a projectile
     * @param bullet - a bullet
     */
	public void add(Projectile bullet) {
		p.add(bullet);
	}

	/**
	 * Removes a projectile
	 * @param bullet - a bullet
	 */
	public void remove(Projectile bullet) {
		p.remove(bullet);
	}
	
    /**
     * Returns the original list of projectiles.
     * Does not return a copy because this list is
     * intended to change
     * @return list of projectiles
     */
	public static List<GameObject> getProjectiles() {
		return p;
	}

}
