package model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Oct 11, 2022
 */
@Entity
public class Player {
	@Id
	@GeneratedValue
	private int id;
	@Column(name="first_name")
	private String fname;
	@Column(name="last_name")
	private String lname;
	@Column(name="user_name", unique = true)
	private String username;
	
	public Player() {
		super();
	}
	public Player(String fname, String lname, String username) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.username = username;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "Player [id=" + id + ", fname=" + fname + ", lname=" + lname + ", username=" + username + "]";
	}
	@Override
	public boolean equals(Object o) {
		if(!o.getClass().equals(this.getClass())) {
			return false;
		}else {
			Player p = (Player)o;
			if(p.getFname().equals(this.fname) &&
				p.getLname().equals(this.lname)&&
				p.getUsername().equals(this.username)) {
				return true;
			}
			return false;
		}
	}
	
}
