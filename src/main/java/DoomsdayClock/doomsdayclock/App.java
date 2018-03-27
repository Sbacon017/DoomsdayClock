package DoomsdayClock.doomsdayclock;

import DoomsdayClock.doomsdayclock.ClockUI;
import DoomsdayClock.doomsdayclock.ClockMechanism;
import java.io.*;

/**
 * 
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	ClockMechanism cmech = new ClockMechanism();
    	cmech.loadData();
        ClockUI clockUI = new ClockUI(cmech);
        clockUI.runUI();
    }
}
