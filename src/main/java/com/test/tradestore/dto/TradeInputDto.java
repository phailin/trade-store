package com.test.tradestore.dto;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.tradestore.constants.TradeStoreConstant;

public class TradeInputDto {

	// 1- Validating the DTO using Java bean validation API(Hibernate Validator)

	@NotBlank(message = TradeStoreConstant.TRADE_ID_VALIDATION_MSG)
	private String tradeId;

	@Positive(message = TradeStoreConstant.VERSION_VALIDATION_MSG)
	private int version;

	@NotBlank(message = TradeStoreConstant.COUNTER_PART_ID_VALIDATION_MSG)
	private String counterPartyId;

	@NotBlank(message = TradeStoreConstant.BOOK_ID_VALIDATION_MSG)
	private String bookId;

	@FutureOrPresent(message = TradeStoreConstant.MATURITY_DATE_VALIDATION_MSG)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate maturityDate;

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCounterPartyId() {
		return counterPartyId;
	}

	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public LocalDate getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

}
