package Model;

import Controller.saveGame;

public abstract class Player {
		
	private String firstname, lastname, pos, name, injury;
	private int age, pri, card = 0, cardTime;
	private Integer dur;
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
	
	public Player(String firstname, String lastname, String pos, int age, int pri, boolean play, int card, int dur) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.pos = pos;
		this.age = age;
		this.pri = pri;
		this.play = play;
		this.card = card;
		this.dur = dur;
		this.name = firstname + " " + lastname;
		this.injury = "";
	}
	
	public int card(){
		double chance = Math.random();
		
		if(chance>0.98){
			card = 2;
			cardTime = saveGame.getDay();
			play = false;

			return 2;
		}
		else if(chance>0.94){
			if(card == 1){
				card = 2;
				cardTime = saveGame.getDay();
				play = false;
			}
			else if(card == 0){
				card = 1;
			}
			return 1;
		}
		return 0;
	}
	
	/**Method to calculate injuries
	 * 
	 * @return the length of an injury (0 if no injury was made)
	 */
	public int injury(){
		double chance = Math.random();
		
		if(chance > 0.98){
			int length = (int) Math.round((4*Math.random()) + 2);
			dur = length;
			play = false;
			return length;
		}
		return 0;
	}
	
	/**Clears player from cards and injury if he has any
	 * 
	 */
	public void clearCardInjury(){
		int time = saveGame.getDay() - cardTime;
		if(card == 2 & time > 1){
			card = 0;
			cardTime = 0;
			play = true;
		}
		
		if(dur > 0){
			dur--;
			if(dur == 0){
				play = true;
			}
		}
		
	}
	
	public boolean checkRedCard(){
		return card == 2;
	}
	
	public boolean checkYellowCard(){
		return card == 1;
	}
	
	public boolean checkInjury(){
		return !(dur == 0);
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
	public int getDur() {return dur; }
	public String getInjury() {
		if(this.dur == 0){
			return "";
		}
		else{
			return dur.toString();
		}
	}

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
	public void setDur(int dur) {this.dur = dur; }
	
}