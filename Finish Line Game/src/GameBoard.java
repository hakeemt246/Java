/**
 *
 * @author Hakeem Thompson, COMP2232 Assignment #2 Finish Line
 */

import java.util.Random;
import java.util.Scanner;

public class GameBoard
{
	
	
	//data members
	protected int numRows; //number of rows for the game board
	protected int numCols; //number of columns for the game board
	protected BoardItem[][] gameBoard; //The game board for Finish Line. An object in the board item class
	protected int numObstacles; //number of obstacles on the game board
	protected int numPrizes; //number of prizes on the game board
	
	//constructor
	GameBoard()
	{
		numRows=6;
		numCols=18;
		numObstacles=6;
		numPrizes=3;
		gameBoard = new BoardItem[numRows][numCols];
		
	}
	
	GameBoard(int nRows, int nCols, int nObstacles, int nPrizes)
	{
		numRows=nRows;
		numCols=nCols;
		numObstacles=nObstacles;
		numPrizes=nPrizes;
		gameBoard = new BoardItem[numRows][numCols];
		
	}
	
	void populateBoard() //adds items to the board
	{
		Obstacle enemy;
		Prize winner;
		for (int i=0; i<numObstacles;i++)
			{
				addBoardItem(new Obstacle());
			}
		for (int j=0; j<numPrizes; j++)
			{
				addBoardItem(new Prize());
			}
		
		
		displayBoard();
		
	}	
	
	void addBoardItem(BoardItem item) // adds board items to the game board
	{
		
		
		Random randNum = new Random();
		int randRow;
		int randCol;
		
		do
		{
			randRow=randNum.nextInt(numRows);
			
			if (item instanceof PlayerItem)
			{
				randCol = 0;
			}
			else
			{
				randCol =randNum.nextInt(numCols-2)+1;
			}
			
		} while ((gameBoard[randRow][randCol]!=null));		
		
		if (item instanceof PlayerItem)
		{
			((PlayerItem)item).setLocation(randRow, randCol);
		}
		gameBoard[randRow][randCol]= item;

		/*if (item instanceof PlayerItem)
			((PlayerItem)item).setLocation(randRow, 0);*/
	}
	
	boolean removePrize(int row, int col)
	{
		
		if (gameBoard[row][col] instanceof Prize)
			gameBoard[row][col]=null;
		
			return true;
	} 
	
	void displayBoard() //Displays the game board
	{
		
			System.out.println("");
			System.out.print("  ");
			System.out.print("|S");
		
			for (int i=1;i<numCols-1;i++)
			{
				System.out.print("|<");
			}
		
			System.out.print("|H|");
			System.out.println("");
						
			for (int hyphens=1;hyphens<((numCols-1)*2+6);hyphens++)
			{
				System.out.print("-");
				
			}
			System.out.println("");
			
			for (int row=0;row<numRows;row++)
			{
				System.out.print(+(row+1)+" |");
				
				for(int col=0;col<numCols-1;col++)
				{
					if (gameBoard[row][col]!=null)
					{
						System.out.print(gameBoard[row][col].getSymbol()+"|");
					}
					else
					{
						System.out.print(" |");
					}
										
				}//col for loop
				
				System.out.print(" |");
				
				System.out.println("");
				for (int hyphens=1;hyphens<(numCols-1)*2+6;hyphens++)
				{
					System.out.print("-");
					
				}
				System.out.println("");
				
			} //row for loop
			
			System.out.println("");
			System.out.println("Prizes remaining on board: "+numPrizes);
			System.out.println("");
			
	} //displayBoard
	
	public void placePlayerPieces(Player p1, Player p2)
	{	
		Stepper stepperP1=new Stepper('S');
		Crisscrosser crisscrosserP1= new Crisscrosser('C');
		Bumper bumperP1= new Bumper('B');
		Stepper stepperP2=new Stepper('s');
		Crisscrosser crisscrosserP2= new Crisscrosser('c');
		Bumper bumperP2= new Bumper('b');
		
		p1.setPieces(stepperP1,crisscrosserP1, bumperP1);
		p2.setPieces(stepperP2,crisscrosserP2,bumperP2);
		
		addBoardItem(stepperP1);
		addBoardItem(stepperP2);
		addBoardItem(crisscrosserP1);
		addBoardItem(crisscrosserP2);
		addBoardItem(bumperP1);
		addBoardItem(bumperP2);
		
	}
	
