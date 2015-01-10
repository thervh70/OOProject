package Model;

import Controller.saveGame;

public abstract class Player {
		
	private String firstname, lastname, pos, name;
	private int age, pri, card, cardTime;
	private boolean play;
	
	/**
	 * Since Player is abstract, no instances can be made of Player.
	 * Fieldplayer and Goalkeeper inherit from Player via this constructor.
	 * @param firstname
	 * @param lastname
	 * @param pos
	 * @param age
	 * @param pri
	 */
	
	public Player(String firstname, String lastname, String pos, int age, int pri, boolean play, int card) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.pos = pos;
		this.age = age;
		this.pri = pri;
		this.play = play;
		this.card = card;
		name = firstname + " " + lastname; 
	}
	
	public int card(){
		double chance = Math.random();
		
		if(chance>0.98){
			this.card = 2;
			this.cardTime = saveGame.getDay();
			return 2;
		}
		else if(chance>0.90){
			if(this.card == 1){
				this.card = 2;
				this.cardTime = saveGame.getDay();
			}
			else if(this.card == 0){
				this.card = 1;
			}
			return 1;
		}
		return 0;
	}
	
	public void clearCard(){
		int time = saveGame.getDay() - cardTime;
		if(this.card == 2 & time >= 1){
			this.card = 0;
			this.cardTime = 0;
		}
	}

	/**
	 * Getters
	 * @return
	 */
	
	public String getName() {return name; }
	public String getFnm() {return firstname; }
	public String getLnm() {return lastname; }
	public String getPos() {return pos;	}
	public int getAge() {return age; }
	public int getPri() {return pri; }
	public boolean getPlay() {return play; }
	public int getCard() {return card; }

	/**
	 * Setters
	 * @param firstname
	 */
	
	public void setFnm(String firstname) {this.firstname = firstname;	}
	public void setLnm(String lastname) {this.lastname = lastname;	}
	public void setPos(String pos) {this.pos = pos; }
	public void setAge(int age) {this.age = age; }
	public void setPri(int pri) {this.pri = pri; }
	public void setPlay(boolean play) {this.play = play; }
	public void setCard(int card) {this.card = card; }
	public void setAvail() {this.card = 0; this.play = true; }
	
}