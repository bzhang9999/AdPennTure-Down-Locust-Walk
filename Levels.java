import java.awt.Graphics;
import java.util.List;


/**
 * 
 * This class contains the common methods for each level in the game
 *
 */
public abstract class Levels {

	public Levels() {
	}

	public abstract void draw(Graphics g);

	/**
	 * Everytime this method is called, it checks whether the bullet
	 * has reached the edge of the screen. If it has, the method
	 * removes the bullet from the list, which also removes it from 
	 * the screen.
	 */
	public void projectTick() {
		 List<GameObject> p = ProjectileController.getProjectiles();
			for (int i = 0; i < p.size(); i++) {
				GameObject proj = p.get(i);
				if (proj.getPy() < 10) {
					p.remove(p.get(i));
					
				}
				proj.move();
			}	
	}

    
	/**
	 * Everytime this method is called, it checks for collisions between
	 * an enemy and the the list of projectiles. If there is a collision,
	 * the method removes the enemy, and the projectile in the index 
	 * that collided. 
	 */
	public abstract void tick();
	
	/**
	 * Determines whether an enemy has reached the bottom of the screen.
	 * If they have reached bottom, the method removes them from the list.
	 */
	public abstract boolean hasReachedBottom(); 
	
	/**
	 * Determines whether there are any enemies left in the 
	 * level. 
	 */
	public abstract boolean isComplete(); 
}
