package Database;

public abstract class Player {
	
	private String firstname, lastname, pos;
	private int age, pri;
	
	public Player(String firstname, String lastname, String pos, int age, int pri) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.pos = pos;
		this.age = age;
		this.pri = pri;
	}

	/**
	 * Getters
	 * @return
	 */
	
	public String getFnm() {return firstname;	}
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