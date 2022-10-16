package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.internal.queries.ContainerPolicy;

import model.Player;
import model.PlayerGroup;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Oct 13, 2022
 */
public class PlayerGroupHelper {

	private static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PartnerMiniProject");
	public PlayerGroupHelper() {	ContainerPolicy.setDefaultContainerClass(ArrayList.class);	}
	/**
	 * @param playerGroup 
	 * @return
	 */
	public boolean groupExists(PlayerGroup playerGroup) {
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PlayerGroup> query = em.createQuery("SELECT g FROM PlayerGroup g", PlayerGroup.class);
		List<PlayerGroup> result = query.getResultList();
		if(result == null) {return false;}
		for(PlayerGroup g : result)	{
			if(playerGroup.equals(g)) {
				return true;
			}
		}
		return false;
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
		TypedQuery<PlayerGroup> query = em.createQuery("SELECT g FROM PlayerGroup g WHERE g.id = :id",PlayerGroup.class);
		query.setParameter("id", id);
		PlayerGroup result = query.getSingleResult();
		result = em.merge(result);
		result = convertLists(result);
		return result;
	}

	/**
	 * @param group
	 * @return
	 */
	private PlayerGroup convertLists(PlayerGroup group) {
		ArrayList<Player> result;
		if(!group.getPlayers().getClass().equals(ArrayList.class)) {
			List<Player> pl = group.getPlayers();
			result = new ArrayList<Player>();
			for(Player p : pl) {
				result.add(p);
			}
		}else {result = (ArrayList<Player>) group.getPlayers();}
		group.setPlayers(result);
		return group;
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
		em.merge(deleteGroup);
		List<Player> pl = deleteGroup.getPlayers();
		for(Player p : pl) {
			p.getGroups().remove(deleteGroup);
			em.merge(p);
		}
		PlayerGroup g = em.merge(deleteGroup);
		//remove group from players
		List<Player> l = g.getPlayers();
		for(Player p : l) {
			List<PlayerGroup> tempg = p.getGroups();
			tempg.remove(g);
			p.setGroups(tempg);
		}
		g = em.merge(g);
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
