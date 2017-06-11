package com.yf.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashishagarwal on 6/10/17.
 */
public class FileReaderImpl implements IFileReader {
	
	public List<String> read() throws IOException {
		List<String> list = new ArrayList<String>();
		BufferedReader br=null;
		try {
			br = new BufferedReader(
				new InputStreamReader(
					FileReaderImpl.class.getClassLoader().getResourceAsStream("Stocks.txt")));
			
			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) 
				list.add(sCurrentLine);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		return list;
	}
}
