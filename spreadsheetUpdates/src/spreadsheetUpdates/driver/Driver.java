package spreadsheetUpdates.driver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import spreadsheetUpdates.observer.Sheet;
import spreadsheetUpdates.util.FileProcessor;
import spreadsheetUpdates.util.Logger;

/**
 * Driver Class containing main method
 * 
 * @author shubham
 * 
 */
public class Driver {

	/**
	 * main method to start the program
	 * 
	 * @param args
	 *            = 3 arguments are passed from command line
	 */
	
	private static String input, output;
	private static int debug_value;
	public static BufferedReader bufferedReaderInput;
	public static BufferedWriter bufferedWriterOutput;
   
	public static void main(String args[]) {
		Driver dr = new Driver();
		System.out.println(args.length);
		dr.validateArgs(args);
		Logger.setDebugValue(debug_value);
		FileProcessor fileProcessor = new FileProcessor(
				bufferedReaderInput);
		FileProcessor fileProcessorOut = new FileProcessor(output);
		Sheet sheet = new Sheet(fileProcessor,fileProcessorOut);
		sheet.initializeSheet();
		sheet.intializerow();
		sheet.allocate();
		sheet.addResult();
	}

	private void validateArgs(String args[]) {
		if (args.length == 3) {
			input = args[0];
			output = args[1];
			try {
				debug_value = Integer.parseInt(args[2]);
				if (debug_value < 0 || debug_value > 4) {
					System.err.println("Debug value should be between 0 to 4");
					System.exit(-1);
				}
				File inputFile = new File(input);
				if (inputFile.length() == 0) {
					System.err.println("Input file is empty");
					System.exit(-1);
				}
				FileInputStream fileInputStream = new FileInputStream(
						inputFile);
				bufferedReaderInput = new BufferedReader(
						new InputStreamReader(fileInputStream));


			} catch (IllegalArgumentException ex) {
				System.err
						.println("NumberFormatException-Cannot parse to integer.");
				ex.printStackTrace();
				System.exit(-1);
			} catch (FileNotFoundException e) {
				System.err
						.println("FIleNotFoundException- File not found in arguments passed at command line");
				e.printStackTrace();
				System.exit(-1);
			}
		} else {
			System.err
					.println("Invalid number of arguments. Expected 3 arguments");
			System.exit(-1);
		}
		
	}
	
}



