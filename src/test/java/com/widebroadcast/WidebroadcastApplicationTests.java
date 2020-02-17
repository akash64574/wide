package com.widebroadcast;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WidebroadcastApplicationTests {

	@Test
	public void applicationTest() {
		WidebroadcastApplication.main(new String[] {});
		assertTrue(true);
	}

}
