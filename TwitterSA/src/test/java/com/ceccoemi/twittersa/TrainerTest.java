package com.ceccoemi.twittersa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.assertTrue;

public class TrainerTest {

  private String tempPath;

  @Before
  public void setUp() {
    tempPath = System.getProperty("java.io.tmpdir") + File.separator + "sentiment.model";
  }

  @After
  public void tearDown() {
    new File(tempPath).delete();
  }

  @Test
  public void testTraining() throws IOException {
    Iterator<Tweet> tweets = Arrays.asList(
        new Tweet("0", "sad sad sad tweet!"),
        new Tweet("1", "good good good tweet!"),
        new Tweet("1", "good good good tweet!"),
        new Tweet("0", "sad sad sad tweet!")).iterator();

    Trainer trainer = new Trainer();
    trainer.train(tweets);
    trainer.storeModel(tempPath);

    assertTrue(new File(tempPath).exists());
  }

  @Test(expected = IOException.class)
  public void throwExceptionIfCantStoreModel() throws IOException {
    // create a directory with the same name to make the file creation fail
    new File(tempPath).mkdir();

    Iterator<Tweet> tweets = Arrays.asList(new Tweet("0", "dummy tweet")).iterator();

    Trainer trainer = new Trainer();
    trainer.train(tweets);

    trainer.storeModel(tempPath);  // IOException should be thrown
  }

}
