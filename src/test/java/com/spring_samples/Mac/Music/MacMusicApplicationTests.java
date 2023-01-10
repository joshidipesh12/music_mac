package com.spring_samples.Mac.Music;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MacMusicApplicationTests {

	@Test
	void contextLoads() {
		int a = 3 * 5;
		assertEquals(15, a);
	}

}
