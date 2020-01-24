package com.pgsoft.service.impl.expenditure.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.Expenditure;
import com.pgsoft.persistence.impl.jpa.dbo.ExpenditureAttachments;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.branch.BranchService;
import com.pgsoft.service.impl.expenditure.dto.ExpenditureDTO;
import com.pgsoft.service.impl.type.ExpenditureCategoryService;
import com.pgsoft.service.impl.user.UserService;
import com.pgsoft.service.mapper.PgSoftParentMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.branch.BranchResource;
import com.pgsoft.web.rest.v1.pgsoft.expenditure.ExpenditureAttachmentsResource;
import com.pgsoft.web.rest.v1.pgsoft.expenditure.ExpenditureResource;
import com.pgsoft.web.rest.v1.pgsoft.user.UserResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ExpenditureMapper extends PgSoftParentMapperAdapter<Expenditure, ExpenditureDTO> {
    private final ExpenditureCategoryService expenditureCategoryService;
    private final UserService userService;
    private final BranchService branchService;

    public ExpenditureMapper(ExpenditureCategoryService expenditureCategoryService
            , UserService userService
            , BranchService branchService) {
        this.expenditureCategoryService = expenditureCategoryService;
        this.userService = userService;
        this.branchService = branchService;
    }

    @Override
    protected void populateDBO(@NotNull Expenditure dbo, @NotNull ExpenditureDTO dto) {
        dbo.setAmount(dto.getAmount());
        dbo.setBillDt(dto.getBillDate());
        dbo.setBranch(branchService.findById(dto.getBranchId()).orElseThrow(NotFoundException::new));
        dbo.setUser(userService.findById(dto.getEmployeeId()).orElseThrow(NotFoundException::new));
        dbo.setExpenditureCategory(expenditureCategoryService.findById(dto.getCategoryId()).orElseThrow(NotFoundException::new));
        Collection<ExpenditureAttachments> list = new ArrayList<>();
        dbo.setExpenditureAttachments(Arrays.stream(dto.getAttachmentUrls()).map(url -> new ExpenditureAttachments(url, dbo)).collect(Collectors.toList()));
    }

    @Override
    protected ExpenditureDTO toDTOImpl(@NotNull Expenditure dbo) {
        ExpenditureDTO dto = new ExpenditureDTO();
        dto.setAmount(dbo.getAmount());
        dto.setBillDate(dbo.getBillDt());
        dto.setAttachmentUrls(dbo.getExpenditureAttachments().stream().map(ExpenditureAttachments::getAttachmentUrl).toArray(String[]::new));
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, Expenditure dbo) {
        links.add(linkTo(methodOn(UserResource.class).readById(dbo.getUser().getId())).withRel("user"));
        links.add(linkTo(methodOn(BranchResource.class).readById(dbo.getBranch().getId())).withRel("branch"));
        links.add(linkTo(methodOn(ExpenditureAttachmentsResource.class).readAll(dbo.getId(), null)).withRel("attachments"));
    }

    @Override
    protected Link getSelfLink(Expenditure dbo) {
        return linkTo(methodOn(ExpenditureResource.class).readById(dbo.getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(Expenditure dbo, @NotNull PgSoftDTOResource<ExpenditureDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("branch", branchService.getMapper().toDTO(dbo.getBranch()));
        dto.embedResource("employee", userService.getMapper().toDTO(dbo.getUser()));
        dto.embedResource("category", expenditureCategoryService.getMapper().toDTO(dbo.getExpenditureCategory()));
    }
}
