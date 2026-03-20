package com.itconsortium.creditunion.chango.services;

import com.itconsortium.creditunion.chango.exceptions.NoTransactionsAvailableException;
import com.itconsortium.creditunion.chango.projections.MemberAccountTransactionSummaryDto;
import com.itconsortium.creditunion.chango.repository.MemberAccountTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MemberAccountTransactionService {

    @Autowired
    private MemberAccountTransactionRepository memberAccountTransactionRepository;

    public List<MemberAccountTransactionSummaryDto> getLast5Statements(Long groupId, String msisdn){
        List<MemberAccountTransactionSummaryDto> transactions = memberAccountTransactionRepository.findLast5Transactions(groupId, msisdn);

        if(transactions.isEmpty()) {
            throw new NoTransactionsAvailableException("There are no statement records");
        } else {
            return transactions;
        }
    }
}
