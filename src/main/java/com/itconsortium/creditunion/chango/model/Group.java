package com.itconsortium.creditunion.chango.model;

import com.itconsortium.creditunion.chango.enums.ActiveStatus;
import com.itconsortium.creditunion.chango.enums.ApprovalStatus;
import com.itconsortium.creditunion.chango.enums.GroupType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "groups")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false)
    private Long groupId;

    @Column(name = "group_name", nullable = false)
    private String groupName;

    @Enumerated(EnumType.STRING)
    @Column(name = "group_type")
    private GroupType groupType;

    @Column(name = "group_icon_path")
    private String groupIconPath;

    @Column(name = "country_id")
    private String countryId;

    @Column(name = "currency")
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ActiveStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status")
    private ApprovalStatus approvalStatus;

    @Column(name = "description")
    private String description;

    @Column(name = "creator_id")
    private String creatorId;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @Builder.Default
    @Column(name = "deleted")
    private Boolean deleted = false;

    @ElementCollection
    @CollectionTable(name = "group_account_ids", joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "account_id")
    private Set<Long> accountIds;

    @OneToMany(mappedBy = "group")
    private List<Member> members;
}
