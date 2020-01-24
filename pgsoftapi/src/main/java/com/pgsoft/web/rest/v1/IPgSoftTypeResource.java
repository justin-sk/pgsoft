package com.pgsoft.web.rest.v1;

import com.querydsl.core.types.Predicate;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.type.dto.PgSoftTypeDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface IPgSoftTypeResource<DTO_TYPE extends PgSoftTypeDTO> extends IPgSoftResource {
    PgSoftDTOResource<DTO_TYPE> readById(Long typeId);

    PgSoftDTOPagedResources<PgSoftDTOResource<DTO_TYPE>> readAll(Predicate predicate, Pageable pageable);

    PgSoftDTOResource<DTO_TYPE> readByCode(String typeCode);

    ResponseEntity<Void> deleteById(Long typeId);
}
