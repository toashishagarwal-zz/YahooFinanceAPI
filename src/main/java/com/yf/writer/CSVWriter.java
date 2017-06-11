package com.yf.writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

import com.yf.beans.CSVItem;

/**
 * Created by ashishagarwal on 6/10/17.
 */
public class CSVWriter implements Writer {
	
	private String fileName;
	
	public CSVWriter(String fileName) {
		this.fileName = fileName;
	}

	public void write(final Map<String, CSVItem> contents) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(fileName));
		
		//write the header
		pw.write("Stock Symbol, Current Price, Year Target Price, Year High, Year Low\n");
		
		for(Map.Entry e : contents.entrySet()) 
				write(pw, (CSVItem) e.getValue());
		
		pw.close();
	}
	
	public void write(final PrintWriter pw, final CSVItem content) throws FileNotFoundException {
		String contentString = getCSVItemAsString(content);
		pw.write(contentString);
	}

	private String getCSVItemAsString(CSVItem content) {
		StringBuilder sb = new StringBuilder();
		sb.append(content.getStockSymbol());
		sb.append(",");
		sb.append(content.getCurrentPrice());
		sb.append(",");
		sb.append(content.getYearTargetPrice());
		sb.append(",");
		sb.append(content.getYearHigh());
		sb.append(",");
		sb.append(content.getYearLow());
		sb.append('\n');
		return sb.toString();
	}
}
