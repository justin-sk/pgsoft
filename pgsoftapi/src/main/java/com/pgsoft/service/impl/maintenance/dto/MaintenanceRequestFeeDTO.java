package com.pgsoft.service.impl.maintenance.dto;

import com.pgsoft.service.dto.ParentInjectableDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "maintenance request fee")
public class MaintenanceRequestFeeDTO extends ParentInjectableDTO {
    private BigDecimal amount;
    private Long maintenanceRequestId;
}
