package com.ceccoemi.twittersa;


import java.util.Iterator;
import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ceccoemi.twittersa.Tweet;
import com.ceccoemi.twittersa.Trainer;


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

  @Test(expected = RuntimeException.class)
  public void storeModelWithoutTrainingiThrowsException() throws IOException {
    Trainer trainer = new Trainer();
    trainer.storeModel(tempPath);
  }

  private Iterator<Tweet> createMockedIterator() {
    Iterator<Tweet> tweetsIter = mock(Iterator.class);
    when(tweetsIter.hasNext()).thenReturn(true, true, true, true, false);
    when(tweetsIter.next()).thenReturn(
        new Tweet("0", "sad sad sad tweet!"),
        new Tweet("1", "good good good tweet!"),
        new Tweet("1", "good good good tweet!"),
        new Tweet("0", "sad sad sad tweet!"));
    return tweetsIter;
  }

  @Test
  public void testTraining() throws IOException {
    Iterator<Tweet> tweetsIter = createMockedIterator();

    Trainer trainer = new Trainer();
    trainer.train(tweetsIter);

    trainer.storeModel(tempPath);
    assertTrue(new File(tempPath).exists());
  }

  @Test(expected = IOException.class)
  public void throwExceptionIfCantStoreModel() throws IOException {
    // create a directory with the same name to make the file creation fail
    new File(tempPath).mkdir();

    Iterator<Tweet> tweetsIter = createMockedIterator();

    Trainer trainer = new Trainer();
    trainer.train(tweetsIter);

    trainer.storeModel(tempPath);
  }

}
