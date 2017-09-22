package spreadsheetUpdates.observer;

import java.util.ArrayList;
import java.util.List;

public class Cell extends IObserverPool implements IObserver {
	public int value;
	public List<Integer> operands = new ArrayList<Integer>();
	public int column;
	public int row;
	public String address;

	public Object lhs;
	public Object rhs;
	
	
	public int calculate() {
		int sum = 0;
		
		
		if(lhs == null || rhs == null){
			return 0;
		}
		
		if(lhs instanceof Integer){
			int i = (Integer) lhs;	
			sum +=  i; 	
		}
		else{
			Cell clhs = (Cell) lhs;
			sum += clhs.value;  
		}
		
		if(rhs instanceof Integer){
			int i = ((Integer) rhs);	
			sum +=  i; 			
		}
		else{
			Cell crhs = (Cell) rhs;
			sum += crhs.value;
		}
		
		return sum;
	}

	public int update() {
		value = calculate();
		return value;
	}

	public void notifyAllObservers() {
		for (IObserver observer : observers) {
			observer.update();
			observer.notifyAllObservers();
		    
		}
	}

	public void notify(IObserver observer) {
		observer.update();
		
	}

	public String address() {
		return address;
	}
	
	
	public List<Cycle> detectCycle() {

		List<Cycle> toReturn = new ArrayList<Cycle>();
		if(this.observers.contains(lhs)) {
			Cycle cycle = new Cycle();
			cycle.dependent = (Cell)lhs;
			cycle.dependencyOver = this;
			toReturn.add(cycle);
		}
		

		if(this.observers.contains(rhs)) {
			Cycle cycle = new Cycle();
			cycle.dependent = (Cell)rhs;
			cycle.dependencyOver = this;
			toReturn.add(cycle);
		}
		if(toReturn.size() > 0) return toReturn;
	
		for (IObserver observer : observers) {
			if(((Cell)observer).observers.contains(lhs)) {
				Cycle cycle = new Cycle();
				cycle.dependent = (Cell)lhs;
				cycle.dependencyOver = this;
				toReturn.add(cycle);
				
			}

			if(((Cell)observer).observers.contains(rhs)) {
				Cycle cycle = new Cycle();
				cycle.dependent = (Cell)rhs;
				cycle.dependencyOver = this;
				toReturn.add(cycle);
			}
		
		}
		return toReturn;
		
	}

	
	@Override
	public void register(IObserver observer) {
		super.register(observer);
		
		Cell cell = (Cell) observer;
		cell.revObservers.add(this);
	}

	@Override
	public boolean equals(Object arg0) {
		Cell cell = (Cell) arg0;
		return cell.address.equalsIgnoreCase(this.address);
	}

	@Override
	public String toString() {
		return address;
	}

}
