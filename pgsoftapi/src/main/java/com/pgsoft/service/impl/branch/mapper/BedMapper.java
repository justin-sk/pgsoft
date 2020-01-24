package com.pgsoft.service.impl.branch.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.Bed;
import com.pgsoft.persistence.impl.jpa.dbo.Room;
import com.pgsoft.persistence.impl.jpa.repository.RoomRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.InternalServerException;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.branch.RoomService;
import com.pgsoft.service.impl.branch.dto.BedDTO;
import com.pgsoft.service.impl.type.BedTypeService;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.branch.BedResource;
import com.pgsoft.web.rest.v1.pgsoft.branch.RoomResource;
import com.pgsoft.web.rest.v1.pgsoft.type.BedTypeResource;
import com.pgsoft.web.rest.v1.pgsoft.user.UserResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class BedMapper extends PgSoftChildMapperAdapter<Bed, BedDTO, Room> {
    private final RoomService roomService;
    private final BedTypeService bedTypeService;

    protected BedMapper(RoomRepository repository
            , RoomService roomService
            , BedTypeService bedTypeService) {
        super(repository);
        this.roomService = roomService;
        this.bedTypeService = bedTypeService;
    }

    @Override
    public void populateChildDBO(@NotNull Bed dbo, @NotNull BedDTO dto) {
        if (dto.getRoomId() == null) {
            throw new InternalServerException("Child id not set");
        }
        dbo.setRoom(this.findParent(dto.getRoomId()).orElseThrow(NotFoundException::new));
        dbo.setBedNo(dto.getBedNo());
        dbo.setRent(dto.getRent());
        dbo.setBedType(bedTypeService.findById(dto.getBedTypeId()).orElseThrow(NotFoundException::new));
    }

    @Override
    protected BedDTO toDTOImpl(@NotNull Bed dbo) {
        BedDTO dto = new BedDTO();
        dto.setBedNo(dbo.getBedNo());
        dto.setRent(dbo.getRent());
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, Bed dbo) {
        links.add(linkTo(methodOn(RoomResource.class).readById(dbo.getRoom().getId())).withRel("room"));
        if (dbo.getUserBed() != null)
            links.add(linkTo(methodOn(UserResource.class).readById(dbo.getUserBed().getUser().getId())).withRel("user"));
        links.add(linkTo(methodOn(BedTypeResource.class).readById(dbo.getBedType().getId())).withRel("bedType"));
    }

    @Override
    protected Link getSelfLink(Bed dbo) {
        return linkTo(methodOn(BedResource.class).readById(dbo.getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(Bed dbo, @NotNull PgSoftDTOResource<BedDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("room", roomService.getMapper().toDTO(dbo.getRoom()));
        dto.embedResource("bedType", bedTypeService.getMapper().toDTO(dbo.getBedType()));
    }
}
