package com.pgsoft.service.impl.user.dto;

import com.pgsoft.service.dto.ParentInjectableDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Relation(collectionRelation = "user suggestion")
public class UserSuggestionDTO extends ParentInjectableDTO {
    private Long id;
    private String description;
    private Long categoryId;
    private Long subCategoryId;
    private Long userId;
}
