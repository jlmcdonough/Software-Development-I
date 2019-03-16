/*
 * Joseph McDonough and Patrick McNamara
 * CMPT 220L-200
 * 14 March 2019
 * Project Three (HallSim v3.0)
 */
package hallSim;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/* WHAT WAS DONE IN THE UPDATE (3/14/19)
 * Modified Room constructor to take gold pieces
 * Added goldAmount to keep track of users gold
 * Added searchStore() to determine if the items is in the store
 * Added two new ArrayLists, one for the items and one for the corresponding prices
 * 
 */

public class ProjectThree {
	public static String currentPosition;  //holds the current location
	public static boolean gameOn;          //control for the game loop. true until user enters "quit"
	public static boolean canGo;           //control for update message. won't print if the user cannot walk in desired direction
	public static int monster;             //variable for the random number to determine monster event
	public static int score;               //variable for the users score
	public static Room [] location = new Room [8];  //list containing all the room objects
	public static String currentDescription;  //holds the description of the current location
	public static ArrayList<String> inventory = new ArrayList<String>(); //holds player inventory
	public static boolean mapFound;       //updates to true once player finds the map
	public static int roomId;
	public static int goldAmount; //amount of gold user has
	/*public static ArrayList<String> magicItems = new ArrayList<String>(); //list of magic shop items
	public static ArrayList<Integer> magicItemsPrice = new ArrayList<Integer>(); //items prices */
	public static ArrayList<MagicShopItems> magicItems = new ArrayList<MagicShopItems>();
	
	public static void main(String[] args) {
		Random rand = new Random();
		currentPosition = "Laboratory";

		String direction = "";
		gameOn = true;
		mapFound = false;
		score = 0;
		System.out.println("HALLWAY SIMULATOR");  //title

		location [0] = new Room(0, "Laboratory", "Welcome to the Laboratory!", 1);
		location [1] = new Room(1, "Classroom", "Welcome to the Classroom!", 10);
		location[1].addItem("watch");
		location [2] = new Room(2, "Gym", "Welcome to the Gym!", 5);
		location [3] = new Room(3, "Magic Shop", "Welcome to the Magic Shop!", 3);
		location [4] = new Room(4, "Nurse", "Welcome to the Nurse's Office!", 6);
		location[4].addItem("binoculars");
		location [5] = new Room(5, "Office", "Welcome to the main Office!", 20);
		location[5].addItem("compass");
		location [6] = new Room(6, "Cafe", "Welcome to the Cafeteria!", 7);
		location [7] = new Room(7, "Auditorium", "Welcome to the Auditorium!", 13);
		location[7].addItem("map");
		
		File file = new File("magicList");
		Scanner magicScanner;
		try {
			magicScanner = new Scanner(file);
		for(int i = 0; i<=666; i++)
		{
			int itemCost = rand.nextInt(20)+1;
			String itemName = magicScanner.nextLine();
			MagicShopItems temp = new MagicShopItems(itemName, itemCost);
			magicItems.add(temp);
			/* magicItems.add(itemName);
			magicItemsPrice.add(itemCost); */
			

		} 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		System.out.println("STARTING LOCATION: LABORATORY" + "\t STARTING SCORE: 0" + "\t YOUR GOLD: " + goldAmount);  //Starting message
		Scanner in = new Scanner(System.in);
		while (gameOn) {  //game loop
			monster = rand.nextInt(5);  //generators a number for monster event
			System.out.println("");
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
					Room thisRoom = location[roomId];
					goldAmount+= thisRoom.getGold();
					take(thisRoom);
				}
			} else 
				{
				getInput(currentPosition, direction);  //user doesn't want to skip or dance, assumes they walk or enter a different command
				if(canGo)
					{
					System.out.println("You walk to the " + currentPosition);
					System.out.println(currentDescription);
					Room thisRoom = location[roomId];
					goldAmount+= thisRoom.getGold();
					take(thisRoom);
					}
				}
			if(currentPosition.equals("Magic Shop")) //if the user is in the magic shop
			{
				enterShop();
			}
			System.out.println("LOCATION: " + currentPosition  + "\t SCORE: " + score + "\t YOUR GOLD: " + goldAmount);  //updates user with current location and score info

		}

