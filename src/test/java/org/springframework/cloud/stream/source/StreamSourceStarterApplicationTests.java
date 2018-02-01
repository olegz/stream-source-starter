package org.springframework.cloud.stream.source;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.transformer.Transformer;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StreamSourceStarterApplicationTests {

	@Autowired
	Transformer handler;
	
	@Test
	public void contextLoads() {
	}

}
