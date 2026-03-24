package com.itconsortium.creditunion.chango.projections;

import java.time.LocalDate;

public interface MemberTransactionSummaryDto {
    LocalDate getCreated();
    String getTransactionType();
    String getGroupCurrency();
    String getAmount();
}
