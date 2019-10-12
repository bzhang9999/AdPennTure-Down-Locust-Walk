import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * 
 * This class is level 5. It contains mixed enemies.
 *
 */
public class StageFive extends Levels {
	public static final String IMG_FILE = "files/drl.jpg";
	List<GameObject> stageFiveEnemies;
	private static BufferedImage img;

	public StageFive() {
		super();
		stageFiveEnemies = new LinkedList<>();
		int py = 0;
		int count = 1;
		for (int i = 0; i < 10; i++) {
			if (count == 1) {
				stageFiveEnemies.add(new PState(600, py));
				count++;
			}
			if (count == 2) {
				stageFiveEnemies.add(new CanadaGoose(600, py));
				count++;
			}
			if (count == 3) {
				stageFiveEnemies.add(new Princeton(600, py));
				count++;
			}
			if (count == 4) {
				stageFiveEnemies.add(new Squirrel(600, py));
				count = 1;
			}
			count++;
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

		for (int i = 0; i < stageFiveEnemies.size(); i++) {
			stageFiveEnemies.get(i).move();
			int collision = ProjectileController.collision(stageFiveEnemies.get(i),
					ProjectileController.getProjectiles());
			if (collision > -1) {
				ProjectileController.getProjectiles().remove(ProjectileController.getProjectiles().get(collision));
				stageFiveEnemies.remove(stageFiveEnemies.get(i));
				GameCourt.highscore++;
			}
		}
	}

	public boolean hasReachedBottom() {
		for (int i = 0; i < stageFiveEnemies.size(); i++) {
			if (stageFiveEnemies.get(i).getPy() > 600) {
				stageFiveEnemies.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean isComplete() {
		if (stageFiveEnemies.size() == 0) {
			return true;
		}
		return false;
	}

	public void draw(Graphics g) {
		g.drawImage(img, 0, 0, 600, 600, null);
		for (int i = 0; i < stageFiveEnemies.size(); i++) {
			stageFiveEnemies.get(i).draw(g);
		}
	}
	
	/**
	 * 
	 * This is created for testing purposes. It is intended to change the contents
	 * of the list.
	 */
	public List<GameObject> getEnemies() {
		return stageFiveEnemies;
	}
}
