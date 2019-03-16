//Joseph McDonough and Patrick McNamara
package hallSim;

import java.util.*;

public class Room 
{
	 private int id;
	 private boolean hasBeen;  //keeps track if the user has previously visited that place
	 private int points;
	 private String name;
	 private String description;
	 private ArrayList<String> items = new ArrayList<String>(); //holds list of obtainable items
	 private int goldCount; //gold amount in the room
	 Random rand = new Random();

	 public Room()
	 {
		 hasBeen = false;
		 points = 5;
	 }
	 public Room(int id, String name, String description, int g) {
		super();
		this.id = id;
		this.goldCount = g;
		this.description = description;
		this.name = name;
		this.hasBeen = false;
		this.points = 5;
	}
	 
	 public Room(int id, String name, String description) {  //constructor but gold count is random
		super();
		this.id = id;
		this.goldCount = rand.nextInt(20)+1;
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
	  goldCount = 0; 		 //removes gold from the room because the user has already collected it
	 }
	 public void addItem(String i)  //adds an item to the location
	 {
		 items.add(i);
	 }
	 public boolean hasItems() //check to see if there are any items at this location
	 {
		 if(items.size()!=0)
		 {
			 return true;
		 }
		 else
			 return false;
	 }
	 public String getItem(int index)  //returns the item that the room contains
	 {
		 return items.get(index);
	 }
	 
	 public void removeItem()  //removes the item from the room so that it can not be picked up again
	 {
		 items.remove(0);
	 }
	 public int getGold() 
	 {
		 return goldCount;
	 }
	 
}
