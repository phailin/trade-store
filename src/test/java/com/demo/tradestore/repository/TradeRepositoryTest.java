package com.demo.tradestore.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.demo.tradestore.entity.Trade;
import com.demo.tradestore.repository.TradeRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class TradeRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TradeRepository tradeRepository;

	@Test
	public void testFindByTradeId() {

		Trade trade = genearteTradeEntity();
		entityManager.persist(trade);
		entityManager.flush();

		Trade tradeRes = tradeRepository.findByTradeId("T1");

		assertThat(tradeRes.getCounterPartyId()).isEqualTo("CP-1");
		assertThat(tradeRes.getVersion()).isEqualTo(1);
		assertThat(tradeRes.getBookId()).isEqualTo("B1");

	}

	@Test
	public void testSetTradeByTradeIdAndVersion() {
		Trade trade = genearteTradeEntity();
		entityManager.persist(trade);
		entityManager.flush();

		Trade tradeRes = tradeRepository.findByTradeId("T1");

		assertThat(tradeRes.getCounterPartyId()).isEqualTo("CP-1");
		assertThat(tradeRes.getVersion()).isEqualTo(1);
		assertThat(tradeRes.getBookId()).isEqualTo("B1");

		Trade trade1 = genearteTradeEntity();
		trade1.setCounterPartyId("CP-2");
		trade1.setBookId("B2");

		tradeRepository.setTradeByTradeIdAndVersion(trade1.getCounterPartyId(), trade1.getBookId(),
				trade1.getMaturityDate(), trade.getTradeId(), trade.getVersion());

		Trade tradeRes1 = tradeRepository.findByTradeId("T1");

		assertThat(tradeRes1.getCounterPartyId()).isEqualTo("CP-2");
		assertThat(tradeRes1.getVersion()).isEqualTo(1);
		assertThat(tradeRes1.getBookId()).isEqualTo("B2");

	}

	@Test
	public void testSetExpired() {
		Trade trade = genearteTradeEntity();
		trade.setMaturityDate(LocalDate.now().minusDays(5));
		entityManager.persist(trade);
		entityManager.flush();

		Trade tradeRes = tradeRepository.findByTradeId("T1");
		assertThat(tradeRes.isExpired()).isEqualTo(false);

		tradeRepository.setExpired();
		Trade tradeRes1 = tradeRepository.findByTradeId("T1");
		assertThat(tradeRes1.isExpired()).isEqualTo(true);

	}

	private Trade genearteTradeEntity() {
		Trade trade = new Trade();
		trade.setTradeId("T1");
		trade.setVersion(1);
		trade.setCounterPartyId("CP-1");
		trade.setBookId("B1");
		trade.setMaturityDate(LocalDate.now().plusDays(5));
		trade.setCreatedDate(LocalDate.now());
		trade.setExpired(false);

		return trade;
	}

}
