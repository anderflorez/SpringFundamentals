
public class CrudeContainer
{
	public static InvoiceService getInvoiceService()
	{
//		InvoicingDAO dao = new InvoicingDAOJdbcImplementation();
		InvoicingDAO dao = new HibernateDAO();
		InvoiceService service = new InvoiceService();
		service.setDao(dao);
		return service;
	}
}
