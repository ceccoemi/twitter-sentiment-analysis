package com.ceccoemi.twittersa;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClassificationMapperTest {

  @Mock
  private Classifier mockedClassifier;

  @Mock
  private Mapper.Context mockedContext;

  @Test
  public void testMap() throws IOException, InterruptedException {
    ClassificationMapper mapper = new ClassificationMapper(mockedClassifier);
    when(mockedClassifier.classify(anyString())).thenReturn("0", "1", "0", "0", "1");
    int mapExecutions = 5;
    for (int i = 0; i < mapExecutions; i++) {
      mapper.map(new Object(), new Text("hello"), mockedContext);
    }

    verify(mockedContext, times(mapExecutions)).write(anyObject(), anyObject());
  }

}
