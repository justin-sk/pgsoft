package com.pgsoft.service.impl.user.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.User;
import com.pgsoft.persistence.impl.jpa.dbo.UserSuggestion;
import com.pgsoft.persistence.impl.jpa.repository.UserRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.type.CategoryService;
import com.pgsoft.service.impl.type.SubCategoryService;
import com.pgsoft.service.impl.user.UserService;
import com.pgsoft.service.impl.user.dto.UserSuggestionDTO;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.user.UserResource;
import com.pgsoft.web.rest.v1.pgsoft.user.UserSuggestionResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserSuggestionMapper extends PgSoftChildMapperAdapter<UserSuggestion, UserSuggestionDTO, User> {
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    private final UserService userService;

    public UserSuggestionMapper(UserRepository repository
            , CategoryService categoryService
            , SubCategoryService subCategoryService
            , UserService userService) {
        super(repository);
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
        this.userService = userService;
    }

    @Override
    public void populateChildDBO(@NotNull UserSuggestion dbo, @NotNull UserSuggestionDTO dto) {
        dbo.setDesc(dto.getDescription());
        dbo.setCategory(categoryService.findById(dto.getCategoryId()).orElseThrow(NotFoundException::new));
        dbo.setSubCategory(subCategoryService.findById(dto.getSubCategoryId()).orElseThrow(NotFoundException::new));
        dbo.setUser(userService.findById(dto.getUserId()).orElseThrow(NotFoundException::new));
    }

    @Override
    protected UserSuggestionDTO toDTOImpl(@NotNull UserSuggestion dbo) {
        UserSuggestionDTO dto = new UserSuggestionDTO();
        dto.setDescription(dbo.getDesc());
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, UserSuggestion dbo) {
        links.add(linkTo(methodOn(UserResource.class).readById(dbo.getUser().getId())).withRel("user"));
    }

    @Override
    protected Link getSelfLink(UserSuggestion dbo) {
        return linkTo(methodOn(UserSuggestionResource.class).readById(dbo.getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(UserSuggestion dbo, @NotNull PgSoftDTOResource<UserSuggestionDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("user", userService.getMapper().toDTO(dbo.getUser()));
        dto.embedResource("category", categoryService.getMapper().toDTO(dbo.getCategory()));
        dto.embedResource("subCategory", subCategoryService.getMapper().toDTO(dbo.getSubCategory()));
    }
}
