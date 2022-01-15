package com.temp.meikik.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.IDialect;

import com.temp.meikik.dialect.TextDialect;

@Configuration
public class DialectAutoConfiguration {
	@Value("${meikik.default-allowed-tags:b,i,url}")
	private String defaultTags;

	@Bean
	public IDialect textDialect() {
		return new TextDialect(defaultTags);
	}

}
