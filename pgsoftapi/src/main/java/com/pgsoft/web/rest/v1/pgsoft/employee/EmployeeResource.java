package com.pgsoft.web.rest.v1.pgsoft.employee;

import com.pgsoft.persistence.impl.jpa.dbo.Employee;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.employee.EmployeeService;
import com.pgsoft.service.impl.employee.binding.EmployeeBinding;
import com.pgsoft.service.impl.employee.dto.EmployeeDTO;
import com.pgsoft.web.rest.v1.IPgSoftResource;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Api(tags = {"Employee"})
@RequestMapping("api/v1/employee")
public class EmployeeResource implements IPgSoftResource {
    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/{employeeId}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<EmployeeDTO> readById(@PathVariable("employeeId") Long employeeId) {
        return employeeService.readById(employeeId).orElseThrow(NotFoundException::new);
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<EmployeeDTO>> readAll(@QuerydslPredicate(root = Employee.class, bindings = EmployeeBinding.class) Predicate predicate, Pageable pageable) {
        return employeeService.readAll(predicate, pageable, p -> linkTo(methodOn(this.getClass()).readAll(predicate, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<EmployeeDTO> create(@RequestBody EmployeeDTO newObject) {
        return employeeService.create(newObject).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(value = "/{employeeId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<EmployeeDTO> update(@PathVariable("employeeId") Long id, @RequestBody EmployeeDTO newObject) {
        return employeeService.update(id, newObject).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{employeeId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("employeeId") Long id) {
        employeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
