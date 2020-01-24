package com.pgsoft.service.impl.expenditure.dto;

import com.pgsoft.service.dto.IPgSoftDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Relation(collectionRelation = "expenditure")
public class ExpenditureDTO implements IPgSoftDTO {
    private Long id;
    private BigDecimal amount;
    private Date billDate;
    private Long categoryId;
    private Long employeeId;
    private Long branchId;
    private String[] attachmentUrls;
}
