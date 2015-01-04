package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public abstract class Player {
		
	private String firstname, lastname, pos, name;
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
		name = firstname + " " + lastname; 
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

	/**
	 * Setters
	 * @param firstname
	 */
	
	public void setFnm(String firstname) {this.firstname = firstname;	}
	public void setLnm(String lastname) {this.lastname = lastname;	}
	public void setPos(String pos) {this.pos = pos; }
	public void setAge(int age) {this.age = age; }
	public void setPri(int pri) {this.pri = pri; }
	
}