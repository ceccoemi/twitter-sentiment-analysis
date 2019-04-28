package com.ceccoemi.twittersa;

import com.aliasi.util.CommaSeparatedValues;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TweetsReaderCsv implements TweetsReader {

  private String[][] rows;
  private int size;

  public TweetsReaderCsv(String fileName) throws IOException {
    File file = new File(fileName);
    CommaSeparatedValues csv = new CommaSeparatedValues(file, "UTF-8");
    rows = csv.getArray();
    // CommaSeparatedValues insert an empty row at the bottom
    size = rows.length > 0 ? rows.length - 1 : 0;
  }

  public Iterator<Tweet> readTweets() {
    List<Tweet> tweets = new ArrayList<>(size);
    for (int i = 0; i < size; i++)
      tweets.add(new Tweet(rows[i][0], rows[i][1]));
    return tweets.iterator();
  }

}
