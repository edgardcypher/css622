package Driver_test;

/* This class represents a user defined define exception.
 * the class will be called when the user will entered a non numeric character
 * */
public class BadInputException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "BAD: YOU ENTERED A NON-NUMERIC CHARACTER.THE PROGRAM WILL EXIT";
	
	public String getMessage()
	{
		return MESSAGE;
	}
}
