package com.itconsortium.creditunion.chango.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member_account_transaction")
public class MemberAccountTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionId")
    private Long transactionId;

    @Column(name = "accountNumber")
    private String accountNumber;

    @Column(name = "groupId")
    private Long groupId;

    @Column(name = "memberId")
    private Long memberId;

    @Column(name = "transactionType")
    private String transactionType;

    @Column(name = "transactionCategory")
    private String transactionCategory;

    @Column(name = "amount")
    private double amount;

    @Column(name = "balanceBefore")
    private double balanceBefore;

    @Column(name = "balanceAfter")
    private double balanceAfter;

    @Column(name = "sharesQuantity")
    private int sharesQuantity;

    @Column(name = "referenceId")
    private Long referenceId;

    @Column(name = "referenceType")
    private String referenceType;

    @Column(name = "narration")
    private String narration;

    @Column(name = "channelId")
    private Long channelId;

    @Column(name = "externalTransactionId")
    private Long externalTransaction;

    @Column(name = "processedBy")
    private String processedBy;

    @Column(name = "created")
    private LocalDate created;

    @Column(name = "modified")
    private LocalDate modified;
}

