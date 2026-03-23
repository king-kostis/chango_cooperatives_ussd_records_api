package com.itconsortium.creditunion.chango.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "MEMBER_TRANSACTIONS")
public class MemberTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "GROUP_ID")
    private Long groupId;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;

    @Column(name = "TRANSACTION_CATEGORY")
    private String transactionCategory;

    @Column(name = "AMOUNT")
    private double amount;

    @Column(name = "BALANCE_BEFORE")
    private double balanceBefore;

    @Column(name = "BALANCE_AFTER")
    private double balanceAfter;

    @Column(name = "SHARES_QUANTITY")
    private int sharesQuantity;

    @Column(name = "REFERENCE_ID")
    private Long referenceId;

    @Column(name = "REFERENCE_TYPE")
    private String referenceType;

    @Column(name = "NARRATION")
    private String narration;

    @Column(name = "CHANNEL_ID")
    private Long channelId;

    @Column(name = "EXTERNAL_TRANSACTION_ID")
    private Long externalTransactionId;

    @Column(name = "PROCESSED_BY")
    private String processedBy;

    @Column(name = "CREATED")
    private LocalDate created;

    @Column(name = "MODIFIED")
    private LocalDate modified;
}

