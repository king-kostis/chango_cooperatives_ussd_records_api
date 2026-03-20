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
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionId")
    private Long transactionId;


}

