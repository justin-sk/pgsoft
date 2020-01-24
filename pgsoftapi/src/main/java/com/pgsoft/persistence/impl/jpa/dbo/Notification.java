package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.Collection;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "NOTIFICATION")
@Entity
public class Notification implements IPgSoftDBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "HDR")
    private String hdr;

    @Column(name = "[DESC]")
    private String desc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REC_TYP_ID", referencedColumnName = "ID")
    private RecurrenceType recurrenceType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRANCH_ID", referencedColumnName = "ID")
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USR_ID", referencedColumnName = "ID")
    private User user;

    @Nullable
    @OneToMany(mappedBy = "notification", fetch = FetchType.LAZY)
    private Collection<NotificationDtls> notificationDtls;
}
