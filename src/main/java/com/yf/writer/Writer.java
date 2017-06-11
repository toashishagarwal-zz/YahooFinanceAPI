package com.yf.writer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

import com.yf.beans.CSVItem;

/**
 * Created by ashishagarwal on 6/10/17.
 */

public interface Writer {
	void write(PrintWriter w, CSVItem content) throws FileNotFoundException;
	void write(final Map<String, CSVItem> contents) throws FileNotFoundException;
}
