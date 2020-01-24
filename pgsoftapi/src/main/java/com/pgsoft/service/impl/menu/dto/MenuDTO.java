package com.pgsoft.service.impl.menu.dto;

import com.pgsoft.service.impl.type.dto.PgSoftTypeDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "menu")
public class MenuDTO extends PgSoftTypeDTO {
    private String url;
}
