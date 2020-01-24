package com.pgsoft.service.impl.notification.dto;

import com.pgsoft.service.dto.ParentInjectableDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

import java.sql.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Relation(collectionRelation = "notification details")
public class NotificationDtlsDTO extends ParentInjectableDTO {
    private Long id;
    private Date sentDate;
    private Boolean notificationSent;
    private Long notificationId;
}
