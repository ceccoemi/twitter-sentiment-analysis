package com.ceccoemi.twittersa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EvaluatorTest {

  @Test
  public void testEvaluate() {
    List<Tweet> tweets = Arrays.asList(
        new Tweet("0", "sad sad sad tweet!"),
        new Tweet("1", "good good good tweet!"),
        new Tweet("1", "good good good tweet!"),
        new Tweet("0", "sad sad sad tweet!"));

    Classifier mockedClassifier = mock(Classifier.class);
    when(mockedClassifier.classify(anyString())).thenReturn("1", "0", "1", "0");

    Evaluator evaluator = new Evaluator(mockedClassifier);
    ConfusionMatrix confusionMatrix = evaluator.evaluate(tweets);
    assertEquals(0.5, confusionMatrix.accuracy(), 0.00001);
  }
}
