package com.pgsoft.persistence.impl.jpa.dbo;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "PAYMENT_RESPONSE_DTLS")
public class PaymentResponseDtls implements IPgSoftDBO, IParentInjectableDBO<PaymentDtls> {
    @Id
    private Long Id;

    @Column(name = "REFERENCE_NO")
    private String referenceNo;

    @Column(name = "VENDOR_ID")
    private String vendorId;

    @Column(name = "RSP_CD")
    private String rspCd;

    @Column(name = "MSG")
    private String msg;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENT_DTLS_ID", referencedColumnName = "ID", nullable = false)
    private PaymentDtls paymentDtls;

    @Override
    public void injectParent(PaymentDtls linkedParent) {
        this.paymentDtls = linkedParent;
    }

    @Override
    public PaymentDtls extractParent() {
        return this.paymentDtls;
    }
}
