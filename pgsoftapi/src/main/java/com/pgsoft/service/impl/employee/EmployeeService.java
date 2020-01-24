package com.pgsoft.service.impl.employee;

import com.pgsoft.persistence.impl.jpa.dbo.Employee;
import com.pgsoft.persistence.impl.jpa.dbo.User;
import com.pgsoft.persistence.impl.jpa.querydsl.EmployeeQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.EmployeeRepository;
import com.pgsoft.service.PgSoftChildServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.employee.dto.EmployeeDTO;
import com.pgsoft.service.impl.employee.mapper.EmployeeMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Service
public class EmployeeService extends PgSoftChildServiceAdapter<Employee, EmployeeDTO, User> {
    protected EmployeeService(EmployeeMapper mapper, EmployeeRepository repository, EmployeeQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    public Optional<PgSoftDTOResource<EmployeeDTO>> create(@NotNull EmployeeDTO newObject) {
        return this.save(this.getMapper().toNewDBO(newObject)).map(this.getMapper()::toDTO);
    }

    public Optional<PgSoftDTOResource<EmployeeDTO>> readById(@Nullable Long id) {
        return super.read(id);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<EmployeeDTO>>> readAll(@Nullable Predicate predicate, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(predicate, pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOResource<EmployeeDTO>> update(@Nullable Long id, @Nullable EmployeeDTO updatedObject) {
        return super.update(id, updatedObject);
    }

    @Override
    public void deleteById(@Nullable Long id) {
        if (id != null && super.findById(id).isPresent()) {
            ((EmployeeMapper) this.getMapper()).getParentRepository().deleteById(super.findById(id).get().getUser().getId());
        }
        super.deleteById(id);
    }
}
