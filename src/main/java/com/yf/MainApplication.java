package com.yf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.yf.beans.CSVItem;
import com.yf.reader.FileReaderImpl;
import com.yf.reader.IFileReader;
import com.yf.writer.Writer;
import com.yf.writer.CSVWriter;

/**
 * Created by ashishagarwal on 6/10/17.
 */
public class MainApplication {
	
	public static void main(String[] a) {
		long begin = System.currentTimeMillis();

		// Read from the Stocks.txt file
		System.out.println("Reading file...");
		IFileReader fileReader = new FileReaderImpl();
		List<String> stockSymbols = null;
		try {
			stockSymbols = fileReader.read();
		} catch (IOException e) {
			System.out.println("Problem reading the stocks file !! " + e.getMessage());
			return;
		}
		System.out.println("Read " + stockSymbols.size() + " symbols from file...");

		// Now get the quotes		
		System.out.println("\nCool. Now lets get the quotes for these...(Be patient. Good things take time)");
		StockQuotes quotes = new StockQuotes();
		Map<String, CSVItem> map = null;
		
		try {
			map = quotes.getStockQuotes(stockSymbols);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
			return;
		}

		System.out.println("\nGot the quotes. Lets write the csv ...");
		Writer writer = new CSVWriter("stocks.csv");
		try {
			writer.write(map);
		} catch (FileNotFoundException e) {
			System.out.println("Problem writing the file !!" + e.getMessage());
			return;
		}

		System.out.println("Your CSV is ready !! ");
		long end = System.currentTimeMillis();
		System.out.println("Total Time (in sec) = " + ((end - begin) / 1000));
	}
}
