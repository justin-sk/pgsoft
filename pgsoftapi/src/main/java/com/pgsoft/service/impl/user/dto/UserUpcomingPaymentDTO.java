package com.pgsoft.service.impl.user.dto;

import com.pgsoft.service.dto.ParentInjectableDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "user upcoming payments")
public class UserUpcomingPaymentDTO extends ParentInjectableDTO {
    private Long id;
    private BigDecimal amount;
    private Date paymentDate;
    private Boolean status;
    private Long userId;
    private Long paymentTypeId;
    private Long paymentDetailsId;
}
