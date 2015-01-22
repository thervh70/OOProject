package Model;

public class Goalkeeper extends Player {
	
	private int div, han, kick, ref, spd, ping, hei;
	
	/**
	 * Constructor creates a Goalkeeper, inherits firstname, lastname, posititon, age and price from Player
	 * @param firstname
	 * @param lastname
	 * @param pos
	 * @param age
	 * @param price
	 * @param diving
	 * @param handling
	 * @param kicking
	 * @param reflexes
	 * @param speed
	 * @param positioning
	 * @param height
	 */
	
	public Goalkeeper(String firstname, String lastname, String pos, int age, int price, boolean play, int card, int dur, int diving, int handling, int kicking,
			int reflexes, int speed, int positioning, int height) {
		super(firstname, lastname, pos, age, price, play, card, dur);
		this.div = diving;
		this.han = handling;
		this.kick = kicking;
		this.ref = reflexes;
		this.spd = speed;
		this.ping = positioning;
		this.hei = height;
	}
	
	/**
	 * assign a certain value to a team based on the cumulative scores of all players in a team for all of their properties
	 * @return
	 */
	public double calcScore() {
		double score = 0;
		int diving = this.div;
		int handling = this.han;
		int kicking = this.kick;
		int reflexes = this.ref;
		int speed = this.spd;
		int positioning = this.ping;
		int height = this.hei;
		
		score= (diving*25 + handling*15 + kicking*10 + reflexes*15 + speed*15 + positioning*10 + height*10)/100;
		
		return score;
	}
	
	/**
	 * Method toString gives a String-representation of a Goalkeeper
	 * @return String
	 */
	
	public String toString() {
		String card = "unknown", play = "unknown";
		switch(this.getCard()) {
		case 0: card = "None"; break;
		case 1: card = "Yellow"; break;
		case 2: card = "Red"; break;
		}
		
		if(this.getPlay()) {play = "Yes"; }
		if(!this.getPlay()) {play = "No"; }
		
		return "  Name: "+this.getFnm()+" "+this.getLnm()
				+ " Position: "+this.getPos()+" age: "+this.getAge()+" price: "+this.getPri()+"\n"
				+ "    Card: "+card+" Available: "+play+" Duration: "+this.getDur()+"\n"
				+ "    Diving: "+this.getDiv()
				+ " Handling: "+this.getHan()
				+ " Kicking: "+this.getKick()
				+ " Reflexes: "+this.getRef()
				+ " Speed: "+this.getSpd()
				+ " Positioning: "+this.getPing()
				+ " Height: "+this.getHei();
	}
	
	/**
	 * Method toWrite stringifies a Goalkeeper so it can be written to xml-file
	 * @return a xml-writable String
	 */
	
	public String toWrite(){
		String res="            <Keeper>\r\n";
		res+="               <Firstname>"+this.getFnm()+"</Firstname>\r\n";
		res+="               <Lastname>"+this.getLnm()+"</Lastname>\r\n";
		res+="               <Age>"+this.getAge()+"</Age>\r\n";
		res+="               <Price>"+this.getPri()+"</Price>\r\n";
		res+="               <Diving>"+this.getDiv()+"</Diving>\r\n";
		res+="               <Handling>"+this.getHan()+"</Handling>\r\n";
		res+="               <Kicking>"+this.getKick()+"</Kicking>\r\n";
		res+="               <Reflexes>"+this.getRef()+"</Reflexes>\r\n";
		res+="               <Speed>"+this.getSpd()+"</Speed>\r\n";
		res+="               <Positioning>"+this.getPing()+"</Positioning>\r\n";
		res+="               <Height>"+this.getHei()+"</Height>\r\n";
		res+="               <Type>GK</Type>\r\n";
		res+="               <Available>"+this.getPlay()+"</Available>\r\n";
		res+="               <Card>"+this.getCard()+"</Card>\r\n";
		res+="               <Duration>"+this.getDur()+"</Duration>\r\n";
		res+="            </Keeper>\r\n";
		return res;
	}
	
	/**
	 * This Method checks if two Goalkeepers are equals to each other.
	 * Criteria:
	 *  - Firstname
	 *  - Lastname
	 *  - Position
	 *  - Price
	 *  - Age
	 *  - Stats (Diving, Handling, Kicking, Reflexes, Positioning and Height)
	 *  @param An object with which the Goalkeeper is checked
	 *  @return A Boolean which indicates whether the Goalkeeper are equal or not.
	 */
	
	public boolean equals(Object other) {
		if(other instanceof Goalkeeper) {
			Goalkeeper that = (Goalkeeper)(other);
			return this.getFnm().equals(that.getFnm()) &
					this.getLnm().equals(that.getLnm()) &
					this.getPos().equals(that.getPos()) &
					this.getPri() == that.getPri() &
					this.getAge() == that.getAge() &
					this.getDiv() == that.getDiv() &
					this.getHan() == that.getHan() &
					this.getKick() == that.getKick() &
					this.getRef() == that.getRef() &
					this.getPing() == that.getPing() &
					this.getHei() == that.getHei();
		}
		return false;
	}
	
	/**
	 * Getters
	 */
	
	/** @return diving score of a keeper*/
	public int getDiv() {return div;}
	/** @return handling score of a keeper*/
	public int getHan() {return han;}
	/** @return kicking score of a keeper*/
	public int getKick() {return kick;}
	/** @return reflex score of a keeper*/
	public int getRef() {return ref;}
	/** @return speed score of a keeper*/
	public int getSpd() {return spd;}
	/** @return positioning score of a keeper*/
	public int getPing() {return ping;}
	/** @return height of a keeper*/
	public int getHei() {return hei;}
	
	/**
	 * Setters
	 */
	
	/** Sets the diving score of a Goalkeeper*/
	public void setDiv(int div) {this.div = div;}
	/** Sets the handling score of a Goalkeeper*/
	public void setHan(int han) {this.han = han;}
	/** Sets the kicking score of a Goalkeeper*/
	public void setKick(int kick) {this.kick = kick;}
	/** Sets the reflex score of a Goalkeeper*/
	public void setRef(int ref) {this.ref = ref;}
	/** Sets the speed score of a Goalkeeper*/
	public void setSpd(int spd) {this.spd = spd;}
	/** Sets the positioning score of a Goalkeeper*/
	public void setPing(int ping) {this.ping = ping;}
	/** Sets the diving score of a Goalkeeper*/
	public void setHei(int hei) {this.hei = hei;}
}
