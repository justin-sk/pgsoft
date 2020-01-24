package com.pgsoft.service.impl.user.dto;

import com.pgsoft.service.dto.ParentInjectableDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Relation(collectionRelation = "user rent")
public class UserRentDTO extends ParentInjectableDTO {
    private Long id;
    private Long userId;
    private BigDecimal rent;
}
