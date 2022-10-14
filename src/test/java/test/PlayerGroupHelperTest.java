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

import data.PlayerGroupHelper;
import data.PlayerHelper;
import model.Player;
import model.PlayerGroup;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Oct 13, 2022
 */
public class PlayerGroupHelperTest {
	//PlayerHelper ph;
	PlayerGroupHelper pgh;
	Player player1, player2;
	List<Player> playerList;
	PlayerGroup playerGroup;
	EntityManager em;
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PartnerMiniProject");
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//ph = new PlayerHelper();
		pgh = new PlayerGroupHelper();
		player1 = new Player("John", "Smith", "jsmith");
		player2 = new Player("Jane", "Small", "jsmall");
		em = emfactory.createEntityManager();
		addPlayer(player1);
		addPlayer(player2);
		playerList = em.createQuery("SELECT p FROM Player p", Player.class).getResultList();
		playerGroup = new PlayerGroup(playerList);
	}
	public void addPlayer(Player player) {
		em.getTransaction().begin();
		em.persist(player);
		em.getTransaction().commit();
	}
	public void removePlayer(Player player) {		
		em.getTransaction().begin();
		Player p = em.merge(player);
		em.remove(p);
		em.getTransaction().commit();
		player.setId(0);
	}
	public void addGroup() {
		em.getTransaction().begin();
		em.persist(playerGroup);
		em.getTransaction().commit();
	}
	public PlayerGroup getGroup() {
		TypedQuery<PlayerGroup> query = em.createQuery("SELECT g FROM PlayerGroup g WHERE g.players = :players", PlayerGroup.class);
		query.setParameter("players", playerList);
		PlayerGroup result = query.getSingleResult();
		return result;
	}
	public void removeGroup() {
		em.getTransaction().begin();
		em.merge(playerGroup);
		em.remove(playerGroup);
		em.getTransaction().commit();
	}
	/*
	public Player getPlayer(int id) {
		TypedQuery<Player> query = em.createQuery("SELECT p FROM Player p WHERE p.fname = :fname AND p.lname = :lname AND p.username = :username",Player.class);
		query.setParameter("fname", player.getFname());
		query.setParameter("lname", player.getLname());
		query.setParameter("username", player.getUsername());
		Player result = query.getSingleResult();
		return result;
	}
	*/
	
	@Test
	public void addGroup_validGroup(){
		assertFalse(pgh.groupExists(playerGroup));
		pgh.addGroup(playerGroup);
		assertTrue(pgh.groupExists(playerGroup));
		System.out.println("addGroup_validGroup");
	}
	@Test
	public void addGroup_duplicateGroup() {
		assertFalse(pgh.groupExists(playerGroup));
		pgh.addGroup(playerGroup);
		assertTrue(pgh.groupExists(playerGroup));
		pgh.addGroup(playerGroup);
		removeGroup();
		assertFalse(pgh.groupExists(playerGroup));
		System.out.println("addGroup_duplicateGroup");
	}
	@Test
	public void getGroupByIdTest() {
		addGroup();
		PlayerGroup g = getGroup();
		PlayerGroup result = pgh.getGroupById(g.getId());
		assertNotNull(result);
		assertTrue(g.equals(result));
		removeGroup();
		System.out.println("getGroupByIdTest");
	}
	@Test
	public void deletePlayer() {
		addGroup();
		assertTrue(pgh.groupExists(playerGroup));
		pgh.deleteGroup(playerGroup);
		assertFalse(pgh.groupExists(playerGroup));
		System.out.println("deleteGroup");
	}
	@After
	public void tearDown() {
		//Verify entities are deleted after each test
		TypedQuery<PlayerGroup> query = em.createQuery("SELECT g FROM PlayerGroup g", PlayerGroup.class);
		List<PlayerGroup> groups = query.getResultList();
		for(PlayerGroup g : groups) {
			System.out.println(g.toString());
		}
		removePlayer(player1);
		removePlayer(player2);
		em.close();
	}
}
