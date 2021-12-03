/**
 *
 * @author Hakeem Thompson, COMP2232 Assignment #2 Finish Line
 */
public class OffBoardException extends Exception
{
	public OffBoardException(String message)
	{
		super(message);
	}
	
	public String toString()
	{
	return( "Destination off board" );
	}
}
