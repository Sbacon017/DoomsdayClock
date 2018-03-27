package DoomsdayClock.doomsdayclock;

import DoomsdayClock.doomsdayclock.ClockMechanism;
import java.util.Scanner;


/*
 * A facade class (although I don't know if you count UI as a 
 * true facade, but it does the same thing) that interfaces between the user
 * and the ClockMechanism class, which handles all the actual doings of maths
 * and things. 
 */
public class ClockUI {
	
	ClockMechanism clockMech;
	
	/*
	 * The UI requires a ClockMechanism class in order to process requests.
	 * It basically just determines what to do and sends the right call to the 
	 * cmech, much like... a facade?
	 */
	ClockUI(ClockMechanism clockMech){
		this.clockMech = clockMech;
	}
	
	
	/* Implements a simply while loop to take user input and
	 * determine what to do.
	 */
	public void runUI(){
		
		//Initialize variables and scanner
		boolean quit = false;
		Scanner keyboard = new Scanner(System.in);
		
		//While loop
		while (quit == false){
			
			//Print user options, formatted in a separate method
			printOptions();
			
			//Get user Input
			char userInput = keyboard.next().charAt(0);
			
			//Switch statement
			switch (userInput){
			case 'T':
			case 't':
				int daysRemaining = this.clockMech.findDoomsday();
				System.out.println("You have " + daysRemaining + " days remaining, my friend.");
				break;
			case 'A':
			case 'a':
				System.out.println("Enter a value to add:");
				float balance = keyboard.nextFloat();
				this.clockMech.addDailyBalance(balance);
				break;
			case 'Q':
			case 'q':
				quit = true;
				System.out.println("The clock is always ticking, my friend.");
				break;
			default:
				System.out.println("Please select an option from the list.");
			
			}
		}
		keyboard.close();
	}
	
	private void printOptions(){
		System.out.println("Select an option from the list:");
		System.out.println("T: \tGet Time Remaining");
		System.out.println("A: \tAdd A Daily Balance");
		System.out.println("Q: \tQuit");
	}

}
