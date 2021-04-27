package com.tradestore.tradestore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

import com.test.tradestore.TradeStoreApplication;
import com.test.tradestore.resource.TradeResource;

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
