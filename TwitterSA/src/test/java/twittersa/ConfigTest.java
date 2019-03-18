package twittersa;


import org.junit.Test;
import static org.junit.Assert.assertSame;

import twittersa.Config;


public class ConfigTest {

	@Test
	public void testSingleton() {
		Config config1 = Config.getInstance();
		Config config2 = Config.getInstance();
		assertSame(config1, config2);
	}

}
