package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import data.PlayerHelper;
import model.Player;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Oct 11, 2022
 */
public class PlayerHelperTest {
	PlayerHelper ph;
	Player player;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ph = new PlayerHelper();
		player = new Player("John", "Smith", "jsmith");
		
	}

	@Test
	public void addPlayer_validPlayer(){
		assertFalse(ph.playerExists(player));
		ph.addPlayer(player);
		assertTrue(ph.playerExists(player));
	}
	@Test
	public void addPlayer_duplicatePlayer() {
		fail("Not Implemented");
	}
	@Test
	public void getPlayerById() {
		Player result = ph.getPlayerById(player.getId());
		assertTrue(player.equals(result));
	}
	@Test
	public void deletePlayer() {
		assertTrue(ph.playerExists(player));
		ph.deletePlayer(player);
		assertFalse(ph.playerExists(player));
	}
}
