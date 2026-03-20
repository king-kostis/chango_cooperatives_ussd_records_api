package com.itconsortium.creditunion.chango.projections;

import java.time.LocalDate;

public interface MemberAccountTransactionSummaryDto {
    LocalDate getTransactionDate();
    String getTransactionType();
    String getAmount();
}
