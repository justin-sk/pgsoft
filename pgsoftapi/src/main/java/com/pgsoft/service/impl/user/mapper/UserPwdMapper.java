package com.pgsoft.service.impl.user.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.User;
import com.pgsoft.persistence.impl.jpa.dbo.UserPwd;
import com.pgsoft.persistence.impl.jpa.repository.UserRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.user.UserService;
import com.pgsoft.service.impl.user.dto.UserPwdDTO;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.user.UserResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserPwdMapper extends PgSoftChildMapperAdapter<UserPwd, UserPwdDTO, User> {
    private final UserService userService;

    public UserPwdMapper(UserService userService
            , UserRepository repository) {
        super(repository);
        this.userService = userService;
    }

    @Override
    public void populateChildDBO(@NotNull UserPwd dbo, @NotNull UserPwdDTO dto) {
        dbo.setUser(userService.findById(dto.getUserId()).orElseThrow(NotFoundException::new));
        dbo.setPwd(dto.getPassword());
    }

    @Override
    protected UserPwdDTO toDTOImpl(@NotNull UserPwd dbo) {
        UserPwdDTO dto = new UserPwdDTO();
        dto.setUserId(dbo.getUser().getId());
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, UserPwd dbo) {
        links.add(linkTo(methodOn(UserResource.class).readById(dbo.getUser().getId())).withRel("user"));
    }

    @Override
    protected Link getSelfLink(UserPwd dbo) {
        return null;
    }

    @Override
    protected void embeddedResources(UserPwd dbo, @NotNull PgSoftDTOResource<UserPwdDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("user", userService.getMapper().toDTO(dbo.getUser()));
    }
}
