package com.pgsoft.service.impl.branch.dto;

import com.pgsoft.service.dto.ParentInjectableDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Relation(collectionRelation = "branch contact info")
public class BranchContactInfoDTO extends ParentInjectableDTO {
    private Long id;
    private String name;
    private String phone;
    private Long branchId;
}