	public void displayMovementOptions(int piece)
	{	
		
		switch (piece)
		{
			case 1:
				System.out.println("Enter 1 to go to the north, 2 to the south, 3 to the east and 4 to the west");
				break;
			case 2:
				System.out.println("Enter 5 to go to the northeast, 6 to the southeast, 7 to the northwest and 8 to the southwest");
				break;
			case 3:
				System.out.println("Enter 1 to go to the north, 2 to the south, 3 to the east and 4 to the west");
				System.out.print("5 to go to the northeast, 6 to the southeast, 7 to the northwest and 8 to the southwest");
				System.out.println("");
				break;
			default:
				System.out.println("Please enter 1, 2, 3");
				break;
				
		}
		
	}
	
	public boolean validateMove(int row, int col) throws OffBoardException, CollisionException
	{	boolean valid=false;
		if (row<0 || row>numRows-1)
			throw new OffBoardException("Re-enter or die");
		else if (col<0 || col>numCols-1)	
			throw new OffBoardException("Re-enter or die");
		else if (gameBoard[row][col] ==null)
			valid=true;
		else if(gameBoard[row][col] instanceof PlayerItem)
			throw new CollisionException("Piece at destination");
		else if (gameBoard[row][col] instanceof Obstacle)
			throw new CollisionException("Obstacle at destination");
		return valid;
	}
	
