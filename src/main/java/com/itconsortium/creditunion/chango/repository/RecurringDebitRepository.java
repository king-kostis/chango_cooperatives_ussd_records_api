package com.itconsortium.creditunion.chango.repository;

import com.itconsortium.creditunion.chango.model.RecurringDebit;
import com.itconsortium.creditunion.chango.projections.RecurringDebitSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecurringDebitRepository extends JpaRepository<RecurringDebit, Long> {

    @Query(value = """
                SELECT DISTINCT
                   g.GROUP_NAME AS groupName,
                   r.FREQUENCY AS frequency,
                   r.START_DATE AS startDate,
                   r.END_DATE AS endDate
                FROM RECURRING_DEBITS r
                INNER JOIN MEMBERS m ON r.MEMBER_ID = m.MEMBER_ID
                INNER JOIN TRANSACTIONS mt ON mt.MEMBER_ID = m.MEMBER_ID
                INNER JOIN GROUPS g ON mt.GROUP_ID = g.GROUP_ID
                WHERE r.MEMBER_ID = :id
                LIMIT 1
            """ , nativeQuery = true)
    Optional<RecurringDebitSummary> findRecurringDetailsById(@Param("id") Long id);

    @Query(value = """
                SELECT DISTINCT
                    g.GROUP_NAME AS groupName,
                    r.FREQUENCY AS frequency,
                    r.START_DATE AS startDate,
                    r.END_DATE AS endDate
                FROM RECURRING_DEBITS r
                INNER JOIN MEMBERS m ON r.MEMBER_ID = m.MEMBER_ID
                INNER JOIN TRANSACTIONS mt ON mt.MEMBER_ID = m.MEMBER_ID
                INNER JOIN GROUPS g ON mt.GROUP_ID = g.GROUP_ID
                WHERE m.MSISDN = :msisdn
                LIMIT 1
            """ , nativeQuery = true)
    Optional<RecurringDebitSummary> findRecurringDetailsByMsisdn(@Param("msisdn") String msisdn);
}