		System.out.println("Game over, thanks for playing!");   //lets user know they successfully quit
		in.close();
	}

	public static MagicShopItems searchStore(String itemDesired)
	{
		for(int i = 0; i<magicItems.size(); i++)
		{
			if(magicItems.get(i).getName().contains(itemDesired))
			{
				return magicItems.get(i);
			}
		}
		return null;
	}
	public static void enterShop()
	{
		boolean inShop = true;  //loop control 
		Scanner storeScan = new Scanner(System.in);
		System.out.print("Would you like to purchase something from the magic shop (Y/N): ");
		String doYouBuy = storeScan.nextLine();
		while(inShop)
		{
			if(doYouBuy.equalsIgnoreCase("Y"))  //user WANTS to buy items, enters purchasing loop
			{
				System.out.println(" ");
				System.out.print("Enter the name of the item you would like to purchase: ");   //
				String itemDesired = storeScan.nextLine();
				if(searchStore(itemDesired)!=null) //searchStore returns index of item if found. If not found, it returns -999
				{
					if(searchStore(itemDesired).getCost()<=goldAmount)  //if the item is found, the gold amount is checked against users amount
					{
						inventory.add(itemDesired);  //adds item to inventory
						System.out.println("You have successfully purchased " + itemDesired + " for " + searchStore(itemDesired).getCost() + " pieces of gold!");
						goldAmount-=searchStore(itemDesired).getCost();  //user "pays" for the item
						//magicItemsPrice.remove(searchStore(itemDesired));  //corresponding gold price is removed
						magicItems.remove(searchStore(itemDesired));  //item is removed
						inShop = false;
					}
					else
					{
						System.out.println(" ");
						System.out.print("You do not have enough gold to pay for that item. Would you like to search again (Y/N): ");  
						String again = storeScan.nextLine();
						if(again.equalsIgnoreCase("Y"))
							inShop = true;
						else
							inShop = false;
					}
				}
				else
				{
					System.out.println();
					System.out.print("We do not have that item in stock. Would you like to search again (Y/N): ");  
					String again = storeScan.nextLine();
					if(again.equalsIgnoreCase("Y"))
						inShop = true;
					else
						inShop = false;
				}
			}
			else if(doYouBuy.equalsIgnoreCase("N"))
			{
				inShop = false;  //user does not want to buy an item and the while loop is exited
			}
			else  //user did not enter y or n
				System.out.println("You are not entering valid input. Please only enter 'Y' or 'N'.");
			
		}
		System.out.println();
		//storeScan.close();
	}
	public static void map() { //prints map
		if(mapFound)
		{
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
		else
			System.out.println("The map has not yet been found and cannot be viewed.  Keep searching.");
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
		else if (userInput.equals("v")) //user enters v, inventory appears
		{
			if(inventory.size()!=0)  //as long as inventory isn't empty, it prints inventory
			{
				for(String item: inventory)  //goes through the inventory arrayList and prints inventory
				{
					System.out.println("\t\u2023" + item);  //prints items
				}
				System.out.println("");
			}
			else
				System.out.println("You have no items in your inventory. Keep searching!");  //lets user know they have no items to view
		}
		else if (userInput.equalsIgnoreCase("h"))  //user enters h, help menu appears
			System.out.println("Valid commands are:" 
					+ "\n\t"+"\u2022" + "'n' to move character north."
					+ "\n\t"+"\u2022" + "'s' to move character south."
					+ "\n\t"+"\u2022" + "'e' to move character east."
					+ "\n\t"+"\u2022" + "'w' to move character west."
					+ "\n\t"+"\u2022" + "'dance' to dance into a new location."
					+ "\n\t"+"\u2022" + "'skip' to skip(the action) into a new location."
					+ "\n\t"+"\u2022" + "'m' to view the map."
					+ "\n\t"+"\u2022" + "'v' to view your inventory."
					+ "\n\t"+"\u2022" + "'h' to view the help menu."
					+ "\n\t"+"\u2022" + "'quit' to quit the game");
		else if (userInput.equalsIgnoreCase("quit"))  //user enters quit, gameLoop get sets to false and gets exited
			gameOn = false;
		else  //if no valid input is given, prints error message and reprompts user to enter a command
			System.out.println("You did not enter a valid input"); 
	}
	
	public static void take(Room currRoom)
	{
		Scanner doYouWant = new Scanner(System.in);
		boolean tempBoolean = false;
		if(currRoom.hasItems())
			 tempBoolean = true;
		else
			System.out.println("There are no items in this room.");
		while(tempBoolean)
		{
				System.out.println("In this room, there is " + currRoom.getItem(0) + ".");
				System.out.print("Would you like to take the item found? (y/n)");
				String ans = doYouWant.nextLine();
				if(ans.equalsIgnoreCase("y"))
				{
					inventory.add(currRoom.getItem(0));
					if(currRoom.getItem(0).equalsIgnoreCase("map"))
					{
						mapFound = true;
					}
					currRoom.removeItem();
					System.out.println("ITEM ACQUIRED!");  //lets user know the successfully picked up an item
					tempBoolean = false;
				}
				else if(ans.equalsIgnoreCase("n"))
				{
					tempBoolean = false; //exits questioning loop
				}
				else
					System.out.println("You are not entering valid input. Please type 'y'or 'n '");		
		}


	}
	
	public static void determineMove(String currRoom, String userInput)
	{
		if (userInput.equalsIgnoreCase("n")) //user enters n for north
		{
			if(currentPosition.equals(location[0].getName()))	//can go from lab (0) to classroom (1)
			{
				currentPosition = location[1].getName();  //moves to classroom 
				currentDescription = location[1].getDescription(); //sets description
				roomId = 1;
				score+=location[1].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[1].getName()))	//can go from classroom (1) to gym (2)
			{
				currentPosition = location[2].getName();  //moves to gym
				currentDescription = location[2].getDescription(); //sets description
				roomId = 2;
				score+=location[2].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[3].getName()))	//can go from magic shop (3) to nurse (4)
			{
				currentPosition = location[4].getName();  //moves to nurse
				currentDescription = location[4].getDescription(); //sets description
				roomId = 4;
				score+=location[4].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[5].getName()))	 //can go from office (5) to cafeteria (6)
			{
				currentPosition = location[6].getName();  //moves to cafeteria
				currentDescription = location[6].getDescription(); //sets description
				roomId = 6;
				score+=location[6].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[6].getName()))	//can go from cafeteria (6) to auditorium (7)
			{
				currentPosition = location[7].getName();  //moves to auditorium
				currentDescription = location[7].getDescription(); //sets description
				roomId = 7;
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
				roomId = 0;
				score+=location[0].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[2].getName()))	//can go from gym (2) to classroom (1)
			{
				currentPosition = location[1].getName();  //moves to classroom
				currentDescription = location[1].getDescription(); //sets description
				roomId = 1;
				score+=location[1].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[4].getName()))	//can go from nurse (4) to magic shop (3)
			{
				currentPosition = location[3].getName();  //moves to magic shop
				currentDescription = location[3].getDescription(); //sets description
				roomId = 3;
				score+=location[3].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[6].getName()))	//can go from cafeteria (6) to office (5)
			{
				currentPosition = location[5].getName();  //moves to office
				currentDescription = location[5].getDescription(); //sets description
				roomId = 5;
				score+=location[5].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[7].getName()))	//can go from auditorium (7) to cafeteria (6)
			{
				currentPosition = location[6].getName();    //moves to cafeteria
				currentDescription = location[6].getDescription(); //sets description
				roomId = 6;
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
				roomId = 5;
				score+=location[5].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[1].getName()))	//can go from classroom (1) to cafeteria (6)
			{
				currentPosition = location[6].getName();   //moves to cafeteria
				currentDescription = location[6].getDescription(); //sets description
				roomId = 6;
				score+=location[6].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[2].getName()))	//can go from gym (2) to auditorium(7)
			{
				currentPosition = location[7].getName();  //moves to auditorium
				currentDescription = location[7].getDescription(); //sets description
				roomId = 7;
				score+=location[7].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[3].getName()))	//can go from magic shop (3) to lab(0)
			{
				currentPosition = location[0].getName();   //moves to lab
				currentDescription = location[0].getDescription(); //sets description
				roomId = 0;
				score+=location[0].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[4].getName()))	//can go from nurse (4) to classroom (1)
			{
				currentPosition = location[1].getName();   //moves to classroom
				currentDescription = location[1].getDescription(); //sets description
				roomId = 1;
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
				roomId = 3;
				score+=location[3].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[1].getName()))	//can go from classroom (1) to nurse (4)
			{
				currentPosition = location[4].getName();  //moves to nurse
				currentDescription = location[4].getDescription(); //sets description
				roomId = 4;
				score+=location[4].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[5].getName()))	//can go from office (5) to lab(0)
			{
				currentPosition = location[0].getName();  //moves to lab
				currentDescription = location[0].getDescription(); //sets description
				roomId = 0;
				score+=location[0].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[6].getName()))	//can go from cafeteria (6) to classroom (1)
			{
				currentPosition = location[1].getName();  //moves to classroom
				currentDescription = location[1].getDescription(); //sets description
				roomId = 1;
				score+=location[1].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else if(currentPosition.equals(location[7].getName()))	//can go from auditorium (7) to gym (2)
			{
				currentPosition = location[2].getName();  //moves to gym
				currentDescription = location[2].getDescription(); //sets description
				roomId = 2;
				score+=location[2].getPoints(); //adds location's points to the player's score
				canGo = true;
			}
			else
				System.out.println("You cannot go in that direction. Please choose another direction or enter 'm' for a map"); //when cannot advance in desired direction
		}
}		
}