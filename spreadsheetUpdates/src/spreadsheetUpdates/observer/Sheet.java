package spreadsheetUpdates.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spreadsheetUpdates.util.FileProcessor;
import spreadsheetUpdates.util.Logger;

public class Sheet {
	private FileProcessor fileProcessorIn,fileProcessorOut;
	public static String rows[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	public static Map<String,Integer> rowno = new HashMap<String, Integer>();
	public static List<List<Cell>> sheetmatrix = new ArrayList<List<Cell>>();
	public List<Cycle> values = new ArrayList<Cycle>();
	public StringBuffer out = new StringBuffer();
	public int sum=0,i=0;
	public Map<Cell,Cell[]> dependent = new HashMap<Cell, Cell[]>();
	
	
	public Sheet(FileProcessor fp,FileProcessor fp2){
		Logger.writeMessage("In Sheet Constructor",
				Logger.DebugLevel.CONSTRUCTOR);
		
		fileProcessorIn = fp;
		fileProcessorOut = fp2;
	}
	/**
	 * initialize character row value to integer
	 */
	
	public void intializerow(){
		Logger.writeMessage("Initialize of row to integer value",
				Logger.DebugLevel.INITIALIZEROW);
		for(int i=0;i<26;i++){
			rowno.put(rows[i], i);
		}
	}
	
	/**
	 * initializeSheet is used to initialize cell
	 * values to 0
	 */
	
	public void initializeSheet(){
		Logger.writeMessage("Initialization of Sheet values to 0",
				Logger.DebugLevel.INITIALIZESHEET);
		for(int i=0;i<26;i++){
			List<Cell> cellvalue = new ArrayList<Cell>();

			for(int j=0;j<26;j++){
				Cell c1 = new Cell();
				c1.row = i;
				c1.column = j;
				c1.value = 0;
				cellvalue.add(c1);
			}
			sheetmatrix.add(cellvalue);

		}
	}
	
	/**
	 * allocate method is used to allocate values to cell
	 */
	
	public void allocate(){
		String input;
		
		while((input=fileProcessorIn.readLineFromFile())!=null){
			String[] inp = input.split("=");
			String lhs = inp[0];
			String rhs = inp[1];
			String[] val = rhs.split("[+]");

			Cell cinp = getCellForAddress(lhs);
			/**
			*Unregister to update the observers
			*/			
			for(IObserver observer : cinp.revObservers) {
				Cell cell = (Cell) observer;
				cell.unregister(cinp);
			}

			/**Also remove from backward referencing
			*/
			cinp.revObservers.clear();
			cinp.operands.clear();
			/** First operand
			*/			
			if(isInteger(val[0])) {
				cinp.operands.add(Integer.parseInt(val[0]));
				cinp.lhs = Integer.parseInt(val[0]);
			} else {
				Cell cell = getCellForAddress(val[0]);
				cell.register(cinp);
				
				cinp.lhs = cell;
			}

			/** Second operand
			*/
			if(isInteger(val[1])) {
				cinp.operands.add(Integer.parseInt(val[1]));
				cinp.rhs = Integer.parseInt(val[1]);
			} else {
				Cell cell = getCellForAddress(val[1]);
				cell.register(cinp);
				cinp.rhs = cell;
			}
		
			
			Logger.writeMessage("Notify function called",
					Logger.DebugLevel.NOTIFYCALLED);
			
			
			List<Cycle> cycles = null;
			
			if((cycles = cinp.detectCycle()).size() > 0) {
				
				for(Cycle cycle : cycles) {
					((Cell)cycle.dependent).value = 0;
					((Cell)cycle.dependencyOver).value = 0;
					//cinp.notifyAllObservers();
					//System.out.println(cycle);
					out.append(cycle.toString()+"\n");
				}
			} else {
				cinp.value=cinp.update();
				cinp.notifyAllObservers();
				
			}
			
		
		}
		
	
	}
	/*
	 * addResult to file
	 */
	public void addResult(){
		for(List<Cell> cells : sheetmatrix) {
			for(Cell cell : cells) {
				sum += cell.value;
			}
		
		}
		String s = "The sum of all cell values is: "+sum;
		fileProcessorOut.writeLineToFile(out.toString());
		fileProcessorOut.writeLineToFile(s);
		fileProcessorOut.close();
	}
	
	
	/**
	 * method will get Cell for Address
	 * @param address = string address of cell
	 * @return Cell object
	 */
	
	public Cell getCellForAddress(String address) {
		String rowout = address.substring(0,1);
		String columnout = address.substring(1, address.length());
		int rownumberout = rowno.get(rowout);
		int colnumberout = Integer.parseInt(columnout);

		Cell cell = sheetmatrix.get(rownumberout).get(colnumberout-1);
		cell.row = rownumberout;
		cell.column = colnumberout;
		cell.address = address;
		return cell;
	}
	
	 /**
	 * to checkInteger
	 * @param v = String passed to check
	 * @return boolean value for condition
	 */
	
	public boolean isInteger(String v){
		try{
			Integer.parseInt(v);
		}
		catch(NumberFormatException e){
			return false;
		}
		return true;
	}
}
