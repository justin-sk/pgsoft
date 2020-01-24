package com.pgsoft.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "xrefs")
public class XRefDTO extends ParentInjectableDTO {
    private Long id;
    private Long parentId;
    private Long xrefId;
}
