package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.internal.jpa.EntityManagerSetupImpl;
import org.eclipse.persistence.internal.queries.ContainerPolicy;

import model.Player;
import model.PlayerGroup;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Oct 11, 2022
 */
public class PlayerHelper {
	private static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PartnerMiniProject");
	public PlayerHelper() {	ContainerPolicy.setDefaultContainerClass(ArrayList.class);}
	
	public List<Player> listAllPlayers(){
		EntityManager em = emfactory.createEntityManager();
		List<Player> allPlayers = em.createQuery("SELECT p FROM Player p").getResultList();
		return allPlayers;
	}
	public void addPlayer(Player player){
		if(!playerExists(player)) {
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			em.persist(player);
			em.getTransaction().commit();
			em.close();
		}		
	}
	public void deletePlayer(Player player) {
		Player deletePlayer;
		if(playerExists(player)) {
			if(player.getId() != 0) {
				deletePlayer = getPlayerById(player.getId());
			}else {
				deletePlayer = getExisting(player);
			}
		}else {	return; }
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Player p = em.merge(deletePlayer);
		List<PlayerGroup> l = p.getGroups();
		for(PlayerGroup g : l) {
			g.removePlayer(p);
		}
		p = em.merge(p);
		em.remove(p);
		em.getTransaction().commit();
		em.close();
	}
	public Player getPlayerById(int id) {
		EntityManager em = emfactory.createEntityManager();
		Player result = em.find(Player.class, id);
		return result;
	}
	public boolean playerExists(Player player) {
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<Player> query = em.createQuery("SELECT p FROM Player p WHERE p.fname = :fname AND p.lname = :lname AND p.username = :username", Player.class);
		query.setParameter("fname", player.getFname());
		query.setParameter("lname", player.getLname());
		query.setParameter("username", player.getUsername());
		
		List<Player> result = query.getResultList();
		
		if(result == null) {return false;}
		else if(result.size() == 1) { return true;}
		else {return false;}
	}
	public Player getExisting(Player player) {
		if(playerExists(player)) {
			EntityManager em = emfactory.createEntityManager();
			TypedQuery<Player> query = em.createQuery("SELECT p FROM Player p WHERE  p.fname = :fname AND p.lname = :lname AND p.username = :username", Player.class);
			query.setParameter("fname", player.getFname());
			query.setParameter("lname", player.getLname());
			query.setParameter("username", player.getUsername());
			
			Player result = query.getSingleResult();
			return result;
		} else  {return null; }
	}
}
