package spreadsheetUpdates.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer pattern methods
 * @author shubham
 *
 */
public abstract class IObserverPool {
	
	protected List<IObserver> observers = new ArrayList<IObserver>();
	protected List<IObserver> revObservers = new ArrayList<IObserver>();
	
	/**
	 * register is used to register observer for cell
	 * @param observer = observer object of cell
	 */
	public void register(IObserver  observer) {
		if(observers.contains(observer)) {
			return;
		}
		
		observers.add(observer);
	}
	
	/**
	 * unregister function to unregister observer for cell
	 * @param observer = observer object of cell
	 */
	
	public void unregister(IObserver observer) {
		
		if(!observers.contains(observer)) {
			return;
		}
		
		observers.remove(observer);
	}
	
	public void unregisterAll() {
		observers.clear();
	}

	
}


