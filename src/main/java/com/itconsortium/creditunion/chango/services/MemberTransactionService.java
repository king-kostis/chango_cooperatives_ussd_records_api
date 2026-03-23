package com.itconsortium.creditunion.chango.services;

import com.itconsortium.creditunion.chango.exceptions.NoTransactionsAvailableException;
import com.itconsortium.creditunion.chango.projections.MemberTransactionSummaryDto;
import com.itconsortium.creditunion.chango.repository.MemberTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MemberTransactionService {

    @Autowired
    private MemberTransactionRepository memberTransactionRepository;

    public List<MemberTransactionSummaryDto> getLast5Transactions(Long groupId, String msisdn){
        List<MemberTransactionSummaryDto> transactions = memberTransactionRepository.findLast5Transactions(groupId, msisdn);

        if(transactions.isEmpty()) {
            throw new NoTransactionsAvailableException("There are no transaction records");
        } else {
            return transactions;
        }
    }
}
