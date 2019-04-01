package com.ceccoemi.twittersa;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EvaluatorTest {

  private Classifier mockedClassifier;
  private Iterator<Tweet> mockedTweetsIterator;

  @Before
  public void setUp() {
    mockedClassifier = mock(Classifier.class);
    when(mockedClassifier.classify(anyString())).thenReturn("1", "0", "1", "0");

    mockedTweetsIterator = mock(Iterator.class);
    when(mockedTweetsIterator.hasNext()).thenReturn(true, true, true, true, false);
    when(mockedTweetsIterator.next()).thenReturn(
        new Tweet("0", "sad sad sad tweet!"),
        new Tweet("1", "good good good tweet!"),
        new Tweet("1", "good good good tweet!"),
        new Tweet("0", "sad sad sad tweet!"));
  }

  @Test
  public void testEvaluate() {
    Evaluator evaluator = new Evaluator(mockedClassifier);
    ConfusionMatrix confusionMatrix = evaluator.evaluate(mockedTweetsIterator);
    assertEquals(0.5, confusionMatrix.accuracy(), 0.00001);
  }
}
