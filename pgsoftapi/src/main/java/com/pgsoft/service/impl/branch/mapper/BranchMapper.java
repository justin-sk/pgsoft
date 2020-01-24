package com.pgsoft.service.impl.branch.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.Branch;
import com.pgsoft.service.impl.type.mapper.TypeMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.branch.BranchInfoResource;
import com.pgsoft.web.rest.v1.pgsoft.branch.BranchResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class BranchMapper extends TypeMapperAdapter<Branch> {
    public BranchMapper() {
        super(BranchResource.class);
    }

    @Override
    protected void populateLinksImpl(@NotNull List<Link> links, @NotNull Branch dbo) {
        super.populateLinksImpl(links, dbo);
        links.add(linkTo(methodOn(BranchInfoResource.class).readById(dbo.getId())).withRel("branchInfo"));
    }
}
