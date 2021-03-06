/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.stream.source;

import static org.junit.Assert.assertTrue;

import java.util.function.Function;

import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.transformer.MethodInvokingTransformer;
import org.springframework.integration.transformer.Transformer;

/**
 * 
 * @author Oleg Zhurakousky
 *
 */
public class StreamSourceStarterApplicationTests {

	@Test
	public void injectedTransformer() {
		ConfigurableApplicationContext context = SpringApplication.run(new Class[] {StreamSourceStarterApplication.class, TestConfig.class}, new String[] {});
		Transformer transformer = context.getBean(Transformer.class);
		assertTrue(transformer instanceof MethodInvokingTransformer);
	}

	@Configuration
	public static class TestConfig {	
		@Bean
		Function<String, Integer> injectedFunction() {
			return (p) -> Integer.parseInt(p);
		}
	}
}
