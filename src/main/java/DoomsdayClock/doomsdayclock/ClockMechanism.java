package DoomsdayClock.doomsdayclock;

import DoomsdayClock.doomsdayclock.DailyBalance;
import DoomsdayClock.doomsdayclock.BalanceFactory;
import DoomsdayClock.doomsdayclock.BalanceSaver;
import java.util.ArrayList;
import java.io.*;

/*
 * The "middleware" of the program, does a single query of the database
 * upon instantiation, holds an array of data objects (DailyBalances), 
 * then does computation upon command. 
 */
public class ClockMechanism {
	
	//Class Variables
	ArrayList<DailyBalance> dbalances;
	float currentBalance;
	float averageDifference;
	String file = "";
	BalanceFactory bfactory = new BalanceFactory(file);

	/*
	 * Loads data from file using the BalanceFactory class
	 * Also sets current Balance and average difference class vars
	 */
	public void loadData() throws IOException{
		dbalances = this.bfactory.makeListFromFile();
		setAvgDifference();
		setCurrentBalance();
	}
	
	//Sets the average difference in balances
	public void setAvgDifference(){
		int counter = 0;
		float totalDifference = 0;
		for (int i = 0; i < this.dbalances.size() - 2; i++){
			counter++;
			totalDifference += (this.dbalances.get(i).getBalance() - this.dbalances.get(i+1).getBalance());			
		}
		this.averageDifference = totalDifference / counter;
	}
	
	//Sets the current balance of the account
	public void setCurrentBalance(){
		int lastIndex = this.dbalances.size()-1;
		this.currentBalance = this.dbalances.get(lastIndex).getBalance();
	}
	
	/* The titular clock
	 * For now, just returns the number of days. 
	 * Will probably want to make it a date at some point. 
	 */
	public int findDoomsday(){
		return (int)(this.currentBalance/this.averageDifference);
	}
	
	/*
	 * Adds a new Daily Balance to the ArrayList
	 */
	public void addDailyBalance(int year, int month, int day, float balance){
		this.dbalances.add(this.bfactory.makeBalance(year, month, day, balance));
	}
	
	/*
	 * Adds a new Daily Balance, assuming that the date is the current one
	 */
	public void addDailyBalance(float balance){
		
		// Sets up some variables for checking
		boolean years = false;
		boolean months = false;
		boolean days = false;
		
		// Gets the last entered to compare it to the current one
		DailyBalance newDBal = this.bfactory.makeBalance(balance);
		int lastIndex = this.dbalances.size()-1;
		DailyBalance lastEntered = this.dbalances.get(lastIndex);
		
		//Check to see if the current date is the same as the last updated
		if (newDBal.getDate().YEAR == lastEntered.getDate().YEAR) years = true;
		if (newDBal.getDate().MONTH == lastEntered.getDate().MONTH) months = true;
		if (newDBal.getDate().DAY_OF_MONTH == lastEntered.getDate().DAY_OF_MONTH) days = true;
		
		//If it is, replaces the last entered with the new value. If not, just adds it to the end. 
		if (years && months && days){
			this.dbalances.remove(lastEntered);
		}
		this.dbalances.add(this.bfactory.makeBalance(balance));
		
	}
	
	/*
	 * Saves the data back to the file it was originally written to
	 */
	public void saveBalances() throws IOException{
		BalanceSaver bs = new BalanceSaver(file);
		bs.saveData(this.dbalances);
		
	}
	
	

}
