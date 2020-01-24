package com.pgsoft.service.impl.branch.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.Room;
import com.pgsoft.persistence.impl.jpa.dbo.RoomAmenity;
import com.pgsoft.persistence.impl.jpa.repository.RoomRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.amenity.AmenityService;
import com.pgsoft.service.impl.branch.RoomService;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.branch.RoomAmenityResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class RoomAmenityMapper extends PgSoftChildMapperAdapter<RoomAmenity, XRefDTO, Room> {
    private final AmenityService amenityService;
    private final RoomService roomService;

    public RoomAmenityMapper(AmenityService amenityService
            , RoomService roomService
            , RoomRepository repository) {
        super(repository);
        this.amenityService = amenityService;
        this.roomService = roomService;
    }

    @Override
    public void populateChildDBO(@NotNull RoomAmenity dbo, @NotNull XRefDTO dto) {
        dbo.setAmenity(amenityService.findById(dto.getXrefId()).orElseThrow(NotFoundException::new));
        dbo.setRoom(roomService.findById(dto.getParentId()).orElseThrow(NotFoundException::new));
    }

    @Override
    protected XRefDTO toDTOImpl(@NotNull RoomAmenity dbo) {
        return new XRefDTO();
    }

    @Override
    protected void populateLinksImpl(List<Link> links, RoomAmenity dbo) {

    }

    @Override
    protected Link getSelfLink(RoomAmenity dbo) {
        return linkTo(methodOn(RoomAmenityResource.class).readAll(null, null)).withSelfRel();
    }

    @Override
    protected void embeddedResources(RoomAmenity dbo, @NotNull PgSoftDTOResource<XRefDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("room", roomService.getMapper().toDTO(dbo.getRoom()));
        dto.embedResource("amenity", amenityService.getMapper().toDTO(dbo.getAmenity()));
    }
}
