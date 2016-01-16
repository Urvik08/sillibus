package com.sillibus.web;

/**
 Created by MurtazaBambot on 1/16/2016.
 */
public class SyllabusDate {
	public String date;
	public int    endIndex;
	public int    startIndex;
	public String type;

	public SyllabusDate (String date, int startIndex, int endIndex) {
		this.date = date;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.type = "Unknown";
	}

	public boolean equals (Object o) {
		if (o == null) {
			return false;
		} else if (null == (SyllabusDate) o) {
			return false;
		}
		SyllabusDate that = (SyllabusDate) o;
		if ((this.date.equals(that.date)) || (this.date == null && that.date == null)) {
			return true;
		}
		return false;
	}

	public String toString () {
		return ("Date: " + this.date + "; Type: " + this.type + ";\n");
	}

	public String getDate () {
		return this.date;
	}

	public int getNewEnd () {
		return this.endIndex + 75;
	}

	public int getNewStart () {
		return this.startIndex - 75;
	}

	public void setType (String newType) {
		this.type = newType;
	}
}
