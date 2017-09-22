package spreadsheetUpdates.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * FileProcessor is used to 
 * process file operations
 * @author shubham
 * 
 */
public class FileProcessor {
	BufferedReader in;
	BufferedWriter bw;
	String outfile;
	String read;

	
	public FileProcessor(BufferedReader bufReaderIn) {
		Logger.writeMessage("In FileProcessor, BufferedReader constructor",
				Logger.DebugLevel.CONSTRUCTOR);
		in = bufReaderIn;
	}

	public FileProcessor(String outFileNameIn) {
		Logger.writeMessage("In FileProcessor, String Parameter constructor",
				Logger.DebugLevel.CONSTRUCTOR);
		
		outfile = outFileNameIn;
		File outputFile = new File(outfile);
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(outputFile);
			bw = new BufferedWriter(new OutputStreamWriter(fout));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * * readLineFromFile is used to read contents of input
	 * file.
	 * @return String as output
	 */
	
	public synchronized String readLineFromFile() {
		try {
			read = in.readLine();
		} catch (IOException e) {
			System.err.println("Error in Reading File");
			e.printStackTrace();
		}
		return read;
	}
	
	/**
	 * * writeLineToFile is used to write contents on file
	 * 
	 * @param data = data to be written in output file
	 */
	
	
	public synchronized void writeLineToFile(String data) {
		Logger.writeMessage(data,
				Logger.DebugLevel.RELEASE);
		try {
			bw.write(data);
			bw.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		return super.toString();
	}
}
