package com.ceccoemi.twittersa;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.After;

public class ConfigTest {

  @After
  public void tearDown() {
    Config.getInstance().setVerbose(false);
  }

  @Test
  public void testConfigIsSingleton() {
    Config config1 = Config.getInstance();
    Config config2 = Config.getInstance();
    assertSame(config1, config2);
  }

  @Test
  public void testVerbose() {
    Config config1 = Config.getInstance();
    Config config2 = Config.getInstance();
    assertFalse(config2.isVerbose());
    config1.setVerbose(true);
    assertTrue(config2.isVerbose());
  }

}
