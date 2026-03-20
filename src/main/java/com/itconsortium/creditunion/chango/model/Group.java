package com.itconsortium.creditunion.chango.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupId")
    private Long groupId;

    @Column(name = "groupName")
    private String groupName;

    @Column(name = "groupType")
    private String groupType;

    @Column(name = "groupIconPath")
    private String groupIconPath;

    @Column(name = "countryId")
    private Long countryId;

    @Column(name = "currency")
    private String currency;

    @Column(name = "status")
    private String status;

    @Column(name = "approve")
    private String approve;

    @Column(name = "description")
    private String description;

    @Column(name = "creatorId")
    private Long creatorId;

    @Column(name = "created")
    private LocalDate created;

    @Column(name = "modified")
    private LocalDate modified;

    @Column(name = "deleted")
    private LocalDate deleted;
}

