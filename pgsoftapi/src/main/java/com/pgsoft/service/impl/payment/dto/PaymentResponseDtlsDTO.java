package com.pgsoft.service.impl.payment.dto;

import com.pgsoft.service.dto.ParentInjectableDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "payment response details")
public class PaymentResponseDtlsDTO extends ParentInjectableDTO {
    private String referenceNumber;
    private String vendorId;
    private String responseCode;
    private String message;
    private Long paymentDetailsId;
}
