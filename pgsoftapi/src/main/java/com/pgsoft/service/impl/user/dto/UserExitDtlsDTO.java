package com.pgsoft.service.impl.user.dto;

import com.pgsoft.service.dto.ParentInjectableDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

import java.sql.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Relation(collectionRelation = "user exit details")
public class UserExitDtlsDTO extends ParentInjectableDTO {
    private Long id;
    private Long userId;
    private Date exitDate;
}
