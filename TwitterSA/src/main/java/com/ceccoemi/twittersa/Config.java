package com.ceccoemi.twittersa;

public class Config {

  private static Config instance = null;
  private boolean verbose = false;

  private Config() {
    // singleton
  }

  public static Config getInstance() {
    if (instance == null)
      instance = new Config();
    return instance;
  }

  public void setVerbose(boolean v) {
    verbose = v;
  }

  public boolean isVerbose() {
    return verbose;
  }

}
