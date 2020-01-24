package com.pgsoft.service.impl.employee.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.Employee;
import com.pgsoft.persistence.impl.jpa.dbo.User;
import com.pgsoft.persistence.impl.jpa.repository.UserRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.employee.dto.EmployeeDTO;
import com.pgsoft.service.impl.type.DesignationService;
import com.pgsoft.service.impl.user.UserService;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.employee.EmployeeResource;
import com.pgsoft.web.rest.v1.pgsoft.type.DesignationResource;
import com.pgsoft.web.rest.v1.pgsoft.user.UserResource;
import org.springframework.hateoas.Link;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class EmployeeMapper extends PgSoftChildMapperAdapter<Employee, EmployeeDTO, User> {
    private final DesignationService designationService;
    private final UserService userService;

    public EmployeeMapper(UserRepository repository
            , DesignationService designationService
            , UserService userService) {
        super(repository);
        this.designationService = designationService;
        this.userService = userService;
    }

    @Override
    public void populateChildDBO(@NotNull Employee dbo, @NotNull EmployeeDTO dto) {

    }

    @Override
    protected void populateDBO(@NotNull Employee dbo, @NotNull EmployeeDTO dto) {
        dbo.setEmplNo(dto.getEmployeeNo());
        dbo.setAddress(dto.getAddress());
        dbo.setPermAddress(dto.getPermanentAddress());
        dbo.setDateOfExit(dto.getDateOfExit());
        dbo.setDateOfJoin(dto.getDateOfJoin());
        dbo.setSalary(dto.getSalary());
        dbo.setDesignation(designationService.findById(dto.getDesignationId()).orElseThrow(() -> new NotFoundException("Designation not found")));
        if (dbo.getUser() == null) {
            dbo.setUser(userService.getMapper().toNewDBO(dto.getUser()));
        }
    }

    @Override
    protected EmployeeDTO toDTOImpl(@NotNull Employee dbo) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeNo(dbo.getEmplNo());
        dto.setAddress(dbo.getAddress());
        dto.setPermanentAddress(dbo.getPermAddress());
        dto.setSalary(dbo.getSalary());
        dto.setDateOfExit(dbo.getDateOfExit());
        dto.setDateOfJoin(dbo.getDateOfJoin());
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, Employee dbo) {
        links.add(linkTo(methodOn(UserResource.class).readById(dbo.getUser().getId())).withRel("user"));
        links.add(linkTo(methodOn(DesignationResource.class).readById(dbo.getDesignation().getId())).withRel("designation"));
    }

    @Override
    protected Link getSelfLink(Employee dbo) {
        return linkTo(methodOn(EmployeeResource.class).readById(dbo.getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(Employee dbo, @NotNull PgSoftDTOResource<EmployeeDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("user", userService.getMapper().toDTO(dbo.getUser()));
        dto.embedResource("designation", designationService.getMapper().toDTO(dbo.getDesignation()));
    }
}
