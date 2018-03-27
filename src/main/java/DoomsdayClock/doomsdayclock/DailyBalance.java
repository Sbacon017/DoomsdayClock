package DoomsdayClock.doomsdayclock;

import java.util.Calendar;

//Data object containing Calendar obj and float balance
public class DailyBalance {
	Calendar date;
	float balance;
	
	DailyBalance(Calendar date, float balance){
		this.date = date;
		this.balance = balance;
	}
	
	//Getters
	public float getBalance(){
		return this.balance;
	}
	
	public Calendar getDate(){
		return this.date;
	}
	

}
