package com.demo.tradestore.exception;

public class TradeStoreException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1100037170685723507L;

	public TradeStoreException(String message) {
		super(message);

	}

	public TradeStoreException(Throwable throwable) {
		super(throwable);

	}

	public TradeStoreException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
