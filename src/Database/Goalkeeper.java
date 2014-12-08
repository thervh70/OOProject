package Database;

public class Goalkeeper extends Player {
	
	private String fnm, lnm, pos;
	private int age, pri, div, han, kick, ref, spd, ping, hei;
	
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
	
	public String toString() {
		return "Name: "+this.getFnm()+" "+this.getLnm()
				+ " Position: "+this.getPos()+" age: "+this.getAge()+" price: "+this.getPri()+"\n"
				+ "    Diving: "+this.getDiv()
				+ " Handling: "+this.getHan()
				+ " Kicking: "+this.getKick()
				+ " Reflexes: "+this.getRef()
				+ " Speed: "+this.getSpd()
				+ " Positioning: "+this.getPing()
				+ " Height "+this.getHei();
	}
	
	public String toWrite(){
		String res="      <KEEPER>\r\n";
		res+="         <FIRSTNAME>"+this.getFnm()+"</FIRSTNAME>\r\n";
		res+="         <LASTNAME>"+this.getLnm()+"</LASTNAME>\r\n";
		res+="         <AGE>"+this.getAge()+"</AGE>\r\n";
		res+="         <PRICE>"+this.getPri()+"</PRICE>\r\n";
		res+="         <DIVING>"+this.getDiv()+"</DIVING>\r\n";
		res+="         <HANDLING>"+this.getHan()+"</HANDLING>\r\n";
		res+="         <KICKING>"+this.getKick()+"</KICKING>\r\n";
		res+="         <REFLEXES>"+this.getRef()+"</REFLEXES>\r\n";
		res+="         <SPEED>"+this.getSpd()+"</SPEED>\r\n";
		res+="         <POSITIONING>"+this.getPing()+"</POSITIONING>\r\n";
		res+="         <HEIGHT>"+this.getHei()+"</HEIGHT>\r\n";
		res+="         <TYPE>GK</TYPE>\r\n";
		res+="      </PLAYER>\r\n";
		return res;
	}
	
	/**
	 * Getters
	 * @return
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
	 * @param div
	 */
	
	public void setDiv(int div) {this.div = div;}
	public void setHan(int han) {this.han = han;}
	public void setKick(int kick) {this.kick = kick;}
	public void setRef(int ref) {this.ref = ref;}
	public void setSpd(int spd) {this.spd = spd;}
	public void setPing(int ping) {this.ping = ping;}
	public void setHei(int hei) {this.hei = hei;}
}
