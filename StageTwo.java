import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * 
 * This is Level 2. It contains only Canada Goose
 *
 */
public class StageTwo extends Levels {
	public static final String IMG_FILE = "files/huntsman.jpg";
	List<GameObject> stageTwoEnemies;
	private static BufferedImage img;

	public StageTwo() {
		stageTwoEnemies = new LinkedList<>();
		int py = 0;
		for (int i = 0; i < 20; i++) {
			stageTwoEnemies.add(new CanadaGoose(600, py));
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

		for (int i = 0; i < stageTwoEnemies.size(); i++) {
			stageTwoEnemies.get(i).move();
			int collision = ProjectileController.collision(stageTwoEnemies.get(i),
					ProjectileController.getProjectiles());
			if (collision > -1) {
				ProjectileController.getProjectiles().remove(ProjectileController.getProjectiles().get(collision));
				stageTwoEnemies.remove(stageTwoEnemies.get(i));
				GameCourt.highscore++;
			}
		}
	}

	public boolean hasReachedBottom() {
		for (int i = 0; i < stageTwoEnemies.size(); i++) {
			if (stageTwoEnemies.get(i).getPy() > 600) {
				stageTwoEnemies.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean isComplete() {
		if (stageTwoEnemies.size() == 0) {
			return true;
		}
		return false;
	}

	public void draw(Graphics g) {
		g.drawImage(img, 0, 0, 600, 600, null);
		for (int i = 0; i < stageTwoEnemies.size(); i++) {
			stageTwoEnemies.get(i).draw(g);
		}
	}
	
	/**
	 * 
	 * This is created for testing purposes. It is intended to change the contents
	 * of the list.
	 */
	public List<GameObject> getEnemies() {
		return stageTwoEnemies;
	}

}
