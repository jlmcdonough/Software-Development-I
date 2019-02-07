/*
 * Joseph McDonough and Patrick McNamara
 * CMPT 220L-200
 * 17 February 2019
 * Project Two (HallSim v2.0)
 */
package hallSim;

import java.util.*;


/* WHAT WAS DONE IN THE UPDATE
 * EIGHT LOCATIONS (MAGIC SHOP, NURSE, OFFICE, CAFETERIA, AUDITORIUM)
 * PLAYER CAN NOW MOVE EAST AND WEST AS WELL AS NORTH AND SOUTH
 * MAP WAS UPDATED
 * TURN LOCALES INTO INSTANCES OF ROOM CLASS
 * STORE LOCALES INTO AN ARRAY CALLED (roomList)
 * MODIFIED MOVE METHOD SO THAT IT USES THE ARRAY AND INSTANCES OF THE ROOM CLASS
 */

public class HallSim {
	public static String currentPosition;  //holds the current location
	public static boolean gameOn;          //control for the game loop. true until user enters "quit"
	public static boolean canGo;           //control for update message. won't print if the user cannot walk in desired direction
	public static int monster;             //variable for the random number to determine monster event
	public static int score;               //variable for the users score

	public static void main(String[] args) {
		Random rand = new Random();
		currentPosition = "LABORATORY";
		Scanner in = new Scanner(System.in);
		String direction = "";
		gameOn = true;
		score = 0;
		System.out.println("HALLWAY SIMULATOR");  //title
		Room lab = new Room("LABORATORY");        //lab instance of Room class
		Room classroom = new Room("CLASSROOM");   //classroom instance of Room class
		Room gym = new Room("GYM");               //gym instance of Room class
		System.out.println("STARTING LOCATION: LABORATORY" + "\t STARTING SCORE: 0");  //Starting message
		while (gameOn) {  //game loop
			monster = rand.nextInt(5);  //generators a number for monster event
			System.out.print("Enter a command or 'h' for help: ");
			direction = in.nextLine();
			if (direction.equals("skip") || direction.equals("dance")) //if users wants to skip or dance, a special question line and message is used
			{  
				System.out.print("Chose a direction to go: "); //since user is skipping, they now choose a direction
				String dir2 = in.nextLine();   
				getInput(currentPosition, dir2);
				if(canGo)
					System.out.println("You " + direction + " into the " + currentPosition);
			} else 
				{
				getInput(currentPosition, direction);  //user doesn't want to skip or dance, assumes they walk or enter a different command
				if(canGo)
					System.out.println("You walk to the " + currentPosition);
				}
			System.out.println("LOCATION: " + currentPosition + "\t SCORE: " + score);  //updates user with current location and score info
		}
		in.close();  //closes scanner
		System.out.println("Game over, thanks for playing!");   //lets user know they successfully quit
	}

	public static void map() { //prints map
		System.out.println("-------------------------------------------------");
		System.out.println("|				      GYM     		AUDITORIUM	|");
		System.out.println("| 				       ^      			 ^		|");
		System.out.println("| 				       |      			 |		|");
		System.out.println("| 				       v      		 	 v		|");
		System.out.println("| 	NURSE	  <-->  CLASSROOM   <--> CAFETERIA	|");
		System.out.println("| 				       ^      			 ^		|");
		System.out.println("| 				       |      			 |		|");
		System.out.println("| 				       v      			 v		|");
		System.out.println("| MAGIC SHOP  <-->  LABORATORY  <-->  OFFICE	|");
		System.out.println("-------------------------------------------------");
	}

	public static void getInput(String currRoom, String userInput) { //determines what to do with user input
		canGo = false; //by default, will not print the walk message
		if ((userInput.equals("n") || userInput.equals("s")|| userInput.equals("e")|| userInput.equals("w")) && monster==3)  //if user enters a direction and monster is summoned, enters if-statement
		{  		//if the monster random number (1-5) lands on 3 (20% chance), the monster event triggers
				System.out.println("You were attacked by a monster and lost 3 points");  //lets user know monster attacked them
				score -= 3; //lose 3 points for getting randomly attacked
		}
		if (userInput.equals("n")) 
		{
			if(currentPosition.equals(lab.name))	
			{
				currentPosition = classroom.name;
				score+=5;
				canGo = true;
			}
		}
		else if (userInput.equals("s")
		{
			pass
		}
		else if(userInput.equals("e"))
		{	
			pass
		}
		else if(userInput.equals("w"))
		{
			pass
		}
			
		else if (userInput.equals("m"))  //user enters m, map appears
			map();
		else if (userInput.equals("h"))  //user enters h, help menu appears
			System.out.println("Valid commands are:" 
					+ "\n\t"+"\u2022" + "'n' to move character north."
					+ "\n\t"+"\u2022" + "'s' to move character south."
					+ "\n\t"+"\u2022" + "'e' to move character east."
					+ "\n\t"+"\u2022" + "'w' to move character west."
					+ "\n\t"+"\u2022" + "'dance' to dance into a new location."
					+ "\n\t"+"\u2022" + "'skip' to skip(the action) into a new location."
					+ "\n\t"+"\u2022" + "'m' to view the map."
					+ "\n\t"+"\u2022" + "'h' to view the help menu."
					+ "\n\t"+"\u2022" + "'quit' to quit the game");
		else if (userInput.equals("quit"))
			gameOn = false;
		else
			System.out.println("You did not enter a valid input");
	}
}