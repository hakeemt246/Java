/**
 *
 * @author Hakeem Thompson, COMP2232 Assignment #2 Finish Line
 */
public class BoardItem 
{

	//data members
	protected String type; //the type of the item (obstacle or prize)
	protected char symbol; //holds the character of the item that will be displayed on the board
	
	//methods
	BoardItem() //default constructor
	{
		type="";
		symbol=' ';
	}
	
	//mutators
	public void setType(String item)
	{
		type=item;
	}
	
	public void setSymbol(char identity)
	{
		symbol=identity;
	}
	
	//accessors
	public String getType()
	{
		return type;
	}
	
	public char getSymbol()
	{
		return symbol;
	}
}
