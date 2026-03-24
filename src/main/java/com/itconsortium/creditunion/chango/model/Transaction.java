package com.itconsortium.creditunion.chango.model;

import com.itconsortium.creditunion.chango.enums.TransactionCategory;
import com.itconsortium.creditunion.chango.enums.TransactionStatus;
import com.itconsortium.creditunion.chango.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id", nullable = false)
    private Long transactionId;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_category", nullable = false)
    private TransactionCategory transactionCategory;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "balance_before", nullable = false)
    private double balanceBefore;

    @Column(name = "balance_after", nullable = false)
    private double balanceAfter;

    @Column(name = "shares_quantity")
    private Integer sharesQuantity;

    @Column(name = "reference_id")
    private String referenceId;

    @Column(name = "reference_type")
    private String referenceType;

    @Column(name = "narration")
    private String narration;

    @Column(name = "channel_id")
    private String channelId;

    @Column(name = "external_transactionId")
    private String externalTransactionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TransactionStatus transactionStatus;

    @Column(name = "processed_by")
    private String processedBy;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @Column(name = "account_id")
    private Long accountId;
}

