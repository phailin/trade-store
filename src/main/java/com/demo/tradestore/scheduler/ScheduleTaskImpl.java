package com.demo.tradestore.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.tradestore.repository.TradeRepository;

@Service
public class ScheduleTaskImpl implements ScheduleTask {

	private TradeRepository tradeRepository;

	@Autowired
	public ScheduleTaskImpl(TradeRepository tradeRepository) {
		this.tradeRepository = tradeRepository;
	}

	/*
	 * Run the task every midnight(12 AM) to update the Trade Expired value
	 */
	@Override
	@Scheduled(cron = "0 0 0 * * *")
	@Transactional
	public void checkAndUpdateExpired() {

		int updateCount = tradeRepository.setExpired();
		System.out.println(updateCount + " records updated");

	}

}
