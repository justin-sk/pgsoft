package com.pgsoft.service.impl.amenity.dto;

import com.pgsoft.service.impl.type.dto.PgSoftTypeDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Relation(collectionRelation = "amenity")
public class AmenityDTO extends PgSoftTypeDTO {
    private String description;
    private String imageURL;
}
