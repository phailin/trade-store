package com.demo.tradestore.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.tradestore.entity.Trade;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

	public Trade findByTradeId(String tradeId);

	@Modifying(clearAutomatically = true)
	@Query("update Trade t set t.counterPartyId = :counterPartyId, t.bookId = :bookId, t.maturityDate = :maturityDate where t.tradeId = :tradeId and t.version = :version")
	public void setTradeByTradeIdAndVersion(@Param("counterPartyId") String counterPartyId,
			@Param("bookId") String bookId, @Param("maturityDate") LocalDate maturityDate,
			@Param("tradeId") String tradeId, @Param("version") int version);

	@Modifying(clearAutomatically = true)
	@Query("update Trade t set t.expired = TRUE where t.maturityDate < CURRENT_DATE")
	public int setExpired();
}
