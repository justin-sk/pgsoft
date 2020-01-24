package com.pgsoft.service.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.UriTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.function.Function;

import static org.springframework.hateoas.Link.*;

public class PgSoftDTOPagedResources<DTO_TYPE extends IPgSoftDTO> extends PagedResources<DTO_TYPE> implements IPgSoftDTO {
    private final HateoasPageableHandlerMethodArgumentResolver hateoasPageableHandlerMethodArgumentResolver = new HateoasPageableHandlerMethodArgumentResolver();

    public static PagedResources.PageMetadata getPageMetadata(Page<?> page) {
        return new PagedResources.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages());
    }

    public PgSoftDTOPagedResources(Collection<DTO_TYPE> content, Page<?> page, Function<Pageable, Link> selfLink, Link... links) {
        super(content, PgSoftDTOPagedResources.getPageMetadata(page), links);
    }

    public PgSoftDTOPagedResources(Collection<DTO_TYPE> content, Page<?> page, Function<Pageable, Link> selfLink, Iterable<Link> links) {
        super(content, PgSoftDTOPagedResources.getPageMetadata(page), links);
    }

    private void addPaginationLinks(Pageable pageable, Function<Pageable, Link> selfLink) {
        if (selfLink == null || pageable == null) {
            return;
        }
        if (this.getMetadata().getNumber() > 0 && this.getMetadata().getNumber() < this.getMetadata().getTotalPages()) {
            if (this.getMetadata().getNumber() > 1) {
                this.add(this.enhance(selfLink.apply(pageable.first()).withRel(REL_FIRST), pageable.first()));
            }
            this.add(this.enhance(selfLink.apply(pageable.previousOrFirst()).withRel(REL_PREVIOUS), pageable.previousOrFirst()));
        }
        this.add(selfLink.apply(pageable).withSelfRel());
        if (this.getMetadata().getNumber() != this.getMetadata().getSize() && this.getMetadata().getTotalPages() != 1) {
            this.add(this.enhance(selfLink.apply(pageable.next()).withRel(REL_NEXT), pageable.next()));
            if (this.getMetadata().getNumber() == this.getMetadata().getSize()) {
                final PageRequest pageRequest = PageRequest.of(Long.valueOf(this.getMetadata().getTotalPages()).intValue(), Long.valueOf(this.getMetadata().getSize()).intValue(), pageable.getSort());
                this.add((this.enhance(selfLink.apply(pageRequest).withRel(REL_LAST), pageRequest)));
            }
        }
    }

    private Link enhance(Link link, Pageable pageable) {
        final String rel = link.getRel();
        final UriComponentsBuilder builder = UriComponentsBuilder.fromUri(link.getTemplate().expand());
        this.getHateoasPageableHandlerMethodArgumentResolver().enhance(builder, null, pageable);
        return new Link(new UriTemplate(builder.build().toString()), rel);
    }

    @JsonProperty("_page")
    @Override
    public PageMetadata getMetadata() {
        return super.getMetadata();
    }

    public HateoasPageableHandlerMethodArgumentResolver getHateoasPageableHandlerMethodArgumentResolver() {
        return this.hateoasPageableHandlerMethodArgumentResolver;
    }
}
