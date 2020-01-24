package com.pgsoft.persistence.impl.jpa.dbo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryInit;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "PAYMENT_DTLS")
public class PaymentDtls implements IPgSoftDBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "PAYMENT_DT")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date paymentDt;

    @Column(name = "STS")
    private String sts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENT_TYPE_ID", referencedColumnName = "ID", nullable = false)
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TENANT_ID", referencedColumnName = "ID", nullable = false)
    @QueryInit("userBranch.branch")
    private User user;

    @OneToOne(mappedBy = "paymentDtls", fetch = FetchType.LAZY)
    private PaymentResponseDtls paymentResponseDtls;

    @OneToMany(mappedBy = "paymentDtls", fetch = FetchType.LAZY)
    private Collection<UserUpcomingPayment> userUpcomingPayments;
}
