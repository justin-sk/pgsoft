package com.pgsoft.service.impl.branch.dto;

import com.pgsoft.service.impl.type.dto.PgSoftTypeDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Relation(collectionRelation = "branch info")
public class BranchInfoDTO extends PgSoftTypeDTO {
    private String name;
    private String nickName;
    private String address;
    private boolean status;
    private Long cityId;
    private Long occupancyTypeId;
}
