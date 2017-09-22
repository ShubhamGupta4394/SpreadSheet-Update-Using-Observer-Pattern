package spreadsheetUpdates.observer;

import java.util.List;

/**
 * Observer Pattern implementation
 * with methods
 * @author shubham
 *
 */
public interface IObserver {
    
	public int update();
     public void notifyAllObservers();
     public void notify(IObserver observer);
     
     
   //  public List<Cycle> detectCycle();
	public String address();
     
}


