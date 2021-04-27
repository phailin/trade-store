package com.demo.tradestore.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.tradestore.constants.TradeStoreConstant;
import com.demo.tradestore.dto.TradeInputDto;
import com.demo.tradestore.entity.Trade;
import com.demo.tradestore.exception.TradeStoreException;
import com.demo.tradestore.repository.TradeRepository;

@Service
public class TradeServiceImpl implements TradeService {
	private TradeRepository tradeRepository;

	@Autowired
	public TradeServiceImpl(TradeRepository tradeRepository) {
		this.tradeRepository = tradeRepository;
	}

	@Override
	@Transactional
	public void addOrUpdateTrade(TradeInputDto tradeInputDto) {

		// version validation- lower version throws exception
		Optional<Trade> tradeFromStore = Optional.ofNullable(tradeRepository.findByTradeId(tradeInputDto.getTradeId()));

		if (tradeFromStore.isPresent() && tradeFromStore.get().getTradeId().equals(tradeInputDto.getTradeId())) {

			if (tradeFromStore.get().getVersion() > tradeInputDto.getVersion()) {
				throw new TradeStoreException(TradeStoreConstant.HIGHER_VERISON_PRESENT);
			}

			if (tradeFromStore.get().getVersion() == tradeInputDto.getVersion()) {
				// Trade trade = getTradeFromTradeDto(tradeInputDto, true);
				tradeRepository.setTradeByTradeIdAndVersion(tradeInputDto.getCounterPartyId(),
						tradeInputDto.getBookId(), tradeInputDto.getMaturityDate(), tradeInputDto.getTradeId(),
						tradeInputDto.getVersion());
				return;
			}

		}

		// TODO : Remove the boolean
		// map tradeInputDto -> trade and add createdDate and expired flag
		// add the trade to the DB
		Trade trade = getTradeFromTradeDto(tradeInputDto, false);
		tradeRepository.save(trade);
	}

	private Trade getTradeFromTradeDto(TradeInputDto tradeInputDto, boolean isUpdate) {
		Trade trade = new Trade();
		trade.setTradeId(tradeInputDto.getTradeId());
		trade.setVersion(tradeInputDto.getVersion());
		trade.setCounterPartyId(tradeInputDto.getCounterPartyId());
		trade.setBookId(tradeInputDto.getBookId());
		trade.setMaturityDate(tradeInputDto.getMaturityDate());

		if (!isUpdate) {
			// Adding the created date for new Trade.
			// For update not changing this value. Could have one extra DateUpdated field in
			// DB
			trade.setCreatedDate(LocalDate.now());
		}

		// 1- For N,Y we can use ENUM , later.
		// 2- Expired will be false and we are not accepting trade with less expiry date
		// than today.
		trade.setExpired(false);

		return trade;
	}
}
