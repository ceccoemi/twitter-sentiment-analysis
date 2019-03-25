package org.ceccoemi.twittersa;


public final class Config {

	private static Config config = new Config();
	private boolean verbose;

	private Config() {
		// singleton
	}

	public void setVerbose(boolean flag) {
		verbose = flag;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public static Config getInstance() {
		return config;
	}

}
