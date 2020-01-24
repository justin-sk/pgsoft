package com.pgsoft.service.impl.branch.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.Branch;
import com.pgsoft.persistence.impl.jpa.dbo.Room;
import com.pgsoft.persistence.impl.jpa.repository.BranchRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.InternalServerException;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.branch.BranchService;
import com.pgsoft.service.impl.branch.dto.RoomDTO;
import com.pgsoft.service.impl.type.RoomTypeService;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.branch.BranchResource;
import com.pgsoft.web.rest.v1.pgsoft.branch.RoomResource;
import com.pgsoft.web.rest.v1.pgsoft.type.RoomTypeResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class RoomMapper extends PgSoftChildMapperAdapter<Room, RoomDTO, Branch> {
    private final RoomTypeService roomTypeService;
    private final BranchService branchService;

    protected RoomMapper(BranchRepository repository
            , RoomTypeService roomTypeService
            , BranchService branchService) {
        super(repository);
        this.roomTypeService = roomTypeService;
        this.branchService = branchService;
    }

    @Override
    public void populateChildDBO(@NotNull Room dbo, @NotNull RoomDTO dto) {
        if (dto.getBranchId() == null) {
            throw new InternalServerException("Child id has not be set");
        }
        dbo.setBranch(this.findParent(dto.getBranchId()).orElseThrow(() -> new NotFoundException("Child DBO has not been found")));
        dbo.setRoomNo(dto.getRoomNo());
        dbo.setSts(dto.getStatus());
        dbo.setRoomType(roomTypeService.findById(dto.getRoomTypeId()).orElseThrow(NotFoundException::new));
    }

    @Override
    protected RoomDTO toDTOImpl(@NotNull Room dbo) {
        RoomDTO dto = new RoomDTO();
        dto.setRoomNo(dbo.getRoomNo());
        dto.setStatus(dbo.getSts());
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, Room dbo) {
        links.add(linkTo(methodOn(BranchResource.class).readById(dbo.getBranch().getId())).withRel("branch"));
        links.add(linkTo(methodOn(RoomTypeResource.class).readById(dbo.getRoomType().getId())).withRel("roomType"));
    }

    @Override
    protected Link getSelfLink(Room dbo) {
        return linkTo(methodOn(RoomResource.class).readById(dbo.getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(Room dbo, @NotNull PgSoftDTOResource<RoomDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("branch", branchService.getMapper().toDTO(dbo.getBranch()));
        dto.embedResource("roomType", roomTypeService.getMapper().toDTO(dbo.getRoomType()));
    }
}
