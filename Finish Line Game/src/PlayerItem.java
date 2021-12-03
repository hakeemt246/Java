/**
 *
 * @author Hakeem Thompson, COMP2232 Assignment #2 Finish Line
 */
public abstract class PlayerItem extends BoardItem
{
	//data members
	protected int[] location; //stores location of player piece
	protected boolean isFinished;
	
	
	PlayerItem()
	{
		location = new int[2];
		isFinished=false;
	}
	//member functions
	public void setLocation(int r, int c)
	{
		location[0]=r;
		location[1]=c;
	}
	
	public int[] getLocation()
	{
		return location;
	}
	
	public boolean getIsFinished()
	{
		return isFinished;
	}
	
	public void setIsFinished(boolean status)
	{
		isFinished=status;
	}
	
	public abstract int[] calcDestination(int direction);
}
