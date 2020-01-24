package com.pgsoft.service.impl.user.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.User;
import com.pgsoft.persistence.impl.jpa.dbo.UserTc;
import com.pgsoft.persistence.impl.jpa.repository.UserRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.user.UserService;
import com.pgsoft.service.impl.user.dto.UserTcDTO;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.user.UserResource;
import com.pgsoft.web.rest.v1.pgsoft.user.UserTcResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserTcMapper extends PgSoftChildMapperAdapter<UserTc, UserTcDTO, User> {
    private final UserService userService;

    public UserTcMapper(UserRepository repository, UserService userService) {
        super(repository);
        this.userService = userService;
    }

    @Override
    public void populateChildDBO(@NotNull UserTc dbo, @NotNull UserTcDTO dto) {
        dbo.setUser(userService.findById(dto.getUserId()).orElseThrow(NotFoundException::new));
        dbo.setTcAcpt(dto.getAccepted());
    }

    @Override
    protected UserTcDTO toDTOImpl(@NotNull UserTc dbo) {
        UserTcDTO dto = new UserTcDTO();
        dto.setAccepted(dbo.getTcAcpt());
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, UserTc dbo) {
        links.add(linkTo(methodOn(UserResource.class).readById(dbo.getUser().getId())).withRel("user"));
    }

    @Override
    protected Link getSelfLink(UserTc dbo) {
        return linkTo(methodOn(UserTcResource.class).readById(dbo.getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(UserTc dbo, @NotNull PgSoftDTOResource<UserTcDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("user", userService.getMapper().toDTO(dbo.getUser()));
    }
}
