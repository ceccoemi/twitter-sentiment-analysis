package com.ceccoemi.twittersa;

import java.util.Iterator;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import com.aliasi.util.CommaSeparatedValues;

public class TweetsReaderCsv implements TweetsReader {

  private Config config = Config.getInstance();
  private String[][] rows;
  private int size;

  public TweetsReaderCsv(String fileName) throws IOException {
    File file = new File(fileName);
    if (config.isVerbose())
      System.out.print("Reading \"" + fileName + "\" ... ");
    CommaSeparatedValues csv = new CommaSeparatedValues(file, "UTF-8");
    rows = csv.getArray();
    size = rows.length - 1;  // CommaSeparatedValues insert an empty row at the bottom
    if (config.isVerbose()) {
      System.out.println("\rReading \"" + fileName + "\" ... Done!");
      System.out.println("Dataset size: " + (size - 1)); // -1 for the header
    }
  }

  public Iterator<Tweet> iter() {
    ArrayList<Tweet> tweets = new ArrayList<>(size);
    for (int i = 1; i < size; i++)  // start from 1 because of the header
      tweets.add(new Tweet(rows[i][0], rows[i][1]));
    return tweets.iterator();
  }

}
