package com.itconsortium.creditunion.chango.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "MEMBERS")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "AUTH_PROVIDER_ID")
    private String authProviderId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String LastName;

    @Column(name = "MSISDN", unique = true)
    private String msisdn;

    @Column(name = "EMAIL_ADDRESS", unique = true)
    private String emailAddress;

    @Column(name = "NETWORK_CODE")
    private String networkCode;

    @Column(name = "COUNTRY_ID")
    private String countryId;

    @Column(name = "MEMBER_ICON_PATH")
    private String memberIconPath;

    @Column(name = "CREATED")
    private String created;

    @Column(name = "MODIFIED")
    private LocalDate modified;
}
