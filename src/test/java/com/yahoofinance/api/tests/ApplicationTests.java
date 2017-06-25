package com.yahoofinance.api.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.quotes.stock.StockStats;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationTests.class);
	private final static String PADINI = "7052.KL";

	@Test
	public void getStockPrice() throws IOException {
		Stock stock = YahooFinance.get(PADINI);
		assertNotNull(stock);
		BigDecimal stockPrice = stock.getQuote().getPrice();
		logger.info("price {}", stockPrice);
		assertTrue(stockPrice.doubleValue() > 0.0);
	}
	
	@Test
	public void getStockName() throws IOException {
		Stock stock = YahooFinance.get(PADINI);
		assertNotNull(stock);
		assertEquals("PADINI HOLDINGS BHD", stock.getName());
	}
	
	@Test
	public void getStockHistoricalPrice() throws IOException {
		Stock stock = YahooFinance.get(PADINI);
		assertNotNull(stock);
		Calendar from = Calendar.getInstance();
		from.set(2017, Calendar.JUNE, 23);
		Calendar to = from;
		List<HistoricalQuote> quotes = stock.getHistory(from, to);
		assertEquals(1, quotes.size());
		assertEquals(3.46, quotes.get(0).getClose().doubleValue(), 0.0);
	}
	
	@Test
	public void getSharesOutstanding() throws IOException {
		Stock stock = YahooFinance.get(PADINI);
		assertNotNull(stock);
		StockStats stats = stock.getStats();
		
		assertTrue(stats.getSharesOutstanding() > 0);
	}	
}
