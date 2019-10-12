import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * 
 * This class is Level 4. It contains mixed enemies.
 *
 */
public class StageFour extends Levels {
	public static final String IMG_FILE = "files/engineering.jpg";
	List<GameObject> stageFourEnemies;
	private static BufferedImage img;

	public StageFour() {
		stageFourEnemies = new LinkedList<>();
		int py = 0;
		int count = 1;
		for (int i = 0; i < 20; i++) {
			if (count == 1) {
				stageFourEnemies.add(new PState(600, py));
				count++;
			}
			if (count == 2) {
				stageFourEnemies.add(new CanadaGoose(600, py));
				count++;
			}

			if (count == 3) {
				stageFourEnemies.add(new Princeton(600, py));
				count = 1;
			}
			py -= 100;
		}
		try {
			if (img == null) {
				img = ImageIO.read(new File(IMG_FILE));
			}
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
	}

	public void tick() {
		projectTick();

		for (int i = 0; i < stageFourEnemies.size(); i++) {
			stageFourEnemies.get(i).move();
			int collision = ProjectileController.collision(stageFourEnemies.get(i),
					ProjectileController.getProjectiles());
			if (collision > -1) {
				ProjectileController.getProjectiles().remove(ProjectileController.getProjectiles().get(collision));
				stageFourEnemies.remove(stageFourEnemies.get(i));
				GameCourt.highscore++;
			}
		}
	}

	public boolean hasReachedBottom() {
		for (int i = 0; i < stageFourEnemies.size(); i++) {
			if (stageFourEnemies.get(i).getPy() > 600) {
				stageFourEnemies.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean isComplete() {
		if (stageFourEnemies.size() == 0) {
			return true;
		}
		return false;
	}

	public void draw(Graphics g) {
		g.drawImage(img, 0, 0, 600, 600, null);
		for (int i = 0; i < stageFourEnemies.size(); i++) {
			stageFourEnemies.get(i).draw(g);
		}
	}
	
	/**
	 * 
	 * This is created for testing purposes. It is intended to change the contents
	 * of the list.
	 */
	public List<GameObject> getEnemies() {
		return stageFourEnemies;
	}
}
