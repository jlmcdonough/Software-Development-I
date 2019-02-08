/*
 * Joseph McDonough
 * CMPT220L-200 Software Development 1
 * 7 February 2019
 * This program is a simple guessing game with the user.
 * This program generates a number between 1 and 10 and the user ought to guess the number
 * Game continues until the user quits
 */

package GuessingGame;

import java.util.*;

public class GuessingGame 
{
	public static void main(String[] args)
	{
		int answer, guess;  //answer holds the random number. guess holds the user guess
		boolean stillPlaying = true;  //control for the while loop -> set to false when user enters 'q'
		String isPlaying;  //holds the users input when asked if they want to continue
		Scanner in = new Scanner(System.in);
		Random rng = new Random();
		while(stillPlaying)
		{
			answer = rng.nextInt(10);   //gives numbers 0-9
			answer+=1;   //adjust the range so that the answer will be between 1-10
			System.out.println("I'm thinking of a number between 1 and 10. Guess what it is.");
			System.out.print("Enter your guess: ");
			guess = in.nextInt();
			if(guess==answer)
			{
				System.out.println("You got it! Nice guess!");
			}
			else
				System.out.println("Sorry, that was not correct. The correct answer was " + answer + ".");
			System.out.print("Do you want to play again? Enter 'q'to quit: ");
			isPlaying = in.next();
			//isPlaying = isPlaying.substring(0,1);  //takes the first letter of the user input in case the user enters quit, the program will still quit
			if(isPlaying.equals("q"))  //checks to see if user entered q
			{
				stillPlaying = false;  //causes the program to exit the while loop
				System.out.println("Thanks for playing! Goodbye!");
			}
			else
				System.out.println("Thanks for playing! Good luck!");
		}
		in.close();
	}
}
