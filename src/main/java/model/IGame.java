package model;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Oct 11, 2022
 */
public interface IGame {
	public void setId(int id);
	public int getId();
	public void setPlayers(PlayerGroup players) throws Exception;
	public PlayerGroup getPlayers();
	public void play();
}
