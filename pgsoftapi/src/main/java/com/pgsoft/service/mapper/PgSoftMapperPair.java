package com.pgsoft.service.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.IPgSoftDBO;
import com.pgsoft.service.dto.IPgSoftDTO;

import javax.validation.constraints.NotNull;

public class PgSoftMapperPair<DBO_TYPE extends IPgSoftDBO, DTO_TYPE extends IPgSoftDTO> {
    public final DBO_TYPE dbo;
    public final DTO_TYPE dto;

    public PgSoftMapperPair(@NotNull DBO_TYPE dbo_type, @NotNull DTO_TYPE dto_type) {
        this.dbo = dbo_type;
        this.dto = dto_type;
    }
}
