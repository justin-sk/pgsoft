package com.pgsoft.service.impl.payment.dto;

import com.pgsoft.service.dto.IPgSoftDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

import java.sql.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Relation(collectionRelation = "payment details")
public class PaymentDtlsDTO implements IPgSoftDTO {
    private Long id;
    private Double amount;
    private Date paymentDate;
    private String status;
    private Long paymentTypeId;
    private Long userId;
}
