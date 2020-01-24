package com.pgsoft.service.impl.expenditure.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.ExpenditureMaster;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.branch.BranchService;
import com.pgsoft.service.impl.expenditure.dto.ExpenditureMasterDTO;
import com.pgsoft.service.impl.type.ExpenditureCategoryService;
import com.pgsoft.service.impl.type.RecurrenceTypeService;
import com.pgsoft.service.impl.user.UserService;
import com.pgsoft.service.mapper.PgSoftParentMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.branch.BranchResource;
import com.pgsoft.web.rest.v1.pgsoft.expenditure.ExpenditureMasterResource;
import com.pgsoft.web.rest.v1.pgsoft.type.ExpenditureCategoryResource;
import com.pgsoft.web.rest.v1.pgsoft.type.RecurrenceTypeResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ExpenditureMasterMapper extends PgSoftParentMapperAdapter<ExpenditureMaster, ExpenditureMasterDTO> {
    private final ExpenditureCategoryService expenditureCategoryService;
    private final UserService userService;
    private final BranchService branchService;
    private final RecurrenceTypeService recurrenceTypeService;

    public ExpenditureMasterMapper(ExpenditureCategoryService expenditureCategoryService
            , UserService userService
            , BranchService branchService
            , RecurrenceTypeService recurrenceTypeService) {
        this.expenditureCategoryService = expenditureCategoryService;
        this.userService = userService;
        this.branchService = branchService;
        this.recurrenceTypeService = recurrenceTypeService;
    }

    @Override
    protected void populateDBO(@NotNull ExpenditureMaster dbo, @NotNull ExpenditureMasterDTO dto) {
        dbo.setAmount(dto.getAmount());
        dbo.setBillDt(dto.getBillDate());
        dbo.setBranch(branchService.findById(dto.getBranchId()).orElseThrow(NotFoundException::new));
        dbo.setUser(userService.findById(dto.getEmployeeId()).orElseThrow(NotFoundException::new));
        dbo.setExpenditureCategory(expenditureCategoryService.findById(dto.getCategoryId()).orElseThrow(NotFoundException::new));
        dbo.setRecurrenceType(recurrenceTypeService.findById(dto.getRecurrenceTypeId()).orElseThrow(NotFoundException::new));
    }

    @Override
    protected ExpenditureMasterDTO toDTOImpl(@NotNull ExpenditureMaster dbo) {
        ExpenditureMasterDTO dto = new ExpenditureMasterDTO();
        dto.setAmount(dbo.getAmount());
        dto.setBillDate(dbo.getBillDt());
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, ExpenditureMaster dbo) {
        links.add(linkTo(methodOn(ExpenditureCategoryResource.class).readById(dbo.getExpenditureCategory().getId())).withRel("expenditureCategory"));
        links.add(linkTo(methodOn(RecurrenceTypeResource.class).readById(dbo.getRecurrenceType().getId())).withRel("recurrence"));
        links.add(linkTo(methodOn(BranchResource.class).readById(dbo.getBranch().getId())).withRel("branch"));
    }

    @Override
    protected Link getSelfLink(ExpenditureMaster dbo) {
        return linkTo(methodOn(ExpenditureMasterResource.class).readById(dbo.getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(ExpenditureMaster dbo, @NotNull PgSoftDTOResource<ExpenditureMasterDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("branch", branchService.getMapper().toDTO(dbo.getBranch()));
        dto.embedResource("employee", userService.getMapper().toDTO(dbo.getUser()));
        dto.embedResource("category", expenditureCategoryService.getMapper().toDTO(dbo.getExpenditureCategory()));
        dto.embedResource("recurrenceType", recurrenceTypeService.getMapper().toDTO(dbo.getRecurrenceType()));
    }
}
