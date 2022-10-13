package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.internal.jpa.EntityManagerSetupImpl;

import model.Player;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Oct 11, 2022
 */
public class PlayerHelper {
	private static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PartnerMiniProject");
	public PlayerHelper() {	}
	
	public List<Player> listAllPlayers(){
		EntityManager em = emfactory.createEntityManager();
		return em.createQuery("SELECT p FROM Player p", Player.class).getResultList();
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
