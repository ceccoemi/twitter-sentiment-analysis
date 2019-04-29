package com.ceccoemi.twittersa;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ClassificationReducerTest {

  @Mock
  private Reducer.Context mockedContext;

  @Test
  public void testReduce() throws IOException, InterruptedException {
    ClassificationReducer reducer = new ClassificationReducer();
    reducer.reduce(
        new Text("positives"),
        Arrays.asList(new IntWritable(1), new IntWritable(1), new IntWritable(1)),
        mockedContext);
    verify(mockedContext).write(new Text("positives"), new IntWritable(3));
  }

}
