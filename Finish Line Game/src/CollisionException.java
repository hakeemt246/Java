/**
 *
 * @author Hakeem Thompson, COMP2232 Assignment #2 Finish Line
 */
public class CollisionException extends Exception
{
	public CollisionException(String message)
	{
		super(message);
	}
	
	public String toString()
	{
	return( "Collision imminent" );
	}
}
