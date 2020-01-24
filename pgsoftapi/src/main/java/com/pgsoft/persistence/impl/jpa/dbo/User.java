package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "USR")
@Entity
public class User implements IPgSoftDBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NM")
    private String nm;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "STS")
    private Boolean sts;

    @Nullable
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Employee employee;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserBed userBed;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserBranch userBranch;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserExitDtls userExitDtls;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserPwd userPwd;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserRent userRent;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserRole userRole;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserTc userTc;

   /* @OneToMany(mappedBy = "user")
    private Collection<Expenditure> expenditures;

    @OneToMany(mappedBy = "user")
    private Collection<ExpenditureMaster> expenditureMasters;

    @OneToMany(mappedBy = "user")
    private Collection<MaintenanceRequest> maintenanceRequests;

    @OneToMany(mappedBy = "user")
    private Collection<MaintenanceRequestAssignTo> maintenanceRequestAssignTo;

    @OneToMany(mappedBy = "user")
    private Collection<Notification> notifications;

    @OneToMany(mappedBy = "user")
    private Collection<PaymentDtls> paymentDtlsById;

    @OneToMany(mappedBy = "user")
    private Collection<UserSuggestion> userSuggestions;

    @OneToMany(mappedBy = "user")
    private Collection<UserUpcomingPayment> userUpcomingPayments;*/
}
