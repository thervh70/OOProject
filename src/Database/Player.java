package Database;

public class Player {
	
	private String fnm, lnm, pos;
	private int pri, age, pac, sho, pas, dri, def, phy;
	/**
	 * Constructor creates a Player
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
	public Player(String fnm, String lnm, String pos, int age, int pri, int pac,
			int sho, int pas, int dri, int def, int phy) {
		this.fnm = fnm;
		this.lnm = lnm;
		this.pos = pos;
		this.pri = pri;
		this.age = age;
		this.pac = pac;
		this.sho = sho;
		this.pas = pas;
		this.dri = dri;
		this.def = def;
		this.phy = phy;
	}
	/**
	 * Method toString gives a String-representation of a Player
	 * @return String
	 */
	public String toString() {
		return "Name: "+this.getFnm()+" "+this.getLnm()
				+ " Position: "+this.getPos()+" age: "+this.getAge()+" price: "+this.getPri()+"\n"
				+ "  Pace: "+this.getPac()
				+ " Shooting: "+this.getSho()
				+ " Passing: "+this.getPas()
				+ " Dribbling: "+this.getDri()
				+ " Defending: "+this.getDef()
				+ " Physical "+this.getPhy();
	}
	/**
	 * Method toWrite stringifies a Player so it can be written to xml-file
	 * @return
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
	/**
	 * Getters
	 */
	public String getFnm() {return fnm;}
	public String getLnm() {return lnm;}
	public String getPos() {return pos;}
	public int getPri() {return pri;}
	public int getAge() {return age;}
	public int getPac() {return pac;}
	public int getSho() {return sho;}
	public int getPas() {return pas;}
	public int getDri() {return dri;}
	public int getDef() {return def;}
	public int getPhy() {return phy;}
	/**
	 * Setters
	 */
	public void setFnm(String fnm) {this.fnm = fnm;}
	public void setLnm(String lnm) {this.lnm = lnm;}
	public void setPos(String pos) {this.pos = pos;}
	public void setPri(int pri) {this.pri = pri;}
	public void setAge(int age) {this.age = age;}
	public void setPac(int pac) {this.pac = pac;}
	public void setSho(int sho) {this.sho = sho;}
	public void setPas(int pas) {this.pas = pas;}
	public void setDri(int dri) {this.dri = dri;}
	public void setDef(int def) {this.def = def;}
	public void setPhy(int phy) {this.phy = phy;}
}