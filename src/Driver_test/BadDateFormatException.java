package Driver_test;

public class BadDateFormatException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "BAD: YOU ENTERED A WRONG DATE FORMAT";
	
	public String getMessage()
	{
		return MESSAGE;
	}
}
