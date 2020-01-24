package com.pgsoft.service.impl.user.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.User;
import com.pgsoft.persistence.impl.jpa.dbo.UserExitDtls;
import com.pgsoft.persistence.impl.jpa.repository.UserRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.user.UserService;
import com.pgsoft.service.impl.user.dto.UserExitDtlsDTO;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.user.UserExitDtlsResource;
import com.pgsoft.web.rest.v1.pgsoft.user.UserResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserExitDtlsMapper extends PgSoftChildMapperAdapter<UserExitDtls, UserExitDtlsDTO, User> {
    private final UserService userService;

    public UserExitDtlsMapper(UserService userService, UserRepository userRepository) {
        super(userRepository);
        this.userService = userService;
    }

    @Override
    public void populateChildDBO(@NotNull UserExitDtls dbo, @NotNull UserExitDtlsDTO dto) {
        dbo.setUser(userService.findById(dto.getUserId()).orElseThrow(NotFoundException::new));
        dbo.setExitDt(dto.getExitDate());
    }

    @Override
    protected UserExitDtlsDTO toDTOImpl(@NotNull UserExitDtls dbo) {
        UserExitDtlsDTO dto = new UserExitDtlsDTO();
        dto.setExitDate(dbo.getExitDt());
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, UserExitDtls dbo) {
        links.add(linkTo(methodOn(UserResource.class).readById(dbo.getUser().getId())).withRel("user"));
    }

    @Override
    protected Link getSelfLink(UserExitDtls dbo) {
        return linkTo(methodOn(UserExitDtlsResource.class).readById(dbo.getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(UserExitDtls dbo, @NotNull PgSoftDTOResource<UserExitDtlsDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("user", userService.getMapper().toDTO(dbo.getUser()));
    }
}
