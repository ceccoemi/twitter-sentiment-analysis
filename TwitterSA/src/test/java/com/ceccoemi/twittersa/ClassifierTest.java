package com.ceccoemi.twittersa;

import org.junit.*;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ClassifierTest {

  private String tempPath;

  @Before
  public void setUp() throws IOException {
    tempPath = System.getProperty("java.io.tmpdir") + File.separator + "sentiment.model";

    ClassLoader classLoader = getClass().getClassLoader();
    String csv = classLoader.getResource("tweets.csv").getFile();

    Trainer trainer = new Trainer();
    trainer.train(new TweetsReaderCsv(csv).readTweets());
    trainer.storeModel(tempPath);
  }

  @After
  public void tearDown() {
    new File(tempPath).delete();
  }

  @Test
  public void loadModelAndClassify() throws IOException, ClassNotFoundException {
    File modelFile = new File(tempPath);
    Classifier classifier = new Classifier(modelFile);
    assertEquals("0", classifier.classify("sad sad sad tweet"));
    assertEquals("1", classifier.classify("good good good tweet"));
  }

}
