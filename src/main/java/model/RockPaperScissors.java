package model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Oct 11, 2022
 */
//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorValue("RockPaperScissors")
public class RockPaperScissors extends Game {
	
	private int player1score;	
	private int player2score;

	public RockPaperScissors() {
		super();
	}
	
	public RockPaperScissors(PlayerGroup players, int player1score, int player2score) {
		super(players);
		this.player1score = player1score;
		this.player2score = player2score;
	}

	@Override
	public void setPlayers(PlayerGroup players) {
		if(players.getPlayers().size() <= 2) {
			super.setPlayers(players);
		}
		else{ System.out.println("There are too many players for this game."); }
	}
}
