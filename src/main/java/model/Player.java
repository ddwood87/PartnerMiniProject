package model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Oct 11, 2022
 */
@Entity
@Table(uniqueConstraints= {@UniqueConstraint(columnNames= {"username"})})
public class Player {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	@Column(name="fname")
	private String fname;
	@Column(name="lname")
	private String lname;
	@Column(name="username")
	private String username;
	@JoinColumn(table="player_playergroup", name="groups_ID")
	@ManyToMany(mappedBy = "players", cascade= {CascadeType.REFRESH}, fetch=FetchType.EAGER)
	private List<PlayerGroup> groups;
	
	public Player() {
		super();
		groups = new ArrayList<PlayerGroup>();
	}
	public Player(String fname, String lname, String username) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		groups = new ArrayList<PlayerGroup>();
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
	public List<PlayerGroup> getGroups() {
		return groups;
	}
	public void setGroups(List<PlayerGroup> groups) {
		this.groups = groups;
	}
	@Override
	public String toString() {
		return "Player [id=" + id + ", fname=" + fname + ", lname=" + lname + ", username=" + username + "]";
	}
	@Override
	public boolean equals(Object o) {
		if(o == null || !o.getClass().equals(this.getClass())) {
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
