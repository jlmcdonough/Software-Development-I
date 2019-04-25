/**
 * Joseph McDonough and Patrick McNamara
 * CMPT 220L-200
 * 25 April 2019
 * Lab 10
 */

package lab10;

import java.util.*;

public class HighCardGame 
{
	public static void main(String args[])
	{
		System.out.println("This game is called high card. The object of the game is to randomly pick two cards from a standard deck and determine which is the higher card.");
		Random rand = new Random();
		int n1 = rand.nextInt(13);  //generates a random number the length of the number array for card 1
		int n2 = rand.nextInt(13);  //generates a random number the length of the number array for card 2
		int s1 = rand.nextInt(4);	//generates a random number the length of the suits array for card 1
		int s2 = rand.nextInt(4);   //generates a random number the length of the suits array for card 2
		String[] numbers = new String[] {"A","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
		String[] suits = new String[]{"clubs","diamonds","hearts","spades"};
		Card c1 = new Card(numbers[n1],suits[s1],n1,s1);
		Card c2 = new Card(numbers[n2],suits[s2],n2,s2);
		//Card c1 = new Card("King", "spades", 12, 3);  TEST TO MAKE SURE THAT THE THIRD CARD IS DRAWN
		//Card c2 = new Card("King", "spades", 12, 3);
		
		//Card c1 = new Card("King","clubs",12,0);  TEST TO MAKE SURE ORDER OF SUITS WORKED
		//Card c2 = new Card("King","spades",12,3);
		
		String c1Wins = "Card One is the higher card.";
		String c2Wins = "Card Two is the higher card.";
		System.out.println("Card One " + c1.toString());  //prints out what card one is
		System.out.println("Card Two " + c2.toString());  //prints out what card two is
		
		boolean cardControl = true;  //control to ensure that the loop will run until card 1 is different than card 2
		while(cardControl)
		{
			if(c1.checkCards(c2))  //if they are different cards, they are then compared for the higher value
			{
				if(c1.getNumberIndex()>c2.getNumberIndex())  //number on card 1 is higher than on card 2
				{
					System.out.println(c1Wins);
					cardControl = false;
				}
				else if(c2.getNumberIndex()>c1.getNumberIndex())  //number on card 2 is higher than on card 1
				{
					System.out.println(c2Wins);
					cardControl = false;
				}
				else if(c1.getNumberIndex()==c2.getNumberIndex())  //number on card 1 is equal to number on card 2
				{
					if(c1.getSuitIndex()>c2.getSuitIndex())   //the suit of card 1 is higher than the suit of card 2
					{
						System.out.println(c1Wins);
						cardControl = false;
					}
					else if(c2.getSuitIndex()>c1.getSuitIndex())  //the suit of card 2 is higher than the suit on card 1
					{
						System.out.println(c2Wins);
						cardControl = false;
					}
				}
			}
			else  //card 1 is the exact same as card 2
			{	
				int tempNum = rand.nextInt(13);  //generates a new number for card 2
				int tempSuit = rand.nextInt(4);  //generates a new suit for card 2
				System.out.println("Cards were the same, drawing a third card.");  //lets user know the cards were the same and a new card is being drawn
				c2.setNumber(numbers[tempNum]);  //sets card 2's number to its new one
				c2.setNumberIndex(tempNum);    //sets card 2's number index to its new one
				c2.setSuit(suits[tempSuit]);   //sets card 2's suit to its new one
				c2.setSuitIndex(tempSuit);   //sets card 2's suit index to its new one
				System.out.println("New Card Two " + c2.toString());  //lets user know what the new card 2 is looking like
			}
		}
	}
}
