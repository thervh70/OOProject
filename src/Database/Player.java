package Database;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Player {
	
	private final SimpleStringProperty name;
	private final SimpleStringProperty Tpos;
	
	private String firstname, lastname, pos;
	private int age, pri;
	
	/**
	 * Since Player is abstract, no instances can be made of Player.
	 * Fieldplayer and Goalkeeper inherit from Player via this constructor.
	 * @param firstname
	 * @param lastname
	 * @param pos
	 * @param age
	 * @param pri
	 */
	
	public Player(String firstname, String lastname, String pos, int age, int pri) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.pos = pos;
		this.age = age;
		this.pri = pri;
		this.name = new SimpleStringProperty(lastname + ", " + firstname);
		this.Tpos = new SimpleStringProperty(pos);
	}

	/**
	 * Getters
	 * @return
	 */
	
	public String getFnm() {return firstname; }
	public String getLnm() {return lastname; }
	public String getPos() {return pos;	}
	public int getAge() {return age; }
	public int getPri() {return pri; }
	public SimpleStringProperty getNameProperty() {return name; }
	public String getName(){return name.get(); }
	public SimpleStringProperty getTposProperty() {return Tpos; }
	public String getTpos() {return Tpos.get(); }

	/**
	 * Setters
	 * @param firstname
	 */
	
	public void setFnm(String firstname) {this.firstname = firstname;	}
	public void setLnm(String lastname) {this.lastname = lastname;	}
	public void setPos(String pos) {this.pos = pos; }
	public void setAge(int age) {this.age = age; }
	public void setPri(int pri) {this.pri = pri; }
	public void setName(String fName) { name.set(fName); }
	public void setTpos(String pos) {Tpos.set(pos); }
	
}