package com.pgsoft.service.impl.maintenance.dto;

import com.pgsoft.service.dto.IPgSoftDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

import java.sql.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Relation(collectionRelation = "maintenance request")
public class MaintenanceRequestDTO implements IPgSoftDTO {
    private Long id;
    private String description;
    private Boolean canEnter;
    private Long categoryId;
    private Long subCategoryId;
    private Long statusId;
    private Long userId;
    private String[] attachmentUrls;
    private Date requestDate;
}
