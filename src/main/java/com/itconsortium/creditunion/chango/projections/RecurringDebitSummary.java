package com.itconsortium.creditunion.chango.projections;

public interface RecurringDebitSummary {
    String getGroupName();
    String getFrequency();
    String getStartDate();
    String getEndDate();
}
