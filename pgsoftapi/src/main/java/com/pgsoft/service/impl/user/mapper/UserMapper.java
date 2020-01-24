package com.pgsoft.service.impl.user.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.*;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.branch.BedService;
import com.pgsoft.service.impl.branch.BranchService;
import com.pgsoft.service.impl.type.RoleService;
import com.pgsoft.service.impl.user.dto.UserDTO;
import com.pgsoft.service.mapper.PgSoftParentMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.employee.EmployeeResource;
import com.pgsoft.web.rest.v1.pgsoft.user.*;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserMapper extends PgSoftParentMapperAdapter<User, UserDTO> {
    private final BedService bedService;
    private final BranchService branchService;
    private final RoleService roleService;

    public UserMapper(BedService bedService
            , BranchService branchService
            , RoleService roleService) {
        this.bedService = bedService;
        this.branchService = branchService;
        this.roleService = roleService;
    }

    @Override
    protected void populateDBO(@NotNull User dbo, @NotNull UserDTO dto) {
        dbo.setEmail(dto.getEmail());
        dbo.setNm(dto.getName());
        dbo.setPhone(dto.getPhone());
        dbo.setSts(dto.getStatus());
        dbo.setUserBed(new UserBed());
        dbo.getUserBed().setUser(dbo);
        dbo.getUserBed().setBed(bedService.findById(dto.getUserBedId()).orElseThrow(NotFoundException::new));
        dbo.setUserBranch(new UserBranch());
        dbo.getUserBranch().setUser(dbo);
        dbo.getUserBranch().setBranch(branchService.findById(dto.getUserBranchId()).orElseThrow(NotFoundException::new));
        dbo.setUserPwd(new UserPwd());
        dbo.getUserPwd().setUser(dbo);
        dbo.getUserPwd().setPwd(dto.getPhone());
        dbo.setUserExitDtls(new UserExitDtls());
        dbo.getUserExitDtls().setUser(dbo);
        dbo.getUserExitDtls().setExitDt(dto.getExitDate());
        dbo.setUserRent(new UserRent());
        dbo.getUserRent().setUser(dbo);
        dbo.getUserRent().setAmount(dto.getRent());
        dbo.setUserRole(new UserRole());
        dbo.getUserRole().setUser(dbo);
        dbo.getUserRole().setRole(roleService.findById(dto.getRoleId()).orElseThrow(NotFoundException::new));
        dbo.setUserTc(new UserTc());
        dbo.getUserTc().setUser(dbo);
        dbo.getUserTc().setTcAcpt(dto.getTcAccepted());
    }

    @Override
    protected UserDTO toDTOImpl(@NotNull User dbo) {
        UserDTO dto = new UserDTO();
        dto.setEmail(dbo.getEmail());
        dto.setPhone(dbo.getPhone());
        dto.setName(dbo.getNm());
        dto.setStatus(dbo.getSts());
        if (dbo.getUserExitDtls() != null)
            dto.setExitDate(dbo.getUserExitDtls().getExitDt());
        if (dbo.getUserRent() != null)
            dto.setRent(dbo.getUserRent().getAmount());
        if (dbo.getUserTc() != null)
            dto.setTcAccepted(dbo.getUserTc().getTcAcpt());
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, User dbo) {
        if (dbo.getEmployee() != null)
            links.add(linkTo(methodOn(EmployeeResource.class).readById(dbo.getEmployee().getId())).withRel("employee"));
        if (dbo.getUserBed() != null)
            links.add(linkTo(methodOn(UserBedResource.class).readById(dbo.getId(), dbo.getUserBed().getBed().getId())).withRel("userBed"));
        if (dbo.getUserExitDtls() != null)
            links.add(linkTo(methodOn(UserExitDtlsResource.class).readById(dbo.getUserExitDtls().getId())).withRel("userExitDetails"));
        if (dbo.getUserTc() != null)
            links.add(linkTo(methodOn(UserTcResource.class).readById(dbo.getUserTc().getId())).withRel("userTermsConditions"));
        if (dbo.getUserBranch() != null)
            links.add(linkTo(methodOn(UserBranchResource.class).readById(dbo.getId(), dbo.getUserBranch().getId())).withRel("userBranch"));
        if (dbo.getUserRent() != null)
            links.add(linkTo(methodOn(UserRentResource.class).readById(dbo.getUserRent().getId())).withRel("userRent"));
        if (dbo.getUserRole() != null)
            links.add(linkTo(methodOn(UserRoleResource.class).readById(dbo.getId(), dbo.getUserRole().getRole().getId())).withRel("userRole"));
    }

    @Override
    protected Link getSelfLink(User dbo) {
        return linkTo(methodOn(UserResource.class).readById(dbo.getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(User dbo, @NotNull PgSoftDTOResource<UserDTO> dto) {
        super.embeddedResources(dbo, dto);
        if (dbo.getUserBed() != null)
            dto.embedResource("bed", bedService.getMapper().toDTO(dbo.getUserBed().getBed()));
        if (dbo.getUserBranch() != null)
            dto.embedResource("branch", branchService.getMapper().toDTO(dbo.getUserBranch().getBranch()));
        if (dbo.getUserRole() != null)
            dto.embedResource("role", roleService.getMapper().toDTO(dbo.getUserRole().getRole()));
    }
}
