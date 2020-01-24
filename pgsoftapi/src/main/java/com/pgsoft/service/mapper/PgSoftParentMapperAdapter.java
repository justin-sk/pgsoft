package com.pgsoft.service.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.IPgSoftDBO;
import com.pgsoft.service.dto.IPgSoftDTO;
import com.pgsoft.service.exception.NotFoundException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public abstract class PgSoftParentMapperAdapter<DBO_TYPE extends IPgSoftDBO, DTO_TYPE extends IPgSoftDTO> extends PgSoftMapper<DBO_TYPE, DTO_TYPE> {
    public PgSoftParentMapperAdapter() {
        super();
    }

    @NotNull
    @Size(min = 1)
    public List<DBO_TYPE> toNewDBOs(@NotNull @Size(min = 1) List<DTO_TYPE> dtos) {
        final List<DBO_TYPE> collect = dtos.stream().map(this::toNewDBO).collect(Collectors.toList());
        if (collect.isEmpty()) {
            throw new NotFoundException();
        }
        return collect;
    }
}
