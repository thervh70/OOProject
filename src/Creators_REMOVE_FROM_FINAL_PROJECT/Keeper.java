package Creators_REMOVE_FROM_FINAL_PROJECT;

public class Keeper {
	private String Fnm;
	private String Lnm;
	private String Div;
	private String Han;
	private String Kic;
	private String Ref;
	private String Spe;
	private String Pos;
	private String Hei;
	private String Age;
	
	public Keeper(String voornaam, String achternaam, String diving, String handling, String kicking, String reflexes, String speed, String positioning, String height, String age){
		Fnm = voornaam;
		Lnm= achternaam;
		Div = diving;
		Han = handling;
		Kic = kicking;
		Ref = reflexes;
		Spe = speed;
		Pos = positioning;
		Hei = height;
		Age = age;
	}

	public String getFnm() {
		return Fnm;
	}

	public void setFnm(String fnm) {
		Fnm = fnm;
	}

	public String getLnm() {
		return Lnm;
	}

	public void setLnm(String lnm) {
		Lnm = lnm;
	}

	public String getDiv() {
		return Div;
	}

	public void setDiv(String div) {
		Div = div;
	}

	public String getHan() {
		return Han;
	}

	public void setHan(String han) {
		Han = han;
	}

	public String getKic() {
		return Kic;
	}

	public void setKic(String kic) {
		Kic = kic;
	}

	public String getRef() {
		return Ref;
	}

	public void setRef(String ref) {
		Ref = ref;
	}

	public String getSpe() {
		return Spe;
	}

	public void setSpe(String spe) {
		Spe = spe;
	}

	public String getPos() {
		return Pos;
	}

	public void setPos(String pos) {
		Pos = pos;
	}

	public String getHei() {
		return Hei;
	}

	public void setHei(String hei) {
		Hei = hei;
	}

	public String getAge() {
		return Age;
	}

	public void setAge(String age) {
		Age = age;
	}
	
	public String toWrite(){
		String res="      <KEEPER>\r\n";
		res+="         <FIRSTNAME>"+this.getFnm()+"</FIRSTNAME>\r\n";
		res+="         <LASTNAME>"+this.getLnm()+"</LASTNAME>\r\n";
		res+="         <DIVING>"+this.getDiv()+"</DIVING>\r\n";
		res+="         <HANDLING>"+this.getHan()+"</HANDLING>\r\n";
		res+="         <KICKING>"+this.getKic()+"</KICKING>\r\n";
		res+="         <REFLEXES>"+this.getRef()+"</REFLEXES>\r\n";
		res+="         <SPEED>"+this.getSpe()+"</SPEED>\r\n";
		res+="         <POSITIONING>"+this.getPos()+"</POSITIONING>\r\n";
		res+="         <HEIGHT>"+this.getHei()+"</HEIGHT>\r\n";
		res+="         <AGE>"+this.getAge()+"</AGE>\r\n";
		res+="      </KEEPER>\r\n";
		return res;
	}
}
