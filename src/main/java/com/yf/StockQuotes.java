package com.yf;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yf.beans.CSVItem;
import com.yf.reader.JSONReader;
import com.yf.reader.JSONReaderImpl;

/**
 * Created by ashishagarwal on 6/10/17.
 */
public class StockQuotes {
	private final int NO_OF_QUOTES_TO_READ_FROM_YAHOO=5;
	
	public Map<String,CSVItem> getStockQuotes(List<String> stockSymbols) throws IOException, JSONException {
		Map<String, CSVItem> map = new HashMap<String, CSVItem>();
		JSONReader reader = new JSONReaderImpl();

		StringJoiner joiner = null;
		int rounds = stockSymbols.size() / NO_OF_QUOTES_TO_READ_FROM_YAHOO;

		int i=0;
		for(; i < stockSymbols.size(); i = i + NO_OF_QUOTES_TO_READ_FROM_YAHOO) {
			if(i == rounds * NO_OF_QUOTES_TO_READ_FROM_YAHOO) 
				break;
			
			joiner = new StringJoiner("\",\"");
			
			for(int j=i ; j < i + NO_OF_QUOTES_TO_READ_FROM_YAHOO ; j++) 
					joiner.add(stockSymbols.get(j));
			
			String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22"+ joiner.toString() + "%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";

			JSONObject json = reader.readJsonFromUrl(url);
			JSONArray quotes = json.getJSONObject("query").getJSONObject("results").getJSONArray("quote");
			populateMapFromJSONArray(map, quotes);
		}

		// populate remaining
		for(; i < stockSymbols.size();i++) {
			String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22"+ stockSymbols.get(i) + "%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";

			JSONObject json = reader.readJsonFromUrl(url);
			JSONObject quote = json.getJSONObject("query").getJSONObject("results").getJSONObject("quote");
			populateMapFromJSONObject(map, quote);
		}
		
		return map;
	}
	
	private void populateMapFromJSONArray(final Map<String, CSVItem> m, JSONArray quotes) throws JSONException {
		for(int i=0;i<quotes.length();i++) {
			JSONObject quote = (JSONObject) quotes.get(i);
			populateMapFromJSONObject(m,quote);
		}
	}

	private void populateMapFromJSONObject(final Map<String, CSVItem> m, JSONObject quote) throws JSONException {
		CSVItem item = getCsvItem(quote);
		m.put(item.getStockSymbol(), item);
	}

	private CSVItem getCsvItem(JSONObject quote) throws JSONException {
		String symbol = (String)quote.get("Symbol");
		return new CSVItem(symbol,
												getDouble(quote.get("LastTradePriceOnly")),
												getDouble(quote.get("OneyrTargetPrice")),
												getDouble(quote.get("YearHigh")),
												getDouble(quote.get("YearLow")));
	}

	private static Double getDouble(final Object s) {
		return (JSONObject.NULL.equals(s)) ? -1 : Double.parseDouble((String)s);
	}
}
