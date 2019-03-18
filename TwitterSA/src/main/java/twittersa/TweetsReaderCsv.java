package twittersa;


import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.aliasi.util.CommaSeparatedValues;

import twittersa.TweetsReader;


public class TweetsReaderCsv implements TweetsReader {

	private CommaSeparatedValues csv;
	private String[][] rows;

	public TweetsReaderCsv(String fileName) throws IOException {
		File file = new File(fileName);
		csv = new CommaSeparatedValues(file, "UTF-8");
	}

	public List<String> readTweets() {
		setRowsIfNotCached();
		int size = rows.length - 1;  // CommaSeparatedValues insert an empty row at the bottom
		List<String> tweets = new ArrayList<>(size);
		for (int i = 0; i < size; i++)
			tweets.add(rows[i][1]);
		return tweets;
	}

	private void setRowsIfNotCached() {
		if (rows == null)
			rows = csv.getArray();
	}

	public List<String> readSentiments() {
		setRowsIfNotCached();
		int size = rows.length - 1;  // CommaSeparatedValues insert an empty row at the bottom
		List<String> sentiments = new ArrayList<>(size);
		for (int i = 0; i < size; i++)
			sentiments.add(rows[i][0]);
		return sentiments;
	}

}
