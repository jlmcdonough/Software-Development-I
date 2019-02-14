//Joseph McDonough and Patrick McNamara
package hallSim;

public class Room 
{
	 private int id;
	 private boolean hasBeen;  //keeps track if the user has previously visited that place
	 private int points;
	 private String name;
	 private String description;
	 

	 public Room()
	 {
		 hasBeen = false;
		 points = 5;
	 }
	 public Room(int id, String name, String description) {
		super();
		this.id = id;
		this.description = description;
		this.name = name;
		this.hasBeen = false;
		this.points = 5;
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
	 public String getDescription()
	 {
		 return description;
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
