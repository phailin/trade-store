package com.demo.tradestore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.demo.tradestore.repository.TradeRepository;
import com.demo.tradestore.scheduler.ScheduleTask;
import com.demo.tradestore.scheduler.ScheduleTaskImpl;
import com.demo.tradestore.service.TradeService;
import com.demo.tradestore.service.TradeServiceImpl;

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
