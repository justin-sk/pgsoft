package com.pgsoft.service.impl.maintenance.dto;

import com.pgsoft.service.dto.IPgSoftDTO;
import com.pgsoft.service.dto.ParentInjectableDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "maintenance request attachment")
public class MaintenanceRequestAttachmentDTO extends ParentInjectableDTO {
    private Long id;
    private String attachmentURL;
    private Long maintenanceRequestId;
}
