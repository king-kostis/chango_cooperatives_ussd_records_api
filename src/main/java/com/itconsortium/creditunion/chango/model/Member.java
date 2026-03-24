package com.itconsortium.creditunion.chango.model;

import com.itconsortium.creditunion.chango.enums.MemberStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "members")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "auth_provider_id")
    private String authProviderId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "msisdn", unique = true)
    private String msisdn;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "network_code")
    private String networkCode;

    @Column(name = "country_id")
    private String countryId;

    @Column(name = "member_icon_path")
    private String memberIconPath;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MemberStatus status;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @Builder.Default
    @Column(name = "deleted")
    private Boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ElementCollection
    @CollectionTable(name = "member_account_ids", joinColumns = @JoinColumn(name = "member_id"))
    @Column(name = "account_id")
    private Set<Long> accountIds;

    @ElementCollection
    @CollectionTable(name = "member_loan_ids", joinColumns = @JoinColumn(name = "member_id"))
    @Column(name = "loan_id")
    private Set<Long> loanIds;

    @ElementCollection
    @CollectionTable(name = "member_document_ids", joinColumns = @JoinColumn(name = "member_id"))
    @Column(name = "member_documents_id")
    private Set<Long> memberDocumentsId;
}