package com.pgsoft.service.impl.branch.dto;

import com.pgsoft.service.dto.ParentInjectableDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Relation(collectionRelation = "room")
public class RoomDTO extends ParentInjectableDTO {
    private String roomNo;
    private Boolean status;
    private Long roomTypeId;
    private Long branchId;
}
