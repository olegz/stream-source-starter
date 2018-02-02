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
package org.springframework.cloud.stream.source.config;

import java.util.function.Function;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.transformer.MethodInvokingTransformer;
import org.springframework.integration.transformer.Transformer;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

/**
 * 
 * @author Oleg Zhurakousky
 *
 */
@Configuration
@EnableBinding(Source.class)
public class SourceAutoConfiguration {
	
	public static final String SOURCE_OUTPUT = "sourceOutput";

	@Bean
	@ServiceActivator(inputChannel=SOURCE_OUTPUT, outputChannel=Source.OUTPUT)
	public <I,O> Transformer handler(@Nullable Function<I, O> sourceProcessor) {
		return sourceProcessor == null 
				? (m) -> m 
				: new MethodInvokingTransformer(sourceProcessor, ReflectionUtils.findMethod(Function.class, "apply", Object.class));
	}
}
