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

		// version validation
		Optional<Trade> tradeFromStore = Optional.ofNullable(tradeRepository.findByTradeId(tradeInputDto.getTradeId()));

		if (tradeFromStore.isPresent() && tradeFromStore.get().getTradeId().equals(tradeInputDto.getTradeId())) {

			// lower version throws exception
			if (tradeFromStore.get().getVersion() > tradeInputDto.getVersion()) {
				throw new TradeStoreException(TradeStoreConstant.HIGHER_VERISON_PRESENT);
			}

			// same version update record
			if (tradeFromStore.get().getVersion() == tradeInputDto.getVersion()) {

				// DateCreated is not updated. Should have one extra column in table as
				// DateModified.
				tradeRepository.setTradeByTradeIdAndVersion(tradeInputDto.getCounterPartyId(),
						tradeInputDto.getBookId(), tradeInputDto.getMaturityDate(), tradeInputDto.getTradeId(),
						tradeInputDto.getVersion());
				return;
			}

		}

		// Map "tradeInputDto" to "trade" entity and add createdDate and expired flag
		// before adding to DB
		Trade trade = getTradeFromTradeDto(tradeInputDto);
		tradeRepository.save(trade);
	}

	private Trade getTradeFromTradeDto(TradeInputDto tradeInputDto) {
		Trade trade = new Trade();
		trade.setTradeId(tradeInputDto.getTradeId());
		trade.setVersion(tradeInputDto.getVersion());
		trade.setCounterPartyId(tradeInputDto.getCounterPartyId());
		trade.setBookId(tradeInputDto.getBookId());
		trade.setMaturityDate(tradeInputDto.getMaturityDate());
		trade.setCreatedDate(LocalDate.now());

		/*
		 * TODO: Expired value is being stored as "TRUE" or "FALSE" as of now. Custom
		 * mapping can be done as improvement to store value as "Y" or N".
		 * 
		 * NOTE: By default setting value of "Expired" as "false" as we are not
		 * accepting trade with less expired date than current date. The scheduler
		 * updates the expired value for existing trades based on maturity date every
		 * midnight.
		 * 
		 */
		trade.setExpired(false);

		return trade;
	}
}
