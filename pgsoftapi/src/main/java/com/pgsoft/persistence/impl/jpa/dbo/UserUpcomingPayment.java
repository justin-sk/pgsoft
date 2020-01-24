package com.pgsoft.persistence.impl.jpa.dbo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "USR_UPCOMING_PAYMENT")
public class UserUpcomingPayment implements IPgSoftDBO, IParentInjectableDBO<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @Column(name = "STS")
    private Boolean sts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USR_ID", referencedColumnName = "ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENT_TYPE_ID", referencedColumnName = "ID")
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENT_DTLS_ID", referencedColumnName = "ID")
    private PaymentDtls paymentDtls;

    @Override
    public void injectParent(User linkedParent) {
        this.user = linkedParent;
    }

    @Override
    public User extractParent() {
        return this.user;
    }
}
