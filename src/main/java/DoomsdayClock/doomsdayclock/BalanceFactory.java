package DoomsdayClock.doomsdayclock;

import DoomsdayClock.doomsdayclock.DailyBalance;
import java.util.Calendar;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/*
 * A factory class for DailyBalance objects. Can also read in objects from a file. 
 */
public class BalanceFactory {
	
	File sourceFile;
	
	BalanceFactory(String FilePath){
		this.sourceFile = new File(FilePath);
	}
	
	/*
	 * Basic create DailyB object, using the objects constructor
	 */
	public DailyBalance makeBalance(float balance){
		Calendar date = Calendar.getInstance();
		return new DailyBalance(date, balance);
	}
	
	/*
	 * Overload of makeBalance that creates and sets the date from ints
	 */
	public DailyBalance makeBalance(int year, int month, int day, float balance){
		Calendar date = Calendar.getInstance();
		date.set(year, month, day);
		return new DailyBalance(date, balance);
		
	}
	
	/*
	 * Method to read in data from a given file and create a bunch of 
	 * DailyBalance object, returning an ArrayList of them. 
	 */
	public ArrayList<DailyBalance> makeListFromFile() throws IOException{
		
		//Init array to be returned
		ArrayList<DailyBalance> balances = new ArrayList<DailyBalance>();
		
		//Create scanner object
		Scanner fileReader = new Scanner (this.sourceFile);
		
		while (fileReader.hasNext()){
			
			//Read in pertinent info
			int year = fileReader.nextInt();
			int month = fileReader.nextInt();
			int day = fileReader.nextInt();
			float balance = fileReader.nextFloat();
			
			//Create Dbal object
			DailyBalance dbal = makeBalance(year, month, day, balance);
			
			//Add it to the ArrayList
			balances.add(dbal);
		}
		
		//Close up shot
		fileReader.close();
		
		//Return the list
		return balances;
		
	}
	

}
