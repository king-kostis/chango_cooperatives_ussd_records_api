package com.itconsortium.creditunion.chango.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private Long memberId;

    @Column(name = "memberName")
    private String memberName;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "emailAddress")
    private String emailAddress;
}
