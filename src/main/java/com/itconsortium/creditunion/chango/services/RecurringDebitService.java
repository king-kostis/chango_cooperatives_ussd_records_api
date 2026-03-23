package com.itconsortium.creditunion.chango.services;

import com.itconsortium.creditunion.chango.dto.RecurringDetailsDto;
import com.itconsortium.creditunion.chango.exceptions.RecurringDebitsNotFoundException;
import com.itconsortium.creditunion.chango.projections.RecurringDebitSummary;
import com.itconsortium.creditunion.chango.repository.RecurringDebitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class RecurringDebitService {
    @Autowired
    private RecurringDebitRepository recurringDebitRepository;

    private String getRecurringDebitLength(RecurringDebitSummary recurringDebitSummary){
        LocalDate startDate = LocalDate.parse(recurringDebitSummary.getStartDate());
        LocalDate endDate = LocalDate.parse(recurringDebitSummary.getEndDate());

        String monthsBetween = "" + ChronoUnit.MONTHS.between(startDate, endDate) + " months";
        return monthsBetween;
    }

    public RecurringDetailsDto getRecurringDetailsById(Long id){
        RecurringDebitSummary recurringDebitSummary = recurringDebitRepository.findSubscriptionById(id)
                .orElseThrow(() -> new RecurringDebitsNotFoundException("The recurring details do not exist"));
        RecurringDetailsDto recurringDetailsDto = new RecurringDetailsDto(
                recurringDebitSummary.getGroupName(),
                recurringDebitSummary.getFrequency(),
                getRecurringDebitLength(recurringDebitSummary)
        );
        return recurringDetailsDto;
    }

    public RecurringDetailsDto getRecurringDetailsByMsisdn(String msisdn){
        RecurringDebitSummary recurringDebitSummary = recurringDebitRepository.findSubscriptionSummaryByMsisdn(msisdn)
                .orElseThrow(() -> new RecurringDebitsNotFoundException("The recurring details do not exist"));
        RecurringDetailsDto recurringDetailsDto = new RecurringDetailsDto(
                recurringDebitSummary.getGroupName(),
                recurringDebitSummary.getFrequency(),
                getRecurringDebitLength(recurringDebitSummary)
        );
        return recurringDetailsDto;
    }
}
