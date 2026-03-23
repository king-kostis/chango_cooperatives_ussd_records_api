package com.itconsortium.creditunion.chango.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "GROUPS")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GROUP_ID")
    private Long groupId;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @Column(name = "GROUP_TYPE")
    private String groupType;

    @Column(name = "GROUP_ICON_PATH")
    private String groupIconPath;

    @Column(name = "COUNTRY_ID")
    private Long countryId;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "APPROVE")
    private String approve;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATOR_ID")
    private Long creatorId;

    @Column(name = "CREATED")
    private LocalDate created;

    @Column(name = "MODIFIED")
    private LocalDate modified;

    @Column(name = "DELETED")
    private LocalDate deleted;
}

