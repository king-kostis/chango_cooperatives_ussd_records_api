package com.itconsortium.creditunion.chango.projections;

import com.mailjet.client.resource.Axtesting;

import java.time.LocalDate;

public interface MemberTransactionSummaryDto {
    LocalDate getCreated();
    String getTransactionType();
    String getMemberCurrency();
    String getAmount();
}
