package com.pgsoft.service.impl.branch.dto;

import com.pgsoft.service.dto.ParentInjectableDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Relation(collectionRelation = "bed")
public class BedDTO extends ParentInjectableDTO {
    private Long id;
    private Integer bedNo;
    private BigDecimal rent;
    private Long roomId;
    private Long bedTypeId;
}
