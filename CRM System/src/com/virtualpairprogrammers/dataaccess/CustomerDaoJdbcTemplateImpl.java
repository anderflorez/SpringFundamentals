package com.virtualpairprogrammers.dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.domain.Customer;

//@Repository("customerDao") - Currently using the JPA version
public class CustomerDaoJdbcTemplateImpl implements CustomerDao {
	
	private JdbcTemplate template;
	
	//This constants could be injected from the spring xml file
	private static final String CREATE_CUSTOMER = "CREATE TABLE CUSTOMER(CUSTOMER_ID VARCHAR(20), " + 
			"COMPANY_NAME VARCHAR(50), EMAIL VARCHAR(50), TELEPHONE " + 
			"VARCHAR(20), NOTES VARCHAR(255))";
	private static final String CREATE_TB_CALL = "CREATE TABLE TBL_CALL(NOTES VARCHAR(255), TIME_AND_DATE DATE, CUSTOMER_ID VARCHAR(20))";
	private static final String INSERT_CUSTOMER = "INSERT INTO CUSTOMER(CUSTOMER_ID, COMPANY_NAME, " + 
			"EMAIL, TELEPHONE, NOTES) VALUES (?,?,?,?,?)";
	private static final String QUERY_ALL_CUSTOMER = "SELECT CUSTOMER_ID, COMPANY_NAME, EMAIL TELEPHONE, NOTES FROM CUSTOMER";
	private static final String QUERY_CUSTOMER_ID = "SELECT CUSTOMER_ID, COMPANY_NAME, EMAIL TELEPHONE, NOTES FROM CUSTOMER WHERE CUSTOMER_ID = ?";
	private static final String QUERY_CUSTOMER_NAME = "SELECT CUSTOMER_ID, COMPANY_NAME, EMAIL, TELEPHONE, NOTES FROM CUSTOMER WHERE COMPANY_NAME = ?";
	private static final String UPDATE_CUSTOMER = "UPDATE CUSTOMER SET COMPANY_NAME=?, EMAIL=?, TELEPHONE=?, NOTES=? WHERE CUSTOMER_ID=?";
	private static final String DELETE_CUSTOMER = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID=?";
	
	private static final String INSERT_CALL = "INSERT INTO TBL_CALL(NOTES, TIME_AND_DATE, CUSTOMER_ID) VALUES (?,?,?)";
	private static final String QUERY_CALL_CUSTOMER = "SELECT * FROM TB_CALL WHERE CUSTOMER_ID = ?";
	//private static final String QUERY_ALL_CALL = "SELECT * FROM TB_CALL";

	@Autowired
	public CustomerDaoJdbcTemplateImpl(JdbcTemplate template)
	{
		this.template = template;
	}
	
	@PostConstruct
	private void createTables() {
		try
		{
			template.update(CREATE_CUSTOMER);
		}
		catch (BadSqlGrammarException e)
		{
			System.err.println("Asuming the Customer table already exists");
		}

		
		try {
			template.update(CREATE_TB_CALL);
		} 
		catch (BadSqlGrammarException e)
		{
			System.err.println("Asuming the Call table already exists");
		}
	}

	@Override
	public void create(Customer customer)
	{
		template.update(INSERT_CUSTOMER, customer.getCustomerId(), customer.getCompanyName(), customer.getEmail(), 
				customer.getTelephone(), customer.getNotes());
	}

	@Override
	public Customer getById(String customerId) throws RecordNotFoundException
	{
		try
		{
			return template.queryForObject(QUERY_CUSTOMER_ID, new CustomerRowMapper(), customerId);
		}
		catch (IncorrectResultSizeDataAccessException e)
		{
			throw new RecordNotFoundException();
		}
	}

	@Override
	public List<Customer> getByName(String name)
	{
		return template.query(QUERY_CUSTOMER_NAME, new CustomerRowMapper(), name);
	}

	@Override
	public void update(Customer customerToUpdate) throws RecordNotFoundException
	{
		int rowsUpdated = template.update(UPDATE_CUSTOMER, customerToUpdate.getCompanyName(), customerToUpdate.getEmail(), 
							customerToUpdate.getTelephone(), customerToUpdate.getNotes(), customerToUpdate.getCustomerId());
		
		if (rowsUpdated != 1)
		{
			throw new RecordNotFoundException();
		}
	}

	@Override
	public void delete(Customer oldCustomer) throws RecordNotFoundException
	{
		int rowsAffected = template.update(DELETE_CUSTOMER, oldCustomer.getCustomerId());
		
		if (rowsAffected != 1)
		{
			throw new RecordNotFoundException();
		}
	}

	@Override
	public List<Customer> getAllCustomers()
	{
		return template.query(QUERY_ALL_CUSTOMER, new CustomerRowMapper());		
	}

	@Override
	public Customer getFullCustomerDetail(String customerId) throws RecordNotFoundException {
		Customer customer = this.getById(customerId);
		List<Call> calls = template.query(QUERY_CALL_CUSTOMER, new CallRowMapper(), customerId);
		customer.setCalls(calls);
		return customer;
	}

	@Override
	public void addCall(Call newCall, String customerId) throws RecordNotFoundException {
		Customer foundCustomer = this.getById(customerId);
		int insertedRows = template.update(INSERT_CALL, newCall.getNotes(), newCall.getTimeAndDate(), foundCustomer.getCustomerId());
		if (insertedRows != 1)
		{
			System.err.println("Error saving the record");
		}
	}

}

class CustomerRowMapper implements RowMapper<Customer>
{
	@Override
	public Customer mapRow(ResultSet rs, int rowNumber) throws SQLException
	{
		String customerId = rs.getString("CUSTOMER_ID");
		String companyName = rs.getString("COMPANY_NAME");
		String email = rs.getString("EMAIL");
		String telephone = rs.getString("TELEPHONE");
		String notes = rs.getString("NOTES");
		return new Customer(customerId, companyName, email, telephone, notes);
	}	
}

class CallRowMapper implements RowMapper<Call>
{	
	@Override
	public Call mapRow(ResultSet rs, int rowNumber) throws SQLException {
		String notes = rs.getString("NOTES");
		//long timestamp = Long.parseLong(rs.getString("TIME_AND_DATE"));
		//return new Call(notes, new Date(timestamp));
		Date dateAndTime = rs.getDate("TIME_AND_DATE");
		return new Call(notes, dateAndTime);
	}
}
