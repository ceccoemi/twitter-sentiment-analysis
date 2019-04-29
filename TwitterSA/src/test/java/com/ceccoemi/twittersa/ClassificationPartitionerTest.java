package com.ceccoemi.twittersa;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ClassificationPartitionerTest {


  @Test
  public void testPartiton() {
    ClassificationPartitioner partitioner = new ClassificationPartitioner();
    assertEquals(0, partitioner.getPartition(
        new Text("negatives"), new IntWritable(0), 1));
    assertEquals(1, partitioner.getPartition(
        new Text("positives"), new IntWritable(0), 1));
  }
}
