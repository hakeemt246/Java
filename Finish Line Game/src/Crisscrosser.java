/**
 *
 * @author Hakeem Thompson, COMP2232 Assignment #2 Finish Line
 */
public class Crisscrosser extends PlayerItem
{
	Crisscrosser(char s)
	{
		type="crisscrossers";
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
					System.out.println("Please enter 5, 6, 7 or 8");
					break;
					
			}			
			return destination;
	}
}

