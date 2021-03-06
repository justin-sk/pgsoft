package com.pgsoft.service.impl.user.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.User;
import com.pgsoft.persistence.impl.jpa.dbo.UserRent;
import com.pgsoft.persistence.impl.jpa.repository.UserRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.user.UserService;
import com.pgsoft.service.impl.user.dto.UserRentDTO;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.user.UserRentResource;
import com.pgsoft.web.rest.v1.pgsoft.user.UserResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserRentMapper extends PgSoftChildMapperAdapter<UserRent, UserRentDTO, User> {
    private final UserService userService;

    public UserRentMapper(UserService userService
            , UserRepository repository) {
        super(repository);
        this.userService = userService;
    }

    @Override
    public void populateChildDBO(@NotNull UserRent dbo, @NotNull UserRentDTO dto) {
        dbo.setUser(userService.findById(dto.getUserId()).orElseThrow(NotFoundException::new));
        dbo.setAmount(dto.getRent());
    }

    @Override
    protected UserRentDTO toDTOImpl(@NotNull UserRent dbo) {
        UserRentDTO dto = new UserRentDTO();
        dto.setRent(dbo.getAmount());
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, UserRent dbo) {
        links.add(linkTo(methodOn(UserResource.class).readById(dbo.getUser().getId())).withRel("user"));
    }

    @Override
    protected Link getSelfLink(UserRent dbo) {
        return linkTo(methodOn(UserRentResource.class).readById(dbo.getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(UserRent dbo, @NotNull PgSoftDTOResource<UserRentDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("user", userService.getMapper().toDTO(dbo.getUser()));
    }
}
