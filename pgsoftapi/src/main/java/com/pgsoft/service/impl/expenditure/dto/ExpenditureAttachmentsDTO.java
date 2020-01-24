package com.pgsoft.service.impl.expenditure.dto;

import com.pgsoft.service.dto.ParentInjectableDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Relation(collectionRelation = "expenditure attachments")
public class ExpenditureAttachmentsDTO extends ParentInjectableDTO {
    private Long id;
    private String attachmentURL;
    private Long expenditureId;
}
