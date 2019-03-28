/**
 * Joseph McDonough and Patrick McNamara
 * 28 March 2019
 * Lab 8
 * CMPT 220L-200
 */

package ReverseWords;

import java.util.*;
import jsjf.*;

public class ReverseWords {
	public static void main(String args[])
	{
		ArrayStack myStack = new ArrayStack(7);  //creates a new stack
		System.out.println("You will be prompted to enter seven words.");  //lets user know they are going to have to enter seven words
		myStack = getWords(myStack);   //function used to get the words
		reverseWords(myStack);   //function used to print the words in reverse order
		System.out.println("You're words have been reversed!");  //lets the user know the words have been successfully reversed, concluding the program
	}
	
	/**
	 * Prompts the user for seven words and upon entering the seven words
	 * Words are placed on top of each other so the first word is at the bottom of the stack
	 * Returns the stack containing them
	 * @param theStack - the previously created stack of size 7, which is empty
	 * @return theStack with the words in it
	 */
	public static ArrayStack getWords(ArrayStack theStack)
	{
		Scanner in = new Scanner(System.in);
		String temp = "";
		for(int i = 1; i <8; i++)  //the directions call for seven words. Starting value is 1 so the user can see what value they are on
		{
			System.out.print("Word " + i + ": ");  //asks the users to print the ith word
			temp = in.nextLine();  //gets the ith word
			theStack.push(temp);  //puts the word atop the stack
		}
		in.close();  
		return theStack; //returns the stack containing the words
	}
	
	/**
	 * While the stack is not empty, it prints the value at the top 
	 * which is the reverse order of how they were inputed
	 * @param wordList - the stack containing the words
	 */
	public static void reverseWords(ArrayStack wordList)
	{
		while(!wordList.isEmpty())  //checks to see if stack is empty
		{
			try
			{
				System.out.println(wordList.pop());  //prints the value atop the list
			}
			catch (Exception EmptyCollectionException)  //exception thrown if stack is empty
			{
				System.out.println("The stack is empty.");  //lets user know the stack is empty
			}
		}
	}
}
