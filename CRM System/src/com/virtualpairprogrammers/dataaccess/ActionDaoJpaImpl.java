package com.virtualpairprogrammers.dataaccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.virtualpairprogrammers.domain.Action;

@Repository
public class ActionDaoJpaImpl implements ActionDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Action newAction)
	{
		em.persist(newAction);
	}

	@Override
	public List<Action> getIncompleteActions(String userId)
	{
		return em.createQuery("select action from Action action where action.owningUser = :userId and action.complete = false", Action.class)
					.setParameter("userId", userId)
					.getResultList();
	}

	@Override
	public void update(Action actionToUpdate) throws RecordNotFoundException
	{
		Action action = this.getById(actionToUpdate.getActionId());
		em.merge(action);
	}

	@Override
	public void delete(Action oldAction) throws RecordNotFoundException
	{
		Action action = this.getById(oldAction.getActionId());
		em.remove(action);
	}
	
	private Action getById(int actionId) throws RecordNotFoundException
	{
		Action action = em.createQuery("select action from Action action where action.actionId = :actionId", Action.class)
							.setParameter("actionId", actionId)
							.getSingleResult();
		if (action == null)
		{
			throw new RecordNotFoundException();
		}
		return action;
	}

}
