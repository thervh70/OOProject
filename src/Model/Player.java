package Model;

import Controller.saveGame;
/**
 * Player is abstract, Fieldplayer and Goalkeeper inherit from it
 * @author Mathias
 */
public abstract class Player {
		
	@SuppressWarnings("unused")
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
	
	/**
	 * give for each minute in the game either a yellow card, a red card or no card
	 * @return 0 = no card; 1 = yellow card; 2 = red card
	 */
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
	
	/**
	 * @return true if player has a red card; false if a player doesn't have a red card
	 */
	public boolean checkRedCard(){
		return card == 2;
	}
	
	/**
	 * @return true if player has a yellow card; false if a player doesn't have a yellow card
	 */
	public boolean checkYellowCard(){
		return card == 1;
	}
	
	/**
	 * @return true if player has an injury; false if a player doesn't have an injury
	 */
	public boolean checkInjury(){
		return !(dur == 0);
	}

	/**
	 * Getters
	 */
	
	/** @return the name of a Player*/
	public String getName() {return name; }
	/** @return the firstname of a Player*/
	public String getFnm() {return firstname; }
	/** @return the lastname of a Player*/
	public String getLnm() {return lastname; }
	/** @return the position of a Player*/
	public String getPos() {return pos;	}
	/** @return the age of a Player*/
	public int getAge() {return age; }
	/** @return the price of a Player*/
	public int getPri() {return pri; }
	/** @return the Availabiliy of a Player*/
	public boolean getPlay() {return play; }
	/** @return the card of a Player*/
	public int getCard() {return card; }
	/** @return the cardTime of a Player*/
	public int getCardTime() {return cardTime; }
	/** @return the duration of an injury of a Player*/
	public int getDur() {return dur; }
	/** @return the String format of the duration of an injury of a Player*/
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
	 */
	
	/** Sets the firstname of a Player*/
	public void setFnm(String firstname) {this.firstname = firstname;	}
	/** Sets the lastname of a Player*/
	public void setLnm(String lastname) {this.lastname = lastname;	}
	/** Sets the position of a Player*/
	public void setPos(String pos) {this.pos = pos; }
	/** Sets the age of a Player*/
	public void setAge(int age) {this.age = age; }
	/** Sets the price of a Player*/
	public void setPri(int pri) {this.pri = pri; }
	/** Sets the availability of a Player*/
	public void setPlay(boolean play) {this.play = play; }
	/** Sets the card of a Player*/
	public void setCard(int card) {this.card = card; }
	/** Sets the cardTime of a Player*/
	public void setCardTime(int cardTime) {this.cardTime = cardTime; }
	/** Sets the card to 0 and the availability to true of a Player*/
	public void setAvail() {this.card = 0; this.play = true; }
	/** Sets the duration of an injury of a Player*/
	public void setDur(int dur) {this.dur = dur; }
	
}