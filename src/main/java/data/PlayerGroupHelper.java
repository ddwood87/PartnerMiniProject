package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Player;
import model.PlayerGroup;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Oct 13, 2022
 */
public class PlayerGroupHelper {

	private static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PartnerMiniProject");
	public PlayerGroupHelper() {	}
	/**
	 * @param playerGroup 
	 * @return
	 */
	public boolean groupExists(PlayerGroup playerGroup) {
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PlayerGroup> query = em.createQuery("SELECT g FROM PlayerGroup g WHERE g.players = :players", PlayerGroup.class);
		query.setParameter("players", playerGroup.getPlayers());
		List<PlayerGroup> result = query.getResultList();
		if(result == null) {return false;}
		else if(result.size() == 1) { return true;}
		else {return false;}
	}

	/**
	 * @param playerGroup
	 */
	public void addGroup(PlayerGroup playerGroup) {
		if(!groupExists(playerGroup)) {
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			em.persist(playerGroup);
			em.getTransaction().commit();
			em.close();
		}
	}

	/**
	 * @param id
	 * @return
	 */
	public PlayerGroup getGroupById(int id) {
		EntityManager em = emfactory.createEntityManager();
		PlayerGroup result = em.find(PlayerGroup.class, id);
		return result;
	}

	/**
	 * @param playerGroup
	 */
	public void deleteGroup(PlayerGroup playerGroup) {
		PlayerGroup deleteGroup;
		if(groupExists(playerGroup)) {
			if(playerGroup.getId() != 0) {
				deleteGroup = getGroupById(playerGroup.getId());
			}else {
				deleteGroup = getExisting(playerGroup);
			}
		}else {	return; }
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		PlayerGroup g = em.merge(deleteGroup);
		em.remove(g);
		em.getTransaction().commit();
		em.close();
	}
	/**
	 * @param playerGroup
	 * @return
	 */
	private PlayerGroup getExisting(PlayerGroup playerGroup) {
		if(groupExists(playerGroup)) {
			EntityManager em = emfactory.createEntityManager();
			TypedQuery<PlayerGroup> query = em.createQuery("SELECT g FROM PlayerGroup g WHERE g.players = :players AND p.games = :games", PlayerGroup.class);
			query.setParameter("players", playerGroup.getPlayers());
			query.setParameter("games", playerGroup.getGames());
			
			PlayerGroup result = query.getSingleResult();
			return result;
		} else  {return null; }
	}	
}
