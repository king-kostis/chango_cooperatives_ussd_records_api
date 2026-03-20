package com.itconsortium.creditunion.chango.repository;

import com.itconsortium.creditunion.chango.model.MemberAccountTransaction;
import com.itconsortium.creditunion.chango.projections.MemberAccountTransactionSummaryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MemberAccountTransactionRepository extends JpaRepository<MemberAccountTransaction, Long> {
    @Query(value = """
            SELECT
                ma.transactionDate AS transactionDate,
                ma.transactionType AS transactionType,
                ma.amount AS amount
            FROM member_account_transaction ma
            JOIN member m ON m.memberId = ma.memberId
            RIGHT JOIN group g ON g.groupId = m.groupId
            WHERE m.msisdn = :msisdn AND g.groupId = m.groupId
            ORDER BY .transactionDate DESC
            LIMIT 5
            """, nativeQuery = true)
    List<MemberAccountTransactionSummaryDto> findLast5Transactions(@Param("groupId") Long groupId, @Param("msisdn") String msisdn);


    @Query(value = """
            SELECT
                mat.transactionDate AS transactionDate,
                mat.transactionType AS transactionType,
                mat.amount AS amount
            FROM member_account_transaction mat
            JOIN member m ON m.memberId = mat.memberId
            WHERE mat.transactionDate >= :date AND WHERE m.msisdn = :msisdn
            ORDER BY s.transactionDate DESC
            """, nativeQuery = true)
    List<MemberAccountTransactionSummaryDto> findByTransactionDate(@Param("date") LocalDate date, @Param("msisdn") String msisdn);
}
