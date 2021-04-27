package com.demo.tradestore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.demo.tradestore.TradeStoreApplication;
import com.demo.tradestore.resource.TradeResource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = TradeStoreApplication.class)
class TradestoreApplicationTests {

	@Autowired
	private TradeResource tradeResource;

	@Test
	public void contextLoads() throws Exception {
		assertThat(tradeResource).isNotNull();
	}

}
