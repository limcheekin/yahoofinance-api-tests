package com.yahoofinance.api.tests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	private final static String PADINI = "7052.KL";

	@Test
	public void getStockPrice() throws IOException {
		Stock stock = YahooFinance.get(PADINI);
		assertNotNull(stock);
		BigDecimal stockPrice = stock.getQuote().getPrice();
		assertTrue(stockPrice.doubleValue() > 0.0D);
	}	
}
