/**
 *
 * @author Hakeem Thompson, COMP2232 Assignment #2 Finish Line
 */
public class Player 
{

	//data members
	protected String name; //name of the player
	protected int numFinish; //number of pieces the player got to the finish line
	protected int numInPlay;//number of pieces the player can play
	protected boolean loseTurn; //determines if the player should lose a turn 
	protected boolean gainTurn;//determines if the player should gain a turn
	protected Stepper stepper;
	protected Crisscrosser crisscrosser;
	protected Bumper bumper;
	

	//methods
	public Player(String n) //explicit constructor
	{
		name=n;
		numFinish=0;
		loseTurn=false;	
		gainTurn=false;
		numInPlay=3;
	}

	public void updateNumFinish()
	{
		numFinish++;
	}
	public void updateNumInPlay()
	{
		numInPlay--;
	}
	//mutators
	public void setName(String newName)
	{
		name=newName;
	}

	public void setNumFinish(int num)
	{
		numFinish=num;
	}
	public void setNumInPlay(int num)
	{
		numInPlay=num;
	}
	public void setLoseTurn(boolean missedTurn)
	{
		loseTurn=missedTurn;
	}
	
	public void setGainTurn(boolean turn)
	{
		gainTurn=turn;
	}

	//accessors
	public String getName()
	{
		return name;
	}

	public int getNumFinish()
	{
		assert numFinish <4;
		return numFinish;
		
	}
	public int getNumInPlay()
	{
		return numInPlay;
		
	}
	public boolean getLoseTurn()
	{
		return loseTurn;
	}
	public boolean getGainTurn()
	{
		return gainTurn;
	}
	
	public void setPieces(Stepper s,Crisscrosser c, Bumper b)
	{
		stepper=s;
		crisscrosser=c;
		bumper=b;
		
	}
	
	public boolean isPieceFinished(int piece)
	{	
		boolean home=false;
		
		if (piece==1) {
		
			//System.out.println("stepper: "+stepper);
			home= stepper.getIsFinished();
		}
		else if (piece==2)
			home=crisscrosser.getIsFinished();
		else if (piece==3)
			home= bumper.getIsFinished();
		
		return home;
	}
	
	public PlayerItem getPiece(int piece)
	{		
		PlayerItem reference=null;
		
		if (piece==1)
			reference=stepper;
		else if (piece==2)
			reference=crisscrosser;
		else if (piece==3)
			reference= bumper;
		
		return reference;
		
		
	}
}
