package com.yahoofinance.api.tests;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import yahoofinance.YahooFinance;

@Configuration
public class ApplicationConfig {
	@Bean
	YahooFinance yahooFinance() {
		return new YahooFinance();
	}

}
