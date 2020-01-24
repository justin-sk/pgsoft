package com.pgsoft.service.impl.employee.dto;

import com.pgsoft.service.dto.ParentInjectableDTO;
import com.pgsoft.service.impl.user.dto.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Relation(collectionRelation = "employee")
public class EmployeeDTO extends ParentInjectableDTO {
    private Long id;
    private String employeeNo;
    private String address;
    private String permanentAddress;
    private BigDecimal salary;
    private Date dateOfJoin;
    private Date dateOfExit;
    private Long designationId;
    private UserDTO user;
}
