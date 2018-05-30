
public class DatabaseUnavailableException extends RuntimeException 
{
	private static final long serialVersionUID = -4038612267948068753L;

	public DatabaseUnavailableException(Throwable root)
	{
		super(root);
	}
	
	public DatabaseUnavailableException()
	{
		super();
	}
}
