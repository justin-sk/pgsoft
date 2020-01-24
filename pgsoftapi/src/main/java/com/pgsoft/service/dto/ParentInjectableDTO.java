package com.pgsoft.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class ParentInjectableDTO implements IParentInjectableDTO {
    public ParentInjectableDTO(Long id) {
        this.id = id;
    }

    public ParentInjectableDTO(Long parentId, Long id) {
        this.setParentId(parentId);
        this.id = id;
    }

    private Long id;
    private Long parentId;
}
