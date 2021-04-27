package com.demo.tradestore.resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.demo.tradestore.constants.TradeStoreConstant;
import com.demo.tradestore.resource.TradeResource;

@WebMvcTest(TradeResource.class)
public class TradeResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TradeResource tradeResource;

	@Test
	public void testUploadTradeRecord_validateDTO() throws Exception {

		final String contentJson = "{ \"tradeId\": \"\", \"version\": \"\",\"counterPartyId\": \"\", \"bookId\": \"\", \"maturityDate\": \"20/03/2021\" }";

		MvcResult mvcRes = mockMvc.perform(post("/trades/upload").contentType(MediaType.APPLICATION_JSON)
				.content(contentJson).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
				.andReturn();

		String content = mvcRes.getResponse().getContentAsString();
		assertThat(content).contains(TradeStoreConstant.TRADE_ID_VALIDATION_MSG,
				TradeStoreConstant.VERSION_VALIDATION_MSG, TradeStoreConstant.COUNTER_PART_ID_VALIDATION_MSG,
				TradeStoreConstant.BOOK_ID_VALIDATION_MSG, TradeStoreConstant.MATURITY_DATE_VALIDATION_MSG);
	}

	@Test
	public void testUploadTradeRecord_maturityDateLessThanToday() throws Exception {

		final String contentJson = "{ \"tradeId\": \"T1\", \"version\": 3,\"counterPartyId\": \"CP-2\", \"bookId\": \"B1\", \"maturityDate\": \"20/01/2022\" }";

		mockMvc.perform(post("/trades/upload").contentType(MediaType.APPLICATION_JSON).content(contentJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}
