package com.pgsoft.service.impl.user.dto;

import com.pgsoft.service.dto.IPgSoftDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@Relation(collectionRelation = "user")
public class UserDTO implements IPgSoftDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Boolean status;
    private Long userBranchId;
    private Long userBedId;
    private Date exitDate;
    private BigDecimal rent;
    private Long roleId;
    private Boolean tcAccepted;
}
