package com.test.tradestore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.test.tradestore.repository.TradeRepository;
import com.test.tradestore.scheduler.ScheduleTask;
import com.test.tradestore.scheduler.ScheduleTaskImpl;
import com.test.tradestore.service.TradeService;
import com.test.tradestore.service.TradeServiceImpl;

@Configuration
@EnableScheduling
public class TradeStoreAppConfiguration {

	@Bean
	public TradeService tradeService(TradeRepository tradeRepository) {
		return new TradeServiceImpl(tradeRepository);
	}

	@Bean
	public ScheduleTask scheduleTask(TradeRepository tradeRepository) {
		return new ScheduleTaskImpl(tradeRepository);

	}

}
