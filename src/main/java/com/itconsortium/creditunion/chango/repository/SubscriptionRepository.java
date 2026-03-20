package com.itconsortium.creditunion.chango.repository;

import com.itconsortium.creditunion.chango.model.Subscription;
import com.itconsortium.creditunion.chango.projections.SubscriptionSummaryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query(value = """
                SELECT
                    g.NAME AS groupName,
                    s.RECURRENCE_FREQUENCY AS recurrenceFrequency,
                    s.DURATION_OF_RECURRENCE AS durationOfRecurrence
                FROM SUBSCRIPTIONS s
                JOIN GROUPS g ON s.GROUP_ID = g.ID
                WHERE s.ID = :id
            """ , nativeQuery = true)
    Optional<SubscriptionSummaryDto> findSubscriptionById(@Param("id") Long id);

    @Query(value = """
                SELECT
                    g.NAME AS groupName,
                    s.RECURRENCE_FREQUENCY AS recurrenceFrequency,
                    s.DURATION_OF_RECURRENCE AS durationOfRecurrence
                FROM SUBSCRIPTIONS s
                JOIN GROUPS g ON s.GROUP_ID = g.ID
                WHERE s.MSISDN = :msisdn
            """ , nativeQuery = true)
    Optional<SubscriptionSummaryDto> findSubscriptionSummaryByMsisdn(@Param("msisdn") String msisdn);


}
