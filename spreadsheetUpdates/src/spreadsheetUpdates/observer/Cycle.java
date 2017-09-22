package spreadsheetUpdates.observer;

public class Cycle {
	
	public IObserver dependent;
	public IObserver dependencyOver;
	
	

	@Override
	public String toString() {
		return dependent.address() + " is dependent on " + dependencyOver.address();
	}
}
