/**
 *
 * @author Hakeem Thompson, COMP2232 Assignment #2 Finish Line
 */
public class Bumper extends PlayerItem
{
	Bumper(char s)
	{
		type="bumper";
		symbol=s;
		isFinished=false;
	}
	
	public int[] calcDestination(int direction) //calculates next move
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
				case 5:
					destination[0]--;
					destination[1]++;
					break;
				case 6:
					destination[0]++;
					destination[1]++;
					break;
				case 7:
					destination[0]--;
					destination[1]--;
					break;
				case 8:
					destination[0]++;
					destination[1]--;
					break;
				default:
					System.out.println("Please enter 1, 2, 3, 4, 5, 6, 7 or 8 ");
					break;
					
			}			
			return destination;
	}
}
