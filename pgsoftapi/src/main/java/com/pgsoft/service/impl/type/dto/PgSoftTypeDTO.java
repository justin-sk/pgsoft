package com.pgsoft.service.impl.type.dto;

import com.pgsoft.service.dto.ParentInjectableDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Relation(collectionRelation = "types")
public class PgSoftTypeDTO extends ParentInjectableDTO implements IPgSoftTypeDTO {
    public PgSoftTypeDTO(Long parentId, long id) {
        super(parentId, id);
    }

    public PgSoftTypeDTO(Long parentId, String code) {
        super();
        this.setParentId(parentId);
        this.code = code;
    }

    public PgSoftTypeDTO(String code) {
        this.code = code;
    }

    public PgSoftTypeDTO(Long id) {
        super(id);
    }

    private String code;
}
