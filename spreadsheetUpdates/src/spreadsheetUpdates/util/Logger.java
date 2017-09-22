
package spreadsheetUpdates.util;

public class Logger{
	
    public static enum DebugLevel {RELEASE, INITIALIZEROW ,INITIALIZESHEET, NOTIFYCALLED, CONSTRUCTOR
                                   };

    private static DebugLevel debugLevel;


    public static void setDebugValue (int levelIn) {
	switch (levelIn) {
		case 0: debugLevel = DebugLevel.RELEASE; break;
		case 1: debugLevel = DebugLevel.INITIALIZEROW; break;
		case 2: debugLevel = DebugLevel.INITIALIZESHEET; break;
		case 3: debugLevel = DebugLevel.NOTIFYCALLED; break;
		case 4: debugLevel = DebugLevel.CONSTRUCTOR; break;
	}
    }

    public static void setDebugValue (DebugLevel levelIn) {
	debugLevel = levelIn;
    }

    public static void writeMessage (String  message  ,
                                     DebugLevel levelIn ) {
	if (levelIn == debugLevel)
	    System.out.println(message);
    }

    public String toString() {
	return "Debug Level is " + debugLevel;
    }
}