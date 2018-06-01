package com.virtualpairprogrammers.services.diary;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.virtualpairprogrammers.domain.Action;

public class DiaryManagementServiceMockImpl implements DiaryManagementService {
	
	private Set<Action> allActions;
	
	public DiaryManagementServiceMockImpl()
	{
		this.allActions = new HashSet<>();
		this.allActions.add(new Action("some task to do", new GregorianCalendar(), "userA"));
		this.allActions.add(new Action("another task to do", new GregorianCalendar(), "userA"));
		this.allActions.add(new Action("a third task to do", new GregorianCalendar(), "userA"));
	}

	@Override
	public void recordAction(Action action) {
		allActions.add(action);
	}

	@Override
	public List<Action> getAllIncompleteActions(String requiredUser) {
		List<Action> results = new ArrayList<>();
		for (Action next : allActions)
		{
			if (next.getOwningUser().equals(requiredUser) && !next.isComplete())
			{
				results.add(next);
			}
		}
		return results;
	}

}