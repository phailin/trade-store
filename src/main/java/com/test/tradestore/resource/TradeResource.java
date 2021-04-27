package com.test.tradestore.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.tradestore.constants.TradeStoreConstant;
import com.test.tradestore.dto.TradeInputDto;
import com.test.tradestore.service.TradeService;

@RestController
public class TradeResource {

	@Autowired
	private TradeService tradeService;

	/*
	 * @GetMapping("/hello") public String hello() { return "Hello World!"; }
	 */

	@PostMapping("/trades/upload")
	public ResponseEntity<String> uploadTradeRecord(@Valid @RequestBody TradeInputDto tradeInputDto) {
		tradeService.addOrUpdateTrade(tradeInputDto);
		return new ResponseEntity<String>(TradeStoreConstant.TRADE_UPLOAD_SUCCESSFUL, HttpStatus.OK);

	}

}