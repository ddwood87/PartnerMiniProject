package model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Oct 11, 2022
 */
@Entity
@MappedSuperclass
@DiscriminatorColumn(name="GAME_TYPE", discriminatorType = DiscriminatorType.STRING)
public class Game {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	private PlayerGroup players;
	
	public Game() {}
	public Game(PlayerGroup players) {
		this.players = players;
	}
	public void setId(int id) { this.id = id;}
	public int getId() {return id;}
	public void setPlayers(PlayerGroup players) {this.players = players;}
	public PlayerGroup getPlayers() {return players;}
	//public void play();
}
