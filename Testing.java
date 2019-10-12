import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class Testing {

	
	@Test
	public void testShooterDecHealth() {
		
		Shooter penn = new Shooter(600, 600);
		penn.setHealth(10);
		penn.decHealth();
		assertEquals(9,  penn.getHealth());

	}
	
	
	@Test
	public void testShooterIsDead() {
		Shooter penn = new Shooter(600, 600);
		penn.setHealth(0);
		assertTrue(penn.isDead());
	}
	
	@Test
	public void testShooterIsNotDead() {
		Shooter penn = new Shooter(600, 600);
		penn.setHealth(1);
		assertFalse(penn.isDead());
		penn.setHealth(10);
		assertFalse(penn.isDead());
	}
	
	@Test
	public void intersects() {
		Squirrel s = new Squirrel(600, 60);
		Princeton p  = new Princeton(600, 60);
		s.setPx(60);
		p.setPx(60);
		assertTrue(s.intersects(p));
		assertTrue(p.intersects(s));
		p.setPx(65);
		assertTrue(p.intersects(s));
	}
	
	@Test
	public void doesNotIntersect() {
		Squirrel s = new Squirrel(600, 60);
		Princeton p  = new Princeton(600, 60);
		s.setPx(60);
		p.setPx(160);
		assertFalse(s.intersects(p));
		assertFalse(p.intersects(s));
	}
	
	@Test
	public void testBulletEnemyCollision() {
		List<GameObject> listOfEnemies = new LinkedList<>();
		Squirrel x = new Squirrel(600, 60);
		x.setPx(80);
		Squirrel y = new Squirrel(600, 60);
		y.setPx(90);
		Squirrel z = new Squirrel(600, 60);
		z.setPx(120);
		listOfEnemies.add(x);
		listOfEnemies.add(y);
		listOfEnemies.add(z);
		Projectile p = new Projectile(0, -10, 0, 0, 20, 20, 600, 600);
		p.setPx(80);
		p.setPy(60);
		assertEquals(0, ProjectileController.collision(p, listOfEnemies));
		
	}
	
	@Test
	public void testBulletEnemyCollisionClip() {
		List<GameObject> listOfEnemies = new LinkedList<>();
		Squirrel x = new Squirrel(600, 60);
		x.setPx(80);
		Squirrel y = new Squirrel(600, 60);
		y.setPx(90);
		Squirrel z = new Squirrel(600, 60);
		z.setPx(120);
		listOfEnemies.add(x);
		listOfEnemies.add(y);
		listOfEnemies.add(z);
		Projectile p = new Projectile(0, -10, 0, 0, 20, 20, 600, 600);
		// testing clip
		p.setPx(150);
		p.setPy(60);
		assertEquals(2, ProjectileController.collision(p, listOfEnemies));
		
	}
	
	@Test
	public void testBulletNoCollision() {
		List<GameObject> listOfEnemies = new LinkedList<>();
		Squirrel x = new Squirrel(600, 60);
		x.setPx(80);
		Squirrel y = new Squirrel(600, 60);
		y.setPx(90);
		Squirrel z = new Squirrel(600, 60);
		z.setPx(120);
		listOfEnemies.add(x);
		listOfEnemies.add(y);
		listOfEnemies.add(z);
		Projectile p = new Projectile(0, -10, 0, 0, 20, 20, 600, 600);
		p.setPx(25);
		p.setPy(60);
		assertEquals(-1, ProjectileController.collision(p, listOfEnemies));
		
	}
	
	
	@Test
	public void hasNotReachedBottom() {
		// initially none are at the bottom
		StageOne s1 = new StageOne();
        assertFalse(s1.hasReachedBottom());	
        
        StageTwo s2 = new StageTwo();
        assertFalse(s2.hasReachedBottom());	
        
        StageThree s3 = new StageThree();
        assertFalse(s3.hasReachedBottom());	
        
        StageFour s4 = new StageFour();
        assertFalse(s4.hasReachedBottom());	
        
        StageFive s5 = new StageFive();
        assertFalse(s5.hasReachedBottom());	
	}
	
	@Test
	public void hasReachedBottom1() {
		StageOne s1 = new StageOne();
		s1.getEnemies().get(0).setPy(600);
		assertFalse(s1.hasReachedBottom());
		s1.getEnemies().get(0).setPy(601);
		assertTrue(s1.hasReachedBottom());
		s1.getEnemies().get(0).setPy(599);
		assertFalse(s1.hasReachedBottom());
	}
	
	@Test
	public void hasReachedBottom2() {
		StageTwo s2 = new StageTwo();
		s2.getEnemies().get(0).setPy(600);
		assertFalse(s2.hasReachedBottom());
		s2.getEnemies().get(0).setPy(606);
		assertTrue(s2.hasReachedBottom());
		s2.getEnemies().get(0).setPy(595);
		assertFalse(s2.hasReachedBottom());
	}
	
	@Test
	public void hasReachedBottom3() {
		StageThree s3 = new StageThree();
		s3.getEnemies().get(0).setPy(600);
		assertFalse(s3.hasReachedBottom());
		s3.getEnemies().get(0).setPy(650);
		assertTrue(s3.hasReachedBottom());
		s3.getEnemies().get(0).setPy(300);
		assertFalse(s3.hasReachedBottom());
	}
	
	@Test
	public void hasReachedBottom4() {
		StageFour s4 = new StageFour();
		s4.getEnemies().get(0).setPy(600);
		assertFalse(s4.hasReachedBottom());
		s4.getEnemies().get(0).setPy(601);
		assertTrue(s4.hasReachedBottom());
		s4.getEnemies().get(0).setPy(599);
		assertFalse(s4.hasReachedBottom());
	}
	
	@Test
	public void hasReachedBottom5() {
		StageFive s5 = new StageFive();
		s5.getEnemies().get(0).setPy(600);
		assertFalse(s5.hasReachedBottom());
		s5.getEnemies().get(0).setPy(601);
		assertTrue(s5.hasReachedBottom());
		s5.getEnemies().get(0).setPy(599);
		assertFalse(s5.hasReachedBottom());
	}
	
	@Test
	public void compareToHighScore() {
		HighScore hs1 = new HighScore("Benjamin", "Date", 83);
		HighScore hs2 = new HighScore("Ryan", "Date", 78);
		assertTrue(hs2.compareTo(hs1) > 0);
	}
	
	@Test
	public void compareHighScoresSameName() {
		HighScore hs1 = new HighScore("Benjamin", "Date", 69);
		HighScore hs2 = new HighScore("Benjamin", "Date", 78);
		assertTrue(hs2.compareTo(hs1) < 0);
	}
	
	@Test
	public void compareSameHighScore() {
		HighScore hs1 = new HighScore("Benjamin", "Date", 83);
		HighScore hs2 = new HighScore("Ryan", "Date", 83);
		assertTrue(hs2.compareTo(hs1) == 0);
	}

}
