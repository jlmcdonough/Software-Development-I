//Joseph McDonough and Patrick McNamara

import java.util.*;


public class projectOne 
{
 public static String currentPosition;
 public static boolean gameOn;
 public static boolean skip;
 public static boolean dance;
 public static int score;
 public static int monster;
 public static void main(String[] args)
 {
  Random rand = new Random();
  currentPosition = "LABORATORY";
  Scanner in = new Scanner(System.in);
  String direction = "";
  gameOn = true;
  score = 0;
  System.out.println("THE GAME");
  System.out.println("STARTING LOCATION: LABORATORY" + "\t STARTING SCORE: 0");
  while (gameOn) {
   monster = rand.nextInt(5);
   System.out.print("Enter a command: ");
   direction = in.nextLine();
   if (direction.equals("skip") || direction.equals("dance")) {
    System.out.print("Chose a direction to go: ");
    String dir2 = in.nextLine();
    getInput(currentPosition, dir2);
    System.out.println("You " + direction + " into the " + currentPosition);
   } else {
    getInput(currentPosition, direction);
    System.out.println("You walk to the " + currentPosition);
   }
   System.out.println("LOCATION: " + currentPosition + "\t SCORE: " + score);
  }
  in.close();
  System.out.println("Game over, thanks for playing!");
 }

 public static void map() {
  System.out.println("----------------");
  System.out.println("|      GYM     |");
  System.out.println("|              |");
  System.out.println("|       ^      |");
  System.out.println("|       |      |");
  System.out.println("|       v      |");
  System.out.println("|              |");
  System.out.println("|   CLASSROOM  |");
  System.out.println("|              |");
  System.out.println("|       ^      |");
  System.out.println("|       |      |");
  System.out.println("|       v      |");
  System.out.println("|              |");
  System.out.println("|   LABORATORY |");
  System.out.println("----------------");
 }

 public static void getInput(String currRoom, String userInput) 
 {
  String newLoc = "";
  if(userInput.equals("n") || userInput.equals("s")) 
   {
   if(currRoom.equals("LABORATORY") && userInput.equals("n")) {
    newLoc = "CLASSROOM";
    currentPosition = newLoc;
    score+=5;}
   else if(currRoom.equals("CLASSROOM") && userInput.equals("n")) {
    newLoc = "GYM";
    currentPosition = newLoc;
    score+=5;}
   else if(currRoom.equals("GYM") && userInput.equals("s")) {
    newLoc = "CLASSROOM";
    currentPosition = newLoc;
    score+=5;}
   else if(currRoom.equals("CLASSROOM") && userInput.equals("s")) {
    newLoc = "LABORATORY";
    currentPosition = newLoc;
    score+=5;}
   else
   {
    System.out.println("You cannot go that way");
    getInput(this,this);
   }
   if(monster==3)
   {
    System.out.println("You were attacked by a monster and lost 3 points");
    score-=3;
   }
   }
  else if(userInput.equals("m"))
   map();
  else if(userInput.equals("h"))
   System.out.println("Valid commands are: 'n' for north, 's' for south, "
     + "'dance' for dance, 'skip' to skip(the action), 'h' for help, 'm' for map, and 'quit' to quit");
  else if(userInput.equals("dance"))
  {
   dance = true;
   System.out.println("You are prepared to dance into the next room");
  }
  else if(userInput.equals("skip"))
  {
   skip = true;
   System.out.println("You are prepared to skip into the next room");
  }
  else if(userInput.equals("quit"))
   gameOn = false;
  else
   System.out.println("You did not enter a valid input"); 
 }


}