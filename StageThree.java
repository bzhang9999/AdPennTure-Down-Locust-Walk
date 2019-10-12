import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * 
 * This class is Level 3. There are mixed enemies.
 *
 */
public class StageThree extends Levels {
	public static final String IMG_FILE = "files/love.jpg";
	List<GameObject> stageThreeEnemies;
	private static BufferedImage img;

	public StageThree() {
		stageThreeEnemies = new LinkedList<>();
		int py = 0;
		int count = 0;
		for (int i = 0; i < 20; i++) {
			if (count % 2 == 0) {
				stageThreeEnemies.add(new PState(600, py));
			} else {
				stageThreeEnemies.add(new CanadaGoose(600, py));
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

		for (int i = 0; i < stageThreeEnemies.size(); i++) {
			stageThreeEnemies.get(i).move();
			int collision = ProjectileController.collision(stageThreeEnemies.get(i),
					ProjectileController.getProjectiles());
			if (collision > -1) {
				ProjectileController.getProjectiles().remove(ProjectileController.getProjectiles().get(collision));
				stageThreeEnemies.remove(stageThreeEnemies.get(i));
				GameCourt.highscore++;
			}
		}
	}

	public boolean hasReachedBottom() {
		for (int i = 0; i < stageThreeEnemies.size(); i++) {
			if (stageThreeEnemies.get(i).getPy() > 600) {
				stageThreeEnemies.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean isComplete() {
		if (stageThreeEnemies.size() == 0) {
			return true;
		}
		return false;
	}

	public void draw(Graphics g) {
		g.drawImage(img, 0, 0, 600, 600, null);
		for (int i = 0; i < stageThreeEnemies.size(); i++) {
			stageThreeEnemies.get(i).draw(g);
		}
	}
	
	/**
	 * 
	 * This is created for testing purposes. It is intended to change the contents
	 * of the list.
	 */
	public List<GameObject> getEnemies() {
		return stageThreeEnemies;
	}
}
