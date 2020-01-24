package com.pgsoft.service.impl.amenity.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.Amenity;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.amenity.dto.AmenityDTO;
import com.pgsoft.service.mapper.PgSoftMapper;
import com.pgsoft.web.rest.v1.pgsoft.amenity.AmenityResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class AmenityMapper extends PgSoftMapper<Amenity, AmenityDTO> {
    @Override
    protected void populateDBO(@NotNull Amenity dbo, @NotNull AmenityDTO dto) {
        dbo.setCode(dto.getCode());
        dbo.setDescription(dto.getDescription());
        dbo.setImageUrl(dto.getImageURL());
    }

    @Override
    protected AmenityDTO toDTOImpl(@NotNull Amenity dbo) {
        AmenityDTO dto = new AmenityDTO();
        dto.setId(dbo.getId());
        dto.setCode(dbo.getCode());
        dto.setDescription(dbo.getDescription());
        dto.setImageURL(dto.getImageURL());
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, Amenity dbo) {

    }

    @Override
    protected Link getSelfLink(Amenity dbo) {
        return linkTo(methodOn(AmenityResource.class).readById(dbo.getId())).withSelfRel();
    }
}
