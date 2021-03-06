package com.virtualpairprogrammers.services.calls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.virtualpairprogrammers.domain.Action;
import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.services.customers.CustomerManagementService;
import com.virtualpairprogrammers.services.customers.CustomerNotFoundException;
import com.virtualpairprogrammers.services.diary.DiaryManagementService;

@Transactional
@Service("callHandlingService")
public class CallHandlingServiceImpl implements CallHandlingService {
	
	private CustomerManagementService customerService;
	private DiaryManagementService diaryService;
	
	@Autowired
	public CallHandlingServiceImpl(CustomerManagementService cms, DiaryManagementService dms)
	{
		this.customerService = cms;
		this.diaryService = dms;
	}

	@Override
	@Transactional(rollbackFor=CustomerNotFoundException.class)
	public void recordCall(String customerId, Call newCall, Collection<Action> actions)
			throws CustomerNotFoundException
	{
		// 1. Call the customer management service to record the call
		customerService.recordCall(customerId, newCall);
		
		// 2. Call the diary service to record the actions
		for (Action nextAction : actions)
		{
			diaryService.recordAction(nextAction);
		}
	}

}
