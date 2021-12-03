/**
 *
 * @author Hakeem Thompson, COMP2232 Assignment #2 Finish Line
 */

public class Prize extends BoardItem // single inheritance from the BoardItem class
{
	
	// data member
	protected int prizeValue; //value of the prize
	
	Prize() //default constructor
	{
		type="prize";
		symbol='P';
		prizeValue=2; 
	}
}
