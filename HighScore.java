/**
 * 
 * This class keeps track of player data: username, date, and highscore
 *
 */
public class HighScore implements Comparable<HighScore> {
	
	private String username;
	private String date;
	private int score;
	
	/**
	 * Constructor
	 * @param username - inputted username
	 * @param date - current date
	 * @param score - high score
	 */
	public HighScore(String username, String date, int score) {
		this.username = username;
	    this.date = date;
	    this.score = score;
	}
	

    /*** GETTERS **********************************************************************************/
	public String getUsername() {
		return username;
	}
	
	public String getDate() {
		return date;
	}
	
	public int getHighScore() {
		return score;
	}
	
    /*** SETTERS **********************************************************************************/
	public void setUsername(String name) {
		username = name;
	}
		
	public void setDate(String date) {
		this.date = date;
	}
		
	public void setHighScore(int highscore) {
		score = highscore;
	}
	
    /**
     * override compareTo. Compares the objects based on the
     * score. Returns the difference of the high scores. 
     */
	@Override
	public int compareTo(HighScore hs) {
		return hs.getHighScore() - this.score;
	}
}
