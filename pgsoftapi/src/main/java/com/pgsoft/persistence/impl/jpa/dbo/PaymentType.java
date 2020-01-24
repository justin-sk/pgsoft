package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "PAYMENT_TYPE")
public class PaymentType extends TypeDBO {
    @OneToMany(mappedBy = "paymentType", fetch = FetchType.LAZY)
    private Collection<PaymentDtls> paymentDtlsById;

    @OneToMany(mappedBy = "paymentType", fetch = FetchType.LAZY)
    private Collection<UserUpcomingPayment> userUpcomingPaymentsById;
}
