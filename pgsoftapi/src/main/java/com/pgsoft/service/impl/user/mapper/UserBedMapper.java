package com.pgsoft.service.impl.user.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.User;
import com.pgsoft.persistence.impl.jpa.dbo.UserBed;
import com.pgsoft.persistence.impl.jpa.repository.UserRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.branch.BedService;
import com.pgsoft.service.impl.user.UserService;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.branch.BedResource;
import com.pgsoft.web.rest.v1.pgsoft.user.UserBedResource;
import com.pgsoft.web.rest.v1.pgsoft.user.UserResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserBedMapper extends PgSoftChildMapperAdapter<UserBed, XRefDTO, User> {
    private final UserService userService;
    private final BedService bedService;

    public UserBedMapper(UserRepository repository
            , UserService userService
            , BedService bedService) {
        super(repository);
        this.userService = userService;
        this.bedService = bedService;
    }

    @Override
    public void populateChildDBO(@NotNull UserBed dbo, @NotNull XRefDTO dto) {
        dbo.setUser(userService.findById(dto.getParentId()).orElseThrow(NotFoundException::new));
        dbo.setBed(bedService.findById(dto.getXrefId()).orElseThrow(NotFoundException::new));
    }

    @Override
    protected XRefDTO toDTOImpl(@NotNull UserBed dbo) {
        return new XRefDTO();
    }

    @Override
    protected void populateLinksImpl(List<Link> links, UserBed dbo) {
        links.add(linkTo(methodOn(UserResource.class).readById(dbo.getUser().getId())).withRel("user"));
        links.add(linkTo(methodOn(BedResource.class).readById(dbo.getBed().getId())).withRel("bed"));
    }

    @Override
    protected Link getSelfLink(UserBed dbo) {
        return linkTo(methodOn(UserBedResource.class).readById(dbo.getUser().getId(), dbo.getBed().getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(UserBed dbo, @NotNull PgSoftDTOResource<XRefDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("user", userService.getMapper().toDTO(dbo.getUser()));
        dto.embedResource("bed", bedService.getMapper().toDTO(dbo.getBed()));
    }
}
