package hallSim;

public class Room 
{
	 private boolean hasBeen;  //keeps track if the user has previously visited that place
	 private int points;
	 private String name;
	 
	 public Room()
	 {
		 hasBeen = false;
		 points = 5;
	 }
	 public Room(String n)
	 {
	  hasBeen = false;  //by default, user has never entered the location
	  points = 5;
	  name = n; 
	 }
	 public String getName()
	 {
		 return name;
	 }
	 public int getPoints()
	 {
	  return points;
	 }
	 public boolean hasBeen() //test to see if user has ever been in the room
	 {
	  return hasBeen;
	 }
	 public void nowBeen()   //used after first time player enters a room. 
	 { 						 //Now the game knows its been previously visited 
	  hasBeen = true;        //next time the user goes there
	 }
}
