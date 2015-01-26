package Model;
/**
 * Objects Fieldplayer and their methods are declared here
 * @author Mathias
 */

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
	
	public Fieldplayer(String fnm, String lnm, String pos, int age, int pri, boolean play, int card, int dur, int pac,
			int sho, int pas, int dri, int def, int phy) {
		super(fnm, lnm, pos, age, pri, play, card, dur);
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
		String card = "None", play = "unknown";
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
				+ "    Pace: "+this.getPac()
				+ " Shooting: "+this.getSho()
				+ " Passing: "+this.getPas()
				+ " Dribbling: "+this.getDri()
				+ " Defending: "+this.getDef()
				+ " Physical: "+this.getPhy();
	}
	
	/**
	 * Method toWrite stringifies a Fieldplayer so it can be written to xml-file
	 * @return a xml-writable String
	 */
	
	public String toWrite(){
		String res="            <Player>\r\n";
		res+="               <Firstname>"+this.getFnm()+"</Firstname>\r\n";
		res+="               <Lastname>"+this.getLnm()+"</Lastname>\r\n";
		res+="               <Age>"+this.getAge()+"</Age>\r\n";
		res+="               <Price>"+this.getPri()+"</Price>\r\n";
		res+="               <Pace>"+this.getPac()+"</Pace>\r\n";
		res+="               <Shooting>"+this.getSho()+"</Shooting>\r\n";
		res+="               <Passing>"+this.getPas()+"</Passing>\r\n";
		res+="               <Dribbling>"+this.getDri()+"</Dribbling>\r\n";
		res+="               <Defending>"+this.getDef()+"</Defending>\r\n";
		res+="               <Physical>"+this.getPhy()+"</Physical>\r\n";
		res+="               <Type>"+this.getPos()+"</Type>\r\n";
		res+="               <Available>"+this.getPlay()+"</Available>\r\n";
		res+="               <Card>"+this.getCard()+"</Card>\r\n";
		res+="               <Duration>"+this.getDur()+"</Duration>\r\n";
		res+="            </Player>\r\n";
		return res;
	}
	
	/**This method calculates the individual attacking score of a player
	 * It relies on the position of the player
	 * 
	 * @return Attacking score for 1 player
	 */
	
	public double calcAttScore() {
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
	
	public double calcDefScore() {
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
			return this.getFnm().equals(that.getFnm()) &
					this.getLnm().equals(that.getLnm()) &
					this.getPos().equals(that.getPos()) &
					this.getPri() == that.getPri() &
					this.getAge() == that.getAge() &
					this.getPac() == that.getPac() &
					this.getSho() == that.getSho() &
					this.getPas() == that.getPas() &
					this.getDri() == that.getDri() &
					this.getDef() == that.getDef() &
					this.getPhy() == that.getPhy();
		}
		return false;
	}
	/**
	 * Getters
	 */

	/** @return pace score of a Fieldplayer*/
	public int getPac() {return pac;}
	/** @return shooting score of a Fieldplayer*/
	public int getSho() {return sho;}
	/** @return passing score of a Fieldplayer*/
	public int getPas() {return pas;}
	/** @return dribbling score of a Fieldplayer*/
	public int getDri() {return dri;}
	/** @return defending score of a Fieldplayer*/
	public int getDef() {return def;}
	/** @return physical score of a Fieldplayer*/
	public int getPhy() {return phy;}
	
	/**
	 * Setters
	 */

	/** Sets the pace score of a Fieldplayer*/
	public void setPac(int pac) {this.pac = pac;}
	/** Sets the shooting score of a Fieldplayer*/
	public void setSho(int sho) {this.sho = sho;}
	/** Sets the passing score of a Fieldplayer*/
	public void setPas(int pas) {this.pas = pas;}
	/** Sets the dribbling score of a Fieldplayer*/
	public void setDri(int dri) {this.dri = dri;}
	/** Sets the defending score of a Fieldplayer*/
	public void setDef(int def) {this.def = def;}
	/** Sets the physical score of a Fieldplayer*/
	public void setPhy(int phy) {this.phy = phy;}
}