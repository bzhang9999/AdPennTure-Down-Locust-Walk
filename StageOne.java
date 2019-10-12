import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * 
 * This class is Level 1. It only contains squirrels
 *
 */
public class StageOne extends Levels {
	public static final String IMG_FILE = "files/highrises.jpg";
	List<GameObject> stageOneEnemies;
	private static BufferedImage img;

	/**
	 * Constructor
	 */
	public StageOne() {
		stageOneEnemies = new LinkedList<>();
		int py = 0;
		for (int i = 0; i < 10; i++) {
			stageOneEnemies.add(new Squirrel(600, py));
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

	@Override
	public void tick() {
		projectTick();

		for (int i = 0; i < stageOneEnemies.size(); i++) {
			stageOneEnemies.get(i).move();
			int collision = ProjectileController.collision(stageOneEnemies.get(i),
					ProjectileController.getProjectiles());
			if (collision > -1) {
				ProjectileController.getProjectiles().remove(ProjectileController.getProjectiles().get(collision));
				stageOneEnemies.remove(stageOneEnemies.get(i));
				GameCourt.highscore++;
			}
		}
	}

	@Override
	public boolean hasReachedBottom() {
		for (int i = 0; i < stageOneEnemies.size(); i++) {
			if (stageOneEnemies.get(i).getPy() > 600) {
				stageOneEnemies.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isComplete() {
		if (stageOneEnemies.size() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, 0, 0, 600, 600, null);
		for (int i = 0; i < stageOneEnemies.size(); i++) {
			stageOneEnemies.get(i).draw(g);
		}
	}

	/**
	 * 
	 * This is created for testing purposes. It is intended to change the contents
	 * of the list.
	 */
	public List<GameObject> getEnemies() {
		return stageOneEnemies;
	}

}
