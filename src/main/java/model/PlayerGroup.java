package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Oct 11, 2022
 */
@Entity
public class PlayerGroup {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	@JoinTable(
		name="player_playergroup", 
		joinColumns = {@JoinColumn(name="Group_ID")},
		inverseJoinColumns = {@JoinColumn(name = "Player_ID")}
	)
	@ManyToMany(cascade={CascadeType.REFRESH}, fetch=FetchType.EAGER)
	private List<Player> players;
	private List<Game> games;
	
	public PlayerGroup() {
		super();
		players = new ArrayList<Player>();
		games = new ArrayList<Game>();
	}
	public PlayerGroup(List<Player> players) {
		super();
		this.setPlayers(players);
		games = new ArrayList<Game>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<Game> getGames() {
		return games;
	}
	public void setGames(List<Game> games) {
		this.games = games;
	}
	public void addPlayer(Player p) {
		players.add(p);
	}
	public void removePlayer(Player p) {
		players.remove(p);
	}
	@Override
	public boolean equals(Object o) {
		boolean result = false;
		if(o == null || !o.getClass().equals(this.getClass())){
			return false;
		}
		else {
			PlayerGroup g = (PlayerGroup)o;
			List<Player> pl = players;
			pl.removeAll(g.getPlayers());
			if(pl.isEmpty()) {
				List<Game> gl = games;
				gl.removeAll(g.getGames());
				if(pl.isEmpty()) {
					result = true;
				}
			}
		}
		return result;
	}
	@Override
	public String toString() {
		return "PlayerGroup [id=" + id + ", players=" + players + "]";
	}
	/**
	 * @param p
	 */
	
	
}
