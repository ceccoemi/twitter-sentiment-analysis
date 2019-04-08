package com.ceccoemi.twittersa;

import org.apache.hadoop.hdfs.util.ByteArrayManager;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class ConfusionMatrixTest {

  @Test
  public void testAccuracy() {
    ConfusionMatrix matrix = new ConfusionMatrix(2, 1, 2, 1);
    assertEquals(0.5, matrix.accuracy(), 0.00001);
  }

  @Test
  public void testToString() {
    ConfusionMatrix matrix = new ConfusionMatrix(2, 1, 2, 1);
    String repr = matrix.toString();
    assertTrue(repr.contains("2") && repr.contains("1"));
  }

  @Test
  public void testJoin() {
    ConfusionMatrix matrix1 = new ConfusionMatrix(2, 1, 1, 1);
    ConfusionMatrix matrix2 = new ConfusionMatrix(2, 1, 2, 0);
    ConfusionMatrix matrix = ConfusionMatrix.join(matrix1, matrix2);
    assertEquals(0.5, matrix.accuracy(), 0.0000001);
  }

}
