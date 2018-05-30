
public class HibernateDAO implements InvoicingDAO {

	@Override
	public void save(Invoice newInvoice)
	{
		System.out.println("Saving an invoice using hibernate");
	}

	@Override
	public void delete(Invoice oldInvoice) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Invoice invoiceToCancel) {
		// TODO Auto-generated method stub

	}

}
