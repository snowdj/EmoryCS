/*
THIS CODE IS MY OWN WORK. IT WAS WRITTEN WITHOUT CONSULTING CODE WRITTEN BY OTHER STUDENTS OR MATERIALS OTHER THAN THIS SEMESTER'S COURSE MATERIALS. TAMER AVCI
*/
import java.util.Scanner;
public class Battleship{
	public static final int SIZEX = 10;
	public static final int SIZEY = 10;
	public static final int NSHIPS = 3;
	public static final int ATTEMPTS = SIZEX*SIZEY/5;
	
	
/*------------------------- Main Method ----------------------------------*/
	public static void main(String[] args){
		//Throughout the comments, I use "ship" and "boat" interchangeably.
		/*NO NEED TO CHANGE THE MAIN METHOD UNTIL THE "TO DO" PART.*/
		int[][] ComputersBoard = new int[Battleship.SIZEX][Battleship.SIZEY];
		int[][] PlayersBoard = new int[Battleship.SIZEX][Battleship.SIZEY];
		System.out.println("Welcome to Battleship. You can press q! at anytime to quit");		
		PrintBoard(PlayersBoard);
		PlaceShips(ComputersBoard);		
		
		int miss_counter = 0;
		int hit_counter = 0;
		
		//This is the total number of squares that have part of a ship.
		int total_hits=(Battleship.NSHIPS+2)*(Battleship.NSHIPS+3)/2-3;
		while(miss_counter+hit_counter < Battleship.ATTEMPTS && hit_counter < total_hits){ 
			System.out.print("Where do you want to shoot? ");		
			//You can't return 2 parameters, but the trick is to return an array.
			Scanner input = new Scanner(System.in);
			int[] Pos=get_positions(input.next());
			int x = Pos[0];
			int y = Pos[1];
			
			if(checkhit(ComputersBoard,x,y)){
				hit_counter++;
				System.out.println("Hit!! Hits remaining: " +(total_hits-hit_counter));
				PlaceShot(PlayersBoard,x,y,'H');				
			}			
			else{
				miss_counter++;
				System.out.println("Missed... Attempts remaining: "+(Battleship.ATTEMPTS - (miss_counter+hit_counter)));
				PlaceShot(PlayersBoard,x,y,'M');				
			}
			PrintBoard(PlayersBoard);				
		}//Closes while
		
		
		/*TO DO:  COMPLETE THE MAIN METHOD TO DISPLAY IF THE PLAYER WON OR LOST. IF THE PLAYER LOST, DISPLAY
		WHERE THE SHIPS ACTUALLY WHERE.
		 */

                 if (total_hits == hit_counter) {
                 	System.out.println("The player has won!!");
                 }
                 else {
                 	System.out.println("The player has lost");
                        PrintBoard(ComputersBoard);
                 }
		
		
		

	}
	
/*-------------------------    fits    ----------------------------------*/
	public static boolean fits(int[][] board, int pos_x, int pos_y, int sizeship, String dir){
		/*Input: The current board,  Position X, Position Y, Size of the Boat, Direction.
		Output: True if a ship of size sizeship, placed starting in position (pos_x, pos_y), 
		in direction dir (either "horizontal" or "vertical") fits in the board. This function
		considers if the ship would go over the board and also if there is no intersection with
		an existing ship.  The method returns false if the new ship does not fit.		
		*/
                if (dir.equals("horizontal")) {
                	if (pos_y + sizeship -1 > 9)
                 	       return false;
                        for (int i = pos_y; i < pos_y+sizeship; i++) {
                               if(board[pos_x][i]=='H')
                               		return false;
                        }
                }
                else {
                        if (pos_x + sizeship -1 > 9)
                        	return false;
                        for (int i = pos_x; i < pos_x+sizeship; i++) {
                                if(board[i][pos_y]=='H')
                                	return false;
                        }
                }
		return true;
	

	}

/*-------------------------  Place Ships    ----------------------------------*/
	public static void PlaceShips(int[][] board){
		/*This method places NSHIPS boats of increasing sizes into the board. 
		The smallest boat is of size 3. For example, if there are 3 boats, their size 
		would be 3, 4, and 5.
		To place the ships, generate a random integer between 0 and SIZEX, another between 
		0 and SIZEY, and a random direction (HINT: you can have an array of Strings size 2
		with "vertical" and "horizontal", and then just choose a randomly 0 or 1 to choose 
		a random direction). Once you have generated these numbers, check if you can 
		actually place them in the board. If you can't, get new numbers again and again until 
		you are sure they fit. Once you are sure, place them on the board.  	
		*/
                for( int sizeship = 3; sizeship < 6; sizeship++) {
                        int x = (int)(Math.random()*SIZEX);
                        int y = (int)(Math.random()*SIZEY);
                        String[] direction = {"horizontal", "vertical"};
                        int a = (int)(Math.random()*2);
                        String dir = direction[a];
                        while (!fits(board,x,y,sizeship,dir)) {
                                x = (int)(Math.random()*SIZEX);
                                y = (int)(Math.random()*SIZEY);
                                a = (int)(Math.random()*2);
                                dir = direction[a];
                        }
                        if (dir.equals("horizontal")) {
                                for( int i = y; i<y+sizeship; i++) {
                                      board[x][i] = 'H';
                                }
                        }
                        else    {
                                for( int i = x; i<x+sizeship; i++) {
                                      board[i][y] = 'H';
                                }
	                }
               }
	}
	
/*-------------------------Check hit---------------------------------*/	
	/*checkhit is a boolean method with input parameters: ComputersBoard, x, and y that 
	simply returns true if there is a part of ship in the ComputersBoard in that position
	or false otherwise*/
	public static boolean checkhit(int[][] board, int x, int y){
		if (board[x][y] == 'H') 
                	return true;
       		return false;
	}
	
	
	
/*-------------------------Place Shots----------------------------------*/	
	/*This function simply places the character c in position (x,y) in the board.*/
	public static void PlaceShot(int[][] board, int x, int y, char c){	
        	board[x][y] = c;
	}





/*NO NEED TO CHANGE PRINTBOARD OR GET_POSITIONS*/	
	
/*-------------------------Print Board----------------------------------*/
	public static void PrintBoard(int[][] board){
		
		for (int j=0; j<Battleship.SIZEY; j++){
			//Print the top line:
			System.out.print("  ");
			for (int i=0; i< Battleship.SIZEX; i++)
				System.out.print(" ___");

			//Print the numbers in each row:
			System.out.println();
			if(j<10)
				System.out.print(j+" ");
			else
				System.out.print(j);
			
			//Print the board, checking if there is anything there.
			for (int i=0; i<Battleship.SIZEX; i++){
				if(board[i][j]==0)
					System.out.print("|___");
				else
					System.out.print("|_"+(char)(board[i][j])+"_");
			}

			
			//Print the line at the right of the board
			System.out.print("|\n");

		}
		//Print the letters at the bottom:
		
		System.out.print("  ");
		for (int i=0; i<Battleship.SIZEX; i++)
			System.out.print("  "+(char)(65+i) +" ");

		System.out.println();

	}

/*------------------------- get Positions    ----------------------------------*/
	public static int[] get_positions(String shot){
		int[] positions = new int[2];
		Scanner input;
		if(shot.equals("q!"))
		   System.exit(1);
		   
		   
		while(shot.length()<2){
		    System.out.println("Shot is invalid. Try again.");
			input = new Scanner(System.in);
		    shot = input.next();
		    if(shot.equals("q!"))
			   System.exit(1);
		
		}
					
		char letter=shot.charAt(0);
		int x = (int)(letter-65);
		int y = Integer.parseInt(shot.substring(1,shot.length()));	
		while(x<0 || x>=Battleship.SIZEX || y < 0 || y >= Battleship.SIZEY){
		    System.out.println("Shot is invalid. Try again.");
		    input = new Scanner(System.in);
		    if(shot.equals("q!"))
			   System.exit(1);
		
		    shot = input.next();
			letter=shot.charAt(0);
			x = (int)(letter-65);
			y = Integer.parseInt(shot.substring(1,shot.length()));					
		} 
		positions[0]=x; 
		positions[1]=y;
		return positions;

	}
	
	
	
	
	

		
}