	public void runPlayerTurn(Player p)
	{	
		Scanner Output = new Scanner(System.in);
		System.out.println("Press 1 to move the stepper, 2 for the crisscrosser and 3 for the bumper");
		int piece = Output.nextInt();
		
		switch (piece)
		{
			case 1:
				Stepper stepperRef;
				if (p.isPieceFinished(1)==false)
				{
					stepperRef=(Stepper)p.getPiece(1);
					
					displayMovementOptions(1);
					int move = Output.nextInt();
					
					
						if (move==1)
						{	
						
								int result[]=stepperRef.calcDestination(1);
							
							try
							{
								int prevLocation[]=stepperRef.getLocation();
								int prevRow=prevLocation[0];
								int prevCol=prevLocation[1];
								validateMove(result[0],result[1]);								
								stepperRef.setLocation(result[0], result[1]);
								if(gameBoard[result[0]][result[1]] instanceof Prize)
								{
									removePrize(result[0],result[1]);
									numPrizes--;
									p.setGainTurn(true);
									System.out.println("You have another turn");
								}
								gameBoard[result[0]][result[1]]=stepperRef; 
								gameBoard[prevRow][prevCol]=null;
							}
							
							catch (OffBoardException e)
							{
								System.out.println(e.getMessage()+". Enter another move");
								p.setGainTurn(true);
							}
							
							catch (CollisionException e)
							{
								if (gameBoard[result[0]][result[1]] instanceof PlayerItem)
								{
									System.out.println(e.getMessage());
									System.out.println(" "+"Invalid move. Enter another move");
									p.setGainTurn(true);
								}
								else if(gameBoard[result[0]][result[1]] instanceof Obstacle) 
								{	
									System.out.println(e.getMessage());
									p.setLoseTurn(true);
								}
							}
							
						//}
					}
						else if (move==2)
						{
							int result[]=stepperRef.calcDestination(2);
							
							try
							{
								int prevLocation[]=stepperRef.getLocation();
								int prevRow=prevLocation[0];
								int prevCol=prevLocation[1];
								validateMove(result[0],result[1]);								
								stepperRef.setLocation(result[0], result[1]);
								if(gameBoard[result[0]][result[1]] instanceof Prize)
								{
									removePrize(result[0],result[1]);
									numPrizes--;
									p.setGainTurn(true);
									System.out.println("You have another turn");
								}
								gameBoard[result[0]][result[1]]=stepperRef; 
								gameBoard[prevRow][prevCol]=null;
							}
							
							catch (OffBoardException e)
							{
								System.out.println(e.getMessage()+". Enter another move");
								p.setGainTurn(true);
							}
							
							catch (CollisionException e)
							{
								if (gameBoard[result[0]][result[1]] instanceof PlayerItem)
								{
									System.out.println(e.getMessage());
									System.out.println(" "+"Invalid move. Enter another move");
									p.setGainTurn(true);
								}
								else if(gameBoard[result[0]][result[1]] instanceof Obstacle) 
								{	
									System.out.println(e.getMessage());
									p.setLoseTurn(true);
								}
								
								
							}
						}
						else if (move==3)
						{
							int result[]=stepperRef.calcDestination(3);
							
							try
							{
								int prevLocation[]=stepperRef.getLocation();
								int prevRow=prevLocation[0];
								int prevCol=prevLocation[1];
								validateMove(result[0],result[1]);								
								stepperRef.setLocation(result[0], result[1]);
								if(gameBoard[result[0]][result[1]] instanceof Prize)
								{
									removePrize(result[0],result[1]);
									numPrizes--;
									p.setGainTurn(true);
									System.out.println("You have another turn");
								}
								
								gameBoard[result[0]][result[1]]=stepperRef;
								gameBoard[prevRow][prevCol]=null; 
								int curRow=result[0];
								int curCol=result[1];
								if (curCol==numCols-1)
								{
									p.updateNumFinish();
									p.updateNumInPlay();
									stepperRef.setIsFinished(true);
									System.out.println("Your Stepper is Home!");
									System.out.println("");
								}
							}
							
							catch (OffBoardException e)
							{
								System.out.println(e.getMessage()+". Enter another move");
								p.setGainTurn(true);
								
							}
							
							catch (CollisionException e)
							{
								if (gameBoard[result[0]][result[1]] instanceof PlayerItem)
								{
									System.out.println(e.getMessage());
									System.out.println(" "+"Invalid move. Enter another move");
									p.setGainTurn(true);
									
								}
								else if(gameBoard[result[0]][result[1]] instanceof Obstacle) 
								{	
									System.out.println(e.getMessage());
									p.setLoseTurn(true);									
								}
							}
							
						}
						else if (move==4)
						{
							int result[]=stepperRef.calcDestination(4);
	
							try
							{
								int prevLocation[]=stepperRef.getLocation();
								int prevRow=prevLocation[0];
								int prevCol=prevLocation[1];
								validateMove(result[0],result[1]);								
								stepperRef.setLocation(result[0], result[1]);
								if(gameBoard[result[0]][result[1]] instanceof Prize)
								{
									removePrize(result[0],result[1]);
									numPrizes--;
									p.setGainTurn(true);
									System.out.println("You have another turn");
								}
								gameBoard[result[0]][result[1]]=stepperRef; 
								gameBoard[prevRow][prevCol]=null;
							}
							
							catch (OffBoardException e)
							{
								System.out.println(e.getMessage()+". Enter another move");
								p.setGainTurn(true);
							}
							
							catch (CollisionException e)
							{
								if (gameBoard[result[0]][result[1]] instanceof PlayerItem)
								{
									System.out.println(e.getMessage());
									System.out.println(" "+"Invalid move. Enter another move");
									p.setGainTurn(true);
								}
								else if(gameBoard[result[0]][result[1]] instanceof Obstacle) 
								{	
									System.out.println(e.getMessage());
									p.setLoseTurn(true);
								}
							}
							break;
						}
						else {
							System.out.println("Invalid option for the Stepper. Please enter 1, 2, 3 or 4");
							break;
						}
							
						
					}
					
					break;
			
				
			case 2:
				Crisscrosser crisscrosserRef;
				
				if (p.isPieceFinished(2)==false)
				{
					crisscrosserRef=(Crisscrosser)p.getPiece(2);
					
					displayMovementOptions(2);
					int move = Output.nextInt();
					
					if (move==5)
					{
						int result[]=crisscrosserRef.calcDestination(5);
						
						try
						{
							int prevLocation[]=crisscrosserRef.getLocation();
							int prevRow=prevLocation[0];
							int prevCol=prevLocation[1];
							validateMove(result[0],result[1]);								
							crisscrosserRef.setLocation(result[0], result[1]);
							if(gameBoard[result[0]][result[1]] instanceof Prize)
							{
								removePrize(result[0],result[1]);
								numPrizes--;
								p.setGainTurn(true);
								System.out.println("You have another turn");
							}
							gameBoard[result[0]][result[1]]=crisscrosserRef; 
							int curRow=result[0];
							int curCol=result[1];
							if (curCol==numCols-1)
							{
								p.updateNumFinish();
								p.updateNumInPlay();
								System.out.println("Your Crisscrosser is Home!");
								System.out.println("");
							}
							gameBoard[prevRow][prevCol]=null;
						}
						
						catch (OffBoardException e)
						{
							System.out.println(e.getMessage()+". Enter another move");
							p.setGainTurn(true);
						}
						
						catch (CollisionException e)
						{
							if (gameBoard[result[0]][result[1]] instanceof PlayerItem)
							{
								System.out.println(e.getMessage());
								System.out.println(" "+"Invalid move. Enter another move");
								p.setGainTurn(true);
							}
							else if(gameBoard[result[0]][result[1]] instanceof Obstacle) 
							{	
								System.out.println(e.getMessage());
								p.setLoseTurn(true);
							}
						}
					}
					else if (move==6)
					{
						int result[]=crisscrosserRef.calcDestination(6);
						
						try
						{
							int prevLocation[]=crisscrosserRef.getLocation();
							int prevRow=prevLocation[0];
							int prevCol=prevLocation[1];
							validateMove(result[0],result[1]);								
							crisscrosserRef.setLocation(result[0], result[1]);
							if(gameBoard[result[0]][result[1]] instanceof Prize)
							{
								removePrize(result[0],result[1]);
								numPrizes--;
								p.setGainTurn(true);
								System.out.println("You have another turn");
							}
							gameBoard[result[0]][result[1]]=crisscrosserRef; 
							int curRow=result[0];
							int curCol=result[1];
							if (curCol==numCols-1)
							{
								p.updateNumFinish();
								p.updateNumInPlay();
								System.out.println("Your Crisscrosser is Home!");
								System.out.println("");
							}
							gameBoard[prevRow][prevCol]=null;
						}
						
						catch (OffBoardException e)
						{
							System.out.println(e.getMessage()+". Enter another move");
							p.setGainTurn(true);
						}
						
						catch (CollisionException e)
						{
							if (gameBoard[result[0]][result[1]] instanceof PlayerItem)
							{
								System.out.println(e.getMessage());
								System.out.println(" "+"Invalid move. Enter another move");
								p.setGainTurn(true);
							}
							else if(gameBoard[result[0]][result[1]] instanceof Obstacle) 
							{	
								System.out.println(e.getMessage());
								p.setLoseTurn(true);
							}
						}
					}
					else if (move==7)
					{
						int result[]=crisscrosserRef.calcDestination(7);
						
						try
						{
							int prevLocation[]=crisscrosserRef.getLocation();
							int prevRow=prevLocation[0];
							int prevCol=prevLocation[1];
							validateMove(result[0],result[1]);								
							crisscrosserRef.setLocation(result[0], result[1]);
							if(gameBoard[result[0]][result[1]] instanceof Prize)
							{
								removePrize(result[0],result[1]);
								numPrizes--;
								p.setGainTurn(true);
								System.out.println("You have another turn");
							}
							gameBoard[result[0]][result[1]]=crisscrosserRef; 
							gameBoard[prevRow][prevCol]=null;
						}
						
						catch (OffBoardException e)
						{
							System.out.println(e.getMessage()+". Enter another move");
							p.setGainTurn(true);
						}
						
						catch (CollisionException e)
						{
							if (gameBoard[result[0]][result[1]] instanceof PlayerItem)
							{
								System.out.println(e.getMessage());
								System.out.println(" "+"Invalid move. Enter another move");
								p.setGainTurn(true);
							}
							else if(gameBoard[result[0]][result[1]] instanceof Obstacle) 
							{	
								System.out.println(e.getMessage());
								p.setLoseTurn(true);
							}
						}
					}
					else if (move==8)
					{
						int result[]=crisscrosserRef.calcDestination(8);
						
						try
						{
							int prevLocation[]=crisscrosserRef.getLocation();
							int prevRow=prevLocation[0];
							int prevCol=prevLocation[1];
							validateMove(result[0],result[1]);								
							crisscrosserRef.setLocation(result[0], result[1]);
							if(gameBoard[result[0]][result[1]] instanceof Prize)
							{
								removePrize(result[0],result[1]);
								numPrizes--;
								p.setGainTurn(true);
								System.out.println("You have another turn");
							}
							gameBoard[result[0]][result[1]]=crisscrosserRef; 
							gameBoard[prevRow][prevCol]=null;
						}
						
						catch (OffBoardException e)
						{
							System.out.println(e.getMessage()+". Enter another move");
							p.setGainTurn(true);
						}
						
						catch (CollisionException e)
						{
							if (gameBoard[result[0]][result[1]] instanceof PlayerItem)
							{
								System.out.println(e.getMessage());
								System.out.println(" "+"Invalid move. Enter another move");
								p.setGainTurn(true);
							}
							else if(gameBoard[result[0]][result[1]] instanceof Obstacle) 
							{	
								System.out.println(e.getMessage());
								p.setLoseTurn(true);
							}
						}
					}
					else
						System.out.println("Please enter 5, 6, 7, or 8");
						break;
				}
				break;
			case 3:
					Bumper bumperRef;
				
				if (p.isPieceFinished(3)==false)
				{
					bumperRef=(Bumper)p.getPiece(3);
					
					displayMovementOptions(3);
					int move = Output.nextInt();
					
					if (move==1)
					{	
						int result[]=bumperRef.calcDestination(1);
						
						try
						{
							int prevLocation[]=bumperRef.getLocation();
							int prevRow=prevLocation[0];
							int prevCol=prevLocation[1];
							validateMove(result[0],result[1]);								
							bumperRef.setLocation(result[0], result[1]);
							if(gameBoard[result[0]][result[1]] instanceof Prize)
							{
								removePrize(result[0],result[1]);
								numPrizes--;
								p.setGainTurn(true);
								System.out.println("You have another turn");
							}
							gameBoard[result[0]][result[1]]=bumperRef; 
							gameBoard[prevRow][prevCol]=null;
						}
						
						catch (OffBoardException e)
						{
							System.out.println(e.getMessage()+". Enter another move");
							p.setGainTurn(true);
						}
						
						catch (CollisionException e)
						{
							if (gameBoard[result[0]][result[1]] instanceof PlayerItem)
							{
								System.out.println(e.getMessage());
								System.out.println(" "+"Invalid move. Enter another move");
								p.setGainTurn(true);
							}
							else if(gameBoard[result[0]][result[1]] instanceof Obstacle) 
							{	
								System.out.println(e.getMessage());
								p.setLoseTurn(true);
							}
						}
						
					}
					else if (move==2)
					{
						int result[]=bumperRef.calcDestination(2);
						
						try
						{
							int prevLocation[]=bumperRef.getLocation();
							int prevRow=prevLocation[0];
							int prevCol=prevLocation[1];
							validateMove(result[0],result[1]);								
							bumperRef.setLocation(result[0], result[1]);
							if(gameBoard[result[0]][result[1]] instanceof Prize)
							{
								removePrize(result[0],result[1]);
								numPrizes--;
								p.setGainTurn(true);
								System.out.println("You have another turn");
							}
							gameBoard[result[0]][result[1]]=bumperRef; 
							gameBoard[prevRow][prevCol]=null;
						}
						
						catch (OffBoardException e)
						{
							System.out.println(e.getMessage()+". Enter another move");
							p.setGainTurn(true);
						}
						
						catch (CollisionException e)
						{
							if (gameBoard[result[0]][result[1]] instanceof PlayerItem)
							{
								System.out.println(e.getMessage());
								System.out.println(" "+"Invalid move. Enter another move");
								p.setGainTurn(true);
							}
							else if(gameBoard[result[0]][result[1]] instanceof Obstacle) 
							{	
								System.out.println(e.getMessage());
								p.setLoseTurn(true);
							}
							
							
						}
					}
					else if (move==3)
					{
						//System.out.println("move:- "+move);
						
						
						int result[]=bumperRef.calcDestination(3);
						
						try
						{
							int prevLocation[]=bumperRef.getLocation();
							int prevRow=prevLocation[0];
							int prevCol=prevLocation[1];
							validateMove(result[0],result[1]);								
							bumperRef.setLocation(result[0], result[1]);
							if(gameBoard[result[0]][result[1]] instanceof Prize)
							{
								removePrize(result[0],result[1]);
								numPrizes--;
								p.setGainTurn(true);
								System.out.println("You have another turn");
							}
							gameBoard[result[0]][result[1]]=bumperRef; 
							
							int curRow=result[0];
							int curCol=result[1];
							if (curCol==numCols-1)
							{
								p.updateNumFinish();
								p.updateNumInPlay();
								System.out.println("Your Bumper is Home!");
								System.out.println("");
							}
							gameBoard[prevRow][prevCol]=null;
						}
						
						catch (OffBoardException e)
						{
							System.out.println(e.getMessage()+". Enter another move");
							p.setGainTurn(true);
						}
						
						catch (CollisionException e)
						{
							if (gameBoard[result[0]][result[1]] instanceof PlayerItem)
							{
								System.out.println(e.getMessage());
								System.out.println(" "+"Invalid move. Enter another move");
								p.setGainTurn(true);
							}
							else if(gameBoard[result[0]][result[1]] instanceof Obstacle) 
							{	
								System.out.println(e.getMessage());
								p.setLoseTurn(true);
							}
						}
						
					}
					else if (move==4)
					{
						int result[]=bumperRef.calcDestination(4);

						try
						{
							int prevLocation[]=bumperRef.getLocation();
							int prevRow=prevLocation[0];
							int prevCol=prevLocation[1];
							validateMove(result[0],result[1]);								
							bumperRef.setLocation(result[0], result[1]);
							if(gameBoard[result[0]][result[1]] instanceof Prize)
							{
								removePrize(result[0],result[1]);
								numPrizes--;
								p.setGainTurn(true);
								System.out.println("You have another turn");
							}
							gameBoard[result[0]][result[1]]=bumperRef; 
							gameBoard[prevRow][prevCol]=null;
						}
						
						catch (OffBoardException e)
						{
							System.out.println(e.getMessage()+". Enter another move");
							p.setGainTurn(true);
						}
						
						catch (CollisionException e)
						{
							if (gameBoard[result[0]][result[1]] instanceof PlayerItem)
							{
								System.out.println(e.getMessage());
								System.out.println(" "+"Invalid move. Enter another move");
								p.setGainTurn(true);
							}
							else if(gameBoard[result[0]][result[1]] instanceof Obstacle) 
							{	
								System.out.println(e.getMessage());
								p.setLoseTurn(true);
							}
						}
						break;
					}
					else if (move==5)
					{
						int result[]=bumperRef.calcDestination(5);
						
						try
						{
							int prevLocation[]=bumperRef.getLocation();
							int prevRow=prevLocation[0];
							int prevCol=prevLocation[1];
							validateMove(result[0],result[1]);								
							bumperRef.setLocation(result[0], result[1]);
							if(gameBoard[result[0]][result[1]] instanceof Prize)
							{
								removePrize(result[0],result[1]);
								numPrizes--;
								p.setGainTurn(true);
								System.out.println("You have another turn");
							}
							gameBoard[result[0]][result[1]]=bumperRef; 
							int curRow=result[0];
							int curCol=result[1];
							if (curCol==numCols-1)
							{
								p.updateNumFinish();
								p.updateNumInPlay();
								System.out.println("Your Bumper is Home!");
								System.out.println("");
							}
							gameBoard[prevRow][prevCol]=null;
						}
						
						catch (OffBoardException e)
						{
							System.out.println(e.getMessage()+". Enter another move");
							p.setGainTurn(true);
						}
						
						catch (CollisionException e)
						{
							if (gameBoard[result[0]][result[1]] instanceof PlayerItem)
							{
								System.out.println(e.getMessage());
								System.out.println(" "+"Invalid move. Enter another move");
								p.setGainTurn(true);
							}
							else if(gameBoard[result[0]][result[1]] instanceof Obstacle) 
							{	
								System.out.println(e.getMessage());
								p.setLoseTurn(true);
							}
						}
					}
					else if (move==6)
					{
						int result[]=bumperRef.calcDestination(6);
						
						try
						{
							int prevLocation[]=bumperRef.getLocation();
							int prevRow=prevLocation[0];
							int prevCol=prevLocation[1];
							validateMove(result[0],result[1]);								
							bumperRef.setLocation(result[0], result[1]);
							if(gameBoard[result[0]][result[1]] instanceof Prize)
							{
								removePrize(result[0],result[1]);
								numPrizes--;
								p.setGainTurn(true);
								System.out.println("You have another turn");
							}
							gameBoard[result[0]][result[1]]=bumperRef; 
							int curRow=result[0];
							int curCol=result[1];
							if (curCol==numCols-1)
							{
								p.updateNumFinish();
								p.updateNumInPlay();
								System.out.println("Your Bumper is Home!");
								System.out.println("");
							}
							gameBoard[prevRow][prevCol]=null;
						}
						
						catch (OffBoardException e)
						{
							System.out.println(e.getMessage()+". Enter another move");
							p.setGainTurn(true);
						}
						
						catch (CollisionException e)
						{
							if (gameBoard[result[0]][result[1]] instanceof PlayerItem)
							{
								System.out.println(e.getMessage());
								System.out.println(" "+"Invalid move. Enter another move");
								p.setGainTurn(true);
							}
							else if(gameBoard[result[0]][result[1]] instanceof Obstacle) 
							{	
								System.out.println(e.getMessage());
								p.setLoseTurn(true);
							}
						}
					}
					else if (move==7)
					{
						int result[]=bumperRef.calcDestination(7);
						
						try
						{
							int prevLocation[]=bumperRef.getLocation();
							int prevRow=prevLocation[0];
							int prevCol=prevLocation[1];
							validateMove(result[0],result[1]);								
							bumperRef.setLocation(result[0], result[1]);
							if(gameBoard[result[0]][result[1]] instanceof Prize)
							{
								removePrize(result[0],result[1]);
								numPrizes--;
								p.setGainTurn(true);
								System.out.println("You have another turn");
							}
							gameBoard[result[0]][result[1]]=bumperRef; 
							gameBoard[prevRow][prevCol]=null;
						}
						
						catch (OffBoardException e)
						{
							System.out.println(e.getMessage()+". Enter another move");
							p.setGainTurn(true);
						}
						
						catch (CollisionException e)
						{
							if (gameBoard[result[0]][result[1]] instanceof PlayerItem)
							{
								System.out.println(e.getMessage());
								System.out.println(" "+"Invalid move. Enter another move");
								p.setGainTurn(true);
							}
							else if(gameBoard[result[0]][result[1]] instanceof Obstacle) 
							{	
								System.out.println(e.getMessage());
								p.setLoseTurn(true);
							}
						}
					}
					else if (move==8)
					{
						int result[]=bumperRef.calcDestination(8);
						
						try
						{
							int prevLocation[]=bumperRef.getLocation();
							int prevRow=prevLocation[0];
							int prevCol=prevLocation[1];
							validateMove(result[0],result[1]);								
							bumperRef.setLocation(result[0], result[1]);
							if(gameBoard[result[0]][result[1]] instanceof Prize)
							{
								removePrize(result[0],result[1]);
								numPrizes--;
								p.setGainTurn(true);
								System.out.println("You have another turn");
							}
							gameBoard[result[0]][result[1]]=bumperRef; 
							gameBoard[prevRow][prevCol]=null;
						}
						
						catch (OffBoardException e)
						{
							System.out.println(e.getMessage()+". Enter another move");
							p.setGainTurn(true);
						}
						
						catch (CollisionException e)
						{
							if (gameBoard[result[0]][result[1]] instanceof PlayerItem)
							{
								System.out.println(e.getMessage());
								System.out.println(" "+"Invalid move. Enter another move");
								p.setGainTurn(true);
							}
							else if(gameBoard[result[0]][result[1]] instanceof Obstacle) 
							{	
								System.out.println(e.getMessage());
								p.setLoseTurn(true);
							}
						}
					}
					else
						System.out.println("Please enter 1, 2, 3, 4, 5, 6, 7 or 8");
						break;
				}
				break;
				
				
			default:
				System.out.println("++ Please enter 1, 2, 3");
				break;
				
		}
			
				
				
	}
}//GameBoard class
