package test;

import static org.junit.Assert.*;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.After;
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
	EntityManager em;
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PartnerMiniProject");
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ph = new PlayerHelper();
		player = new Player("John", "Smith", "jsmith");
		em = emfactory.createEntityManager();
	}
	public void addPlayer() {
		em.getTransaction().begin();
		em.persist(player);
		em.getTransaction().commit();
	}
	public Player getPlayer() {
		TypedQuery<Player> query = em.createQuery("SELECT p FROM Player p WHERE p.fname = :fname AND p.lname = :lname AND p.username = :username",Player.class);
		query.setParameter("fname", player.getFname());
		query.setParameter("lname", player.getLname());
		query.setParameter("username", player.getUsername());
		Player result = query.getSingleResult();
		return result;
	}
	public void removePlayer() {
		Player remove = getPlayer();
		
		em.getTransaction().begin();
		Player p = em.merge(remove);
		em.remove(p);
		em.getTransaction().commit();
		player.setId(0);
	}
	@Test
	public void addPlayer_validPlayer(){
		assertFalse(ph.playerExists(player));
		ph.addPlayer(player);
		assertTrue(ph.playerExists(player));
		removePlayer();
		System.out.println("addPlayer_validPlayer");
	}
	@Test
	public void addPlayer_duplicatePlayer() {
		assertFalse(ph.playerExists(player));
		ph.addPlayer(player);
		assertTrue(ph.playerExists(player));
		ph.addPlayer(player);
		removePlayer();
		assertFalse(ph.playerExists(player));
		System.out.println("addPlayer_duplicatePlayer");
	}
	@Test
	public void getPlayerById() {
		addPlayer();
		Player p = getPlayer();
		Player result = ph.getPlayerById(p.getId());
		assertNotNull(result);
		assertTrue(player.equals(result));
		removePlayer();
		System.out.println("getPlayerById");
	}
	@Test
	public void deletePlayer() {
		addPlayer();
		assertTrue(ph.playerExists(player));
		ph.deletePlayer(player);
		assertFalse(ph.playerExists(player));
		System.out.println("deletePlayer");
	}
	@After
	public void tearDown() {
		//Verify entities are deleted after each test
		TypedQuery<Player> query = em.createQuery("SELECT p FROM Player p", Player.class);
		List<Player> players = query.getResultList();
		for(Player p : players) {
			System.out.println(p.toString());
		}
		em.close();
	}
}
