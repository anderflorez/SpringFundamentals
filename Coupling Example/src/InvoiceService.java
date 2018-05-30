
public class InvoiceService 
{	
	private InvoicingDAO dao;
	
	public void setDao (InvoicingDAO dao)
	{
		this.dao = dao;
	}
	
	public void raiseInvoice(Invoice newInvoice)
	{
		dao.save(newInvoice);
		
	}
	
	public void updateInvoice(Invoice updatedInvoice)
	{
		dao.update(updatedInvoice);
	}
	
}