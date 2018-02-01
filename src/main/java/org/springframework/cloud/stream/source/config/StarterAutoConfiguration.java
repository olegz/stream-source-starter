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

@Configuration
@EnableBinding(Source.class)
public class StarterAutoConfiguration {

	@Bean
	@ServiceActivator(inputChannel="sourceOutput", outputChannel=Source.OUTPUT)
	public <I,O> Transformer handler(@Nullable Function<I, O> sourceProcessor) {
		return sourceProcessor == null 
				? (m) -> m 
						: new MethodInvokingTransformer(sourceProcessor, ReflectionUtils.findMethod(Function.class, "apply", Object.class));
	}
}
