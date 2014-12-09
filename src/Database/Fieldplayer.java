package Database;

public class Fieldplayer extends Player {
	
	private int pac, sho, pas, dri, def, phy;
	
	/**
	 * Constructor creates a Fieldplayer, inherits firstname, lastname, posititon, age and price from Player
	 * @param fnm Firstname
	 * @param lnm Lastname
	 * @param pos Position
	 * @param age Age
	 * @param pri Price
	 * @param pac Pace
	 * @param sho Shooting
	 * @param pas Passing
	 * @param dri Dribbling
	 * @param def Defending
	 * @param phy Physical
	 */
	
	public Fieldplayer(String fnm, String lnm, String pos, int age, int pri, int pac,
			int sho, int pas, int dri, int def, int phy) {
		super(fnm, lnm, pos, age, pri);
		this.pac = pac;
		this.sho = sho;
		this.pas = pas;
		this.dri = dri;
		this.def = def;
		this.phy = phy;
	}
	
	/**
	 * Method toString gives a String-representation of a Fieldplayer
	 * @return String
	 */
	
	public String toString() {
		return "Name: "+this.getFnm()+" "+this.getLnm()
				+ " Position: "+this.getPos()+" age: "+this.getAge()+" price: "+this.getPri()+"\n"
				+ "    Pace: "+this.getPac()
				+ " Shooting: "+this.getSho()
				+ " Passing: "+this.getPas()
				+ " Dribbling: "+this.getDri()
				+ " Defending: "+this.getDef()
				+ " Physical "+this.getPhy();
	}
	
	/**
	 * Method toWrite stringifies a Fieldplayer so it can be written to xml-file
	 * @return a xml-writable String
	 */
	
	public String toWrite(){
		String res="      <PLAYER>\r\n";
		res+="         <FIRSTNAME>"+this.getFnm()+"</FIRSTNAME>\r\n";
		res+="         <LASTNAME>"+this.getLnm()+"</LASTNAME>\r\n";
		res+="         <AGE>"+this.getAge()+"</AGE>\r\n";
		res+="         <PRICE>"+this.getPri()+"</PRICE>\r\n";
		res+="         <PACE>"+this.getPac()+"</PACE>\r\n";
		res+="         <SHOOTING>"+this.getSho()+"</SHOOTING>\r\n";
		res+="         <PASSING>"+this.getPas()+"</PASSING>\r\n";
		res+="         <DRIBBLING>"+this.getDri()+"</DRIBBLING>\r\n";
		res+="         <DEFENDING>"+this.getDef()+"</DEFENDING>\r\n";
		res+="         <PHYSICAL>"+this.getPhy()+"</PHYSICAL>\r\n";
		res+="         <TYPE>"+this.getPos()+"</TYPE>\r\n";
		res+="      </PLAYER>\r\n";
		return res;
	}
	
	/**This method calculates the individual attacking score of a player
	 * It relies on the position of the player
	 * 
	 * @return Attacking score for 1 player
	 */
	
	public double calcAttScore(){
		double score = 0;
		String pos = this.getPos();
		int pac = this.getPac();
		int sho = this.getSho();
		int pas = this.getPas();
		int dri = this.getDri();
		int def = this.getDef();
		int phy = this.getPhy();

		if(pos.equals("CAM") || pos.equals("LW") || pos.equals("RW")) {
			score = (pac*15 + sho*15 + pas*15 + dri*15 + def*15 + phy*25)/100;
		}
		else if(pos.equals("ST")) {
			score = (pac*10 + sho*25 + pas*15 + dri*15 + def*10 + phy*25)/100;
		}
		
		return score;
	}
	
	/**This method calculates the individual defending score of a player
	 * It relies on the position of the player.
	 * 
	 * @return Defending score for 1 player
	 */
	
	public double calcDefScore(){
		double score = 0;
		String pos = this.getPos();
		int pac = this.getPac();
		int sho = this.getSho();
		int pas = this.getPas();
		int dri = this.getDri();
		int def = this.getDef();
		int phy = this.getPhy();

		if(pos.equals("RB") || pos.equals("CB") || pos.equals("LB")) {
			score = (pac*10 + sho*10 + pas*15 + dri*10 + def*40 + phy*15)/100;
		}
		else if(pos.equals("CDM") || pos.equals("CM")) {
			score = (pac*15 + sho*10 + pas*20 + dri*10 + def*25 + phy*20)/100;
		}
		
		return score;
	}
	
	/**
	 * Method equals checks if two Fieldplayers are equals to each other
	 * Criteria:
	 *  - Firstname
	 *  - Lastname
	 *  - Position
	 *  - Price
	 *  - Age
	 *  - Stats (Pace, Shooting, Passing, Defending, Dribbling, Physical)
	 *  @param An object with which the Fieldplayer is checked
	 *  @return A Boolean which indicates whether the Fieldplayers are equal or not.
	 */
	
	public boolean equals(Object other) {
		if(other instanceof Fieldplayer) {
			Fieldplayer that = (Fieldplayer)(other);
			return this.getFnm().equals(that.getFnm()) &&
					this.getLnm().equals(that.getLnm()) &&
					this.getPos().equals(that.getPos()) &&
					this.getPri() == that.getPri() &&
					this.getAge() == that.getAge() &&
					this.getPac() == that.getPac() &&
					this.getSho() == that.getSho() &&
					this.getPas() == that.getPas() &&
					this.getDri() == that.getDri() &&
					this.getDef() == that.getDef() &&
					this.getPhy() == that.getPhy();
		}
		return false;
	}
	/**
	 * Getters
	 */

	public int getPac() {return pac;}
	public int getSho() {return sho;}
	public int getPas() {return pas;}
	public int getDri() {return dri;}
	public int getDef() {return def;}
	public int getPhy() {return phy;}
	
	/**
	 * Setters
	 */

	public void setPac(int pac) {this.pac = pac;}
	public void setSho(int sho) {this.sho = sho;}
	public void setPas(int pas) {this.pas = pas;}
	public void setDri(int dri) {this.dri = dri;}
	public void setDef(int def) {this.def = def;}
	public void setPhy(int phy) {this.phy = phy;}
}