package com.itconsortium.creditunion.chango.repository;

import com.itconsortium.creditunion.chango.model.MemberTransaction;
import com.itconsortium.creditunion.chango.projections.MemberTransactionSummaryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MemberTransactionRepository extends JpaRepository<MemberTransaction, Long> {
    @Query(value = """
            SELECT
                mt.CREATED AS created,
                mt.TRANSACTION_TYPE AS transactionType,
                g.CURRENCY AS memberCurrency,
                mt.AMOUNT AS amount
            FROM MEMBER_TRANSACTIONS mt
            JOIN MEMBERS m ON m.MEMBER_ID = mt.MEMBER_ID
            JOIN GROUPS g ON mt.GROUP_ID = g.GROUP_ID
            WHERE m.MSISDN = :msisdn
            AND g.GROUP_ID = :groupId
            ORDER BY mt.CREATED DESC
            LIMIT 5;
            """, nativeQuery = true)
    List<MemberTransactionSummaryDto> findLast5Transactions(@Param("groupId") Long groupId, @Param("msisdn") String msisdn);


    @Query(value = """
            SELECT
                mt.CREATED AS created,
                mt.TRANSACTION_TYPE AS transactionType,
                m.CURRENCY AS memberCurrency,
                mt.AMOUNT AS amount
            FROM MEMBER_TRANSACTIONS mt
            JOIN MEMBERS m ON m.MEMBER_ID = mt.MEMBER_ID
            JOIN GROUPS g ON mt.GROUP_ID = g.GROUP_ID
            WHERE mt.CREATED > :date
            AND m.MSISDN = :msisdn
            AND g.GROUP_ID = :groupId
            ORDER BY s.CREATED DESC;
            """, nativeQuery = true)
    List<MemberTransactionSummaryDto> findByTransactionDate(@Param("date") LocalDate date,
                                                            @Param("msisdn") String msisdn,
                                                            @Param("groupId") Long groupId );
}
