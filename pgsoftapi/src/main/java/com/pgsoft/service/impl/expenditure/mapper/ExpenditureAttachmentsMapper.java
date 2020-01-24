package com.pgsoft.service.impl.expenditure.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.Expenditure;
import com.pgsoft.persistence.impl.jpa.dbo.ExpenditureAttachments;
import com.pgsoft.persistence.impl.jpa.repository.ExpenditureRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.expenditure.ExpenditureService;
import com.pgsoft.service.impl.expenditure.dto.ExpenditureAttachmentsDTO;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.expenditure.ExpenditureAttachmentsResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ExpenditureAttachmentsMapper extends PgSoftChildMapperAdapter<ExpenditureAttachments, ExpenditureAttachmentsDTO, Expenditure> {
    private final ExpenditureService expenditureService;

    public ExpenditureAttachmentsMapper(ExpenditureService expenditureService
            , ExpenditureRepository repository) {
        super(repository);
        this.expenditureService = expenditureService;
    }

    @Override
    public void populateChildDBO(@NotNull ExpenditureAttachments dbo, @NotNull ExpenditureAttachmentsDTO dto) {
        dbo.setAttachmentUrl(dto.getAttachmentURL());
        dbo.setExpenditure(expenditureService.findById(dto.getExpenditureId()).orElseThrow(NotFoundException::new));
    }

    @Override
    protected ExpenditureAttachmentsDTO toDTOImpl(@NotNull ExpenditureAttachments dbo) {
        ExpenditureAttachmentsDTO dto = new ExpenditureAttachmentsDTO();
        dto.setAttachmentURL(dbo.getAttachmentUrl());
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, ExpenditureAttachments dbo) {

    }

    @Override
    protected Link getSelfLink(ExpenditureAttachments dbo) {
        return linkTo(methodOn(ExpenditureAttachmentsResource.class).readById(dbo.getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(ExpenditureAttachments dbo, @NotNull PgSoftDTOResource<ExpenditureAttachmentsDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("expenditure", expenditureService.getMapper().toDTO(dbo.getExpenditure()));
    }
}
