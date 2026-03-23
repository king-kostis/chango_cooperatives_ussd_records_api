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

    //Method to get number of months till debit recurrence expires
    private String getRecurringDebitDuration(RecurringDebitSummary recurringDebitSummary){
        LocalDate startDate = LocalDate.parse(recurringDebitSummary.getStartDate());
        LocalDate endDate = LocalDate.parse(recurringDebitSummary.getEndDate());

        String monthsBetween = "" + ChronoUnit.MONTHS.between(startDate, endDate) + " months";
        return monthsBetween;
    }

    public RecurringDetailsDto getRecurringDetailsById(Long id){
        RecurringDebitSummary recurringDebitSummary = recurringDebitRepository.findSubscriptionById(id)
                .orElseThrow(() -> new RecurringDebitsNotFoundException("The recurring details do not exist"));

        //Initializing DTO object for transferring recurring details with recurrence duration included
        RecurringDetailsDto recurringDetailsDto = new RecurringDetailsDto(
                recurringDebitSummary.getGroupName(),
                recurringDebitSummary.getFrequency(),
                getRecurringDebitDuration(recurringDebitSummary)
        );
        return recurringDetailsDto;
    }

    //Same as previous method but with msisdn
    public RecurringDetailsDto getRecurringDetailsByMsisdn(String msisdn){
        RecurringDebitSummary recurringDebitSummary = recurringDebitRepository.findSubscriptionSummaryByMsisdn(msisdn)
                .orElseThrow(() -> new RecurringDebitsNotFoundException("The recurring details do not exist"));

        RecurringDetailsDto recurringDetailsDto = new RecurringDetailsDto(
                recurringDebitSummary.getGroupName(),
                recurringDebitSummary.getFrequency(),
                getRecurringDebitDuration(recurringDebitSummary)
        );
        return recurringDetailsDto;
    }
}
