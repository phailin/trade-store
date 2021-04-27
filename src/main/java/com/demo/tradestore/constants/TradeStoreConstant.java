package com.demo.tradestore.constants;

public class TradeStoreConstant {

	public static final String TRADE_UPLOAD_SUCCESSFUL = "Trade added/updated to store";

	// validation messages
	public static final String TRADE_ID_VALIDATION_MSG = "TradeId cannot be null or empty";
	public static final String VERSION_VALIDATION_MSG = "Version should be a postive number";
	public static final String COUNTER_PART_ID_VALIDATION_MSG = "CounterPartyId cannot be null or empty";
	public static final String BOOK_ID_VALIDATION_MSG = "BookId cannot be null or empty";
	public static final String MATURITY_DATE_VALIDATION_MSG = "Maturity date must be today or a future date";

	// custom exception messages
	public static final String HIGHER_VERISON_PRESENT = "Trade with higher version already present in trade store";

}
