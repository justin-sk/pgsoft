package com.pgsoft.service.impl.notification.dto;

import com.pgsoft.service.dto.IPgSoftDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@Relation(collectionRelation = "notification")
public class NotificationDTO implements IPgSoftDTO {
    private Long id;
    private String header;
    private String description;
    private Long recurrenceTypeId;
    private Long branchId;
    private Long userId;
}
