package DoomsdayClock.doomsdayclock;

import java.util.ArrayList;
import java.io.*;
import DoomsdayClock.doomsdayclock.DailyBalance;
import java.util.Scanner;


public class BalanceSaver {
	
	File sourceFile;
	
	BalanceSaver(String pathToFile){
		
		this.sourceFile = new File(pathToFile);
	}
	
	public void saveData(ArrayList<DailyBalance> dBalanceList) throws IOException{
		PrintWriter dataSaver = new PrintWriter(sourceFile);
		Scanner dataChecker = new Scanner(sourceFile);
		for (DailyBalance dbal : dBalanceList){
			int year = dbal.getDate().YEAR;
			int month = dbal.getDate().MONTH;
			int day = dbal.getDate().DAY_OF_MONTH;
			float balance = dbal.getBalance();
			dataSaver.write(year + " " + month + " " + day + " " + balance + "\n");
			
		}
		dataSaver.flush();
		dataSaver.close();
		dataChecker.close();
		
		
	}

}
