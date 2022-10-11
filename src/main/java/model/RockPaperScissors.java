package model;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Oct 11, 2022
 */
public class RockPaperScissors implements IGame {
	private int id;
	private PlayerGroup players;
	private int player1score;
	private int player2score;

	public RockPaperScissors() {
		super();
	}
	
	public RockPaperScissors(int id, PlayerGroup players, int player1score, int player2score) {
		super();
		this.id = id;
		this.players = players;
		this.player1score = player1score;
		this.player2score = player2score;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setPlayers(PlayerGroup players) throws Exception {
		if(players.getPlayers().size() <= 2) {
			this.players = players;
		}
		else{ throw new Exception("Must have two players or less to play."); }
	}

	@Override
	public PlayerGroup getPlayers() {
		return players;
	}

	@Override
	public void play() {
		player1score = 0;
		player2score = 0;
		
		//Call game servlet
	}

}
