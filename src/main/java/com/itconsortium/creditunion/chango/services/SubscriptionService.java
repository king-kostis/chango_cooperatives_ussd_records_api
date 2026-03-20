package com.itconsortium.creditunion.chango.services;

import com.itconsortium.creditunion.chango.exceptions.SubscriptionNotFoundException;
import com.itconsortium.creditunion.chango.projections.SubscriptionSummaryDto;
import com.itconsortium.creditunion.chango.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public SubscriptionSummaryDto getSubscriptionDetailsById(Long id){
        return subscriptionRepository.findSubscriptionById(id)
                .orElseThrow(() -> new SubscriptionNotFoundException("The subscription does not exist"));
    }

    public SubscriptionSummaryDto getSubscriptionDetailsByMsisdn(String subscriptionMsisdn){
        return subscriptionRepository.findSubscriptionSummaryByMsisdn(subscriptionMsisdn)
                .orElseThrow(() -> new SubscriptionNotFoundException("The subscription does not exist"));
    }
}
