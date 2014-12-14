package Database;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Player {
	
	private final SimpleStringProperty first;
	
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
		this.first = new SimpleStringProperty(firstname);
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
	public StringProperty getfirstProperty(){return first;}

	/**
	 * Setters
	 * @param firstname
	 */
	
	public void setFnm(String firstname) {this.firstname = firstname;	}
	public void setLnm(String lastname) {this.lastname = lastname;	}
	public void setPos(String pos) {this.pos = pos; }
	public void setAge(int age) {this.age = age; }
	public void setPri(int pri) {this.pri = pri; }

	public SimpleStringProperty getFirstProperty() {
		return first;
	}
	public void setFirst(String fName) { first.set(fName); }
	public String getFirst(){return first.get();}
}