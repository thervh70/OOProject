package Database;

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
	
	public Goalkeeper(String firstname, String lastname, String pos, int age, int price, int diving, int handling, int kicking,
			int reflexes, int speed, int positioning, int height) {
		super(firstname, lastname, pos, age, price);
		this.div = diving;
		this.han = handling;
		this.kick = kicking;
		this.ref = reflexes;
		this.spd = speed;
		this.ping = positioning;
		this.hei = height;
	}
	
	/**
	 * Method toString gives a String-representation of a Goalkeeper
	 * @return String
	 */
	
	public String toString() {
		return "  Name: "+this.getFnm()+" "+this.getLnm()
				+ " Position: "+this.getPos()+" age: "+this.getAge()+" price: "+this.getPri()+"\n"
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
		String res="         <KEEPER>\r\n";
		res+="            <FIRSTNAME>"+this.getFnm()+"</FIRSTNAME>\r\n";
		res+="            <LASTNAME>"+this.getLnm()+"</LASTNAME>\r\n";
		res+="            <AGE>"+this.getAge()+"</AGE>\r\n";
		res+="            <PRICE>"+this.getPri()+"</PRICE>\r\n";
		res+="            <DIVING>"+this.getDiv()+"</DIVING>\r\n";
		res+="            <HANDLING>"+this.getHan()+"</HANDLING>\r\n";
		res+="            <KICKING>"+this.getKick()+"</KICKING>\r\n";
		res+="            <REFLEXES>"+this.getRef()+"</REFLEXES>\r\n";
		res+="            <SPEED>"+this.getSpd()+"</SPEED>\r\n";
		res+="            <POSITIONING>"+this.getPing()+"</POSITIONING>\r\n";
		res+="            <HEIGHT>"+this.getHei()+"</HEIGHT>\r\n";
		res+="            <TYPE>GK</TYPE>\r\n";
		res+="         </KEEPER>\r\n";
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
			return this.getFnm().equals(that.getFnm()) &&
					this.getLnm().equals(that.getLnm()) &&
					this.getPos().equals(that.getPos()) &&
					this.getPri() == that.getPri() &&
					this.getAge() == that.getAge() &&
					this.getDiv() == that.getDiv() &&
					this.getHan() == that.getHan() &&
					this.getKick() == that.getKick() &&
					this.getRef() == that.getRef() &&
					this.getPing() == that.getPing() &&
					this.getHei() == that.getHei();
		}
		return false;
	}
	
	/**
	 * Getters
	 */
	
	public int getDiv() {return div;}
	public int getHan() {return han;}
	public int getKick() {return kick;}
	public int getRef() {return ref;}
	public int getSpd() {return spd;}
	public int getPing() {return ping;}
	public int getHei() {return hei;}
	
	/**
	 * Setters
	 */
	
	public void setDiv(int div) {this.div = div;}
	public void setHan(int han) {this.han = han;}
	public void setKick(int kick) {this.kick = kick;}
	public void setRef(int ref) {this.ref = ref;}
	public void setSpd(int spd) {this.spd = spd;}
	public void setPing(int ping) {this.ping = ping;}
	public void setHei(int hei) {this.hei = hei;}
}
