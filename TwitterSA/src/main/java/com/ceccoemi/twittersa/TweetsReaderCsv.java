package com.ceccoemi.twittersa;


import java.util.Iterator;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import com.aliasi.util.CommaSeparatedValues;
import com.ceccoemi.twittersa.Tweet;
import com.ceccoemi.twittersa.TweetsReader;


public class TweetsReaderCsv implements TweetsReader {

	private String[][] rows;

	public TweetsReaderCsv(String fileName) throws IOException {
		File file = new File(fileName);
		CommaSeparatedValues csv = new CommaSeparatedValues(file, "UTF-8");
		rows = csv.getArray();
	}

	public Iterator<Tweet> iter() {
		int size = rows.length - 1;  // CommaSeparatedValues insert an empty row at the bottom
		ArrayList<Tweet> tweets = new ArrayList<>(size);
		for (int i = 1; i < size; i++)  // start from 1 because of the header
			tweets.add(new Tweet(rows[i][0], rows[i][1]));
		return tweets.iterator();
	}

}
