package com.virtualpairprogrammers.dataaccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.domain.Customer;

@Repository
public class CustomerDaoJpaImpl implements CustomerDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Customer customer)
	{
		em.persist(customer);
	}

	@Override
	public Customer getById(String customerId) throws RecordNotFoundException
	{
		try {
			return em.createQuery("select customer from Customer as customer where customer.customerId = :customerId", Customer.class)
					.setParameter("customerId", customerId)
					.getSingleResult();
		}
		catch (NoResultException e)
		{
			throw new RecordNotFoundException();
		}
 
	}

	@Override
	public List<Customer> getByName(String name)
	{
		return em.createQuery("select customer from Customer as customer where customer.companyName = :name", Customer.class)
										.setParameter("name", name)
										.getResultList();
	}

	@Override
	public void update(Customer customerToUpdate) throws RecordNotFoundException
	{
		Customer customer = this.getById(customerToUpdate.getCustomerId());
		em.merge(customer);
	}

	@Override
	public void delete(Customer oldCustomer) throws RecordNotFoundException
	{
		Customer customer = this.getById(oldCustomer.getCustomerId());
		em.remove(customer);
	}

	@Override
	public List<Customer> getAllCustomers()
	{
		return em.createQuery("select customer from Customer customer", Customer.class).getResultList();
	}

	@Override
	public Customer getFullCustomerDetail(String customerId) throws RecordNotFoundException
	{
		try {
			return em.createQuery("select customer from Customer customer left join fetch customer.calls where customer.customerId = :customerId", Customer.class)
									.setParameter("customerId", customerId)
									.getSingleResult();
		}
		catch (NoResultException e)
		{
			throw new RecordNotFoundException();
		}
	}

	@Override
	public void addCall(Call newCall, String customerId) throws RecordNotFoundException
	{
		Customer customer = this.getById(customerId);
		customer.addCall(newCall);
	}

}
