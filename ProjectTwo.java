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
 * STORE LOCALES INTO AN ARRAY CALLED (location)
 * ADDED determineMove() function which is called upon when a valid direction is entered
 * MODIFIED getInput() function so that the moving is not determined within and deals only with map,help,quit
 */

public class ProjectTwo {
	public static String currentPosition;  //holds the current location
	public static boolean gameOn;          //control for the game loop. true until user enters "quit"
	public static boolean canGo;           //control for update message. won't print if the user cannot walk in desired direction
	public static int monster;             //variable for the random number to determine monster event
	public static int score;               //variable for the users score
	public static Room [] location = new Room [8];  //list containing all the room objects
	public static String currentDescription;  //holds the description of the current location
	public static void main(String[] args) {
		Random rand = new Random();
		currentPosition = "Laboratory";
		Scanner in = new Scanner(System.in);
		String direction = "";
		gameOn = true;
		score = 0;
		System.out.println("HALLWAY SIMULATOR");  //title

		location [0] = new Room(0, "Laboratory", "Welcome to the Laboratory!");
		location [1] = new Room(1, "Classroom", "Welcome to the Classroom!");
		location [2] = new Room(2, "Gym", "Welcome to the Gym!");
		location [3] = new Room(3, "Magic Shop", "Welcome to the Magic Shop!");
		location [4] = new Room(4, "Nurse", "Welcome to the Nurse's Office!");
		location [5] = new Room(5, "Office", "Welcome to the main Office!");
		location [6] = new Room(6, "Cafe", "Welcome to the Cafeteria!");
		location [7] = new Room(7, "Auditorium", "Welcome to the Auditorium!");
		
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
				{
					System.out.println("You " + direction + " into the " + currentPosition);
					System.out.println(currentDescription);
				}
			} else 
				{
				getInput(currentPosition, direction);  //user doesn't want to skip or dance, assumes they walk or enter a different command
				if(canGo)
					{
					System.out.println("You walk to the " + currentPosition);
					System.out.println(currentDescription);
					}
				}
			System.out.println("LOCATION: " + currentPosition + "\t SCORE: " + score);  //updates user with current location and score info
		}
		in.close();  //closes scanner
		System.out.println("Game over, thanks for playing!");   //lets user know they successfully quit
	}

	public static void map() { //prints map
		System.out.println("-------------------------------------------------");
		System.out.println("|                     GYM           AUDITORIUM  |");
		System.out.println("|                      ^                 ^      |");
		System.out.println("|                      |                 |      |");
		System.out.println("|                      v                 v      |");
		System.out.println("|    NURSE   <-->  CLASSROOM   <--> CAFETERIA	|");
		System.out.println("|                      ^                 ^      |");
		System.out.println("|                      |                 |      |");
		System.out.println("|                      v                 v      |");
		System.out.println("| MAGIC SHOP  <-->  LABORATORY  <-->  OFFICE	|");
		System.out.println("-------------------------------------------------");
	}

	public static void getInput(String currRoom, String userInput) { //determines what to do with user input
		canGo = false; //by default, will not print the walk message
		if ((userInput.equals("n") || userInput.equals("s")|| userInput.equals("e")|| userInput.equals("w")))  //if user enters a direction and monster is summoned, enters if-statement
		{  		
			if(monster==3)  //if the monster random number (1-5) lands on 3 (20% chance), the monster event triggers
			{
				System.out.println("You were attacked by a monster and lost 3 points");  //lets user know monster attacked them
				score -= 3; //lose 3 points for getting randomly attacked
			}
			determineMove(currRoom,userInput);
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
		else if (userInput.equals("quit"))  //user enters quit, gameLoop get sets to false and gets exited
			gameOn = false;
		else  //if no valid input is given, prints error message and reprompts user to enter a command
			System.out.println("You did not enter a valid input"); 
	}
	public static void determineMove(String currRoom, String userInput)
	{
		if (userInput.equals("n")) //user enters n for north
		{
			if(currentPosition.equals(location[0].getName()))	//can go from lab (0) to classroom (1)
			{
				currentPosition = location[1].getName();  //moves to classroom 
				currentDescription = location[1].getDescription(); //sets description
				score+=location[1].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[1].getName()))	//can go from classroom (1) to gym (2)
			{
				currentPosition = location[2].getName();  //moves to gym
				currentDescription = location[2].getDescription(); //sets description
				score+=location[2].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[3].getName()))	//can go from magic shop (3) to nurse (4)
			{
				currentPosition = location[4].getName();  //moves to nurse
				currentDescription = location[4].getDescription(); //sets description
				score+=location[4].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[5].getName()))	 //can go from office (5) to cafeteria (6)
			{
				currentPosition = location[6].getName();  //moves to cafeteria
				currentDescription = location[6].getDescription(); //sets description
				score+=location[6].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[6].getName()))	//can go from cafeteria (6) to auditorium (7)
			{
				currentPosition = location[7].getName();  //moves to auditorium
				currentDescription = location[7].getDescription(); //sets description
				score+=location[7].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else
				System.out.println("You cannot go in that direction. Please choose another direction or enter 'm' for a map"); //when cannot advance in desired direction
		}
		else if (userInput.equals("s"))   //user enters s for south
		{
			if(currentPosition.equals(location[1].getName()))	//can go from classroom (1) to lab (0)
			{
				currentPosition = location[0].getName();  //moves to lab
				currentDescription = location[0].getDescription(); //sets description
				score+=location[0].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[2].getName()))	//can go from gym (2) to classroom (1)
			{
				currentPosition = location[1].getName();  //moves to classroom
				currentDescription = location[1].getDescription(); //sets description
				score+=location[1].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[4].getName()))	//can go from nurse (4) to magic shop (3)
			{
				currentPosition = location[3].getName();  //moves to magic shop
				currentDescription = location[3].getDescription(); //sets description
				score+=location[3].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[6].getName()))	//can go from cafeteria (6) to office (5)
			{
				currentPosition = location[5].getName();  //moves to office
				currentDescription = location[5].getDescription(); //sets description
				score+=location[5].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[7].getName()))	//can go from auditorium (7) to cafeteria (6)
			{
				currentPosition = location[6].getName();    //moves to cafeteria
				currentDescription = location[6].getDescription(); //sets description
				score+=location[6].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else
				System.out.println("You cannot go in that direction. Please choose another direction or enter 'm' for a map");  //when cannot advance in desired direction
		}
		else if(userInput.equals("e"))  //user enters e for east
		{	
			if(currentPosition.equals(location[0].getName()))	//can go from lab (0) to office (5)
			{
				currentPosition = location[5].getName();   //moves to office
				currentDescription = location[5].getDescription(); //sets description
				score+=location[5].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[1].getName()))	//can go from classroom (1) to cafeteria (6)
			{
				currentPosition = location[6].getName();   //moves to cafeteria
				currentDescription = location[6].getDescription(); //sets description
				score+=location[6].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[2].getName()))	//can go from gym (2) to auditorium(7)
			{
				currentPosition = location[7].getName();  //moves to auditorium
				currentDescription = location[7].getDescription(); //sets description
				score+=location[7].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[3].getName()))	//can go from magic shop (3) to lab(0)
			{
				currentPosition = location[0].getName();   //moves to lab
				currentDescription = location[0].getDescription(); //sets description
				score+=location[0].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[4].getName()))	//can go from nurse (4) to classroom (1)
			{
				currentPosition = location[1].getName();   //moves to classroom
				currentDescription = location[1].getDescription(); //sets description
				score+=location[1].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else
				System.out.println("You cannot go in that direction. Please choose another direction or enter 'm' for a map"); //when cannot advance in desired direction
		}
		else if(userInput.equals("w"))  //user enters w for west
		{
			if(currentPosition.equals(location[0].getName()))	//can go from lab (0) to magic shop (3)
			{
				currentPosition = location[3].getName();   //moves to magic shop
				currentDescription = location[3].getDescription(); //sets description
				score+=location[3].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[1].getName()))	//can go from classroom (1) to nurse (4)
			{
				currentPosition = location[4].getName();  //moves to nurse
				currentDescription = location[4].getDescription(); //sets description
				score+=location[4].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[5].getName()))	//can go from office (5) to lab(0)
			{
				currentPosition = location[0].getName();  //moves to lab
				currentDescription = location[0].getDescription(); //sets description
				score+=location[0].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[6].getName()))	//can go from cafeteria (6) to classroom (1)
			{
				currentPosition = location[1].getName();  //moves to classroom
				currentDescription = location[1].getDescription(); //sets description
				score+=location[1].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[7].getName()))	//can go from auditorium (7) to gym (2)
			{
				currentPosition = location[2].getName();  //moves to gym
				currentDescription = location[2].getDescription(); //sets description
				score+=location[2].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else
				System.out.println("You cannot go in that direction. Please choose another direction or enter 'm' for a map"); //when cannot advance in desired direction
		}
}		
}