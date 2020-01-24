package com.pgsoft.service.impl.maintenance.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.MaintenanceRequest;
import com.pgsoft.persistence.impl.jpa.dbo.MaintenanceRequestAttachment;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.maintenance.dto.MaintenanceRequestDTO;
import com.pgsoft.service.impl.type.CategoryService;
import com.pgsoft.service.impl.type.StatusService;
import com.pgsoft.service.impl.type.SubCategoryService;
import com.pgsoft.service.impl.user.UserService;
import com.pgsoft.service.mapper.PgSoftParentMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.maintenance.MaintenanceRequestResource;
import com.pgsoft.web.rest.v1.pgsoft.type.CategoryResource;
import com.pgsoft.web.rest.v1.pgsoft.type.StatusResource;
import com.pgsoft.web.rest.v1.pgsoft.type.SubCategoryResource;
import com.pgsoft.web.rest.v1.pgsoft.user.UserResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class MaintenanceRequestMapper extends PgSoftParentMapperAdapter<MaintenanceRequest, MaintenanceRequestDTO> {
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    private final StatusService statusService;
    private final UserService userService;

    public MaintenanceRequestMapper(CategoryService categoryService
            , SubCategoryService subCategoryService
            , StatusService statusService
            , UserService userService) {
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
        this.statusService = statusService;
        this.userService = userService;
    }

    @Override
    protected void populateDBO(@NotNull MaintenanceRequest dbo, @NotNull MaintenanceRequestDTO dto) {
        dbo.setDesc(dto.getDescription());
        dbo.setPermToEnter(dto.getCanEnter());
        dbo.setRequestDate(dto.getRequestDate());
        dbo.setCategory(categoryService.findById(dto.getCategoryId()).orElseThrow(NotFoundException::new));
        dbo.setSubCategory(subCategoryService.findById(dto.getSubCategoryId()).orElseThrow(NotFoundException::new));
        dbo.setStatus(statusService.findById(dto.getStatusId()).orElseThrow(NotFoundException::new));
        dbo.setUser(userService.findById(dto.getUserId()).orElseThrow(NotFoundException::new));
        dbo.setMaintenanceRequestAttachments(Arrays.stream(dto.getAttachmentUrls()).map(url -> new MaintenanceRequestAttachment(url, dbo)).collect(Collectors.toList()));
    }

    @Override
    protected MaintenanceRequestDTO toDTOImpl(@NotNull MaintenanceRequest dbo) {
        MaintenanceRequestDTO dto = new MaintenanceRequestDTO();
        dto.setDescription(dbo.getDesc());
        dto.setCanEnter(dbo.getPermToEnter());
        dto.setRequestDate(dbo.getRequestDate());
        if (dbo.getMaintenanceRequestAttachments() != null) {
            dto.setAttachmentUrls(dbo.getMaintenanceRequestAttachments().stream().map(MaintenanceRequestAttachment::getAttachmentUrl).toArray(String[]::new));
        }
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, MaintenanceRequest dbo) {
        links.add(linkTo(methodOn(CategoryResource.class).readById(dbo.getCategory().getId())).withRel("category"));
        links.add(linkTo(methodOn(SubCategoryResource.class).readById(dbo.getCategory().getId())).withRel("subCategory"));
        links.add(linkTo(methodOn(UserResource.class).readById(dbo.getCategory().getId())).withRel("user"));
        links.add(linkTo(methodOn(StatusResource.class).readById(dbo.getCategory().getId())).withRel("status"));
    }

    @Override
    protected Link getSelfLink(MaintenanceRequest dbo) {
        return linkTo(methodOn(MaintenanceRequestResource.class).readById(dbo.getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(MaintenanceRequest dbo, @NotNull PgSoftDTOResource<MaintenanceRequestDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("category", categoryService.getMapper().toDTO(dbo.getCategory()));
        dto.embedResource("subCategory", subCategoryService.getMapper().toDTO(dbo.getSubCategory()));
        dto.embedResource("status", statusService.getMapper().toDTO(dbo.getStatus()));
        dto.embedResource("user", userService.getMapper().toDTO(dbo.getUser()));
    }
}
