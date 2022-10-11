package model;

import java.util.List;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Oct 11, 2022
 */
public class PlayerGroup {
	private int id;
	private List<Player> players;
	private List<IGame> games;
	
	public PlayerGroup() {
		super();
	}
	public PlayerGroup(int id, List<Player> players, List<IGame> games) {
		super();
		this.id = id;
		this.players = players;
		this.games = games;
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
	public List<IGame> getGames() {
		return games;
	}
	public void setGames(List<IGame> games) {
		this.games = games;
	}
	@Override
	public String toString() {
		return "PlayerGroup [id=" + id + ", players=" + players + "]";
	}
	
}
