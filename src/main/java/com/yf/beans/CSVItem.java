package com.yf.beans;

/**
 * Created by ashishagarwal on 6/10/17.
 */
public class CSVItem {
	private String stockSymbol;
	private double currentPrice;
	private double yearTargetPrice;
	private double yearHigh;
	private double yearLow;

	public CSVItem(String stockSymbol, double currentPrice, double yearTargetPrice, double yearHigh, double yearLow) {
		this.stockSymbol = stockSymbol;
		this.currentPrice = currentPrice;
		this.yearTargetPrice = yearTargetPrice;
		this.yearHigh = yearHigh;
		this.yearLow = yearLow;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public double getYearTargetPrice() {
		return yearTargetPrice;
	}

	public void setYearTargetPrice(double yearTargetPrice) {
		this.yearTargetPrice = yearTargetPrice;
	}

	public double getYearHigh() {
		return yearHigh;
	}

	public void setYearHigh(double yearHigh) {
		this.yearHigh = yearHigh;
	}

	public double getYearLow() {
		return yearLow;
	}

	public void setYearLow(double yearLow) {
		this.yearLow = yearLow;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		CSVItem csvItem = (CSVItem) o;

		if (Double.compare(csvItem.currentPrice, currentPrice) != 0) {
			return false;
		}
		if (Double.compare(csvItem.yearTargetPrice, yearTargetPrice) != 0) {
			return false;
		}
		if (Double.compare(csvItem.yearHigh, yearHigh) != 0) {
			return false;
		}
		if (Double.compare(csvItem.yearLow, yearLow) != 0) {
			return false;
		}
		return stockSymbol != null ? stockSymbol.equals(csvItem.stockSymbol) : csvItem.stockSymbol == null;

	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = stockSymbol != null ? stockSymbol.hashCode() : 0;
		temp = Double.doubleToLongBits(currentPrice);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(yearTargetPrice);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(yearHigh);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(yearLow);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
}
