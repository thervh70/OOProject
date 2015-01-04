package Model;

public class Result {

	private int scoreA, scoreB;
	private String A,B,score;
	
	public Result(Team A, Team B, int scoreA, int scoreB) {
		this.A = A.getNm();
		this.B = B.getNm();
		this.scoreA = scoreA;
		this.scoreB = scoreB;
		score = scoreA + " - " + scoreB;
	}
	
	/**
	 * @return the a
	 */
	public String getA() {
		return A;
	}

	/**
	 * @return the b
	 */
	public String getB() {
		return B;
	}

	/**
	 * @return the scoreA
	 */
	public int getScoreA() {
		return scoreA;
	}

	/**
	 * @return the scoreB
	 */
	public int getScoreB() {
		return scoreB;
	}

	/**
	 * @return the score
	 */
	public String getScore() {
		return score;
	}
}
