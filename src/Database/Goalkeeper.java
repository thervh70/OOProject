package Database;

public class Goalkeeper extends Player {
	
	private String firstname, lastname, pos;
	private int age, price, diving, handling, kicking, reflexes, speed, positioning, height;
	
	public Goalkeeper(String firstname, String lastname, String pos, int age, int price, int diving, int handling, int kicking,
			int reflexes, int speed, int positioning, int height) {
		super(firstname, lastname, pos, age, price);
		this.diving = diving;
		this.handling = handling;
		this.kicking = kicking;
		this.reflexes = reflexes;
		this.speed = speed;
		this.positioning = positioning;
		this.height = height;
	}
}
