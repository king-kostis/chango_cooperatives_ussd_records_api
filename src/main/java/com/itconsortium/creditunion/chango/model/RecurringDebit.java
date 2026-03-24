package com.itconsortium.creditunion.chango.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "RECURRING_DEBITS")
public class RecurringDebit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEBIT_TRANSACTION_ID")
    private Long debitTransactionId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column(name = "DEBIT_AMOUNT")
    private double debitAmount;

    @Column(name = "FREQUENCY")
    private String frequency;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "DEBIT_DAY")
    private LocalDate debitDay;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "REFERENCE")
    private String reference;

    @Column(name = "CHANNEL")
    private String channel;

    @Column(name = "DEBIT_TIME")
    private LocalTime debitTime;

    @Column(name = "CURRENCY")
    private String currency;
}

