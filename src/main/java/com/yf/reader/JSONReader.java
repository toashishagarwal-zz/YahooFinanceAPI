package com.yf.reader;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ashishagarwal on 6/10/17.
 */
public interface JSONReader {
	JSONObject readJsonFromUrl(String url) throws IOException, JSONException;
}
