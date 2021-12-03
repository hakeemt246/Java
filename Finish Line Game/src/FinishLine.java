/**
 *
 * @author Hakeem Thompson, COMP2232 Assignment #2 Finish Line
 */
import java.util.Scanner; // Allows the user to enter data from keyboard

public class FinishLine
{

	public static void main(String args[])
	{
		Scanner Output = new Scanner(System.in);
		GameBoard board= new GameBoard(); //new GameBoard object		
	
		Player Player1= new Player(" "); //Player 1 object
		Player Player2= new Player(" "); //Player 2 object
		int play=0;
				
		while ( play!=999) 
		{
			System.out.println("Do you want a standard or customized gameboard? Type 1 for standard, or 2 for customized.");
			int boardtype = Output.nextInt();
			
				switch (boardtype) //chooses the option based on the number the user entered
				{
					case 1: //standard board
						System.out.println("Player 1 please enter your name");
						Player1.setName(Output.next());	//uses mutator in the Player Class to set player 1's name					
						System.out.println("Player 2 please enter your name"); //uses mutator in the Player Class to set player 2's name
						Player2.setName(Output.next());
						board.placePlayerPieces(Player1, Player2); //adds player pieces to the board
						board.populateBoard();
						System.out.println("PLAYER STATUS");
						System.out.println(Player1.getName()+": "+Player1.getNumFinish()+" Finish; "+Player1.getNumInPlay()+" in play");
						System.out.println(Player2.getName()+": "+Player2.getNumFinish()+" Finish; "+Player2.getNumInPlay()+" in play");
						System.out.println("");
						
						while (Player1.getNumFinish()<3 && Player2.getNumFinish()<3) //stops the game when one of the players gets all 3 pieces to the finish
							{	
							boolean skip=false;
								while (skip!=true)
								{
									System.out.println(Player1.getName()+"'s turn");
									board.runPlayerTurn(Player1);
									board.displayBoard();
									System.out.println("PLAYER STATUS");
									System.out.println(Player1.getName()+": "+Player1.getNumFinish()+" Finish; "+Player1.getNumInPlay()+" in play");
									System.out.println(Player2.getName()+": "+Player2.getNumFinish()+" Finish; "+Player2.getNumInPlay()+" in play");
									System.out.println("");
									
									if (Player1.getLoseTurn()==true)
									{
										System.out.println(Player2.getName()+"'s turn");
										board.runPlayerTurn(Player2);
										board.displayBoard();
										System.out.println("PLAYER STATUS");
										System.out.println(Player1.getName()+": "+Player1.getNumFinish()+" Finish; "+Player1.getNumInPlay()+" in play");
										System.out.println(Player2.getName()+": "+Player2.getNumFinish()+" Finish; "+Player2.getNumInPlay()+" in play");
										System.out.println("");
										Player1.setLoseTurn(false);
										skip=true;
									}
								
									if (Player1.getGainTurn()==true)
									{
										System.out.println(Player1.getName()+"'s turn again");
										board.runPlayerTurn(Player1);
										board.displayBoard();
										System.out.println("PLAYER STATUS");
										System.out.println(Player1.getName()+": "+Player1.getNumFinish()+" Finish; "+Player1.getNumInPlay()+" in play");
										System.out.println(Player2.getName()+": "+Player2.getNumFinish()+" Finish; "+Player2.getNumInPlay()+" in play");
										System.out.println("");
										Player1.setGainTurn(false);
									}
									
									System.out.println(Player2.getName()+"'s turn");
									board.runPlayerTurn(Player2);
									board.displayBoard();
									System.out.println("PLAYER STATUS");
									System.out.println(Player1.getName()+": "+Player1.getNumFinish()+" Finish; "+Player1.getNumInPlay()+" in play");
									System.out.println(Player2.getName()+": "+Player2.getNumFinish()+" Finish; "+Player2.getNumInPlay()+" in play");
									System.out.println("");
									
									if (Player2.getGainTurn()==true)
									{
										System.out.println(Player2.getName()+"'s turn again");
										board.runPlayerTurn(Player2);
										board.displayBoard();
										System.out.println("PLAYER STATUS");
										System.out.println(Player1.getName()+": "+Player1.getNumFinish()+" Finish; "+Player1.getNumInPlay()+" in play");
										System.out.println(Player2.getName()+": "+Player2.getNumFinish()+" Finish; "+Player2.getNumInPlay()+" in play");
										System.out.println("");
										Player2.setGainTurn(false);
									}
									
									if (Player2.getLoseTurn()==true)
									{
										System.out.println(Player1.getName()+"'s turn");
										board.runPlayerTurn(Player1);
										board.displayBoard();
										System.out.println("PLAYER STATUS");
										System.out.println(Player1.getName()+": "+Player1.getNumFinish()+" Finish; "+Player1.getNumInPlay()+" in play");
										System.out.println(Player2.getName()+": "+Player2.getNumFinish()+" Finish; "+Player2.getNumInPlay()+" in play");
										System.out.println("");
										Player2.setLoseTurn(false);
										skip=true;
									}
								}
								
							}
						if (Player1.getNumFinish()==3) //if player 1 win's his/her name is displayed with the message
						{
							System.out.println("");
							System.out.println("Congratulations"+Player1.getName()+"! You won the game");
						}
						else //if player 2 win's his/her name is displayed with the message
						{
							System.out.println("");
							System.out.println("Congratulations "+Player2.getName()+"! You won the game");
						}
						break;
						
					case 2:
							System.out.println("Player 1 please enter your name");
							Player1.setName(Output.next());							
							System.out.println("Player 2 please enter your name");
							Player2.setName(Output.next());
							System.out.println("Enter a number between 6 and 9 for the number of rows you would like");
							int customRows = Output.nextInt();	
							if (customRows<6 || customRows>9)
							{
								System.out.println("Please enter a number between 6 and 9");
								System.out.println("");
								continue;
							}
							
							System.out.println("Enter the number between 12 and 15 for the number of columns you would like");
							int customCols = Output.nextInt();
							if (customCols<12 || customCols>15)
							{
								System.out.println("Please enter a number between 12 and 15");
								System.out.println("");
								continue;
							}

							System.out.println("Enter the number between 3 and 9 for the number of obstacles you would like");
							int customObstacles = Output.nextInt();	
							if (customObstacles<3 || customObstacles>9)
							{
								System.out.println("Please enter a number between 3 and 9");
								System.out.println("");
								continue;
							}
							
							System.out.println("Enter the number between 3 and 6 for the number of prizes you would like");
							int customPrizes = Output.nextInt();
							if (customPrizes<3 || customPrizes>6)
							{
								System.out.println("Please enter a number between 3 and 6");
								System.out.println("");
								continue;
							}						
							System.out.println("");
							board=new GameBoard(customRows,customCols,customObstacles,customPrizes); //creates custom gameboard using explicit constructor
							board.placePlayerPieces(Player1, Player2);
							board.populateBoard();
							System.out.println("");
							System.out.println("PLAYER STATUS");
							System.out.println(Player1.getName()+": "+Player1.getNumFinish()+" Finish; "+Player1.getNumInPlay()+" in play");
							System.out.println(Player2.getName()+": "+Player2.getNumFinish()+" Finish; "+Player2.getNumInPlay()+" in play");
							System.out.println("");
							
							while (Player1.getNumFinish()<3 && Player2.getNumFinish()<3)
							{	
							boolean skip=false;
								while (skip!=true)
								{
									System.out.println(Player1.getName()+"'s turn");
									board.runPlayerTurn(Player1);
									board.displayBoard();
									System.out.println("PLAYER STATUS");
									System.out.println(Player1.getName()+": "+Player1.getNumFinish()+" Finish; "+Player1.getNumInPlay()+" in play");
									System.out.println(Player2.getName()+": "+Player2.getNumFinish()+" Finish; "+Player2.getNumInPlay()+" in play");
									System.out.println("");
									
									if (Player1.getLoseTurn()==true)
									{
										System.out.println(Player2.getName()+"'s turn");
										board.runPlayerTurn(Player2);
										board.displayBoard();
										System.out.println("PLAYER STATUS");
										System.out.println(Player1.getName()+": "+Player1.getNumFinish()+" Finish; "+Player1.getNumInPlay()+" in play");
										System.out.println(Player2.getName()+": "+Player2.getNumFinish()+" Finish; "+Player2.getNumInPlay()+" in play");
										System.out.println("");
										Player1.setLoseTurn(false);
										skip=true;
									}
								
									if (Player1.getGainTurn()==true)
									{
										System.out.println(Player1.getName()+"'s turn again");
										board.runPlayerTurn(Player1);
										board.displayBoard();
										System.out.println("PLAYER STATUS");
										System.out.println(Player1.getName()+": "+Player1.getNumFinish()+" Finish; "+Player1.getNumInPlay()+" in play");
										System.out.println(Player2.getName()+": "+Player2.getNumFinish()+" Finish; "+Player2.getNumInPlay()+" in play");
										System.out.println("");
										Player1.setGainTurn(false);
									}
									
									System.out.println(Player2.getName()+"'s turn");
									board.runPlayerTurn(Player2);
									board.displayBoard();
									System.out.println("PLAYER STATUS");
									System.out.println(Player1.getName()+": "+Player1.getNumFinish()+" Finish; "+Player1.getNumInPlay()+" in play");
									System.out.println(Player2.getName()+": "+Player2.getNumFinish()+" Finish; "+Player2.getNumInPlay()+" in play");
									System.out.println("");
									
									if (Player2.getGainTurn()==true)
									{
										System.out.println(Player2.getName()+"'s turn again");
										board.runPlayerTurn(Player2);
										board.displayBoard();
										System.out.println("PLAYER STATUS");
										System.out.println(Player1.getName()+": "+Player1.getNumFinish()+" Finish; "+Player1.getNumInPlay()+" in play");
										System.out.println(Player2.getName()+": "+Player2.getNumFinish()+" Finish; "+Player2.getNumInPlay()+" in play");
										System.out.println("");
										Player2.setGainTurn(false);
									}
									
									if (Player2.getLoseTurn()==true)
									{
										System.out.println(Player1.getName()+"'s turn");
										board.runPlayerTurn(Player1);
										board.displayBoard();
										System.out.println("PLAYER STATUS");
										System.out.println(Player1.getName()+": "+Player1.getNumFinish()+" Finish; "+Player1.getNumInPlay()+" in play");
										System.out.println(Player2.getName()+": "+Player2.getNumFinish()+" Finish; "+Player2.getNumInPlay()+" in play");
										System.out.println("");
										Player2.setLoseTurn(false);
										skip=true;
									}
								}
								
							}
						if (Player1.getNumFinish()==3)
						{
							System.out.println("");
							System.out.println("Congratulations"+Player1.getName()+"! You won the game");
						}
						else
						{
							System.out.println("");
							System.out.println("Congratulations"+Player2.getName()+"! You won the game");
						}
						break;
							
					default:
						
						System.out.println("Please enter 1 or 2");
						System.out.println("");
						continue;
				} // end of switch				
				
				System.out.println("Do you want to play again? Press any number to continue or type 999 to end the game");
				int restart = Output.nextInt();
				
				if (restart==999) //restarts the game with new players
					play=999; 
								
				
		} // end of while loop
	} //end of main
}//end of class
