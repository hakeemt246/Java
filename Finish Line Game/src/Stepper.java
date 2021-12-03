/**
 *
 * @author Hakeem Thompson, COMP2232 Assignment #2 Finish Line
 */
public class Stepper extends PlayerItem
{
	//member functions
	Stepper(char s)
	{
		type="stepper";
		symbol=s;
		isFinished=false;
	}
	
	public int[] calcDestination(int direction)
	{
		int[] destination= new int[2];
		destination[0]=location[0];
		destination[1]=location[1];
		
			switch (direction)
			{
				case 1:
					destination[0]--;
					break;
				case 2:
					destination[0]++;
					break;
					
				case 3:
					destination[1]++;
					break;
			case 4:
					destination[1]--;
					break;
				default:
					System.out.println("Please enter 1, 2, 3 or 4");
					break;
					
			}			
			return destination;
	}
}
