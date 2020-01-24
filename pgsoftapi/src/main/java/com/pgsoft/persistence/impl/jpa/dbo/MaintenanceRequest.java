package com.pgsoft.persistence.impl.jpa.dbo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryInit;
import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "MAINT_REQ")
public class MaintenanceRequest implements IPgSoftDBO {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "[DESC]")
    private String desc;

    @Column(name = "PERM_TO_ENTER")
    private Boolean permToEnter;

    @Column(name = "REQ_DT")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date requestDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CTG_ID", referencedColumnName = "ID", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUB_CTG_ID", referencedColumnName = "ID")
    private SubCategory subCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STS_ID", referencedColumnName = "ID", nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USR_ID", referencedColumnName = "ID", nullable = false)
    @QueryInit("userBranch.branch")
    private User user;

    @Nullable
    @OneToMany(mappedBy = "maintenanceRequest", fetch = FetchType.LAZY)
    private Collection<MaintenanceRequestAssignTo> maintenanceRequestAssignTo;

    @Nullable
    @OneToMany(mappedBy = "maintenanceRequest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<MaintenanceRequestAttachment> maintenanceRequestAttachments;

    @Nullable
    @OneToMany(mappedBy = "maintenanceRequest", fetch = FetchType.LAZY)
    private Collection<MaintenanceRequestFee> maintenanceRequestFees;
}
