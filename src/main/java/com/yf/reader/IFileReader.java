package com.yf.reader;

import java.io.IOException;
import java.util.List;

/**
 * Created by ashishagarwal on 6/10/17.
 */
public interface IFileReader {
	List<String> read() throws IOException;
}
